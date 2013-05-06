package distribucion;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistencia.ServiceEntity;
import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import utils.JSFSelectItem;
import utils.JSFTable;
import utils.JSFTableMultiSelects;
import venta.Pedido;
import venta.PedidoDetalle;
import venta.estados.pedido.EstadoPedido;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import base.EmpleadoBusiness;

public class DiagramarDistribucionMB {

	@EJB
	private DiagramarDistribucionBusiness business;	
	@EJB
	private EmpleadoBusiness empleadoBusiness;
	@EJB(beanName="ServiceEntity")
	protected ServiceEntity service;
	
	private JSFTableMultiSelects<Pedido> pedidosAsignados;
	private JSFTableMultiSelects<Pedido> pedidosNoAsignados;	
	private JSFTable<DiagramacionDistribucionDetalle> maestros;
	private JSFSelectItem<DiagramacionDistribucionDetalle> empleados;
	
	
	private DiagramacionDistribucionDetalle maestro;
	private DiagramacionDistribucionDetalle maestroNoAsignados;
	private DiagramacionDistribucion diagramacion;
	private Date fecha;
	
	private boolean renderPanel;
	private JSFTable<Vehiculo> vehiculos;
	private List<Vehiculo> vehiculosTotales;
	
	private DiagramacionDistribucionMB distribucionMB;
	private ManagedBeanActions action = ManagedBeanActions.NONE;
	
	private boolean pedidosDesasignados;
	
	private boolean renderPedido;
	private JSFTable<PedidoDetalle> detallePedido;
	private Pedido pedido;

	public DiagramarDistribucionMB() {		
	}	
	
