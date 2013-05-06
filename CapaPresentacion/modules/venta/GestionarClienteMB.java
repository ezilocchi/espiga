package venta;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import utils.JSFSelectEnum;
import utils.JSFTable;
import utils.JSFTableMultiSelects;
import utils.WrapperEntity;
import venta.Cliente.TipoCliente;
import venta.estados.pedido.EstadoPedido;
import base.CondicionIVA;
import base.Recurso;

public class GestionarClienteMB extends SimpleAMBMB<Recurso>{
	
	@EJB
	private ClienteBusiness business;
	private Cliente cliente;
	private JSFTable<Cliente> clientes;
	private Pedido pedido;
	private JSFTable<Pedido> pedidos;
	private JSFTable<PedidoDetalle> detalle;	
	private boolean showPanel;
	private boolean renderPanelCliente;
	
	private Cliente filtroCliente;
	private Pedido filtroPedido;
	
	//Para registrar cliente
	private JSFSelectEnum<CondicionIVA> condicionesIva;
	private JSFSelectEnum<TipoCliente> tiposCliente;
	
	//Para facturar los pedidos
	private JSFTableMultiSelects<Pedido> pedidosFacturar;
	private Factura factura;
	private boolean renderFacturar;
	
	public GestionarClienteMB() {		
		super(NavigationRules.gestionarCliente);
		this.condicionesIva = new JSFSelectEnum<CondicionIVA>(CondicionIVA.values());
		this.tiposCliente = new JSFSelectEnum<TipoCliente>(TipoCliente.values());
	}		
		
