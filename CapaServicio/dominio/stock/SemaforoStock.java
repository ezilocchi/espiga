package stock;


public enum SemaforoStock {

	VERDE("Óptimo"),
	AMARILLO("Regular"),
	ROJO("Crítico");
	
	private String nombre;
	
	SemaforoStock(String nombre) {
		this.nombre = nombre;		
	}
	
	public String getNombre() {
		return nombre;
	}	
	
}