	public JSFTableMultiSelects<Pedido> getPedidosAsignados() {
		return pedidosAsignados;
	}
	public void setPedidosAsignados(JSFTableMultiSelects<Pedido> pedidosAsignados) {
		this.pedidosAsignados = pedidosAsignados;
	}
	public JSFTable<PedidoDetalle> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(JSFTable<PedidoDetalle> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public JSFTableMultiSelects<Pedido> getPedidosNoAsignados() {
		return pedidosNoAsignados;
	}
	public void setPedidosNoAsignados(
			JSFTableMultiSelects<Pedido> pedidosNoAsignados) {
		this.pedidosNoAsignados = pedidosNoAsignados;
	}
	
	public boolean isRenderPedido() {
		return renderPedido;
	}

	public void setRenderPedido(boolean renderPedido) {
		this.renderPedido = renderPedido;
	}

	public DiagramacionDistribucion getDiagramacion() {
		return diagramacion;
	}
	public void setDiagramacion(DiagramacionDistribucion diagramacion) {
		this.diagramacion = diagramacion;
	}
	public DiagramacionDistribucionDetalle getMaestro() {
		return maestro;
	}
	public void setMaestro(DiagramacionDistribucionDetalle maestro) {
		this.maestro = maestro;
	}
	public JSFSelectItem<DiagramacionDistribucionDetalle> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(JSFSelectItem<DiagramacionDistribucionDetalle> empleados) {
		this.empleados = empleados;
	}
	public boolean isRenderPanel() {
		return renderPanel;
	}
	public void setRenderPanel(boolean renderPanel) {
		this.renderPanel = renderPanel;
	}
	public JSFTable<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(JSFTable<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public JSFTable<DiagramacionDistribucionDetalle> getMaestros() {
		return maestros;
	}
	public void setMaestros(JSFTable<DiagramacionDistribucionDetalle> maestros) {
		this.maestros = maestros;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setDistribucionMB(DiagramacionDistribucionMB distribucionMB) {
		this.distribucionMB = distribucionMB;
	}
	public boolean isPedidosDesasignados() {
		return pedidosDesasignados;
	}
	public void setPedidosDesasignados(boolean pedidosDesasignados) {
		this.pedidosDesasignados = pedidosDesasignados;
	}
	public ManagedBeanActions getAction() {
		return action;
	}
	public void setAction(ManagedBeanActions action) {
		this.action = action;
	}
	//*** FIN GETTERS & SETTERS

	public String nuevaDiagramacion(){
		this.pedidosDesasignados = false;
		this.fecha = null;
		this.diagramacion = null;
		this.renderPanel = false;
		this.vehiculosTotales = this.business.listVehiculo(new Vehiculo());
		this.vehiculos = new JSFTable<Vehiculo>(new ArrayList<Vehiculo>(this.vehiculosTotales));
		this.maestros = new JSFTable<DiagramacionDistribucionDetalle>();
		this.pedidosAsignados = new JSFTableMultiSelects<Pedido>();
		this.maestroNoAsignados = null;
		this.pedidosNoAsignados = new JSFTableMultiSelects<Pedido>();
		return NavigationRules.diagramarDistribucion.name();
	}
	
	public void seleccionFecha(ActionEvent event){
		try {
			this.pedidosDesasignados = false;
			this.diagramacion = this.business.nuevaDiagramacion(this.fecha);
			
			this.pedidosAsignados = new JSFTableMultiSelects<Pedido>();
			this.pedidosNoAsignados = new JSFTableMultiSelects<Pedido>();
			List<DiagramacionDistribucionDetalle> list = new ArrayList<DiagramacionDistribucionDetalle>();
			for (DiagramacionDistribucionDetalle det : this.diagramacion.getDetalle()) {
				if(det.getPedidos().size()>0){
					list.add(det);
				}
			}
			this.maestros = new JSFTable<DiagramacionDistribucionDetalle>(list);
			this.empleados = new JSFSelectItem<DiagramacionDistribucionDetalle>(this.diagramacion.getDetalle(),"empleado.apellido");
			List<Pedido> pedidos = new ArrayList<Pedido>();
			
			if(this.diagramacion.getPedidosSinTerminar().getPedidos().size()>0){				
				pedidos.addAll(this.diagramacion.getPedidosSinTerminar().getPedidos());
				this.pedidosDesasignados = true;
			}
			if(this.diagramacion.getPedidosSinZona().getPedidos().size()>0){
				pedidos.addAll(this.diagramacion.getPedidosSinZona().getPedidos());
				this.pedidosDesasignados = true;
			}
			this.pedidosNoAsignados = new JSFTableMultiSelects<Pedido>(pedidos);
			
			if(this.pedidosDesasignados){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Existen pedidos sin Zona definida","");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			this.maestros = new JSFTable<DiagramacionDistribucionDetalle>();
			ExceptionHandler.getInstance().handleException(e);
		}
		
	}
	
	public String aceptar(){
		try {
			this.business.save(this.diagramacion);
			this.distribucionMB.init();			
			return NavigationRules.listarDiagramacionDistribucion.name();
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
			return null;
		}
	}
	
	public String cancelar(){
		return NavigationRules.listarDiagramacionDistribucion.name();
	}	
	
	public void selectMaestro(ActionEvent event) throws InstantiationException,IllegalAccessException {
		this.maestro = this.maestros.getSelectedRow();
		this.pedidosAsignados = new JSFTableMultiSelects<Pedido>(this.maestro.getPedidos());
	}
	
	public boolean isDiagramacionSelected(){
		return this.diagramacion!=null;
	}
	
	public String modificarDiagramacion(){
		this.action = ManagedBeanActions.UPDATE;
		this.pedidosAsignados = new JSFTableMultiSelects<Pedido>();
		return NavigationRules.modificarDiagramacionDistribucion.name();
	}	
	
	public String modificarDiagramacionMaestro(){
		this.action = ManagedBeanActions.UPDATE;
		this.maestro = this.maestros.getSelectedRow();
		this.pedidosAsignados = new JSFTableMultiSelects<Pedido>(this.maestro.getPedidos());
		this.empleados.setItemSelected(this.maestro.getEmpleado().getApellido());
		return NavigationRules.modificarDiagramacionDistribucion.name();		
	}
	
	public String modificarDiagramacionExistente(){
		List<DiagramacionDistribucionDetalle> list = business.getDiagramacionDetalle(diagramacion);
		this.diagramacion.setDetalle(list);		
		this.maestros = new JSFTable<DiagramacionDistribucionDetalle>(list);
		return this.modificarDiagramacion();
	}
	
	public void selectCheck(ActionEvent actionEvent){
		UIComponent comp = actionEvent.getComponent();
		HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) comp.getParent();
		if (comp.getId().equalsIgnoreCase("selectAsignados")) {
			this.pedidosAsignados.selectAll(checkbox.isSelected());
		} else {
			this.pedidosNoAsignados.selectAll(checkbox.isSelected());
		}
	}
	
	public String volver(){
//		this.maestros = new JSFTable<DiagramacionDistribucionDetalle>(this.diagramacion.getDetalle());
		this.pedidosAsignados = new JSFTableMultiSelects<Pedido>();
		if (this.action.equals(ManagedBeanActions.UPDATE)) {
			return NavigationRules.diagramarDistribucion.name();
		} else {
			return NavigationRules.listarDiagramacionDistribucion.name();
		}
		
	}		
	
	public void asignarPedidos(ActionEvent event){
		DiagramacionDistribucionDetalle detalle = this.empleados.getEntitySelected();		
		if(detalle == null){
			//TODO Message boundle
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar un repartidor","");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		List<Pedido> pedidos = this.pedidosNoAsignados.getSelectedEntities();
		if (pedidos.size()==0) {
			//TODO Message boundle
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar algun pedido","");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		boolean flag = false;
		if(detalle.getPedidos().size()==0){
			flag = true;
		}
		
		for (Pedido pedido : pedidos) {
			detalle.addPedido(pedido);
			this.pedidosAsignados.addEntity(pedido);
			this.pedidosNoAsignados.removeEntity(pedido);
			this.diagramacion.asignarPedido(pedido);
		}
		if(flag){
			this.maestros.addRow(detalle);			
		}		
	}
	
	public void desasignarPedidos(ActionEvent event){
		DiagramacionDistribucionDetalle detalle = this.empleados.getEntitySelected();		
		if(detalle == null){
			//TODO Message boundle
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar un repartidor","");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		List<Pedido> pedidos = this.pedidosAsignados.getSelectedEntities();
		if (pedidos.size()==0) {
			//TODO Message boundle
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar algun pedido","");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		for (Pedido pedido : pedidos) {
			detalle.getPedidos().remove(pedido);
			this.pedidosAsignados.removeEntity(pedido);
			this.pedidosNoAsignados.addEntity(pedido);
			this.diagramacion.desasignarPedido(pedido);
		}
		boolean flag = false;
		if(detalle.getPedidos().size()==0){
			this.maestros.removeRow(detalle);
		}
	}
	
	public void selectEmpleado(ActionEvent event){
		DiagramacionDistribucionDetalle ddd = this.empleados.getEntitySelected();
		this.pedidosAsignados = new JSFTableMultiSelects<Pedido>(ddd.getPedidos());
	}
	
	public void renderPanel(ActionEvent event){		
		this.maestro = this.maestros.getSelectedRow();
		List<Vehiculo> list = this.diagramacion.getVehiculosAsignados();
		List<Vehiculo> aux = new ArrayList<Vehiculo>();
		for (Vehiculo vehiculo : this.vehiculosTotales) {
			if (!list.contains(vehiculo)) {
				aux.add(vehiculo);
			}
		}
		this.vehiculos = new JSFTable<Vehiculo>(aux);
		this.renderPanel = true;
	}
	
	public void selectVehiculo(ActionEvent event){		
		Vehiculo vehiculo = this.vehiculos.getSelectedRow();
		this.maestro.setVehiculo(vehiculo);
		this.renderPanel = false;
	}
	
	public void hidePanel(ActionEvent event){
		this.renderPanel = false;
	}
	

	public void verPedido(ActionEvent event){
		this.pedido = this.pedidosNoAsignados.getSelectedEntity().getEntity();
		this.detallePedido = new JSFTable<PedidoDetalle>(pedido.getDetalle());
		this.renderPedido = true;
	}
	public void esconderPedido(ActionEvent event){		
		this.renderPedido = false;
	}
	
	public void borrarDetalle(ActionEvent event){
		List<PedidoDetalle> detalle = this.detallePedido.getEntities();
		List<PedidoDetalle> detalleRevomed = new ArrayList<PedidoDetalle>();
		for (PedidoDetalle pedidoDetalle : detalle) {
			if(!pedidoDetalle.getEstado().equals(EstadoPedidoDetalle.PRODUCIDO)){				
				detalleRevomed.add(pedidoDetalle);
			}
		}
		for (PedidoDetalle pedidoDetalle : detalleRevomed) {
			this.pedido.removeDetalle(pedidoDetalle);			
		}
		this.pedido.setEstado(EstadoPedido.PRODUCIDO);
		try {			
			this.business.updatePedido(pedido, detalleRevomed);
//			this.renderPedido = false;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	public void nuevoPedido(ActionEvent event){
		List<PedidoDetalle> detalle = this.detallePedido.getEntities();
		List<PedidoDetalle> detalleRevomed = new ArrayList<PedidoDetalle>();
		for (PedidoDetalle pedidoDetalle : detalle) {
			if(!pedidoDetalle.getEstado().equals(EstadoPedidoDetalle.PRODUCIDO)){				
				detalleRevomed.add(pedidoDetalle);
			}
		}
		for (PedidoDetalle pedidoDetalle : detalleRevomed) {
			this.pedido.removeDetalle(pedidoDetalle);			
		}
		try {
			this.business.nuevoPedido(pedido, detalleRevomed);
//			this.renderPedido = false;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
}
