package venta;

public enum UnidadMedidaPoducto{
	KG("Kg"),	 
	UNIDAD("Unidades"), 
	DOCENA("Docenas"), 
	DECENA("Decenas");	

	private String nombre;

	private UnidadMedidaPoducto(String nombre) {
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
