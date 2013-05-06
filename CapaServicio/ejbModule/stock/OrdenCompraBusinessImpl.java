package stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import produccion.Insumo;
import advices.BusinessException;
import advices.ExceptionHandlerAdvice;

@Stateless
public class OrdenCompraBusinessImpl implements OrdenCompraBusiness{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public OrdenCompra nuevaOrdenCompra(Proveedor proveedor) {
		String q = "select p from Proveedor p join fetch p.insumos where p.id = :idProveedor";
		Query query = em.createQuery(q);
		query.setParameter("idProveedor", proveedor.getId());
		Proveedor p = (Proveedor) query.getSingleResult();		
		System.out.println("Proveedor: "+p);
		System.out.println("Insumos: "+p.getInsumos());
		if(p.getInsumos().size()==0){
			//TODO implementar menssage bundle
			throw new BusinessException("El proveedor seleccionado no posee Insumos asociados");
		}	
		
		OrdenCompra orden = new OrdenCompra();
		orden.setProveedor(proveedor);
		orden.setFecha(new Date(System.currentTimeMillis()));		
		for (Insumo insumo : p.getInsumos()) {			
			UnidadMedidaInsumoCompra unidadCompra = insumo.getUnidadPredeterminada();
			if(unidadCompra == null){
				continue;
			}
			
			OrdenCompraDetalle detalle = new OrdenCompraDetalle();
			detalle.setInsumo(insumo);
			detalle.setUnidadMedida(unidadCompra);
			detalle.setPrecioUnitario(unidadCompra.getPrecioUnitario());			
			if (insumo.reponer()) {				
				int cant = insumo.getCantidadReposicion();
				float proporcion = unidadCompra.getCantidad();				
				int cantidad  = (int) (cant / proporcion);
				if(cant%proporcion != 0){
					cantidad++;
				}
				detalle.setCantidad(cantidad);
			}else{
				detalle.setCantidad(0);
			}		
			orden.addDetalle(detalle);
		}				
		return orden;
	}

	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void save(OrdenCompra compra) {
		List<OrdenCompraDetalle> list = new ArrayList<OrdenCompraDetalle>();
		for (OrdenCompraDetalle detalle : compra.getDetalle()) {
			if(!(detalle.getCantidad() > 0)){
				list.add(detalle);
			}
		}
		compra.getDetalle().removeAll(list);
		if(compra.getDetalle().size()==0){
			throw new BusinessException("Debe ingresar una cantidad mayor a cero por lo menos para un insumo");
		}
		compra.setFechaPedido(new Date(System.currentTimeMillis()));
		compra.actualizarEstado();
		em.persist(compra);
	}
	
	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void update(OrdenCompra orden){
		List<OrdenCompraDetalle> list = new ArrayList<OrdenCompraDetalle>();
		for (OrdenCompraDetalle detalle : orden.getDetalle()) {
			if(!(detalle.getCantidad() > 0)){
				list.add(detalle);
			}
		}
		orden.getDetalle().removeAll(list);
		orden.actualizarEstado();
		em.merge(orden);
	}
	
	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void registarIngreso(OrdenCompra orden){		
		orden.actualizarEstado();
		List<OrdenCompraDetalle> list = new ArrayList<OrdenCompraDetalle>();
		for (OrdenCompraDetalle detalle : orden.getDetalle()) {
			if(detalle.isCancelado()){
				list.add(detalle);
				continue;
			}
			if(!detalle.isPendiente()){
				Insumo insumo = detalle.getInsumo();
				int cant = detalle.getIngreso() + insumo.getStockDisponible();
				insumo.setStockDisponible(cant);
				em.merge(insumo);
			}
		}
		for (OrdenCompraDetalle ordenCompraDetalle : list) {
			orden.removeDetalle(ordenCompraDetalle);
			em.remove(em.merge(ordenCompraDetalle));
		}
		em.merge(orden);
	}
	
	@Override
	public List<Proveedor> getProveedores(Insumo insumo){
		String q = "select p from Proveedor p where :insumo MEMBER p.insumos";
		Query query = em.createQuery(q);
		query.setParameter("insumo", insumo);
		List<Proveedor> list = query.getResultList(); 
		return list;
	}
}
