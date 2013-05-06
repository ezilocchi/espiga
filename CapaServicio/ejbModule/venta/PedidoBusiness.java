package venta;

import java.util.List;

import javax.ejb.Local;

import persistencia.ServiceEntity;

@Local
public interface PedidoBusiness extends ServiceEntity{


	Pedido buildNewPedido(Cliente cliente);
	List<TipoProducto> listTipoProducto();
	List<Producto> listProductoByTipo(TipoProducto tipoProducto);
}
