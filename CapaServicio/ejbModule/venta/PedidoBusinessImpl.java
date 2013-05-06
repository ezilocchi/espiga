package venta;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistencia.ServiceEntityImpl;
import utils.FechaUtils;
import advices.BusinessException;
import advices.BusinessExceptionMsg;
import advices.ExceptionHandlerAdvice;

@Stateless
//TODO Hacer funcionar las @NamedQueries 
@NamedQueries({@NamedQuery(name="tipoProductoQuery", query="select t from TipoProducto t"),
					 @NamedQuery(name="productosByTipoQuery", query="select p from Producto p " +
					 												"where p.tipoProducto.id = :idTipo")})
public class PedidoBusinessImpl extends ServiceEntityImpl implements PedidoBusiness{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Pedido buildNewPedido(Cliente cliente) {
		Pedido pedido = new Pedido();		
		pedido.setCliente(cliente);
		pedido.setFecha(new Date(System.currentTimeMillis()));
		pedido.setZona(cliente.getDireccion().getBarrio().getZona());
		return pedido;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoProducto> listTipoProducto() {		
		return em.createQuery("select t from TipoProducto t").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> listProductoByTipo(TipoProducto tipoProducto) {
		Query q = em.createQuery("select p from Producto p where p.tipo.id = :idTipo");
		q.setParameter("idTipo", tipoProducto.getId());
		return q.getResultList();
	}

	@Override	
	@Interceptors(ExceptionHandlerAdvice.class)	
	public void save(Object entity) {		
		Pedido pedido = (Pedido) entity;
		if(pedido.getDetalle()==null || pedido.getDetalle().size()==0){
			throw new BusinessException(new BusinessExceptionMsg("El pedido debe contener al menos un producto","",FacesMessage.SEVERITY_ERROR));
		}
		Date hoy = FechaUtils.getFecha(new Date(System.currentTimeMillis()));
		Date fechaPedido = FechaUtils.getFecha(pedido.getFechaEntrega());
		if(fechaPedido.compareTo(hoy)<0){
			throw new BusinessException(new BusinessExceptionMsg("El pedido debe tener fecha de entrega valida","El pedido debe tener fecha de entrega valida","fechaEntrega",FacesMessage.SEVERITY_ERROR,pedido.getClass()));
		}		
		pedido.actualizarEstado();
		super.save(pedido);
	}	

	
}
