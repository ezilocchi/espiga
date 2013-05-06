package base;

import java.util.List;

import javax.ejb.EJB;

import persistencia.ServiceParametricas;
import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import utils.JSFSelectEnum;
import utils.JSFSelectItem;
import utils.JSFTable;

public class GestionEmpleadoMB extends SimpleAMBMB<Empleado>{
	
	private JSFSelectItem<Cargo> cargos;
	private JSFSelectEnum<TipoDocumento> tiposDocumento;
	
	@EJB
	private ServiceParametricas serviceParametricas;

	public GestionEmpleadoMB() {		
		super(NavigationRules.gestionarEmpleado,"Listado Empleados");
		super.reportColumns.put("apellido", "Apellido");
		super.reportColumns.put("nombre", "Nombre");
		super.reportColumns.put("cargo", "Cargo");
		super.reportColumns.put("dir", "Direccion");		
	}
	
	public JSFSelectItem<Cargo> getCargos() {
		return cargos;
	}
	public void setCargos(JSFSelectItem<Cargo> cargos) {
		this.cargos = cargos;
	}
	public JSFSelectEnum<TipoDocumento> getTiposDocumento() {
		return tiposDocumento;
	}	
	public void setTiposDocumento(JSFSelectEnum<TipoDocumento> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	@Override
	public String init() {
		List<Cargo> list = this.dao.list(new Cargo());
		this.cargos = new JSFSelectItem<Cargo>(list, "nombre", this, "entity.user.group");
		this.tiposDocumento = new JSFSelectEnum<TipoDocumento>(TipoDocumento.values());
		entities = new JSFTable<Empleado>(super.dao.list(new Empleado()));
		return super.init();
	}

	@Override
	protected List<Empleado> buscar() {
		Cargo cargo;
		try {
			cargo = this.cargos.getEntitySelected();
			if(cargo != null){
				this.cargos.cleanSelection();
				return this.serviceParametricas.listDetalle(new Empleado(), cargo, "user.group");
				
			}else{
				return super.buscar();
			}		
		} catch (Exception e) {
			return super.buscar();
		}
	}

	
}
