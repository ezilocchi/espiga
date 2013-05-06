package stock.estados;

import stock.OrdenCompra;
import stock.OrdenCompraDetalle;
import entity.Estado;

public class Creada implements Estado<OrdenCompra>{

	@Override
	public void performAction(OrdenCompra entity) {		
		boolean flag = false;
		for (OrdenCompraDetalle detalle : entity.getDetalle()) {
			if (detalle.isPendiente()) {
				flag = true;
				detalle.setEstado(EstadoDetalleOrdenCompra.PENDIENTE);
			} else if(detalle.isCancelado()){
				detalle.setEstado(EstadoDetalleOrdenCompra.NO_RECIBIDA);
			} else{
				detalle.setEstado(EstadoDetalleOrdenCompra.RECIBIDA);
			}
		}
		if(flag){
			entity.setEstado(EstadoOrdenCompra.RECIBIDA_PARCIALMENTE);
		}else{
			entity.setEstado(EstadoOrdenCompra.RECIBIDA);
		}
		
	}

}
