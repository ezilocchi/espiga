package stock.estrategias;

public enum EstrategiaGestion {

	SIN_ESTRATEGIA("Sin Estrategia",new SinEstrategia()),
	STOCK_OPTIMO("Manual",new Manual()),	
	MANUAL("Manual",new Manual());
	
	private String nombre;
	private EstrategiaGestionStock estrategia;
	
	private EstrategiaGestion(String nombre, EstrategiaGestionStock estrategia) {
		this.nombre = nombre;
		this.estrategia = estrategia;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EstrategiaGestionStock getEstrategia() {
		return estrategia;
	}
	public void setEstrategia(EstrategiaGestionStock estrategia) {
		this.estrategia = estrategia;
	}	
}
