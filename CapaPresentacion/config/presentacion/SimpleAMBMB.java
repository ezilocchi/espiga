package presentacion;

import integracion.ExceptionHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;
import persistencia.ServiceEntity;
import reporting.engine.Report;
import reporting.engine.ReportFactory;
import reporting.engine.EntityReportFactory;
import utils.JSFTable;

public class SimpleAMBMB<ENTITY> {
	
	private Class<ENTITY> clazz;
	private boolean renderPanel;
	protected Map<String, String> reportColumns;
	private Map<String, String> reportParams;
		
	protected ManagedBeanActions action;	
	protected ENTITY entity;
	protected JSFTable<ENTITY> entities;
	protected NavigationRules navigationRules;
	
	protected ENTITY filtro;
	
	@EJB(beanName="ServiceEntity")
	protected ServiceEntity dao;	

	/**
	 * 
	 * @param navigationRules Regla de navegacion para poder acceder a este ABM desde el menu principal
	 */
	public SimpleAMBMB(NavigationRules navigationRules) {
		Type[] vec = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments();
		this.clazz = (Class<ENTITY>) vec[0];
		try {
			this.entity = this.clazz.newInstance();
			this.filtro = this.clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.entities = new JSFTable<ENTITY>(new ArrayList<ENTITY>());
		this.action = ManagedBeanActions.NONE;
		this.renderPanel = false;
		this.navigationRules = navigationRules;
		this.reportColumns = new HashMap<String, String>();
		this.reportParams = new HashMap<String, String>();		
	}
	
	/**
	 * 
	 * @param navigationRules Regla de navegacion para poder acceder a este ABM desde el menu principal
	 * @param reportColumns Los atributos que se mostraran en la impresion en PDF y el orden de los mismos
	 */
	public SimpleAMBMB(NavigationRules navigationRules, Map<String, String> reportColumns, String reportTitle) {
		this(navigationRules);
		this.reportColumns = reportColumns;
		this.reportParams.put("TITLE", reportTitle);
	}
	
	/**
	 * 
	 * @param navigationRules Regla de navegacion para poder acceder a este ABM desde el menu principal
	 * @param reportColumns Los atributos que se mostraran en la impresion en PDF y el orden de los mismos
	 * @param reportParams Conjunto de parametros que posee el reporte para este ABM
	 */
	public SimpleAMBMB(NavigationRules navigationRules, Map<String, String> reportColumns, Map<String, String> reportParams) {
		this(navigationRules,reportColumns,"");
		this.reportParams = reportParams;
	}
	
	/**
	 * 
	 * @param navigationRules Regla de navegacion para poder acceder a este ABM desde el menu principal
	 * @param reportTitle Titulo del reporte que se genera en PDF
	 */
	public SimpleAMBMB(NavigationRules navigationRules, String reportTitle) {
		this(navigationRules);
		this.reportParams.put("TITLE", reportTitle);
	}

	public ManagedBeanActions getAction() {
		return action;
	}
	public void setAction(ManagedBeanActions action) {
		this.action = action;
	}
	public ENTITY getEntity() {
		return entity;
	}
	public void setEntity(ENTITY entity) {
		this.entity = entity;
	}
	public JSFTable<ENTITY> getEntities() {
		return entities;
	}
	public void setEntities(JSFTable<ENTITY> entities) {
		this.entities = entities;
	}	
	public boolean isRenderPanel() {
		return renderPanel;
	}
	public void setRenderPanel(boolean renderPanel) {
		this.renderPanel = renderPanel;
	}
	public ENTITY getFiltro() {
		return filtro;
	}
	public void setFiltro(ENTITY filtro) {
		this.filtro = filtro;
	}
	protected NavigationRules getNavigationRules() {
		return navigationRules;
	}
	protected void setNavigationRules(NavigationRules navigationRules) {
		this.navigationRules = navigationRules;
	}
	/* FIN GETTERS SETTERS*/

	public String init(){
		return this.navigationRules.name();
	}
	
	/**
	 * 
	 * @param event
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void renderPanel(ActionEvent event) throws InstantiationException, IllegalAccessException{		
		this.renderPanel = true;
		if(this.action == ManagedBeanActions.NEW){
			this.entity = this.clazz.newInstance();
			this.initEntity();
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void accept(ActionEvent event){		
		if(this.action == ManagedBeanActions.NEW){
			try {
				this.save();
				this.entities.addRow(entity);
				this.action = ManagedBeanActions.SELECTED;
				this.renderPanel = false;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		}else{
			try {
				this.update();
				this.action = ManagedBeanActions.SELECTED;
				this.renderPanel = false;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		}
	}
	
	/**
	 * 
	 * @param event
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void cancel(ActionEvent event) throws InstantiationException, IllegalAccessException{
		this.renderPanel = false;
		if(this.action == ManagedBeanActions.NEW){
			this.action = ManagedBeanActions.NONE;
			this.entity = this.clazz.newInstance();
		}else{
			this.action = ManagedBeanActions.SELECTED;
		}
	}
	
	/**
	 * 
	 * @param event
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void delete(ActionEvent event) throws InstantiationException, IllegalAccessException{
		try {
			this.delete();
			this.entities.removeRow(entity);
			this.entity = this.clazz.newInstance();
			this.action = ManagedBeanActions.NONE;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void selectEntity(ActionEvent event){
		this.entity = this.entities.getSelectedRow();
		this.action = ManagedBeanActions.SELECTED;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSelected(){
		if(this.action == ManagedBeanActions.SELECTED){
			return true;
		}else{
			return false;
		}
	}
	
	public void buscar(ActionEvent event) throws InstantiationException, IllegalAccessException{
		this.entities = new JSFTable<ENTITY>(this.buscar());
	}
	
	public void print(ActionEvent event) throws JRException{
		ReportFactory factory = new EntityReportFactory();		
		Report report = factory.buildReport(this.entities.getEntities(), this.reportColumns, this.reportParams);
		report.print();
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	protected void save() throws Exception{
		this.dao.save(entity);
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	protected void update() throws Exception{
		this.dao.update(entity);
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	protected void delete() throws Exception{
		this.dao.delete(entity);
	}
	
	protected List<ENTITY> buscar(){
		return this.dao.listFiltter(getFiltro());
	}
	
	public void limpiarFiltro(ActionEvent event) throws InstantiationException, IllegalAccessException{
		this.filtro = this.clazz.newInstance();
	}
	
	protected void initEntity(){
		
	}
}
