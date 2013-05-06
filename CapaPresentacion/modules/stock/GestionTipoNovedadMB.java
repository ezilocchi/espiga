package stock;

import java.util.List;

import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import utils.JSFTable;

public class GestionTipoNovedadMB extends SimpleAMBMB<TipoNovedadStock>{
	
	public GestionTipoNovedadMB() {		
		super(NavigationRules.gestionarTipoNovedad,"Listado Novedades");
		super.reportColumns.put("nombre", "Nombre");
		super.reportColumns.put("descripcion", "Descripcion");
	}

	@Override
	protected List<TipoNovedadStock> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String init() {
		this.entities = new JSFTable<TipoNovedadStock>(this.dao.list(new TipoNovedadStock()));
		return super.init();
	}	
	
	
	
}
