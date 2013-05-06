package base;

import integracion.ExceptionHandler;

import java.util.List;

import javax.faces.event.ActionEvent;

import presentacion.MaestroDetalleMB;
import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import security.Rol;
import utils.JSFSelectItems;


public class GestionCargoMB extends MaestroDetalleMB<Cargo,Rol> {
	
	private boolean renderPanel;
	private boolean modificado;
	private JSFSelectItems<Rol> roles;

	public GestionCargoMB() {
		super(NavigationRules.gestionarCargo, "roles","");//TODO REVEER!!!
		this.roles = new JSFSelectItems<Rol>();
	}

	public boolean isRenderPanel() {
		return renderPanel;
	}
	public void setRenderPanel(boolean renderPanel) {
		this.renderPanel = renderPanel;
	}	
	public JSFSelectItems<Rol> getRoles() {
		return roles;
	}
	public void setRoles(JSFSelectItems<Rol> roles) {
		this.roles = roles;
	}
	public boolean isModificado() {
		return modificado;
	}
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public void renderPanel(ActionEvent event) {		
		this.renderPanel = true;
		if(this.actionMaestro == ManagedBeanActions.NEW){
			this.maestro = new Cargo();			
		}
	}
	
	public void acceptMaestro(ActionEvent event){
		this.renderPanel= false;
		if(this.actionMaestro == ManagedBeanActions.NEW){
			try {
				super.service.save(maestro);
				this.maestros.addRow(maestro);
				this.roles = new JSFSelectItems<Rol>();
				this.actionMaestro = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}			
		}else{
			try {
				super.service.update(maestro);
				this.actionMaestro = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
			
		}
	}
	public void cancelMaestro(ActionEvent event){
		this.renderPanel = false;
		if(this.actionMaestro == ManagedBeanActions.NEW){
			this.roles = new JSFSelectItems<Rol>();
			this.actionMaestro = ManagedBeanActions.NONE;
		}else{
			this.actionMaestro = ManagedBeanActions.SELECTED;
			//TODO hacer refresh de la entidad para que quede igual que estaba antes de que se la modificara
		}
	}
	
	public void deleteMaestro(ActionEvent event){
		try {
			this.maestros.removeRow(maestro);
			this.roles = new JSFSelectItems<Rol>();
			this.maestro = new Cargo();
			this.actionMaestro = ManagedBeanActions.NONE;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}		
	}

	@Override
	protected void selectMaestro() {
		this.modificado = false;
		this.maestro = super.service.list(this.maestro, "roles").get(0);		
		
		List<Rol> rolesEmpleado = this.maestro.getRoles();
		List<Rol> roles = super.service.list(new Rol());
		
		this.roles = new JSFSelectItems<Rol>(roles, "nombre");
		if(rolesEmpleado.size()>0){
			String s[] = new String[rolesEmpleado.size()];    		
    		for (int i = 0; i < rolesEmpleado.size(); i++) {				
				s[i] = rolesEmpleado.get(i).getNombre();
			}
    		this.roles.setItemsSelected(s);
		}
	}
	
	public void guardar(ActionEvent event){
		this.maestro.setRoles(this.roles.getEntitiesSelected());
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
}
