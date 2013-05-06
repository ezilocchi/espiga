package venta.estados.pedido;

import entity.Estado;
import venta.Pedido;

public enum EstadoPedido {

	CREADO("Creado",new Creado()),
	PARCIALMENTE_DIAGRAMADO("Parcialmente Diagramado",new ParcialmenteDiagramado()),
	DIAGRAMADO("Diagramado",new Diagramado()),
	PRODUCIDO("Producido",new Producido()),
	PARCIALMENTE_PRODUCIDO("Parcialmente Producido",new ParcialmenteProducido()),
	PENDIENTE_ENTREGA("Pendiente Entrega",new PendienteEntrega()),
	ENTREGADO("Entregado",new Entregado()),
	NO_ENTREGADO("No Entregado",new NoEntregado()),
	CANCELADO("Cancelado",new Cancelado()),
	ANULADO("Anulado",new Anulado());		
	
	private String nombre;		
	private Estado<Pedido> estado;
	
	EstadoPedido(String nombre, Estado<Pedido> estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}
	public Estado<Pedido> getEstado() {
		return estado;
	}	
}
