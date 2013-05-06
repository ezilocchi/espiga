package produccion.estados;

import produccion.DiagramacionProduccion;
import entity.Estado;

public enum EstadoDiagramacion {

	INICIADA("Iniciada",new Iniciada()),
	PRODUCIDA("Producida",new Producida());
	
	private String nombre;
	private Estado<DiagramacionProduccion> estado;
	
	private EstadoDiagramacion(String nombre,Estado<DiagramacionProduccion> estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}
	public Estado<DiagramacionProduccion> getEstado() {
		return estado;
	}
	
}
