package venta;

import presentacion.MaestroDetalleABMMB;
import presentacion.NavigationRules;
import utils.JSFSelectEnum;

public class GestionProductoMB extends MaestroDetalleABMMB<TipoProducto, Producto>{
	
	private JSFSelectEnum<UnidadMedidaPoducto> unidades;

	public GestionProductoMB() throws InstantiationException, IllegalAccessException{
		super(NavigationRules.gestionarProductos,"tipo","productos");
	}
	
	public JSFSelectEnum<UnidadMedidaPoducto> getUnidades() {
		return unidades;
	}
	public void setUnidades(JSFSelectEnum<UnidadMedidaPoducto> unidades) {
		this.unidades = unidades;
	}

	@Override
	public String init(){		
		this.unidades = new JSFSelectEnum<UnidadMedidaPoducto>(UnidadMedidaPoducto.values(),this,"detalle.unidadMedida");		
		return super.init();
	}
	
	
}
