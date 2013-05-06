package venta.estados.pedidoDetalle;

import venta.PedidoDetalle;
import entity.Estado;

public enum EstadoPedidoDetalle {

	
	DIAGRAMADO("Diagramado",new Diagramado()),
	PENDIENTE_DIAGRAMACION("Pendiente Diagramación", new PendienteDiagramacion()),
	PRODUCIDO("Producido",new Producido()),
	PENDIENTE_ENTREGA("Pendiente Entrega",new PendienteEntrega()),
	ENTREGADO("Entregado",new Entregado());
	
	private String nombre;
	private Estado<PedidoDetalle> estado;
	
	EstadoPedidoDetalle(String nombre, Estado<PedidoDetalle> estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}
	public Estado<PedidoDetalle> getEstado() {
		return estado;
	}
	
}
