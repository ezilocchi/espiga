package base;

import java.util.List;

import javax.ejb.EJB;

import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import security.Permiso;
import utils.JSFSelectItem;
import utils.JSFTable;


public class GestionZonaMB extends SimpleAMBMB<Zona> {
	
	private JSFSelectItem<Empleado> empleados;
	private List<Empleado> empleadosList;
	@EJB
	private EmpleadoBusiness business;

	public GestionZonaMB() {
		super(NavigationRules.gestionarZona,"Listado Zonas");
		super.reportColumns.put("nombre", "Nombre");
	}
	
	@Override
	public String init() {
		this.empleadosList = business.getEmpleados(Permiso.DISTRIBUIDOR);
		entities = new JSFTable<Zona>(super.dao.list(new Zona()));
		this.empleados = new JSFSelectItem<Empleado>(this.empleadosList,"nombres",this,"entity.empleado");
		return super.init();
	}

	public JSFSelectItem<Empleado> getEmpleados()
	{
		return empleados;
	}
	public void setEmpleados(JSFSelectItem<Empleado> empleados)
	{
		this.empleados = empleados;
	}	
	
	
}
