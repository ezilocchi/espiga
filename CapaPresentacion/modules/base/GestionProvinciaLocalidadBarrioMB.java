package base;

import integracion.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;

import persistencia.ServiceParametricas;
import presentacion.MaestroDetalleABMMB;
import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;


public class GestionProvinciaLocalidadBarrioMB extends MaestroDetalleABMMB<Provincia, Localidad>{	
		
	private ManagedBeanActions actionBarrio;	
	private boolean renderPanelBarrio;
	private Barrio barrio;
	//private JSFTable<Barrio> barrios;
	private List<Barrio> barriosList;
	private ExtendedTableDataModel<Barrio> barrios;
	@EJB
	private ServiceParametricas serviceParametricas;

	public GestionProvinciaLocalidadBarrioMB() throws InstantiationException,IllegalAccessException {
		super(NavigationRules.gestionarProvinciaLocalidadBarrio,"provincia","Localidades");
		this.barriosList = new ArrayList<Barrio>();
		this.barrios = this.buildDataTable();
		
	}
	
	public ManagedBeanActions getActionBarrio() {
		return actionBarrio;
	}
	public void setActionBarrio(ManagedBeanActions actionBarrio) {
		this.actionBarrio = actionBarrio;
	}
	public Barrio getBarrio() {
		return barrio;
	}
	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}
		
	public boolean isRenderPanelBarrio() {
		return renderPanelBarrio;
	}
	public void setRenderPanelBarrio(boolean renderPanelBarrio) {
		this.renderPanelBarrio = renderPanelBarrio;
	}
	

	public ExtendedTableDataModel<Barrio> getBarrios() {
		return barrios;
	}

	public void setBarrios(ExtendedTableDataModel<Barrio> barrios) {
		this.barrios = barrios;
	}

	public void selectBarrio(ActionEvent event){
		//this.barrio = this.barrios.getSelectedRow();
	}	
	
	@Override
	public void selectDetalle(ActionEvent event) {
		super.selectDetalle(event);
		this.barriosList = serviceParametricas.listDetalle(new Barrio(), super.detalle, "localidad");
		this.barrios = this.buildDataTable();
		this.barrio = new Barrio();
	}	
		
	@Override
	public void selectMaestro(ActionEvent event) throws InstantiationException, IllegalAccessException {	
		super.selectMaestro(event);
		this.barrios = this.buildDataTable();
		this.barrio = new Barrio();
	}

	public void acceptBarrio(ActionEvent event){
		this.renderPanelBarrio = false;
		if(this.actionBarrio == ManagedBeanActions.NEW){
			try {
				super.service.save(barrio);
				//this.barrios.addRow(barrio);			
				this.actionBarrio = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}			
		}else{
			try {
				service.update(barrio);
				this.actionBarrio = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}			
		}		
	}
	public void cancelBarrio(ActionEvent event){
		this.renderPanelBarrio = false;
		if(this.actionBarrio == ManagedBeanActions.NEW){
			this.actionBarrio = ManagedBeanActions.NONE;
		}else{
			this.actionBarrio = ManagedBeanActions.SELECTED;
			//TODO hacer refresh de la entidad para que quede igual que estaba antes de que se la modificara 
		}
	}
	
	public boolean isBarrioSelected(){
		if(this.barrio != null && this.barrio.getId()!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public void deleteBarrio(ActionEvent event) throws InstantiationException, IllegalAccessException{
		try {
			super.service.delete(barrio);
			//this.barrios.removeRow(barrio);		
			this.barrio = new Barrio();
			this.actionBarrio= ManagedBeanActions.NONE;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}		
	}

	@Override
	protected void deleteDetalle() throws Exception {
		super.deleteDetalle();
		this.barrios = this.buildDataTable();
		this.barrio = new Barrio();
	}

	@Override
	protected void deleteMaestro() throws Exception {
		super.deleteMaestro();
		this.barrios = this.buildDataTable();
		this.barrio = new Barrio();
	}
	
	public void renderPanelBarrio(ActionEvent event){		
		this.renderPanelBarrio = true;
		if(this.actionBarrio == ManagedBeanActions.NEW){
			this.barrio = new Barrio();			
		}
	}
	
	private ExtendedTableDataModel<Barrio> buildDataTable(){
		return new ExtendedTableDataModel<Barrio>(new DataProvider<Barrio>() {
			@Override
			public Barrio getItemByKey(Object key) {
				for (Barrio barrio : barriosList) {
					if(key.equals(getKey(barrio))){
						return barrio;
					}
				}
				return null;
			}

			@Override
			public List<Barrio> getItemsByRange(int from, int to) {
				return barriosList.subList(from, to);
			}

			@Override
			public Object getKey(Barrio barrio) {
				return barrio.getNombre();
			}

			@Override
			public int getRowCount() {				
				return barriosList.size();
			}							
		});
	}
}
