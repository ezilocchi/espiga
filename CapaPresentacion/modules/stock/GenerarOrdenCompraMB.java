package stock;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import persistencia.ServiceEntity;
import persistencia.ServiceParametricas;
import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import produccion.Insumo;
import utils.JSFTable;

public class GenerarOrdenCompraMB {	
	
	@EJB
	private OrdenCompraBusiness business;
	@EJB
	private ServiceEntity service;
	@EJB
	private ServiceParametricas serviceParametricas;
	private boolean renderProveedores;	
	private boolean renderUnidades;
	private boolean renderConfirmar;
	private JSFTable<Proveedor> proveedores;
	private JSFTable<OrdenCompraDetalle> detalle;
	private OrdenCompraDetalle detalleSelected;
	private JSFTable<UnidadMedidaInsumoCompra> unidades;
	private OrdenCompra orden;
	private Insumo insumoSelected;
	private Proveedor proveedorSelected;
	private ManagedBeanActions action;
	
	private GestionOrdenCompraMB ordenCompraMB;
	
	/**
	 * Inicia la generacion de una orden de compra.
	 * @return
	 */
	public String init(){
		this.action = ManagedBeanActions.NEW;
		List<Proveedor> list = this.service.list(new Proveedor());
		this.renderConfirmar = false;
		this.init(list);
		return NavigationRules.generarOrdenCompra.name();
	}
	
	/**
	 * Inicia la generacion de una orden de compra para un Insumo en particular
	 * @return
	 */
	public String generarOrdenCompra(){
		this.action = ManagedBeanActions.NEW;
		List<Proveedor> proveedores = this.business.getProveedores(this.insumoSelected);
		this.init(proveedores);		
		this.renderConfirmar = false;
		return NavigationRules.generarOrdenCompra.name();
	}
	
	/**
	 * Inicia la modificacion para una orden de compra seleccionada
	 * @return
	 */
	public String modificarOrdenCompra(){
		this.action = ManagedBeanActions.UPDATE;
		this.renderProveedores = false;		
		this.detalle = new JSFTable<OrdenCompraDetalle>(this.orden.getDetalle());		
		this.renderConfirmar = false;
		return NavigationRules.generarOrdenCompra.name();
	}
	
	private void init(List<Proveedor> proveedores){
		this.orden = null;
		this.renderProveedores = true;		
		this.detalle = new JSFTable<OrdenCompraDetalle>();
		this.proveedores = new JSFTable<Proveedor>(proveedores);
	}
	

	public OrdenCompraBusiness getBusiness() {
		return business;
	}

	public void setBusiness(OrdenCompraBusiness business) {
		this.business = business;
	}

	public ServiceEntity getService() {
		return service;
	}

	public void setService(ServiceEntity service) {
		this.service = service;
	}

	public Proveedor getProveedorSelected() {
		return proveedorSelected;
	}

	public void setProveedorSelected(Proveedor proveedorSelected) {
		this.proveedorSelected = proveedorSelected;
	}

	public boolean isRenderProveedores() {
		return renderProveedores;
	}

	public void setRenderProveedores(boolean renderProveedores) {
		this.renderProveedores = renderProveedores;
	}
	
	public GestionOrdenCompraMB getOrdenCompraMB() {
		return ordenCompraMB;
	}

	public void setOrdenCompraMB(GestionOrdenCompraMB ordenCompraMB) {
		this.ordenCompraMB = ordenCompraMB;
	}

	public OrdenCompra getOrden() {
		return orden;
	}

	public void setOrden(OrdenCompra orden) {
		this.orden = orden;
	}

	public boolean isRenderConfirmar() {
		return renderConfirmar;
	}

	public void setRenderConfirmar(boolean renderConfirmar) {
		this.renderConfirmar = renderConfirmar;
	}

	public boolean isRenderUnidades() {
		return renderUnidades;
	}

	public void setRenderUnidades(boolean renderUnidades) {
		this.renderUnidades = renderUnidades;
	}

	public JSFTable<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(JSFTable<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public JSFTable<UnidadMedidaInsumoCompra> getUnidades() {
		return unidades;
	}

	public void setUnidades(JSFTable<UnidadMedidaInsumoCompra> unidades) {
		this.unidades = unidades;
	}

	public JSFTable<OrdenCompraDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(JSFTable<OrdenCompraDetalle> detalle) {
		this.detalle = detalle;
	}
	
	public Insumo getInsumoSelected() {
		return insumoSelected;
	}

	public void setInsumoSelected(Insumo insumoSelected) {
		this.insumoSelected = insumoSelected;
	}

	public void selectProveedor(ActionEvent event){
		this.proveedorSelected = this.proveedores.getSelectedRow();
		try {
			this.orden = this.business.nuevaOrdenCompra(this.proveedorSelected);
			this.detalle = new JSFTable<OrdenCompraDetalle>(this.orden.getDetalle());			
		} catch (Exception e) {			
			ExceptionHandler.getInstance().handleException(e);
			this.detalle = new JSFTable<OrdenCompraDetalle>();
		}
		this.renderProveedores = false;
	}
	
	public void cancelProveedor(ActionEvent event){
		this.renderProveedores = false;
	}
	
	public String confirmar(){
		try {
			if(this.action.equals(ManagedBeanActions.NEW)){
				this.business.save(this.orden);				
			}else{
				this.business.update(orden);
			}
			return this.ordenCompraMB.init();			
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
			return null;
		}
	}
	public String cancelar(){
		if(this.action.equals(ManagedBeanActions.UPDATE)){
			return NavigationRules.gestionarOrdenCompra.name();
		}else{
			return NavigationRules.loginOk.name();			
		}
	}
	public void aceptar(ActionEvent event){
		List<OrdenCompraDetalle> list = new ArrayList<OrdenCompraDetalle>();
		for (OrdenCompraDetalle detalle : this.detalle.getEntities()) {
			if(detalle.getCantidad()==0){
				list.add(detalle);
			}
		}
		List<OrdenCompraDetalle> list2 = new ArrayList<OrdenCompraDetalle>(this.detalle.getEntities());
		list2.removeAll(list);
		this.detalle = new JSFTable<OrdenCompraDetalle>(list2);
		this.renderConfirmar = true;
	}
	public void hide(ActionEvent event){
		this.renderConfirmar = false;
	}
	
	public void renderPanelUnidades(ActionEvent event){
		this.detalleSelected = this.detalle.getSelectedRow();
		if(this.action.equals(ManagedBeanActions.UPDATE)){
			List<UnidadMedidaInsumoCompra> list = this.serviceParametricas.listDetalle(new UnidadMedidaInsumoCompra(),this.detalleSelected.getInsumo() , "insumo");
			this.detalleSelected.getInsumo().setUnidades(list);
		}
		this.unidades = new JSFTable<UnidadMedidaInsumoCompra>(this.detalleSelected.getInsumo().getUnidades());
		this.renderUnidades = true;
	}
	
	public void selectUnidad(ActionEvent event){
		UnidadMedidaInsumoCompra unidad = this.unidades.getSelectedRow();
		this.detalleSelected.setUnidadMedida(unidad);
		this.renderUnidades = false;
	}
	
	public void hideUnidades(ActionEvent event){
		this.renderUnidades = false;
	}
}
