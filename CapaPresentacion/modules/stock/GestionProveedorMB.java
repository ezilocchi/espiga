package stock;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import produccion.Insumo;
import utils.JSFSelectItem;
import utils.JSFTable;

public class GestionProveedorMB extends SimpleAMBMB<Proveedor>{
		
	private JSFTable<Insumo> insumos;	
	private Insumo insumo;
	private ManagedBeanActions actionsInsumo;
	
	private JSFSelectItem<Insumo> insumosSelect;
	
	private boolean renderInsumo;
	
	public GestionProveedorMB() {
		super(NavigationRules.gestinarProveedor,"Listado Proveedores");
		super.reportColumns.put("razonSocial", "Razon Social");
		super.reportColumns.put("direccion", "Direccion");		
	}

	public JSFTable<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(JSFTable<Insumo> insumos) {
		this.insumos = insumos;
	}

	public JSFSelectItem<Insumo> getInsumosSelect() {
		return insumosSelect;
	}

	public void setInsumosSelect(JSFSelectItem<Insumo> insumosSelect) {
		this.insumosSelect = insumosSelect;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public ManagedBeanActions getActionsInsumo() {
		return actionsInsumo;
	}

	public void setActionsInsumo(ManagedBeanActions actionsInsumo) {
		this.actionsInsumo = actionsInsumo;
	}

	public boolean isRenderInsumo() {
		return renderInsumo;
	}

	public void setRenderInsumo(boolean renderInsumo) {
		this.renderInsumo = renderInsumo;
	}

	@Override
	protected List<Proveedor> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String init() {
		this.insumos = new JSFTable<Insumo>();
		this.entities = new JSFTable<Proveedor>(this.dao.list(new Proveedor()));
		this.insumosSelect = new JSFSelectItem<Insumo>();
		return super.init();
	}

	@Override
	public void selectEntity(ActionEvent event) {		
		super.selectEntity(event);
		this.entity = this.dao.listFeched(this.entity, "insumos").get(0);
		this.insumos = new JSFTable<Insumo>(this.entity.getInsumos());
		List<Insumo> insumosTotales = super.dao.list(new Insumo());
		
		List<Insumo> list = new ArrayList<Insumo>();
		
		for (Insumo insumo : insumosTotales) {
			if(!this.insumos.getEntities().contains(insumo)){
				list.add(insumo);
			}
		}
		this.insumosSelect = new JSFSelectItem<Insumo>(list, "nombre");
		
		this.actionsInsumo = ManagedBeanActions.NONE;
	}
	
	public void selectInsumo(ActionEvent event){
		this.insumo = this.insumosSelect.getEntitySelected();		
	}
	
	public void selectInsumoList(ActionEvent event){
		this.insumo = this.insumos.getSelectedRow();
		this.actionsInsumo = ManagedBeanActions.SELECTED;
	}
	
	public void acceptInsumo(ActionEvent event) {
		
		this.renderInsumo = false;
		if (this.actionsInsumo == ManagedBeanActions.NEW) {
			try {
				this.entity.addInsumo(insumo);
				this.insumosSelect.remove(insumo.getNombre());
				this.dao.update(entity);				
				this.actionsInsumo = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		} else {
			try {
				this.dao.update(this.insumo);
				this.actionsInsumo = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		}
	}
	
	public void renderInsumo(ActionEvent event) throws InstantiationException, IllegalAccessException{		
		this.renderInsumo = true;
		if(this.actionsInsumo == ManagedBeanActions.NEW){
			this.insumo = new Insumo();			
		}
	}
	
	public void cancelInsumo(ActionEvent event) throws InstantiationException, IllegalAccessException{
		this.renderInsumo = false;
		if(this.actionsInsumo == ManagedBeanActions.NEW){
			this.actionsInsumo = ManagedBeanActions.NONE;
			this.insumo = new Insumo();
		}else{
			this.actionsInsumo = ManagedBeanActions.SELECTED;
		}
	}
	
	public void deleteInsumo(ActionEvent event) throws InstantiationException, IllegalAccessException{
		try {
			this.dao.delete(this.insumo);
			this.insumos.removeRow(this.insumo);
			this.insumo = new Insumo();
			this.actionsInsumo = ManagedBeanActions.NONE;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	public boolean isSelectedInsumo(){
		if(this.actionsInsumo == ManagedBeanActions.SELECTED){
			return true;
		}else{
			return false;
		}
	}
}
