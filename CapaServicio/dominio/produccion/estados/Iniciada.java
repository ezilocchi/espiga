package produccion.estados;

import produccion.DiagramacionProduccion;
import venta.Pedido;
import entity.Estado;

public class Iniciada implements Estado<DiagramacionProduccion>{

	@Override
	public void performAction(DiagramacionProduccion entity) {
		for (Pedido p : entity.getPedidos()) {
			p.actualizarEstado();
		}
		entity.setEstado(EstadoDiagramacion.PRODUCIDA);
				
	}

}
