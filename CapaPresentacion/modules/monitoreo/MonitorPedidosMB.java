package monitoreo;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.CriteriaHelper;
import persistencia.ServiceEntity;
import presentacion.NavigationRules;
import reporting.engine.EntityReportFactory;
import reporting.engine.Report;
import reporting.engine.ReportFactory;
import utils.FechaUtils;
import utils.JSFSelectEnum;
import utils.JSFTable;
import venta.Cliente;
import venta.Pedido;
import venta.PedidoDetalle;
import venta.estados.pedido.EstadoPedido;
import venta.filtros.PedidoFiltro;

public class MonitorPedidosMB {

	@EJB
	private ServiceEntity service;	
	private JSFTable<Pedido> pedidos;
	private JSFTable<PedidoDetalle> detalle;
	private JSFSelectEnum<EstadoPedido> estados;
	private PedidoFiltro filtro;
	private Pedido pedido;
	private boolean renderPanelDetalle;
	
	private Map<String, String> reportColumns;
	private Map<String, String> reportParams;
	
	public MonitorPedidosMB() {
		this.reportColumns = new LinkedHashMap<String, String>();
		this.reportColumns.put("id", "Nro");
		this.reportColumns.put("fechaEntrega", "Entrega");
		this.reportColumns.put("cliente.razonSocial", "Cliente");
		this.reportColumns.put("estado.nombre", "Estado");
		this.reportColumns.put("total", "Total");
		this.reportParams = new HashMap<String, String>();
		this.reportParams.put("TITLE", "Listado Pedidos");
	}

	public String init(){
		this.filtro = new PedidoFiltro(FechaUtils.getFecha(new Date(System.currentTimeMillis())), null);
		this.filtro.setCliente(new Cliente());
		List list = service.listFiltter(this.filtro,"detalle");
		this.pedidos = new JSFTable<Pedido>(list);
		this.estados = new JSFSelectEnum<EstadoPedido>(EstadoPedido.values(), this, "filtro.estado");
		return NavigationRules.monitorearPedidos.name();
	}

	public JSFTable<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(JSFTable<Pedido> pedidos) {
		this.pedidos = pedidos;
	}		
	public PedidoFiltro getFiltro() {
		return filtro;
	}
	public void setFiltro(PedidoFiltro filtro) {
		this.filtro = filtro;
	}
	public JSFTable<PedidoDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(JSFTable<PedidoDetalle> detalle) {
		this.detalle = detalle;
	}
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public boolean isRenderPanelDetalle() {
		return renderPanelDetalle;
	}

	public void setRenderPanelDetalle(boolean renderPanelDetalle) {
		this.renderPanelDetalle = renderPanelDetalle;
	}

	public JSFSelectEnum<EstadoPedido> getEstados() {
		return estados;
	}
	public void setEstados(JSFSelectEnum<EstadoPedido> estados) {
		this.estados = estados;
	}

	public void showPedido(ActionEvent event){
		this.pedido = this.pedidos.getSelectedRow();
		this.detalle = new JSFTable<PedidoDetalle>(this.pedido.getDetalle());
		this.renderPanelDetalle = true;
	}
	
	public void hidePedido(ActionEvent event){
		this.renderPanelDetalle = false;
	}
	
	public void buscar(ActionEvent event){
		
		DetachedCriteria crit = CriteriaHelper.createDetachedCriteria(filtro, "detalle");
			
		if(this.filtro.getId() != null && this.filtro.getId() != 0){
			crit.add(Restrictions.eq("id", filtro.getId()));			
		}
		if(filtro.getCliente().getRazonSocial() != null && !filtro.getCliente().getRazonSocial().isEmpty()){
			crit.createAlias("cliente", "c");
			crit.add(Restrictions.ilike("c.razonSocial", filtro.getCliente().getRazonSocial(), MatchMode.START));			
		}
		List list = service.listCriteria(crit, filtro.getClass());
		this.pedidos = new JSFTable<Pedido>(list);
	}
	
	public void print(ActionEvent event) throws JRException{
		ReportFactory factory = new EntityReportFactory();		
		Report report = factory.buildReport(this.pedidos.getEntities(), this.reportColumns, this.reportParams);
		report.print();
	}
}
