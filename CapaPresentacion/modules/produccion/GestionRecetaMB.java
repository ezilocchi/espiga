package produccion;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import persistencia.ServiceParametricas;
import presentacion.MaestroDetalleABMMB;
import presentacion.ManagedBeanActions;
import presentacion.NavigationRules;
import utils.JSFSelectItem;
import utils.JSFTable;
import venta.Producto;
import venta.RecetaBusiness;
import venta.TipoProducto;

public class GestionRecetaMB extends MaestroDetalleABMMB<Receta, RecetaDetalle>{

	@EJB
	private ServiceParametricas serviceParametricas;
	@EJB
	private RecetaBusiness business;
	
	private JSFSelectItem<TipoProducto> tipos; 
	private JSFSelectItem<Producto> productos;
	private JSFSelectItem<Insumo> insumos;

	public GestionRecetaMB() throws InstantiationException, IllegalAccessException {
		super(NavigationRules.gestionarRecetas,"receta","detalle");
		super.reportColumnsDetalle.put("insumo.nombre", "Insumo");
		super.reportColumnsDetalle.put("cantidad", "Cantidad");
	}
	
	@Override
	public String init() {
		this.tipos = new JSFSelectItem<TipoProducto>(super.service.list(new TipoProducto()),"nombre");
		this.productos = new JSFSelectItem<Producto>();		
		this.insumos = new JSFSelectItem<Insumo>(super.service.list(new Insumo()),"nombre",this,"detalle.insumo");		
		return super.navigationRules.name();
	}

	public JSFSelectItem<Producto> getProductos() {
		return productos;
	}
	public void setProductos(JSFSelectItem<Producto> productos) {
		this.productos = productos;
	}
	public JSFSelectItem<Insumo> getInsumos() {
		return insumos;
	}
	public void setInsumos(JSFSelectItem<Insumo> insumos) {
		this.insumos = insumos;
	}
	public JSFSelectItem<TipoProducto> getTipos() {
		return tipos;
	}
	public void setTipos(JSFSelectItem<TipoProducto> tipos) {
		this.tipos = tipos;
	}	
	
	public void selectTipoProducto(ActionEvent event){
		TipoProducto tipo = this.tipos.getEntitySelected();
		List<Producto> list = serviceParametricas.listDetalle(new Producto(), tipo, "tipo");
		this.productos = new JSFSelectItem<Producto>(list,"nombre",this,"maestro.producto");
	}

	@Override
	protected void saveMaestro() throws Exception{		
		this.maestro.setFechaAlta(new Date(System.currentTimeMillis()));
		this.maestro.setProducto(this.productos.getEntitySelected());
		this.business.save(this.maestro,this.maestros.getEntities());		
	}
	
	@Override
	protected void updateMaestro() throws Exception {
		this.business.update(maestro, this.maestros.getEntities());
	}

	@Override
	protected void renderMaestro() {
		if(super.actionMaestro == ManagedBeanActions.UPDATE){
			this.tipos.setItemSelected(super.maestro.getProducto().getTipo().getNombre());
		}else{
			this.tipos.cleanSelection();
		}
	}
	
	public void selectProducto(ActionEvent event){
		Producto producto = this.productos.getEntitySelected();
		List<Receta> list = this.serviceParametricas.listDetalle(new Receta(), producto, "producto");
		this.maestros = new JSFTable<Receta>(list);
		this.detalles = new JSFTable<RecetaDetalle>();
		
	}
	
	public void selectPredeterminada(ActionEvent actionEvent){
		Receta receta = this.maestros.getSelectedRow();
		System.out.println(receta.getPredeterminada());
	}
	
	
}
