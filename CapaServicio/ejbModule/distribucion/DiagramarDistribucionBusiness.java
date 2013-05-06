package distribucion;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import venta.Pedido;
import venta.PedidoDetalle;

import base.Empleado;

@Local
public interface DiagramarDistribucionBusiness {

	DiagramacionDistribucion nuevaDiagramacion(Date fecha);	
	void save(DiagramacionDistribucion diagramacion);
	List<Empleado> listEmleado(Empleado example);
	List<Vehiculo> listVehiculo(Vehiculo example);
	List<DiagramacionDistribucionDetalle> getDiagramacionDetalle(DiagramacionDistribucion diagramacionDistribucion);
	void cerrarDiagramacion(DiagramacionDistribucion diagramacion);
	void cancelar(DiagramacionDistribucion diagramacion);
	void nuevoPedido(Pedido pedido, List<PedidoDetalle> detalle);
	void updatePedido(Pedido pedido, List<PedidoDetalle> detalles);
}
