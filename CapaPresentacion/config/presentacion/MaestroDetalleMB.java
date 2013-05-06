package presentacion;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import persistencia.ServiceMaestroDetalle;

import entity.BaseEntity;

import utils.JSFTable;
//TODO trabajar las excepciones en esta clase, para que las hijas no tengan que renegar al pedio!
public abstract class MaestroDetalleMB <MAESTRO extends BaseEntity,DETALLE extends BaseEntity> {

	@EJB(beanName="ServiceMaestroDetalle")
	protected ServiceMaestroDetalle service;
	
	protected Class<MAESTRO> clazzMaestro;
	protected Class<DETALLE> clazzDetalle;	
	
	protected NavigationRules navigationRules;
	protected String nameMaestro;
	protected String nameDetalle;
		
	protected ManagedBeanActions actionMaestro;	
	protected ManagedBeanActions actionDetalle;
	protected MAESTRO maestro;
	protected DETALLE detalle;
	protected JSFTable<MAESTRO> maestros;		
	protected JSFTable<DETALLE> detalles;
	
	protected MAESTRO filtroMaestro;
	protected DETALLE filtroDetalle;
	
	public MaestroDetalleMB(NavigationRules navigationRules, String nameMaestro, String nameDetalle){
		Type[] vec = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments();
		this.clazzMaestro = (Class<MAESTRO>) vec[0];
		this.clazzDetalle = (Class<DETALLE>) vec[1];		
		this.actionMaestro = ManagedBeanActions.NONE;
		this.actionDetalle = ManagedBeanActions.NONE;
		this.navigationRules = navigationRules;		
		this.nameMaestro = nameMaestro;
		this.nameDetalle = nameDetalle;
		try {
			this.maestro = this.clazzMaestro.newInstance();
			this.filtroMaestro = this.clazzMaestro.newInstance();
			this.filtroDetalle = this.clazzDetalle.newInstance();
		} catch (InstantiationException e) {
			// TODO Hacer log
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Hacer log
			throw new RuntimeException(e);
		}
	}
	
	
	public MAESTRO getMaestro() {
		return maestro;
	}
	public void setMaestro(MAESTRO maestro) {
		this.maestro = maestro;
	}
	public DETALLE getDetalle() {
		return detalle;
	}
	public void setDetalle(DETALLE detalle) {
		this.detalle = detalle;
	}
	public ManagedBeanActions getActionMaestro() {
		return actionMaestro;
	}
	public void setActionMaestro(ManagedBeanActions actionMaestro) {
		this.actionMaestro = actionMaestro;
	}
	public ManagedBeanActions getActionDetalle() {
		return actionDetalle;
	}
	public void setActionDetalle(ManagedBeanActions actionDetalle) {
		this.actionDetalle = actionDetalle;
	}
	public JSFTable<MAESTRO> getMaestros() {
		return maestros;
	}
	public void setMaestros(JSFTable<MAESTRO> maestros) {
		this.maestros = maestros;
	}
	public MAESTRO getFiltroMaestro() {
		System.out.println("Filtro Maestro");
		return filtroMaestro;
	}
	public void setFiltroMaestro(MAESTRO filtroMaestro) {
		this.filtroMaestro = filtroMaestro;
	}
	public DETALLE getFiltroDetalle() {
		return filtroDetalle;
	}
	public void setFiltroDetalle(DETALLE filtroDetalle) {
		this.filtroDetalle = filtroDetalle;
	}
	public JSFTable<DETALLE> getDetalles() {
		return detalles;
	}
	public void setDetalles(JSFTable<DETALLE> detalles) {
		this.detalles = detalles;
	}
	/* FIN GETTERS SETTERS*/

	public String init(){
		this.filterCondition();
		List<MAESTRO> list =  this.service.list(this.filtroMaestro);
		this.maestros = new JSFTable<MAESTRO>(list);
		this.detalles = new JSFTable<DETALLE>();
		return this.navigationRules.name();
	}	
	
	/**
	 * 
	 * @param event	 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void selectMaestro(ActionEvent event) throws InstantiationException, IllegalAccessException{
		this.maestro = this.maestros.getSelectedRow();
		this.actionMaestro = ManagedBeanActions.SELECTED;
		this.actionDetalle = ManagedBeanActions.NONE;
		this.detalle = this.clazzDetalle.newInstance();
		this.selectMaestro();		
	}	
	
	/**
	 * 
	 * @return
	 */
	public boolean isMaestroSelected(){
		if(this.maestro != null && this.maestro.getId()!=null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */
	protected void selectMaestro(){		
		try {
			List<DETALLE> list = this.service.listDetalle(this.clazzDetalle.newInstance(),maestro,this.nameMaestro);			
			this.detalles = new JSFTable<DETALLE>(list);
		} catch (InstantiationException e) {
			// TODO Hacer log
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Hacer log
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 */
	protected void filterCondition(){
		try {
			this.filtroMaestro = this.clazzMaestro.newInstance();
		} catch (InstantiationException e) {
			// TODO Hacer log
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Hacer log
			throw new RuntimeException(e);
		}
	}
	
	public void buscarMaestro(ActionEvent event){
		this.maestros = new JSFTable<MAESTRO>(this.buscar());
		this.actionMaestro = ManagedBeanActions.NONE;
		this.detalles = new JSFTable<DETALLE>();
		this.actionMaestro = ManagedBeanActions.NONE;
	}
	
	protected List<MAESTRO> buscar(){
		return this.service.filtterMaestro(getFiltroMaestro());
	}
	
	public void buscarDetalle(ActionEvent event){		
		this.detalles = new JSFTable<DETALLE>();
		this.actionMaestro = ManagedBeanActions.NONE;
	}
	protected List<DETALLE> buscarDetalle(){
		return this.service.filtterDetalle(getFiltroDetalle());
	}
	
	public void limpiarFiltroMaestro(ActionEvent event) throws InstantiationException, IllegalAccessException{
		this.filtroMaestro = this.clazzMaestro.newInstance(); 
	}
}
