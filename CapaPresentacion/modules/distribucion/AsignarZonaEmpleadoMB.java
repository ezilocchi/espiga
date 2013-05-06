package distribucion;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import base.Empleado;
import base.EmpleadoBusiness;
import base.Zona;
import presentacion.MaestroDetalleMB;
import presentacion.NavigationRules;
import security.Permiso;
import utils.JSFSelectItems;
import utils.JSFTable;

public class AsignarZonaEmpleadoMB extends MaestroDetalleMB<Empleado, Zona>{

	private JSFSelectItems<Zona> zonas;
	private boolean modificado;
	@EJB
	private EmpleadoBusiness business;
	
	public AsignarZonaEmpleadoMB() throws InstantiationException, IllegalAccessException {
		super(NavigationRules.asignarZonaEmpleado, "no se", "no se");// TODO Ver que propiedad
		this.zonas = new JSFSelectItems<Zona>();		
	}	

	public JSFSelectItems<Zona> getZonas() {
		return zonas;
	}
	public void setZonas(JSFSelectItems<Zona> zonas) {
		this.zonas = zonas;
	}		
	public boolean isModificado() {
		return modificado;
	}
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	@Override
	protected void selectMaestro() {
		this.modificado = false;
		List<Zona> zonaEmpleado = super.service.listDetalle(new Zona(), this.maestro, "empleado");		
		List<Zona> zonaLibre = super.service.listNull(new Zona(), "empleado");
		List<Zona> zona = new ArrayList<Zona>();
		zona.addAll(zonaLibre);
		zona.addAll(zonaEmpleado);
		this.zonas = new JSFSelectItems<Zona>(zona, "nombre");
		if(zonaEmpleado.size()>0){
			String s[] = new String[zonaEmpleado.size()];    		
    		for (int i = 0; i < zonaEmpleado.size(); i++) {				
				s[i] = zonaEmpleado.get(i).getNombre();
			}
    		this.zonas.setItemsSelected(s);
		}
		
	}	
	
	@Override
	public String init() {
		this.maestros = new JSFTable<Empleado>(this.business.getEmpleados(Permiso.DISTRIBUIDOR));		
		return super.navigationRules.name();
	}
	
	public void guardar(ActionEvent event){
		this.maestro.setZonas(new HashSet<Zona>(this.zonas.getEntitiesSelected()));
		try {
			this.service.update(maestro);
			this.modificado = false;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	public void cambio(ActionEvent event){
		this.modificado = true;
	}

	@Override
	protected void filterCondition() {
		// TODO Auto-generated method stub
		super.filterCondition();
	}
	
	
}
