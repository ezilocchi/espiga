package stock.estrategias;

import produccion.Insumo;
import stock.SemaforoStock;

public class SinEstrategia implements EstrategiaGestionStock {

	@Override
	public Integer getCantidadReposicion(Insumo insumo) {		
		return 0;
	}

	@Override
	public boolean reponer(Insumo insumo) {		
		return false;
	}

	@Override
	public SemaforoStock getEstado(Insumo insumo) {		
		return SemaforoStock.VERDE;
	}

}
