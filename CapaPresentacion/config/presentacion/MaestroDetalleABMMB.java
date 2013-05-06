package presentacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integracion.ExceptionHandler;

import javax.faces.event.ActionEvent;

import produccion.Receta;

import net.sf.jasperreports.engine.JRException;

import reporting.engine.MaestroDetalleReportFactory;
import reporting.engine.Report;
import reporting.engine.ReportFactory;
import reporting.engine.EntityReportFactory;

import utils.JSFTable;
import utils.ReflectionTool;
import entity.BaseEntity;

//TODO trabajar las excepciones en esta clase, para que las hijas no tengan que renegar al pedo! 
public class MaestroDetalleABMMB<MAESTRO extends BaseEntity, DETALLE extends BaseEntity>
		extends MaestroDetalleMB<MAESTRO, DETALLE> {
	
	private boolean renderPanelMaestro;
	private boolean renderPanelDetalle;

	protected Map<String, String> reportColumnsDetalle;
	private Map<String, String> reportParamsDetalle;

	public MaestroDetalleABMMB(NavigationRules navigationRules,
			String nameMaestro, String nameDetalle) {
		super(navigationRules, nameMaestro, nameDetalle);
		this.renderPanelMaestro = false;
		this.renderPanelDetalle = false;
		this.reportColumnsDetalle = new HashMap<String, String>();
		this.reportParamsDetalle = new HashMap<String, String>();
	}

	/**
	 * 
	 * @param navigationRules Regla de navegacion para poder acceder a este ABM desde el menu principal
	 * @param nameMaestro El nombre de la propiedad maestro en el detalle
	 * @param reportColumnsDetalle Los atributos que se mostraran en la impresion en PDF y el orden de los mismos
	 * @param titleReportDetalle Titulo del reporte que se genera en PDF
	 */
	public MaestroDetalleABMMB(NavigationRules navigationRules,
							String nameMaestro, String nameDetalle,  
							Map<String, String> reportColumnsDetalle) {
		this(navigationRules, nameMaestro, nameDetalle);
		this.reportColumnsDetalle = reportColumnsDetalle;		
	}

	public boolean isRenderPanelMaestro() {
		return renderPanelMaestro;
	}

	public void setRenderPanelMaestro(boolean renderPanelMaestro) {
		this.renderPanelMaestro = renderPanelMaestro;
	}

	public boolean isRenderPanelDetalle() {
		return renderPanelDetalle;
	}

	public void setRenderPanelDetalle(boolean renderPanelDetalle) {
		this.renderPanelDetalle = renderPanelDetalle;
	}

	/* FIN GETTERS SETTERS */

	/**
	 * 
	 * @param event
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void renderPanelMaestro(ActionEvent event)
			throws InstantiationException, IllegalAccessException {
		this.renderMaestro();
		this.renderPanelMaestro = true;
		if (this.actionMaestro == ManagedBeanActions.NEW) {
			this.maestro = this.clazzMaestro.newInstance();
		}
	}

	/**
	 * 
	 * @param event
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void renderPanelDetalle(ActionEvent event)
			throws InstantiationException, IllegalAccessException {
		this.renderPanelDetalle = true;
		if (this.actionDetalle == ManagedBeanActions.NEW) {
			this.detalle = this.clazzDetalle.newInstance();
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void acceptMaestro(ActionEvent event) {
		this.renderPanelMaestro = false;
		if (this.actionMaestro == ManagedBeanActions.NEW) {
			try {
				this.saveMaestro();
				this.maestros.addRow(maestro);
				this.detalles = new JSFTable<DETALLE>();
				this.actionMaestro = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		} else {
			try {
				this.updateMaestro();
				this.actionMaestro = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}

		}
	}

	/**
	 * 
	 * @param event
	 */
	public void acceptDetalle(ActionEvent event) {
		this.renderPanelDetalle = false;
		if (this.actionDetalle == ManagedBeanActions.NEW) {
			try {
				this.saveDetalle();
				this.detalles.addRow(detalle);
				this.actionDetalle = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		} else {
			try {
				this.updateDetalle();
				this.actionDetalle = ManagedBeanActions.SELECTED;
			} catch (Exception e) {
				ExceptionHandler.getInstance().handleException(e);
			}
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void cancelDetalle(ActionEvent event) {
		this.renderPanelDetalle = false;
		if (this.actionDetalle == ManagedBeanActions.NEW) {
			this.actionDetalle = ManagedBeanActions.NONE;
		} else {
			this.actionDetalle = ManagedBeanActions.SELECTED;
			// TODO hacer refresh de la entidad para que quede igual que estaba
			// antes de que se la modificara
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void cancelMaestro(ActionEvent event) {
		this.renderPanelMaestro = false;
		if (this.actionMaestro == ManagedBeanActions.NEW) {
			this.detalles = new JSFTable<DETALLE>();
			this.actionMaestro = ManagedBeanActions.NONE;
		} else {
			this.actionMaestro = ManagedBeanActions.SELECTED;
			// TODO hacer refresh de la entidad para que quede igual que estaba
			// antes de que se la modificara
		}
	}

	/**
	 * 
	 * @param event
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void deleteMaestro(ActionEvent event) throws InstantiationException,
			IllegalAccessException {
		try {
			this.deleteMaestro();
			this.maestros.removeRow(maestro);
			this.detalles = new JSFTable<DETALLE>();
			this.maestro = this.clazzMaestro.newInstance();
			this.actionMaestro = ManagedBeanActions.NONE;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}

	/**
	 * 
	 * @param event
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void deleteDetalle(ActionEvent event) throws InstantiationException,
			IllegalAccessException {
		try {
			this.deleteDetalle();
			this.detalles.removeRow(detalle);
			this.detalle = this.clazzDetalle.newInstance();
			this.actionDetalle = ManagedBeanActions.NONE;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDetalleSelected() {
		if (this.detalle != null && this.detalle.getId() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void selectDetalle(ActionEvent event) {
		this.detalle = this.detalles.getSelectedRow();
		this.actionDetalle = ManagedBeanActions.SELECTED;
	}

	/**
	 * @throws Exception
	 * 
	 */
	protected void saveMaestro() throws Exception {
		this.service.save(maestro);
	}

	/**
	 * @throws Exception
	 * 
	 */
	protected void updateMaestro() throws Exception {
		this.service.update(maestro);
	}

	/**
	 * @throws Exception
	 * 
	 */
	protected void deleteMaestro() throws Exception {
		this.service.delete(maestro);
	}

	/**
	 * @throws Exception
	 * 
	 */
	protected void saveDetalle() throws Exception {
		ReflectionTool.setValue(detalle, nameMaestro, maestro);
		this.service.save(detalle);
	}

	/**
	 * @throws Exception
	 * 
	 */
	protected void updateDetalle() throws Exception {
		this.service.update(detalle);
	}

	/**
	 * @throws Exception
	 * 
	 */
	protected void deleteDetalle() throws Exception {
		this.service.delete(detalle);
	}

	protected void renderMaestro() {

	}

	public void printDetalle(ActionEvent event) throws JRException {
		ReportFactory factory = new EntityReportFactory();
		this.reportParamsDetalle.put("TITLE", this.maestro.toString());
		Report report = factory.buildReport(this.detalles.getEntities(), this.reportColumnsDetalle, this.reportParamsDetalle);
		report.print();
	}
	public void printMaestro(ActionEvent event){
		MaestroDetalleReportFactory reportFactory = new MaestroDetalleReportFactory();
		reportFactory.addParam("TITLE", "Listado");
		Report report = null;
		List<Receta> list = super.service.list(new Receta(), "detalle");
		
		try {
//			report = reportFactory.buildReport(list, "nombre", new String[]{"cantidad"}, super.nameDetalle);
			report = reportFactory.buildReport(list, "nombre", new String[]{"cantidad"}, super.nameDetalle);
			report.print();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
