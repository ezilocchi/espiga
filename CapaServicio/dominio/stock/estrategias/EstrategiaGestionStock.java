package stock.estrategias;

import produccion.Insumo;
import stock.SemaforoStock;

public interface EstrategiaGestionStock {

	boolean reponer(Insumo insumo);
	Integer getCantidadReposicion(Insumo insumo);
	SemaforoStock getEstado(Insumo insumo);
	
}
