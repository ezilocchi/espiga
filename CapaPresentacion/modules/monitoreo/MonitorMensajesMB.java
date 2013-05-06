package monitoreo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import produccion.Insumo;
import venta.Pedido;
import venta.estados.pedido.EstadoPedido;

public class MonitorMensajesMB {

	@EJB
	private VerificadorMonitor monitor;
	private List<Mensaje> mensajes;	
	
	private boolean stock;
	private boolean pendientesProduccion;
	private boolean pendientesEntrega;
	
	private boolean renderPanel;

	public MonitorMensajesMB() {		
		this.mensajes = new ArrayList<Mensaje>();		
	}	
	
	public boolean isRenderPanel() {
		return renderPanel;
	}
	public void setRenderPanel(boolean renderPanel) {
		this.renderPanel = renderPanel;
	}
	
	public boolean isStock() {
		return stock;
	}

	public boolean isPendientesEntrega() {	
		return pendientesEntrega;
	}

	public boolean isPendientesProduccion() {
		return pendientesProduccion;
	}
	
	public boolean isMensajes(){
		return !(this.stock && this.pendientesEntrega && this.pendientesProduccion);
	}
	
	private void verificarStock(){
		List<Insumo> insumos = this.monitor.checkStock();
		System.out.println(insumos);
		if(insumos.size()>0){
			this.stock = true;
		}else{
			this.stock = false;
		}
		
	}
	
	private void verificarPedidosPendienteProduccion(){
		List<Pedido> list = new ArrayList<Pedido>();		
		List<Pedido> pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.CREADO);
		list.addAll(pedidos);
		pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.PARCIALMENTE_DIAGRAMADO);
		list.addAll(pedidos);
		if (list.size()>0) {
			this.pendientesProduccion = true;
		} else {
			this.pendientesProduccion = false;
		}	
	}
	
	private void verificarPedidosPendienteEntrega(){
		List<Pedido> list = new ArrayList<Pedido>();		
		List<Pedido> pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.PRODUCIDO);
		list.addAll(pedidos);
		pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.PARCIALMENTE_PRODUCIDO);
		list.addAll(pedidos);
		if (list.size()>0) {
			this.pendientesEntrega = true;
		} else {
			this.pendientesEntrega = false;
		}	
	}
	
	public void verMonitor(ActionEvent event){
		System.out.println("entro");
		this.renderPanel = true;
	}
	public void cerrarMonitor(ActionEvent event){
		this.renderPanel = false;
	}
	
	public void verificar(ActionEvent event){
		this.verificarStock();
		this.verificarPedidosPendienteEntrega();
		this.verificarPedidosPendienteProduccion();
	}
	
//	private void addPedidos(Mensaje mensaje, List<Pedido> pedidos){
//		for (Pedido pedido : pedidos) {
//			mensaje.addDetalle("Nro: "+pedido.getId()+", "+pedido.getCliente().getRazonSocial());
//		}		
//	}
	
//	private void verificarStock(){
//		List<Insumo> insumos = this.monitor.checkStock();
//		Mensaje mensaje = new Mensaje();
//		for (Insumo insumo : insumos) {			
//			mensaje.addDetalle(insumo.getNombre());
//		}
//	}
//	
//	private void verificarPedidosPendienteProduccion(){
//		List<Pedido> list = new ArrayList<Pedido>();		
//		List<Pedido> pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.CREADO);
//		list.addAll(pedidos);
//		pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.PARCIALMENTE_DIAGRAMADO);
//		list.addAll(pedidos);
//		Mensaje mensaje = new Mensaje("Pedidos Pendiente de Diagramación","");		
//		this.addPedidos(mensaje, list);
//		this.mensajes.add(mensaje);	
//	}
//	
//	private void verificarPedidosPendienteEntrega(){
//		List<Pedido> list = new ArrayList<Pedido>();		
//		List<Pedido> pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.PRODUCIDO);
//		list.addAll(pedidos);
//		pedidos = this.monitor.checkPedidoPendiente(EstadoPedido.PARCIALMENTE_PRODUCIDO);
//		list.addAll(pedidos);
//		Mensaje mensaje = new Mensaje("Pedidos Pendiente de Producción","");		
//		this.addPedidos(mensaje, list);
//		this.mensajes.add(mensaje);	
//	}
}

