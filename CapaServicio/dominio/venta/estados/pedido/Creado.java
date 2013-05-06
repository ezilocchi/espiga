package venta.estados.pedido;

import venta.Pedido;
import venta.PedidoDetalle;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import entity.Estado;

public class Creado implements Estado<Pedido>{

	@Override
	public void performAction(Pedido pedido) {
		boolean flag = false;
		for (PedidoDetalle detalle : pedido.getDetalle()) {
			if(detalle.getEstado().equals(EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION)){
				flag = true;
				break;
			}
		}
		if(flag){
			pedido.setEstado(EstadoPedido.PARCIALMENTE_DIAGRAMADO);
		}else{
			pedido.setEstado(EstadoPedido.DIAGRAMADO);			
		}
	}	
	
}
