package stock.estrategias;

public class EstrategiaManual extends EstrategiaRenderer{

	@Override
	public void render() {
		super.addLine("Stock Máximo", "stockMaximo");		
		super.addLine("Punto Reposición", "stockReposicion");
	}

	
}
