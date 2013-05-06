package distribucion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.event.ActionEvent;

import presentacion.NavigationRules;
import utils.JSFTable;
import utils.JSFTableMultiSelects;
import utils.WrapperCollection;
import utils.WrapperEntity;
import venta.Pedido;
import venta.estados.pedido.EstadoPedido;

public class RegistrarResultadoDistribucionMB {

	@EJB
	private DiagramarDistribucionBusiness business;
	
	private JSFTableMultiSelects<DiagramacionDistribucionDetalle> detalle;
	private DiagramacionDistribucion diagramacion;
	
	private DiagramacionDistribucionMB distribucionMB;
	
	public String init(){
		List<DiagramacionDistribucionDetalle> list = business.getDiagramacionDetalle(diagramacion);
		this.diagramacion.setDetalle(list);
		this.detalle = new JSFTableMultiSelects<DiagramacionDistribucionDetalle>();
		for (DiagramacionDistribucionDetalle ddd : diagramacion.getDetalle()) {
			WrapperCollection<DiagramacionDistribucionDetalle, Pedido> wc = new WrapperCollection<DiagramacionDistribucionDetalle, Pedido>(ddd);
			for (Pedido pedido : ddd.getPedidos()) {
				wc.addDetail(pedido);
			}
			wc.setSelectedAll(true);
			this.detalle.addWapper(wc);
		}
		return NavigationRules.registrarResultadoDistribucion.name();
	}

	public JSFTableMultiSelects<DiagramacionDistribucionDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(
			JSFTableMultiSelects<DiagramacionDistribucionDetalle> detalle) {
		this.detalle = detalle;
	}
	public DiagramacionDistribucion getDiagramacion() {
		return diagramacion;
	}
	public void setDiagramacion(DiagramacionDistribucion diagramacion) {
		this.diagramacion = diagramacion;
	}	
	public void setDistribucionMB(DiagramacionDistribucionMB distribucionMB) {
		this.distribucionMB = distribucionMB;
	}

	public void selectProducto(ActionEvent event){
		UIComponent comp = event.getComponent();
		HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) comp.getParent();
		WrapperCollection<DiagramacionDistribucionDetalle, Pedido> wc = (WrapperCollection<DiagramacionDistribucionDetalle, Pedido>) this.detalle.getSelectedEntity();
		wc.setSelectedAll(checkbox.isSelected());
	}
	
	public String aceptar(){
		for (WrapperEntity<DiagramacionDistribucionDetalle> detalle : this.detalle.content) {
			WrapperCollection<DiagramacionDistribucionDetalle, Pedido> wc = (WrapperCollection<DiagramacionDistribucionDetalle, Pedido>) detalle;			
			for (WrapperEntity<Pedido> pedido : wc.getDetail()) {
				if(pedido.isSelected()){
					pedido.getEntity().setEstado(EstadoPedido.ENTREGADO);
				}else{
					pedido.getEntity().setEstado(EstadoPedido.NO_ENTREGADO);
				}
			}
		}
		this.business.cerrarDiagramacion(diagramacion);
		this.distribucionMB.getMaestros().removeRow(diagramacion);
		this.distribucionMB.setDetalles(new JSFTable<DiagramacionDistribucionDetalle>());
		return NavigationRules.listarDiagramacionDistribucion.name();
	}
	
	public String cancelar(){		
		return NavigationRules.listarDiagramacionDistribucion.name();
	}
}
