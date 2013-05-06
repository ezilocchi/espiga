package produccion;

import java.util.List;

import javax.ejb.Local;

@Local
public interface RegistrarProduccionBusiness {

	DiagramacionProduccion cerrarDiagramacion(DiagramacionProduccion diagramacion) throws Exception;
	List<Insumo> confirmarCierreDiagramacion(DiagramacionProduccion diagramacion) throws Exception;
}
