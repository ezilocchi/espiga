package distribucion.estados;

import distribucion.DiagramacionDistribucion;
import entity.Estado;

public enum EstadoDiagramacion {

	INICIADA("Iniciada",new Iniciada()),
	FINALIZADA("Finalizada", new Finalizada());
	
	private String nombre;
	private Estado<DiagramacionDistribucion> estado;
	
	private EstadoDiagramacion(String nombre, Estado<DiagramacionDistribucion> estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}
	public Estado<DiagramacionDistribucion> getEstado() {
		return estado;
	}	
	
}
