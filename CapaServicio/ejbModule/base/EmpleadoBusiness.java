package base;

import java.util.List;

import javax.ejb.Local;

import security.Permiso;
import base.Empleado;

@Local
public interface EmpleadoBusiness {

	List<Empleado> getEmpleados(Permiso permiso);
	List<Empleado> getEmpleados(Permiso[] permiso);
}
