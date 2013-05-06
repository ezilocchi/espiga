package distribucion;

import integracion.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;
import presentacion.MaestroDetalleMB;
import presentacion.NavigationRules;
import reporting.engine.HojaRutaFactory;
import reporting.engine.RemitoFactory;
import reporting.engine.Report;
import reporting.engine.ReportFactory;
import utils.JSFSelectEnum;
import utils.JSFTable;
import venta.Pedido;
import venta.PedidoDetalle;
import distribucion.estados.EstadoDiagramacion;
import distribucion.filtros.DiagramacionDistribucionFiltro;


public class DiagramacionDistribucionMB extends MaestroDetalleMB<DiagramacionDistribucion, DiagramacionDistribucionDetalle>{
	
	@EJB
	private DiagramarDistribucionBusiness business;
	private JSFSelectEnum<EstadoDiagramacion> estados;
	private DiagramacionDistribucionFiltro filtroMaestro;

	public DiagramacionDistribucionMB() throws InstantiationException, IllegalAccessException {
		super(NavigationRules.listarDiagramacionDistribucion,"diagramacion","detalle");
		this.estados = new JSFSelectEnum<EstadoDiagramacion>(EstadoDiagramacion.values(), this, "filtroMaestro.estado");
	}

	
	@Override
	protected void filterCondition() {		
		this.filtroMaestro = new DiagramacionDistribucionFiltro();		
		this.filtroMaestro.setEstado(EstadoDiagramacion.INICIADA);
	}
	
	public void setDiagramacion(DiagramacionDistribucion diagramacionDistribucion){
		this.maestros.addRow(diagramacionDistribucion);
	}

	@Override
	public DiagramacionDistribucionFiltro getFiltroMaestro() {
		return filtroMaestro;
	}
	@Override
	public void setFiltroMaestro(DiagramacionDistribucion filtroMaestro) {
		this.filtroMaestro = (DiagramacionDistribucionFiltro) filtroMaestro;
	}


	public JSFSelectEnum<EstadoDiagramacion> getEstados() {
		return estados;
	}
	public void setEstados(JSFSelectEnum<EstadoDiagramacion> estados) {
		this.estados = estados;
	}
	
	@Override
	protected void selectMaestro(){
		List<DiagramacionDistribucionDetalle> list = this.service.listDetalle(new DiagramacionDistribucionDetalle(), maestro, "diagramacion", "empleado","pedidos");		
		this.detalles = new JSFTable<DiagramacionDistribucionDetalle>(list);
	}
	
	public void cancelar(ActionEvent event){
		try {			
			this.business.cancelar(super.maestro);
			this.maestros.removeRow(maestro);
			this.detalles = new JSFTable<DiagramacionDistribucionDetalle>();
			this.maestro = null;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	public void printRemitos(ActionEvent event) throws JRException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "Nro");
		List<DiagramacionDistribucionDetalle> list = super.service.listDetalle(new DiagramacionDistribucionDetalle(), maestro, "diagramacion", "pedidos");
		for (DiagramacionDistribucionDetalle detalle : list) {
			for (Pedido pedido : detalle.getPedidos()) {
				pedido.setDetalle(super.service.listDetalle(new PedidoDetalle(), pedido, "pedido"));
			}
		}
		ReportFactory factory = new RemitoFactory();		
		Report report = factory.buildReport(list, map);
		report.print();
	}
	
	public void printHojaRuta(ActionEvent event) throws JRException{
		Map<String, String> map = new HashMap<String, String>();		
		List<DiagramacionDistribucionDetalle> list = super.service.listDetalle(new DiagramacionDistribucionDetalle(), maestro, "diagramacion", "pedidos");
		for (DiagramacionDistribucionDetalle detalle : list) {
			for (Pedido pedido : detalle.getPedidos()) {
				pedido.setDetalle(super.service.listDetalle(new PedidoDetalle(), pedido, "pedido"));
			}
		}
		ReportFactory factory = new HojaRutaFactory();		
		Report report = factory.buildReport(list, map);
		report.print();
	}
	
	public boolean isFinalizable(){		
		return this.isMaestroSelected() && this.maestro.getEstado().equals(EstadoDiagramacion.INICIADA);
	}
}
