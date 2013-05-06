package venta;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import utils.FechaUtils;
import utils.JSFSelectItem;
import utils.JSFTable;
import venta.Cliente;
import venta.Pedido;
import venta.PedidoBusiness;
import venta.PedidoDetalle;
import venta.Producto;
import venta.TipoProducto;

public class GestionarPedidoMB{

	@EJB
	private PedidoBusiness business;
	private JSFTable<PedidoDetalle> detalles;
	private JSFSelectItem<TipoProducto> tipos;
	private JSFSelectItem<Producto> productos; 
	private Pedido pedido;
	private PedidoDetalle detalle;
	private Cliente cliente;
	
	private GestionarClienteMB clienteMB;
	private ManagedBeanActions actions;
	
	public GestionarPedidoMB() {
		this.actions = ManagedBeanActions.NONE;
	}
		
	public JSFTable<PedidoDetalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(JSFTable<PedidoDetalle> detalles) {
		this.detalles = detalles;
	}
	public JSFSelectItem<TipoProducto> getTipos() {
		return tipos;
	}
	public void setTipos(JSFSelectItem<TipoProducto> tipos) {
		this.tipos = tipos;
	}
	public JSFSelectItem<Producto> getProductos() {
		return productos;
	}
	public void setProductos(JSFSelectItem<Producto> productos) {
		this.productos = productos;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public PedidoDetalle getDetalle() {
		return detalle;
	}
	public void setDetalle(PedidoDetalle detalle) {
		this.detalle = detalle;
	}
	public void setClienteMB(GestionarClienteMB clienteMB) {
		this.clienteMB = clienteMB;
	}
	public ManagedBeanActions getActions() {
		return actions;
	}
	public void setActions(ManagedBeanActions actions) {
		this.actions = actions;
	}

	public String registrar(){
		this.init();		
		this.pedido = this.business.buildNewPedido(cliente);
		this.detalles = new JSFTable<PedidoDetalle>(pedido.getDetalle());
		this.pedido.setFechaEntrega(FechaUtils.getManana());
		this.actions = ManagedBeanActions.NEW;
		return NavigationRules.registrarPedido.name();		
	}
	public String modificar(){
		this.init();		
		this.detalles = new JSFTable<PedidoDetalle>(this.pedido.getDetalle());
		this.actions = ManagedBeanActions.UPDATE;
		return NavigationRules.registrarPedido.name();
	}
	
	private void init(){
		this.tipos = new JSFSelectItem<TipoProducto>(this.business.listTipoProducto(),"nombre");
		this.productos = new JSFSelectItem<Producto>();
		this.detalle = new PedidoDetalle();		
	}
	
	
	public void selectTipoProducto(ActionEvent event){		
		List<Producto> list = this.business.listProductoByTipo(this.tipos.getEntitySelected());
		this.productos = new JSFSelectItem<Producto>(list,"nombre");		
	}
	public void selectProducto(ActionEvent event){		
		Producto p = this.productos.getEntitySelected();
		this.detalle.setPrecioVentaUnitario(p.getPrecioLista());
	}
	
	public void agregarDetalle(ActionEvent event){
		this.detalle.setProducto(this.productos.getEntitySelected());
		this.pedido.addDetalle(detalle);		
		this.productos.cleanSelection();
		this.tipos.cleanSelection();
		this.detalle = new PedidoDetalle();
	}
	public void eliminarDetalle(ActionEvent event){
		PedidoDetalle detalle = this.detalles.getSelectedRow();
		this.pedido.removeDetalle(detalle);
		this.detalles.removeRow(detalle);
	}
	
	public void limpiarDetalle(ActionEvent event){
		this.productos.cleanSelection();
		this.tipos.cleanSelection();
		this.detalle = new PedidoDetalle();
	}		
	
	public void limpiarTodo(ActionEvent event){
		this.limpiarDetalle(event);
		this.detalles = new JSFTable<PedidoDetalle>();
		this.pedido.setDetalle(new ArrayList<PedidoDetalle>());
	}		
	
	public String cancelar(){
		return NavigationRules.gestionarCliente.name();
	}
	
	public String confirmar(){
		if (this.actions.equals(ManagedBeanActions.NEW)) {
			try {
				this.business.save(pedido);
				this.clienteMB.setPedido(pedido);				
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
				return null;
			}			
		} else {
			try {
				this.business.update(pedido);
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
				return null;
			}
		}
		return NavigationRules.gestionarCliente.name();
	}	
}
