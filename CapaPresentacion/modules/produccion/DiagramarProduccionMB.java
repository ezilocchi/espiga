package produccion;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.DropEvent;


import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import produccion.estados.EstadoDiagramacion;
import reporting.engine.EntityReportFactory;
import reporting.engine.Report;
import reporting.engine.ResultadoProduccionFactory;
import security.Permiso;
import utils.JSFSelectEnum;
import utils.JSFTable;
import utils.JSFTableMultiSelects;
import utils.WrapperCollection;
import utils.WrapperEntity;
import venta.Pedido;
import venta.PedidoDetalle;
import venta.Producto;
import base.Empleado;
import base.EmpleadoBusiness;

public class DiagramarProduccionMB {

	@EJB
	private DiagramarProduccionBusiness business;
	@EJB
	private EmpleadoBusiness empleadoBusiness; 
	private DiagramacionProduccion diagramacion;
	private JSFTable<Empleado> empleados;
	private JSFTableMultiSelects<ProduccionProducto> productos;
	private Date fechaEntrega;	
	private JSFTableMultiSelects<Pedido> pedidos;
	private HtmlTree treePedidos;
	private HtmlTree treeDiagramacion;
	
	private boolean renderPanelResultado;
	
	private boolean imprimir;	
	
	private String p = "Panaderos";
	private String i = "Insumos";
	private ManagedBeanActions action = ManagedBeanActions.NONE;
	
	private DiagramacionProduccionMB produccionMB;
	private boolean renderBuscador;
	private ProduccionProducto maestro;
	
	private GestionRecetaMB gestionRecetaMB;
	
	public String init(){
		this.renderPanelResultado = false;
		this.renderBuscador = false;
		this.imprimir = false;
		return NavigationRules.diagramarProduccion.name();
	}
	
