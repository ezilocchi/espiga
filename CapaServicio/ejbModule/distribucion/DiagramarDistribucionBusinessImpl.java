package distribucion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;

import venta.Pedido;
import venta.PedidoDetalle;
import venta.estados.pedido.EstadoPedido;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import advices.BusinessException;
import advices.BusinessExceptionMsg;
import advices.ExceptionHandlerAdvice;
import base.Empleado;
import base.Zona;

@Stateless
public class DiagramarDistribucionBusinessImpl implements DiagramarDistribucionBusiness{
	
	@PersistenceContext
	private EntityManager em;	

	@Override
	public DiagramacionDistribucion nuevaDiagramacion(Date fecha) {
		//Pedidos pendientes
		String query = "select DISTINCT pedido " +
		   "from Pedido pedido inner join fetch pedido.detalle dp " +
		   "where pedido.fechaEntrega = :fecha and " +
		   "(pedido.estado = :producido or pedido.estado = :producidoParcialmente)";	
		
		Query q = em.createQuery(query);
		q.setParameter("fecha", fecha);
		q.setParameter("producido", EstadoPedido.PRODUCIDO);
		q.setParameter("producidoParcialmente", EstadoPedido.PARCIALMENTE_PRODUCIDO);
		List<Pedido> pedidos = q.getResultList();		
		if(pedidos.size() == 0){
			//TODO aplicar messages Boundle		
			throw new BusinessException(new BusinessExceptionMsg("No hay pedidos pendiente para la fecha seleccionada","",FacesMessage.SEVERITY_ERROR),null);
		}	
		
		DiagramacionDistribucion diagramacion = new DiagramacionDistribucion();				
		diagramacion.setFecha(new Date(System.currentTimeMillis()));
		diagramacion.setFechaEntrega(fecha);
		
		String query2 = "select z from Zona z inner join fetch z.empleado ";	
		
		List<Zona> zonas = em.createQuery(query2).getResultList();
		for (Zona zona : zonas) {
			DiagramacionDistribucionDetalle detalle = new DiagramacionDistribucionDetalle();
			detalle.setEmpleado(zona.getEmpleado());
			detalle.setZona(zona);			
			diagramacion.addDetalle(detalle);
		}				
		for (Pedido p : pedidos) {
			diagramacion.addPedido(p);
		}		
		return diagramacion;
	}

	@Interceptors(ExceptionHandlerAdvice.class)
	public void save(DiagramacionDistribucion diagramacion) {
		List<DiagramacionDistribucionDetalle> remove = new ArrayList<DiagramacionDistribucionDetalle>();		
		for (DiagramacionDistribucionDetalle detalle : diagramacion.getDetalle()) {
			if (detalle.isEmpty()) {
				remove.add(detalle);
				continue;
			}
			if(detalle.getVehiculo() == null){
				throw new BusinessException("Existen Repartos sin ningun vehiculo asociado");
			}
		}
		diagramacion.getDetalle().removeAll(remove);
		diagramacion.actualizarEstado();
		em.persist(diagramacion);
		for (DiagramacionDistribucionDetalle detalle : diagramacion.getDetalle()) {
			for (Pedido pedido : detalle.getPedidos()) {
				em.merge(pedido);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> listEmleado(Empleado example) {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(example.getClass());
		c.createCriteria("zonas",CriteriaSpecification.LEFT_JOIN);
		c.add(Example.create(example));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}

	@Override
	public List<DiagramacionDistribucionDetalle> getDiagramacionDetalle(DiagramacionDistribucion diagramacionDistribucion) {
		String query = "select distinct ddd from DiagramacionDistribucionDetalle ddd " +
				"join fetch ddd.pedidos " +
				"where ddd.diagramacion.id = :idDiagramacion ";
		Query q = em.createQuery(query);
		q.setParameter("idDiagramacion", diagramacionDistribucion.getId());
		return q.getResultList();
		
	}

	@Interceptors(ExceptionHandlerAdvice.class)
	public void cerrarDiagramacion(DiagramacionDistribucion diagramacion){
		diagramacion.actualizarEstado();		
		for (DiagramacionDistribucionDetalle ddd : diagramacion.getDetalle()) {
			for (Pedido pedido : ddd.getPedidos()) {
				em.merge(pedido);
			}
		}
		em.merge(diagramacion);
	}

	@Override
	public List<Vehiculo> listVehiculo(Vehiculo example) {
		Query query = em.createQuery("select v from Vehiculo v");
		return query.getResultList();
	}
	
	@Interceptors(ExceptionHandlerAdvice.class)
	public void cancelar(DiagramacionDistribucion diagramacion){
		diagramacion = this.em.merge(diagramacion);
		for(DiagramacionDistribucionDetalle ddd : diagramacion.getDetalle()){
			for(Pedido pedido : ddd.getPedidos()){
				pedido.setEstado(EstadoPedido.PRODUCIDO);				
				em.merge(pedido);				
			}
			ddd.getPedidos().clear();
		}		
		em.remove(diagramacion);
	}
	
	@Interceptors(ExceptionHandlerAdvice.class)
	public void nuevoPedido(Pedido pedido, List<PedidoDetalle> detalle){
		Pedido nuevoPedido = new Pedido();
		nuevoPedido.setCliente(pedido.getCliente());
		nuevoPedido.setZona(pedido.getZona());
		nuevoPedido.setFechaEntrega(pedido.getFechaEntrega());
		nuevoPedido.setFecha(new Date(System.currentTimeMillis()));
		
		List<PedidoDetalle> detalleNuevo = new ArrayList<PedidoDetalle>();
		boolean flag = false;
		boolean flag2 = false;
		for (PedidoDetalle pedidoDetalle : detalle) {
			if(pedidoDetalle.getEstado().equals(EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION)){
				flag = true;
			}else if(pedidoDetalle.getEstado().equals(EstadoPedidoDetalle.DIAGRAMADO)){
				flag2 = true;
			}
			PedidoDetalle nuevoDetalle = new PedidoDetalle();
			nuevoDetalle.setCantidad(pedidoDetalle.getCantidad());
			nuevoDetalle.setEstado(pedidoDetalle.getEstado());
			nuevoDetalle.setPrecioVentaUnitario(pedidoDetalle.getPrecioVentaUnitario());
			nuevoDetalle.setProducto(pedidoDetalle.getProducto());
			detalleNuevo.add(nuevoDetalle);
			
		}
		nuevoPedido.setDetalle(detalleNuevo);
		
		if(flag && flag2){
			nuevoPedido.setEstado(EstadoPedido.PARCIALMENTE_DIAGRAMADO);
		}else if(flag){
			nuevoPedido.setEstado(EstadoPedido.CREADO);
		}else if(flag2){
			nuevoPedido.setEstado(EstadoPedido.DIAGRAMADO);
		}
		em.persist(nuevoPedido);
		pedido.setEstado(EstadoPedido.PRODUCIDO);
		em.merge(pedido);
	}

	@Interceptors(ExceptionHandlerAdvice.class)
	public void updatePedido(Pedido pedido, List<PedidoDetalle> detalles){
		for (PedidoDetalle pedidoDetalle : detalles) {
			em.merge(pedidoDetalle);
		}
		em.merge(pedido);
	}
}
