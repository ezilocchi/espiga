package stock;

import java.util.Date;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import advices.BusinessException;
import advices.ExceptionHandlerAdvice;

import produccion.Insumo;

@Stateless
public class NovedadStockBusinessImpl implements NovedadStockBusiness{

	@PersistenceContext
	private EntityManager em;
	
	@Interceptors(ExceptionHandlerAdvice.class)
	public void save(NovedadStock novedadStock){
		Insumo insumo = novedadStock.getInsumo();
		int cantidadActualizar = (int) (novedadStock.getCantidad()*novedadStock.getUnidadMedida().getCantidad());
		
		if(!novedadStock.getTipo().getIncrementa() && (insumo.getStockDisponible()-cantidadActualizar)<0){
			throw new BusinessException("No hay suficiente stock para realizar el ajuste");
		}
		if(novedadStock.getTipo().getIncrementa()){
			insumo.setStockDisponible(insumo.getStockDisponible()+cantidadActualizar);
		}else{
			insumo.setStockDisponible(insumo.getStockDisponible()-cantidadActualizar);
		}
		novedadStock.setFecha(new Date(System.currentTimeMillis()));
		em.merge(insumo);
		em.persist(novedadStock);
	}
}
