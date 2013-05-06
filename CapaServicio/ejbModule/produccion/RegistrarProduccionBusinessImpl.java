package produccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;

import persistencia.ServiceEntity;
import venta.Pedido;
import advices.ExceptionHandlerAdvice;
//TODO Investigar como mierda se hace para que el EntityManager funcione en modo read-only
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
public class RegistrarProduccionBusinessImpl implements RegistrarProduccionBusiness{

	@EJB(beanName="ServiceEntity")
	private ServiceEntity service;
	
	@Resource
	private SessionContext context;
	
	@PersistenceContext	
	private EntityManager em;	

	@Override	
	public DiagramacionProduccion cerrarDiagramacion(DiagramacionProduccion diagramacion) {
		Session session = (Session) em.getDelegate();
		Query q = session.createQuery("select d from DiagramacionProduccion d join fetch d.pedidos where d.id = :id");
		q.setLong("id", diagramacion.getId());
		q.setReadOnly(true);
		diagramacion = (DiagramacionProduccion) q.uniqueResult();
		diagramacion.cerrar();
		return diagramacion;
	}

//	@Override
//	@Interceptors(ExceptionHandlerAdvice.class)
//	public void confirmarCierreDiagramacion(DiagramacionProduccion diagramacion) throws Exception {
//		//em.getTransaction().begin();
//		diagramacion = em.find(diagramacion.getClass(), diagramacion.getId());
//		UserTransaction t = context.getUserTransaction();
//		t.begin();
//		diagramacion.actualizarEstado();
//		service.update(diagramacion);
//		for (Pedido pedido : diagramacion.getPedidos()) {
//			service.update(pedido);
//		}
//		for (ProduccionProducto pp : diagramacion.getDetalle()) {
//			for (ResultadoProductoDetalle rd : pp.getDetalleResultado()) {
//				service.save(rd);
//			}
//			service.update(pp);
//		}
//		t.commit();
//		
//		//em.getTransaction().commit();
//	}
	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public List<Insumo> confirmarCierreDiagramacion(DiagramacionProduccion diagramacion) throws Exception {		
		diagramacion = em.find(diagramacion.getClass(), diagramacion.getId());
		List<Insumo> insumos = new ArrayList<Insumo>();
		for (Entry<Insumo, Float> insumo : diagramacion.getTotalInsumosConsumidos()) {			
			int resultado = (int)(insumo.getKey().getStockDisponible()-insumo.getValue());
			if(resultado<0){
				insumos.add(insumo.getKey());
			}
		}
		if(insumos.size()>0){
			return insumos;
		}
		for (Entry<Insumo, Float> insumo : diagramacion.getTotalInsumosConsumidos()) {
			Insumo i = insumo.getKey();
			int resultado = (int)(i.getStockDisponible()-insumo.getValue());
			i.setStockDisponible(resultado);
			em.merge(i);
		}
		diagramacion.actualizarEstado();
		diagramacion.setFechaCierre(new Date(System.currentTimeMillis()));
		service.update(diagramacion);
		for (Pedido pedido : diagramacion.getPedidos()) {
			service.update(pedido);
		}
		for (ProduccionProducto pp : diagramacion.getDetalle()) {
			for (ResultadoProductoDetalle rd : pp.getDetalleResultado()) {
				service.save(rd);
			}
			service.update(pp);
		}
		return insumos;
	}
}
