package stock;

import java.util.List;

import javax.ejb.Local;

import produccion.Insumo;

@Local
public interface OrdenCompraBusiness {

	OrdenCompra nuevaOrdenCompra(Proveedor proveedor);
	void save(OrdenCompra compra);
	void update(OrdenCompra orden);
	List<Proveedor> getProveedores(Insumo insumo);
	void registarIngreso(OrdenCompra orden);
}
