package produccion;

public enum UnidadMedidaInsumo{
	KG("Kg"), 
	GRAMO("Gramos"), 
	UNIDAD("Unidades"),	 
	LITRO("Litros"), 
	CM3("cm3");

	private String nombre;

	private UnidadMedidaInsumo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}	
}
