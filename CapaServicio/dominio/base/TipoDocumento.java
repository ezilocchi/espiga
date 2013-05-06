package base;

public enum TipoDocumento {

	DNI("DNI","Documento Nacional de Identidad"),
	LE("LE","Libreta de Enrolamiento"),
	LC("LC","Libreta Civica"),
	CUIL("Cuil",""),
	CUIT("Cuit",""),
	CEDULA_FEDERAL("Cédula Federal del Mercosur",""),
	OTRO("Otro","Otro");
	
	private String nombre;
	private String descripcion;
	
	private TipoDocumento(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {		
		return this.nombre;
	}	
}
