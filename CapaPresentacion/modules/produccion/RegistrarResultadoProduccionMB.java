package produccion;

import java.util.List;

import integracion.ExceptionHandler;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import presentacion.MaestroDetalleMB;
import presentacion.NavigationRules;
import utils.JSFTable;

public class RegistrarResultadoProduccionMB extends MaestroDetalleMB<ProduccionProducto, ResultadoProductoDetalle>{

	@EJB
	private RegistrarProduccionBusiness business;
	
	private DiagramacionProduccion diagramacion;
	
	private DiagramacionProduccionMB produccionMB;
	
	private List<Insumo> insumos;
	private boolean renderInsumos;
	
	public RegistrarResultadoProduccionMB() throws InstantiationException, IllegalAccessException {
		super(NavigationRules.registrarResultadoProduccion, "produccionProducto","detalleResultado");		
	}

	@Override
	public String init(){
		this.detalles = new JSFTable<ResultadoProductoDetalle>();
		try {
			this.diagramacion = this.business.cerrarDiagramacion(diagramacion);
			super.maestros = new JSFTable<ProduccionProducto>(this.diagramacion.getDetalle());
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}		
		return super.navigationRules.name();
	}

	public DiagramacionProduccion getDiagramacion() {
		return diagramacion;
	}
	public void setDiagramacion(DiagramacionProduccion diagramacion) {
		this.diagramacion = diagramacion;
	}	
	public List<Insumo> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}
	public boolean isRenderInsumos() {
		return renderInsumos;
	}
	public void setRenderInsumos(boolean renderInsumos) {
		this.renderInsumos = renderInsumos;
	}
	public DiagramacionProduccionMB getProduccionMB() {
		return produccionMB;
	}
	public void setProduccionMB(DiagramacionProduccionMB produccionMB) {
		this.produccionMB = produccionMB;
	}

	@Override
	protected void selectMaestro(){
		this.detalles = new JSFTable<ResultadoProductoDetalle>(this.maestro.getDetalleResultado());
	}

	public String aceptar(){
		try {
			this.insumos = this.business.confirmarCierreDiagramacion(diagramacion);
			if(insumos.size()>0){
				this.renderInsumos = true; 
				return null;
			}
			this.produccionMB.getMaestros().removeRow(diagramacion);
			this.produccionMB.setDetalles(new JSFTable<ProduccionProducto>());
			return NavigationRules.listarDiagramacionProduccion.name();
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
			return null;
		}
	}
	public String cancelar(){
		return NavigationRules.listarDiagramacionProduccion.name();
	}
	public void hide(ActionEvent event){
		this.renderInsumos = false;
	}
}
