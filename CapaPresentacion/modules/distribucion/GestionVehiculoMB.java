package distribucion;

import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import utils.JSFTable;

public class GestionVehiculoMB extends SimpleAMBMB<Vehiculo>{

	public GestionVehiculoMB() {
		super(NavigationRules.gestionarVehiculo);
	}

	@Override
	public String init()
	{
		this.entities = new JSFTable<Vehiculo>(this.dao.list(new Vehiculo()));
		return super.init();
	}
	
}
