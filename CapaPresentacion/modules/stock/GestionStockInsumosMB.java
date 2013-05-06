package stock;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.event.ActionEvent;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.CriteriaHelper;
import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import produccion.Insumo;
import stock.estrategias.EstrategiaGestion;
import utils.JSFSelectEnum;
import utils.JSFTable;


public class GestionStockInsumosMB extends SimpleAMBMB<Insumo>{
	
	private JSFSelectEnum<EstrategiaGestion> estrategias;	
	private JSFSelectEnum<EstrategiaGestion> estrategiasFiltro;
	private JSFSelectEnum<SemaforoStock> estados;
	private JSFTable<Proveedor> proveedores;
	private HtmlPanelGrid panelGrid;
	private EstrategiaGestion estrategiaSelected;
	private List<Insumo> insumos;
	private boolean renderManual;
	private boolean renderStockOptimo;
	
	public GestionStockInsumosMB() {
		super(NavigationRules.gestionarStockIsumos);
	}

	public JSFSelectEnum<EstrategiaGestion> getEstrategias() {
		return estrategias;
	}
	public void setEstrategias(JSFSelectEnum<EstrategiaGestion> estrategias) {
		this.estrategias = estrategias;
	}	
	public JSFSelectEnum<SemaforoStock> getEstados() {
		return estados;
	}
	public void setEstados(JSFSelectEnum<SemaforoStock> estados) {
		this.estados = estados;
	}
	public HtmlPanelGrid getPanelGrid() {
		return panelGrid;
	}
	public void setPanelGrid(HtmlPanelGrid panelGrid) {
		this.panelGrid = panelGrid;
	}

	public void selectEstrategia(ActionEvent event){
		this.estrategiaSelected = this.estrategias.getSelectedEnum();	
	}

	@Override
	public String init() {
		this.action = ManagedBeanActions.NONE;
		this.entity = new Insumo();
		this.estados = new JSFSelectEnum<SemaforoStock>(SemaforoStock.values());
		this.insumos = super.dao.list(new Insumo());
		this.entities = new JSFTable<Insumo>(new ArrayList<Insumo>(this.insumos));
		this.estrategias = new JSFSelectEnum<EstrategiaGestion>(EstrategiaGestion.values(),this,"entity.estrategiaGestion");
		this.estrategiasFiltro = new JSFSelectEnum<EstrategiaGestion>(EstrategiaGestion.values(),this,"filtro.estrategiaGestion");
		return super.init();
	}

	public boolean isRenderManual() {
		try {
			if(this.entity.getEstrategiaGestion().equals(EstrategiaGestion.MANUAL)){
				return true;
			}
		} catch (Exception e) {
			return false;
		}	
		return false;
	}
	public boolean isRenderStockOptimo() {
		try {
			if(this.entity.getEstrategiaGestion().equals(EstrategiaGestion.STOCK_OPTIMO)){
				return true;
			}
		} catch (Exception e) {
			return false;
		}	
		return false;
	}	

	public EstrategiaGestion getEstrategiaSelected() {
		return estrategiaSelected;
	}

	
	public boolean isSinEstrategia(){		
		return this.estrategiaSelected.equals(EstrategiaGestion.SIN_ESTRATEGIA);
	}
	public boolean isManual(){
		return this.estrategiaSelected.equals(EstrategiaGestion.MANUAL);
	}
	public boolean isStockOptimo(){
		return this.estrategiaSelected.equals(EstrategiaGestion.STOCK_OPTIMO);
	}
	
	
	public JSFSelectEnum<EstrategiaGestion> getEstrategiasFiltro() {
		return estrategiasFiltro;
	}

	public void setEstrategiasFiltro(
			JSFSelectEnum<EstrategiaGestion> estrategiasFiltro) {
		this.estrategiasFiltro = estrategiasFiltro;
	}

	public SemaforoStock getRojo(){
		return SemaforoStock.ROJO;
	}
	public SemaforoStock getAmarillo(){
		return SemaforoStock.AMARILLO;
	}
	public SemaforoStock getVerde(){
		return SemaforoStock.VERDE;
	}

	@Override
	public void selectEntity(ActionEvent event) {		
		super.selectEntity(event);
		this.estrategiaSelected = this.entity.getEstrategiaGestion();
	}
	
	public void selectEstado(ActionEvent event){
		SemaforoStock semaforoStock = this.estados.getSelectedEnum();
		List<Insumo> list = new ArrayList<Insumo>();
		if(semaforoStock == null){
			this.entities = new JSFTable<Insumo>(new ArrayList<Insumo>(this.insumos));
			return;
		}else{
			for (Insumo insumo : this.insumos) {
				if(insumo.getEstado().equals(semaforoStock)){
					list.add(insumo);
				}
			}
		}
		this.entities = new JSFTable<Insumo>(new ArrayList<Insumo>(list));
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
