package venta.estados.pedidoDetalle;

import venta.PedidoDetalle;
import entity.Estado;

public class PendienteDiagramacion implements Estado<PedidoDetalle>{

	@Override
	public void performAction(PedidoDetalle entity) {
		entity.setEstado(EstadoPedidoDetalle.DIAGRAMADO);		
	}	
	
}
