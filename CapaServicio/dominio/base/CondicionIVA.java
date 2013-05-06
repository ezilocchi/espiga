package base;

public enum CondicionIVA {
	
	RESPONSABLE_INSCRIPTO("Responsable Inscripto"), 
	CONSUMIDOR_FINAL("Consumidor Final"), 
	RESPONSABLE_NO_INSCRIPTO("Responsable No Inscripto"), 
	EXENTO("Exento"), 
	MONOTRIBUTO("Monotributo"), 
	NO_RESPONSABLE("No Responsable");
	
	private String descripcion;

	CondicionIVA(String descripcion) {		
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return descripcion;
	}
}
