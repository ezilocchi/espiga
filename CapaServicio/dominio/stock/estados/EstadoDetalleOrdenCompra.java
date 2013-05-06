package stock.estados;

import stock.OrdenCompraDetalle;
import entity.Estado;

public enum EstadoDetalleOrdenCompra {
	
	CREADO("Creado",new DetalleCreado()),
	PENDIENTE("Pendiente",new DetallePendiente()),
	RECIBIDA("Recibida",new DetalleRecibido()),
	NO_RECIBIDA("Recibida",new DetalleNoRecibido());
	
	private String nombre;
	private Estado<OrdenCompraDetalle> estado;
	
	private EstadoDetalleOrdenCompra(String nombre, Estado<OrdenCompraDetalle> estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Estado<OrdenCompraDetalle> getEstado() {
		return estado;
	}

	public void setEstado(Estado<OrdenCompraDetalle> estado) {
		this.estado = estado;
	}
}
