package produccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistencia.ServiceEntityImpl;
import security.Permiso;
import venta.Pedido;
import venta.PedidoDetalle;
import venta.Producto;
import venta.estados.pedido.EstadoPedido;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import advices.BusinessException;
import advices.BusinessExceptionMsg;
import base.Empleado;
import base.EmpleadoBusiness;

@Stateless
public class DiagramarProduccionBusinessImpl extends ServiceEntityImpl implements DiagramarProduccionBusiness{

	@EJB
	private EmpleadoBusiness empleadoBusiness; 
	
	@PersistenceContext
	private EntityManager em;	
	
	@Override
	public DiagramacionProduccion nuevaDiagramacion(Date fecha) {
		//Obtenemos los pedidos
		String query = "select DISTINCT pedido " +
		   "from Pedido pedido inner join fetch pedido.detalle dp " +
		   "where pedido.fechaEntrega = :fecha and " +
		   "(dp.estado = :pendienteDiagramacion)";
//		   "(dp.estado = :pendienteDiagramacion or dp.estado = :diagramado)";		
		Query q = em.createQuery(query);
		q.setParameter("fecha", fecha);
//		q.setParameter("pendienteDiagramacion", EstadoPedidoDetalle.DIAGRAMADO);
		q.setParameter("pendienteDiagramacion", EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION);
		List<Pedido> list = q.getResultList();		
		if(list.size() == 0){
			//TODO aplicar messages Boundle		
			throw new BusinessException(new BusinessExceptionMsg("No hay pedidos pendiente para la fecha seleccionada","",FacesMessage.SEVERITY_ERROR),null);
		}
		DiagramacionProduccion diagramacion = new DiagramacionProduccion();		
		diagramacion.setFechaEntrega(fecha);//TODO corroborar esta fecha
		
		for (Pedido pedido : list) {
			diagramacion.addPedido(pedido);
		}
		
		//Obtenemos las recetas
		Query query2 = em.createQuery("select distinct r " +
									  "from Receta r join fetch r.detalle d " +
									  "where r.predeterminada = :pre ");
		query2.setParameter("pre", true);
		List<Receta> recetas = query2.getResultList();
		
		List<Empleado> empleados = this.empleadoBusiness.getEmpleados(Permiso.PANADERO);
		
		for (ProduccionProducto pp : diagramacion.getDetalle()) {
			for (Empleado empleado : empleados) {
				if(empleado.getProductos().contains(pp.getProducto())){
					ProduccionProductoDetalle detalle = new ProduccionProductoDetalle();
					detalle.setEmpleado(empleado);
					pp.addDetalle(detalle);					
				}
			}
			
			for (Receta receta : recetas) {
				if(pp.getProducto().equals(receta.getProducto())){
					pp.setReceta(receta);					
					break;
				}				
			}			
		}
		
		
		
		return diagramacion;
	}

	@Override
	public void save(Object entity) {
		DiagramacionProduccion diagramacion = (DiagramacionProduccion) entity;
		List<ProduccionProducto> list = new ArrayList<ProduccionProducto>();
		for (ProduccionProducto pp : diagramacion.getDetalle()) {
			if(pp.getReceta() == null){
				throw new BusinessException("Existen Productos sin una receta asociada");
			}
			if(pp.getCantidadDemanda() == 0){
				list.add(pp);
			}
		}
		diagramacion.getDetalle().removeAll(list);
		diagramacion.actualizarEstado();
		super.save(diagramacion);
		for (Pedido pedido : diagramacion.getPedidos()) {
			super.update(pedido);
		}
	}
	
	public List<Receta> listRecetas(Producto producto){
		String query = "select DISTINCT r from Receta r " +
						"where r.producto.id = :idProducto";
		Query q = em.createQuery(query).setParameter("idProducto", producto.getId());
		return q.getResultList();
	}
	
	public void cancelarDiagramacion(DiagramacionProduccion diagramacion){
		diagramacion = this.em.merge(diagramacion);
		for(Pedido pedido : diagramacion.getPedidos()){
			pedido.setEstado(EstadoPedido.CREADO);
			for (PedidoDetalle detalle : pedido.getDetalle()) {
				detalle.setEstado(EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION);
			}
			em.merge(pedido);
		}
		diagramacion.getPedidos().clear();
		em.remove(diagramacion);
	}	
}
