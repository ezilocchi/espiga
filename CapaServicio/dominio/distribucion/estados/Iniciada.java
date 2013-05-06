package distribucion.estados;

import distribucion.DiagramacionDistribucion;
import entity.Estado;

public class Iniciada implements Estado<DiagramacionDistribucion>{

	@Override
	public void performAction(DiagramacionDistribucion entity) {
		entity.setEstado(EstadoDiagramacion.FINALIZADA);
		//TODO verificar si se contemplara un estado para distinguir entre finalizada con todos los pedidos entregados y con algunos pedidos entregados		
//		for (DiagramacionDistribucionDetalle dd : entity.getDetalle()) {
//			for (Pedido pedido : dd.getPedidos()) {
//				if(pedido.getEstado() == EstadoPedido.PENDIENTE_ENTREGA){
//					
//				}
//			}
//		}				
	}
}
