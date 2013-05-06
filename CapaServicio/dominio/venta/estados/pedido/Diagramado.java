package venta.estados.pedido;

import venta.Pedido;
import venta.PedidoDetalle;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import entity.Estado;

public class Diagramado implements Estado<Pedido>{

	@Override
	public void performAction(Pedido entity) {
		entity.setEstado(EstadoPedido.PRODUCIDO);
		for (PedidoDetalle detalle : entity.getDetalle()) {
			detalle.setEstado(EstadoPedidoDetalle.PRODUCIDO);
		}
	}

}
