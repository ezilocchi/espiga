package stock.estados;

import stock.OrdenCompra;
import entity.Estado;

public enum EstadoOrdenCompra {

	CREADA("Creada",new Creada()),
	RECIBIDA_PARCIALMENTE("Recibida Parcialmente",new RecibidaParcialmente()),
	RECIBIDA("Recibida",new Recibida());
	
	private String nombre;
	private Estado<OrdenCompra> estado;
	
	private EstadoOrdenCompra(String nombre, Estado<OrdenCompra> estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Estado<OrdenCompra> getEstado() {
		return estado;
	}

	public void setEstado(Estado<OrdenCompra> estado) {
		this.estado = estado;
	}
}
