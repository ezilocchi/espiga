package venta;

import integracion.ExceptionHandler;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.CriteriaHelper;
import persistencia.ServiceParametricas;
import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import produccion.Insumo;
import produccion.UnidadMedidaInsumo;
import stock.Proveedor;
import stock.UnidadMedidaInsumoCompra;
import stock.estrategias.EstrategiaGestion;
import utils.JSFSelectEnum;
import utils.JSFTable;

public class GestionInsumoMB extends SimpleAMBMB<Insumo>{
	
	@EJB
	private ServiceParametricas serviceParametricas;
	private JSFSelectEnum<UnidadMedidaInsumo> unidades;
	private JSFSelectEnum<UnidadMedidaInsumo> unidadesFiltro;
	private JSFTable<UnidadMedidaInsumoCompra> unidadesCompra;
	private UnidadMedidaInsumoCompra unidad;
	private ManagedBeanActions actionUnidad;
	
	private boolean renderUnidad;
	private JSFTable<Proveedor> proveedores;	
	
	public GestionInsumoMB() {		
		super(NavigationRules.gestionarInsumos,"Listado Insumos");
		super.reportColumns.put("nombre", "Nombre");
		super.reportColumns.put("descripcion", "Descripcion");
		super.reportColumns.put("stockDisponible", "Stock");
		this.renderUnidad = false;
		this.proveedores = null;	
	}	
	
	@Override
	public String init() {
		this.unidades = new JSFSelectEnum<UnidadMedidaInsumo>(UnidadMedidaInsumo.values(),this,"entity.unidadMedida");
		this.unidadesFiltro = new JSFSelectEnum<UnidadMedidaInsumo>(UnidadMedidaInsumo.values(),this,"filtro.unidadMedida");
		this.unidadesCompra = new JSFTable<UnidadMedidaInsumoCompra>();
		entities = new JSFTable<Insumo>(super.dao.list(new Insumo()));
		this.unidad = new UnidadMedidaInsumoCompra();
		return super.init();
	}	
	public JSFSelectEnum<UnidadMedidaInsumo> getUnidades() {
		return unidades;
	}
	public void setUnidades(JSFSelectEnum<UnidadMedidaInsumo> unidades) {
		this.unidades = unidades;
	}
	
	
	public JSFSelectEnum<UnidadMedidaInsumo> getUnidadesFiltro() {
		return unidadesFiltro;
	}

	public void setUnidadesFiltro(JSFSelectEnum<UnidadMedidaInsumo> unidadesFiltro) {
		this.unidadesFiltro = unidadesFiltro;
	}

	public boolean isRenderUnidad() {
		return renderUnidad;
	}

	public void setRenderUnidad(boolean renderUnidad) {
		this.renderUnidad = renderUnidad;
	}

	public JSFTable<UnidadMedidaInsumoCompra> getUnidadesCompra() {
		return unidadesCompra;
	}
	public void setUnidadesCompra(JSFTable<UnidadMedidaInsumoCompra> unidadesCompra) {
		this.unidadesCompra = unidadesCompra;
	}
	public JSFTable<Proveedor> getProveedores() {
		return proveedores;
	}
	public void setProveedores(JSFTable<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public ManagedBeanActions getActionUnidad() {
		return actionUnidad;
	}

	public void setActionUnidad(ManagedBeanActions actionUnidad) {
		this.actionUnidad = actionUnidad;
	}

	public UnidadMedidaInsumoCompra getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadMedidaInsumoCompra unidad) {
		this.unidad = unidad;
	}	

	@Override
	public void selectEntity(ActionEvent event) {		
		super.selectEntity(event);
		this.actionUnidad = ManagedBeanActions.NONE;
		List<UnidadMedidaInsumoCompra> unidades = this.serviceParametricas.listDetalle(new UnidadMedidaInsumoCompra(), this.entity, "insumo");
		this.unidadesCompra = new JSFTable<UnidadMedidaInsumoCompra>(unidades);
	}
	
	public void selectUnidad(ActionEvent event){
		this.unidad = this.unidadesCompra.getSelectedRow();
		this.actionUnidad = ManagedBeanActions.SELECTED;
	}
	
	public void acceptUnidad(ActionEvent event) {
		this.renderUnidad = false;
		UnidadMedidaInsumoCompra aux = null;
		List<UnidadMedidaInsumoCompra> list = this.serviceParametricas.listDetalle(new UnidadMedidaInsumoCompra(), this.entity, "insumo");
		this.unidadesCompra = new JSFTable<UnidadMedidaInsumoCompra>(list);
		for (UnidadMedidaInsumoCompra unidadMedidaInsumoCompra : list) {
			if (unidadMedidaInsumoCompra.isPredeterminada() && !unidadMedidaInsumoCompra.equals(unidad)) {
				unidadMedidaInsumoCompra.setPredeterminada(false);
				aux = unidadMedidaInsumoCompra;
				break;
			}
		}
		
		if (this.actionUnidad == ManagedBeanActions.NEW) {
			try {
				if(unidad.isPredeterminada()  && aux != null){
					this.dao.update(aux);
				}
				this.unidad.setInsumo(this.entity);
				this.dao.save(unidad);
				this.unidadesCompra.addRow(unidad);
				this.actionUnidad = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		} else {
			try {
				if(unidad.isPredeterminada() && aux != null){
					this.dao.update(aux);
				}
				this.dao.update(this.unidad);
				this.actionUnidad = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		}		 
	}	
	
	public void renderUnidad(ActionEvent event) throws InstantiationException, IllegalAccessException{		
		this.renderUnidad = true;
		if(this.actionUnidad == ManagedBeanActions.NEW){
			this.unidad = new UnidadMedidaInsumoCompra();			
		}
	}
	
	public void cancelUnidad(ActionEvent event) throws InstantiationException, IllegalAccessException{
		this.renderUnidad = false;
		if(this.actionUnidad == ManagedBeanActions.NEW){
			this.actionUnidad = ManagedBeanActions.NONE;
			this.unidad = new UnidadMedidaInsumoCompra();
		}else{
			this.actionUnidad = ManagedBeanActions.SELECTED;
		}
	}
	
	public void deleteUnidad(ActionEvent event) throws InstantiationException, IllegalAccessException{
		try {
			this.dao.delete(this.unidad);
			this.unidadesCompra.removeRow(this.unidad);
			this.unidad = new UnidadMedidaInsumoCompra();
			this.actionUnidad = ManagedBeanActions.NONE;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	public boolean isSelectedUnidad(){
		if(this.actionUnidad == ManagedBeanActions.SELECTED){
			return true;
		}else{
			return false;
		}
	}

	@Override
	protected void initEntity() {
		this.entity.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);		
	}

	@Override
	protected List<Insumo> buscar() {
		DetachedCriteria crit = CriteriaHelper.createDetachedCriteria(filtro, "unidades");
		
		if(this.filtro.getId() != null && this.filtro.getId() != 0){
			crit.add(Restrictions.eq("id", filtro.getId()));			
		}
		if(this.filtro.getStockDisponible() != null && this.filtro.getStockDisponible() != 0){
			crit.add(Restrictions.eq("stockDisponible", filtro.getStockDisponible()));			
		}
		if(filtro.getNombre() != null && !filtro.getNombre().isEmpty()){			
			crit.add(Restrictions.ilike("nombre", filtro.getNombre(), MatchMode.START));			
		}
		List list = dao.listCriteria(crit, filtro.getClass());
		return list;
	}

	
}