	public JSFTable<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(JSFTable<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public boolean isShowPanel() {
		return showPanel;
	}
	public void setShowPanel(boolean showPanel) {
		this.showPanel = showPanel;
	}
	public boolean isRenderPanelCliente() {
		return renderPanelCliente;
	}
	public void setRenderPanelCliente(boolean renderPanelCliente) {
		this.renderPanelCliente = renderPanelCliente;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;		
	}
	public JSFTable<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(JSFTable<Cliente> clientes) {
		this.clientes = clientes;
	}
	public JSFTable<PedidoDetalle> getDetalle() {
		return detalle;
	}
	public JSFSelectEnum<CondicionIVA> getCondicionesIva() {
		return condicionesIva;
	}
	public void setCondicionesIva(JSFSelectEnum<CondicionIVA> condicionesIva) {
		this.condicionesIva = condicionesIva;
	}
	public JSFSelectEnum<TipoCliente> getTiposCliente() {
		return tiposCliente;
	}
	public void setTiposCliente(JSFSelectEnum<TipoCliente> tiposCliente) {
		this.tiposCliente = tiposCliente;
	}	
	public JSFTableMultiSelects<Pedido> getPedidosFacturar() {
		return pedidosFacturar;
	}
	public void setPedidosFacturar(JSFTableMultiSelects<Pedido> pedidosFacturar) {
		this.pedidosFacturar = pedidosFacturar;
	}
	public boolean isRenderFacturar() {
		return renderFacturar;
	}
	public void setRenderFacturar(boolean renderFacturar) {
		this.renderFacturar = renderFacturar;
	}
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Pedido getFiltroPedido() {
		return filtroPedido;
	}
	public void setFiltroPedido(Pedido filtroPedido) {
		this.filtroPedido = filtroPedido;
	}
	public Cliente getFiltroCliente() {
		return filtroCliente;
	}
	public void setFiltroCliente(Cliente filtroCliente) {
		this.filtroCliente = filtroCliente;
	}
	public void setDetalle(JSFTable<PedidoDetalle> detalle) {
		this.detalle = detalle;
	}
	public void setPedido(Pedido pedido){
		this.pedidos.addRow(pedido);
	}
	//********* FIN GETTERS & SETTER **********	
	
	@Override
	public String init()	{		
		return super.init();
	}

	public String registrarCliente() {
		this.cliente = new Cliente();
		this.entity = new Recurso();
		this.cliente.addContacto(entity);
		this.pedidos = new JSFTable<Pedido>();
		super.entities = new JSFTable<Recurso>();
		return NavigationRules.registrarCliente.name();
	}
	public String confirmRegistrarCliente() {		
		this.cliente.setCondicionIVA(this.condicionesIva.getSelectedEnum());
		this.cliente.setTipoCliente(this.tiposCliente.getSelectedEnum());
		try {
			this.business.save(cliente);			
			super.entities = new JSFTable<Recurso>(new ArrayList<Recurso>());
			super.entities.addRow(this.entity);
			return NavigationRules.gestionarCliente.name();
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
			return null;
		}		
	}
	
	public String cancelar(){
		super.setRenderPanel(false);
		return NavigationRules.loginOk.name();
	}
	
	public String buscarCliente() {
		this.clientes = new JSFTable<Cliente>(super.dao.list(new Cliente()));
		this.pedidos = new JSFTable<Pedido>();
		super.entities = new JSFTable<Recurso>();
		return NavigationRules.buscarCliente.name();
	}
	public String gestionarCliente() {
		Pedido pedido = new Pedido();
		pedido.setEstado(EstadoPedido.CREADO);
		this.pedidos = new JSFTable<Pedido>(this.business.getPedidosByCliente(cliente,pedido,false));
		this.entities = new JSFTable<Recurso>(this.business.getRecursosByCliente(cliente));
		this.renderPanelCliente = false;
		return NavigationRules.gestionarCliente.name();
	}
	
	public void showPedido(ActionEvent event){
		this.pedido = this.pedidos.getSelectedRow();
		this.detalle = new JSFTable<PedidoDetalle>(new ArrayList<PedidoDetalle>(this.pedido.getDetalle()));
		this.showPanel = true;
	}
	public void hidePedido(ActionEvent event){
		this.showPanel = false;
	}	
	
	public String deleteCliente(){
		try{
			this.dao.delete(cliente);
			return NavigationRules.buscarCliente.name();
		}catch (Exception e){
			ExceptionHandler.getInstance().handleException(e);
			return null;
		}
	}
	
	public void renderPanelCliente(ActionEvent event){
		this.tiposCliente.setItemSelected(this.cliente.getTipoCliente().getName());
		this.condicionesIva.setItemSelected(this.cliente.getCondicionIVA().getDescripcion());
		this.renderPanelCliente = true;
	}
	
	public void aceptar(ActionEvent event){		
		try {
			this.cliente.setCondicionIVA(this.condicionesIva.getSelectedEnum());
			this.cliente.setTipoCliente(this.tiposCliente.getSelectedEnum());
			super.dao.update(cliente);
			this.renderPanelCliente = false;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}		
	}
	
	public void cancelar(ActionEvent event){
		this.renderPanelCliente = false;
	}
	
	public void cancelarPedido(ActionEvent event){
		Pedido pedido = this.pedidos.getSelectedRow();
		try {
			super.dao.delete(pedido);
			this.pedidos.removeRow(pedido);
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	
	//Facturacion
	public String facturar(){		
		Pedido pedido = new Pedido();
		pedido.setEstado(EstadoPedido.ENTREGADO);
		List<Pedido> list = this.business.getPedidosByCliente(cliente,pedido,true);
		if(list.size() == 0){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay pedidos pendientes de facturación", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		this.pedidosFacturar = new JSFTableMultiSelects<Pedido>(list);
		this.pedidosFacturar.selectAll(true);
		this.factura = new Factura();
		this.factura.addPedidos(list);
		return NavigationRules.facturarPedido.name();
	}
	
	public void selectPedido(ActionEvent event){
		WrapperEntity<Pedido> wrapper = this.pedidosFacturar.getSelectedEntity();
		if(wrapper.isSelected()){
			this.factura.addPedido(wrapper.getEntity());
		}else{
			this.factura.removePedido(wrapper.getEntity());
		}
	}
	
	public String aceptarFacturacion(){
		try {			
			this.business.saveFactura(factura);			
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
			return null;
		}
		return NavigationRules.gestionarCliente.name();
	}
	public String cancelarFacturacion(){
		return NavigationRules.gestionarCliente.name();
	}
	
	public void showPedidoFacturar(ActionEvent event){
		this.pedido = this.pedidosFacturar.getSelectedEntity().getEntity();
		this.detalle = new JSFTable<PedidoDetalle>(new ArrayList<PedidoDetalle>(this.pedido.getDetalle()));
		this.showPanel = true;
	}

	@Override
	protected List<Recurso> buscar() {
		// TODO Auto-generated method stub
		return null;
	}
}