	public JSFTable<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(JSFTable<Empleado> empleados) {
		this.empleados = empleados;
	}
	public JSFTableMultiSelects<ProduccionProducto> getProductos() {
		return productos;
	}
	public void setProductos(JSFTableMultiSelects<ProduccionProducto> productos) {
		this.productos = productos;
	}	
	public JSFTableMultiSelects<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(JSFTableMultiSelects<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public boolean isImprimir() {
		return imprimir;
	}

	public void setImprimir(boolean imprimir) {
		this.imprimir = imprimir;
	}

	public HtmlTree getTreePedidos() {
		return treePedidos;
	}
	public void setTreePedidos(HtmlTree treePedidos) {
		this.treePedidos = treePedidos;
	}
	public HtmlTree getTreeDiagramacion() {
		return treeDiagramacion;
	}
	public void setTreeDiagramacion(HtmlTree treeDiagramacion) {
		this.treeDiagramacion = treeDiagramacion;
	}
	public DiagramacionProduccion getDiagramacion() {
		return diagramacion;
	}
	public void setDiagramacion(DiagramacionProduccion diagramacion) {
		this.diagramacion = diagramacion;
	}
	public boolean isRenderPanelResultado() {
		return renderPanelResultado;
	}

	public void setRenderPanelResultado(boolean renderPanelResultado) {
		this.renderPanelResultado = renderPanelResultado;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public void setProduccionMB(DiagramacionProduccionMB produccionMB) {
		this.produccionMB = produccionMB;
	}	
	public boolean isRenderBuscador() {
		return renderBuscador;
	}
	public void setRenderBuscador(boolean renderBuscador) {
		this.renderBuscador = renderBuscador;
	}
	public GestionRecetaMB getGestionRecetaMB() {
		return gestionRecetaMB;
	}
	public void setGestionRecetaMB(GestionRecetaMB gestionRecetaMB) {
		this.gestionRecetaMB = gestionRecetaMB;
	}
	public ManagedBeanActions getAction() {
		return action;
	}
	public void setAction(ManagedBeanActions action) {
		this.action = action;
	}
	
	public String nuevaDiagramacion(){
		this.fechaEntrega = null;
		this.diagramacion = null;
		this.renderPanelResultado = false;
		this.renderBuscador = false;
		this.productos = new JSFTableMultiSelects<ProduccionProducto>();		
		this.empleados = new JSFTable<Empleado>(this.empleadoBusiness.getEmpleados(Permiso.PANADERO));
		return NavigationRules.diagramarProduccion.name();	
	}
	public void aceptar(ActionEvent event){
		
		this.renderPanelResultado = true;
	}
	public void hide(ActionEvent event){
		this.renderPanelResultado = false;
	}
	
	public String confirmar(){		
		try {
			this.business.save(diagramacion);
			this.produccionMB.init();
			this.renderBuscador = false;
			this.renderPanelResultado = false;
//			if(this.imprimir){
//				ResultadoProduccionFactory factory = new ResultadoProduccionFactory();
//				List<ProduccionProducto> productos = this.productos.getSelectedEntities();
//				List<Entry<Insumo, Float>> insumos = this.diagramacion.getTotalInsumosEstimados();
//				List<ProduccionProducto> insumo = this.productos.getSelectedEntities();
//				Report report = factory.buildReport(productos, insumos);
//				report.print();
//			}
			return NavigationRules.listarDiagramacionProduccion.name();
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
			return null;
		}		
	}
	public String cancelar(){
		return NavigationRules.listarDiagramacionProduccion.name();
	}	
	
	public void seleccionFecha(ActionEvent event) {
		try {
			this.diagramacion = this.business.nuevaDiagramacion(this.fechaEntrega);
			this.productos = new JSFTableMultiSelects<ProduccionProducto>(this.diagramacion.getDetalle());
			for (WrapperEntity<ProduccionProducto> item : this.productos.content) {
				item.setSelected(true);
			}
			this.pedidos = new JSFTableMultiSelects<Pedido>();
			for (Pedido item : this.diagramacion.getPedidos()) {
				WrapperCollection<Pedido, PedidoDetalle> wrapperPedido = new WrapperCollection<Pedido, PedidoDetalle>(item,item.getDetalle());
				wrapperPedido.setSelectedAll(true);
				this.pedidos.addWapper(wrapperPedido);
			}
		} catch (Exception e) {
			this.productos = new JSFTableMultiSelects<ProduccionProducto>();
			ExceptionHandler.getInstance().handleException(e);
		}		
	}
	
	public String modificarDiagramacion(){
		return NavigationRules.modificarDiagramacionProduccion.name();
	}
	public String volver(){
		if(this.action.equals(ManagedBeanActions.NEW)){
			return NavigationRules.diagramarProduccion.name();
		} else{
			return NavigationRules.listarDiagramacionProduccion.name();
		}
	}
	
	public void selectProducto(ActionEvent event){
		WrapperEntity<ProduccionProducto> pp = this.productos.getSelectedEntity();		
		for (WrapperEntity<Pedido> pedido : this.pedidos.content) {
			WrapperCollection<Pedido, PedidoDetalle> p =(WrapperCollection<Pedido, PedidoDetalle>)pedido;
			for (WrapperEntity<PedidoDetalle> detalle : p.getDetail()) {
				if(detalle.getEntity().getProducto().equals(pp.getEntity().getProducto())){				
					detalle.setSelected(pp.isSelected());
					if(pp.isSelected()){						
						p.setSelected(true);
						pp.getEntity().addPedidoDetalle(detalle.getEntity());
					}else{
						pp.getEntity().removePedidoDetalle(detalle.getEntity());
					}
				}
			}
		}				
	}	
	
	public void selectPedido(ActionEvent event){
		Object object = treePedidos.getRowData();
		if(object instanceof WrapperCollection){
			WrapperCollection<Pedido, PedidoDetalle> pedido = (WrapperCollection<Pedido, PedidoDetalle>)object;
			pedido.setSelectedAll(pedido.isSelected());
			if(pedido.isSelected()){				
				this.diagramacion.addPedido(pedido.getEntity());
			}else{
				this.diagramacion.removePedido(pedido.getEntity());
			}
		}else{
			WrapperEntity<PedidoDetalle> detalle = (WrapperEntity<PedidoDetalle>) object;
			if(detalle.isSelected()){
				this.diagramacion.addPedidoDetalle(detalle.getEntity());
			}else{
				this.diagramacion.removePedidoDetalle(detalle.getEntity());
			}
		}
	}
	
	public boolean isDiagramacionSelected(){
		return this.diagramacion!=null;
	}	
	
	public void addEmpleado(DropEvent event){
		Object object = event.getDragValue();
		if(object.getClass().equals(Empleado.class)){
			Empleado empleado = (Empleado) object;
			ProduccionProducto produccionProducto = (ProduccionProducto) event.getDropValue();
			for (ProduccionProductoDetalle ppd : produccionProducto.getDetalle()) {
				if(ppd.getEmpleado().equals(empleado)){
					return;
				}
			}
			produccionProducto.addDetalle(new ProduccionProductoDetalle(empleado));
		}else{
			ProduccionProductoDetalle ppd = (ProduccionProductoDetalle) object;
			ppd.getProduccionProducto().removeDetalle(ppd);
		}						
	}
	
	public void removeEmpleado(DropEvent event){
		Object object = event.getDragValue();
		ProduccionProductoDetalle ppd = (ProduccionProductoDetalle) object;
		ppd.getProduccionProducto().removeDetalle(ppd);
	}
	
	public List<String> getNodos(){
		List<String> list = new ArrayList<String>();
		list.add(this.p);
		list.add(this.i);
		return list;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getI() {
		return i;
	}
	public void setI(String i) {
		this.i = i;
	}
	
	
	public void buscarReceta(ActionEvent event){
		this.renderBuscador = true;
		WrapperEntity<ProduccionProducto> wrapper = (WrapperEntity<ProduccionProducto>) this.treeDiagramacion.getRowData();
		this.maestro = wrapper.getEntity();
		List<Receta> list = this.business.listRecetas(maestro.getProducto());
		this.gestionRecetaMB.setMaestros(new JSFTable<Receta>(list));
		this.gestionRecetaMB.setDetalles(new JSFTable<RecetaDetalle>());
	}
	
	public void volver(ActionEvent event){
		this.renderBuscador = false;
	}
	
//	public void select(ActionEvent event){
//		this.
//		this.gestionRecetaMB.selectMaestro(event);
//	}
	
	public void selectReceta(ActionEvent event){
		Receta receta = this.gestionRecetaMB.getMaestro();
		receta = this.business.listFeched(receta, "detalle").get(0);
		this.maestro.setReceta(receta);
		this.renderBuscador = false;
	}
	
	
}
