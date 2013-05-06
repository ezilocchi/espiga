package monitoreo;

import java.util.List;

import javax.ejb.Local;

import produccion.Insumo;
import venta.Pedido;
import venta.estados.pedido.EstadoPedido;

@Local
public interface VerificadorMonitor {

	List<Insumo> checkStock();
	List<Pedido> checkPedidoPendiente(EstadoPedido estado);
}
