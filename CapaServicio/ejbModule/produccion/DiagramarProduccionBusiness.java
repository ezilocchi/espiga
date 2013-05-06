package produccion;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import persistencia.ServiceEntity;
import venta.Producto;

@Local
public interface DiagramarProduccionBusiness extends ServiceEntity{

	DiagramacionProduccion nuevaDiagramacion(Date fecha) throws Exception;
	List<Receta> listRecetas(Producto producto);
	void cancelarDiagramacion(DiagramacionProduccion diagramacion);
}
