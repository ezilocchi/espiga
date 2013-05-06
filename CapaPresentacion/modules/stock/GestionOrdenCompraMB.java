package stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import persistencia.CriteriaHelper;
import presentacion.MaestroDetalleMB;
import presentacion.NavigationRules;
import stock.estados.EstadoDetalleOrdenCompra;
import stock.estados.EstadoOrdenCompra;
import stock.filtros.OrdenCompraFiltro;

import utils.FechaUtils;
import utils.JSFSelectEnum;
import utils.JSFTable;
import venta.Pedido;


public class GestionOrdenCompraMB extends MaestroDetalleMB<OrdenCompra, OrdenCompraDetalle>{

	@EJB
	private OrdenCompraBusiness business;
	private boolean renderConfirmar;
	private JSFSelectEnum<EstadoOrdenCompra> estados;
	private OrdenCompraFiltro filtroMaestro;
	
	public GestionOrdenCompraMB() {
		super(NavigationRules.gestionarOrdenCompra, "ordenCompra", "detalle");
	}
	@Override
	public String init(){		
		this.filtroMaestro = new OrdenCompraFiltro(FechaUtils.getFecha(new Date(System.currentTimeMillis())), null);
		this.estados = new JSFSelectEnum<EstadoOrdenCompra>(EstadoOrdenCompra.values(), this, "filtroMaestro.estado");
		List list =  this.service.list(new OrdenCompra(),"detalle");
		this.maestros = new JSFTable<OrdenCompra>(list);
		this.detalles = new JSFTable<OrdenCompraDetalle>();
		return NavigationRules.gestionarOrdenCompra.name();
	}

	@Override
	protected void selectMaestro() {		
		super.selectMaestro();
		this.maestro.setDetalle(detalles.getEntities());
	}	

	public boolean isRenderConfirmar() {
		return renderConfirmar;
	}
	public void setRenderConfirmar(boolean renderConfirmar) {
		this.renderConfirmar = renderConfirmar;
	}
	
	public OrdenCompraFiltro getFiltroMaestro() {
		return filtroMaestro;
	}
	public void setFiltroMaestro(OrdenCompraFiltro filtroMaestro) {
		this.filtroMaestro = filtroMaestro;
	}
	public JSFSelectEnum<EstadoOrdenCompra> getEstados() {
		return estados;
	}
	public void setEstados(JSFSelectEnum<EstadoOrdenCompra> estados) {
		this.estados = estados;
	}
	
	public void confirmarIngreso(ActionEvent event){
		List<OrdenCompraDetalle> list = new ArrayList<OrdenCompraDetalle>();
		for(OrdenCompraDetalle detalle : this.maestro.getDetalle()){
			if(detalle.getCantidad()==0){
				list.add(detalle);
			}
		}
//		this.maestro.getDetalle().removeAll(list);
		this.detalles.getEntities().removeAll(list);
		this.renderConfirmar = true;
	}

	public void registrarIngreso(ActionEvent event){		
		this.business.registarIngreso(this.maestro);
		this.renderConfirmar = false;
	}
	
	
	public void hide(ActionEvent event){
		this.renderConfirmar = false;
	}
	
	public EstadoDetalleOrdenCompra getDetalleRecibido(){
		return EstadoDetalleOrdenCompra.RECIBIDA;
	}
	@Override
	protected List<OrdenCompra> buscar() {
		DetachedCriteria crit = CriteriaHelper.createDetachedCriteria(filtroMaestro, "detalle");
		
		if(this.filtroMaestro.getId() != null && this.filtroMaestro.getId() != 0){
			crit.add(Restrictions.eq("id", filtroMaestro.getId()));			
		}
		if(filtroMaestro.getProveedor().getRazonSocial() != null && !filtroMaestro.getProveedor().getRazonSocial().isEmpty()){
			crit.createAlias("proveedor", "p");
			crit.add(Restrictions.ilike("p.razonSocial", filtroMaestro.getProveedor().getRazonSocial(), MatchMode.START));			
		}
		List list = service.listCriteria(crit, filtroMaestro.getClass());
			
		return list;
	}
	
	
}
