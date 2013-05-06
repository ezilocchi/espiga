package stock;

import java.util.List;
import java.util.Map;

import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;

public class GestionUnidadMedidaMB extends SimpleAMBMB<UnidadMedidaInsumoCompra>{

	

	public GestionUnidadMedidaMB(NavigationRules navigationRules, Map<String, String> reportColumns) {
		super(NavigationRules.gestionarUnidadMedida, "Listado Unidades Medida");
		super.reportColumns.put("nombre", "Nombre");
		super.reportColumns.put("unidadMedidaInsumo", "Unidad Insumo");
		super.reportColumns.put("cantidad", "Cantidad");
	}

	@Override
	protected List<UnidadMedidaInsumoCompra> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
