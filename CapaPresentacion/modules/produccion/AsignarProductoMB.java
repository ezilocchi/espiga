package produccion;

import integracion.ExceptionHandler;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import presentacion.MaestroDetalleMB;
import presentacion.NavigationRules;
import security.Permiso;
import utils.JSFSelectItems;
import utils.JSFTable;
import venta.Producto;
import base.Empleado;
import base.EmpleadoBusiness;
import base.Zona;

public class AsignarProductoMB extends MaestroDetalleMB<Empleado, Zona>{

	private JSFSelectItems<Producto> productos;
	private List<Producto> productosList;
	private boolean modificado;
	@EJB
	private EmpleadoBusiness business;
	
	public AsignarProductoMB() throws InstantiationException, IllegalAccessException {
		super(NavigationRules.asignarProductoEmpleado, "no se", "no se");// TODO Ver que propiedad
		this.productos = new JSFSelectItems<Producto>();		
	}	
	
	public JSFSelectItems<Producto> getProductos() {
		return productos;
	}
	public void setProductos(JSFSelectItems<Producto> productos) {
		this.productos = productos;
	}
	public boolean isModificado() {
		return modificado;
	}
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	@Override
	protected void selectMaestro() {
		this.modificado = false;
		List<Producto> productosEmpleado = super.service.list(this.maestro, "productos").get(0).getProductos();
		this.productos = new JSFSelectItems<Producto>(this.productosList, "nombre");
		if(productosEmpleado.size()>0){
			String s[] = new String[productosEmpleado.size()];    		
    		for (int i = 0; i < productosEmpleado.size(); i++) {				
				s[i] = productosEmpleado.get(i).getNombre();
			}
    		this.productos.setItemsSelected(s);
		}		
	}	
	
	@Override
	public String init() {
		this.maestros = new JSFTable<Empleado>(this.business.getEmpleados(Permiso.PANADERO));
		this.productosList = super.service.list(new Producto());
		return super.navigationRules.name();
	}
	
	public void guardar(ActionEvent event){
		this.maestro.setProductos(productos.getEntitiesSelected());
		try {
			this.service.update(maestro);
			this.modificado = false;
		} catch (Exception e) {
			ExceptionHandler.getInstance().handleException(e);
		}
	}
	
	public void cambio(ActionEvent event){
		this.modificado = true;
	}

	@Override
	protected void filterCondition() {
		// TODO Auto-generated method stub
		super.filterCondition();
	}
	
	
}
