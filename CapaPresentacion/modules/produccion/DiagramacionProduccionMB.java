package produccion;

import integracion.ExceptionHandler;

import java.util.List;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;

import presentacion.MaestroDetalleMB;
import presentacion.NavigationRules;
import produccion.estados.EstadoDiagramacion;
import produccion.filtros.DiagramacionProduccionFiltro;
import reporting.engine.Report;
import reporting.engine.ResultadoProduccionFactory;
import utils.JSFSelectEnum;
import utils.JSFTable;


public class DiagramacionProduccionMB extends MaestroDetalleMB<DiagramacionProduccion, ProduccionProducto>{

	@EJB
	private DiagramarProduccionBusiness business;
	private JSFSelectEnum<EstadoDiagramacion> estados;
	
	private DiagramacionProduccionFiltro filtroMaestro;
	
	public DiagramacionProduccionMB() throws InstantiationException, IllegalAccessException {
		super(NavigationRules.listarDiagramacionProduccion, "diagramacion","detalle");
		this.estados = new JSFSelectEnum<EstadoDiagramacion>(EstadoDiagramacion.values(), this, "filtroMaestro.estado");
	}
	
	public JSFSelectEnum<EstadoDiagramacion> getEstados() {
		return estados;
	}
	public void setEstados(JSFSelectEnum<EstadoDiagramacion> estados) {
		this.estados = estados;
	}
	
	
	

	public DiagramacionProduccionFiltro getFiltroMaestro() {
		return filtroMaestro;
	}

	public void setFiltroMaestro(DiagramacionProduccion filtroMaestro) {
		this.filtroMaestro = (DiagramacionProduccionFiltro) filtroMaestro;
	}

	@Override
	protected void filterCondition() {
		this.filtroMaestro = new DiagramacionProduccionFiltro();
		this.filtroMaestro.setEstado(EstadoDiagramacion.INICIADA);		
	}
	
	public void cancelar(ActionEvent event){		
		try {			
			this.business.cancelarDiagramacion(super.maestro);
			this.maestros.removeRow(maestro);
			this.detalles = new JSFTable<ProduccionProducto>();
			this.maestro = null;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	public boolean isFinalizable(){
		
		return this.isMaestroSelected() && this.maestro.getEstado().equals(EstadoDiagramacion.INICIADA);
	}
	
	public void printResultado(ActionEvent event) throws JRException{		
		this.maestro.setDetalle(this.service.listDetalle(new ProduccionProducto(), maestro, "diagramacion", "pedidoDetalles"));
		for (ProduccionProducto detalle : this.maestro.getDetalle()) {
			Receta receta = detalle.getReceta();
			List<RecetaDetalle> rd = this.service.listDetalle(new RecetaDetalle(), receta, "receta", "detalle");
			receta.setDetalle(rd);			
		}
		ResultadoProduccionFactory factory = new ResultadoProduccionFactory();
		List<ProduccionProducto> productos = this.maestro.getDetalle();
		List<Entry<Insumo, Float>> insumos = this.maestro.getTotalInsumosEstimados();		
		Report report = factory.buildReport(productos, insumos);
		report.print();
	}
	
	public EstadoDiagramacion getProducida(){
		return EstadoDiagramacion.PRODUCIDA;
	}
}
