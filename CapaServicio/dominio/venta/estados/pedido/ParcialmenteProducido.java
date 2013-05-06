package venta.estados.pedido;

import venta.Pedido;
import venta.PedidoDetalle;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import entity.Estado;

public class ParcialmenteProducido implements Estado<Pedido>{

	@Override
	public void performAction(Pedido entity) {
		boolean flag = false;
		for (PedidoDetalle detalle : entity.getDetalle()) {
			if(detalle.getEstado().equals(EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION)){
				flag = true;
			}else if(detalle.getEstado().equals(EstadoPedidoDetalle.DIAGRAMADO)){
				detalle.setEstado(EstadoPedidoDetalle.PRODUCIDO);
			}
		}
		if(!flag){
			entity.setEstado(EstadoPedido.PRODUCIDO);
		}		
	}

}
