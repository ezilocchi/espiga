package base;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import security.Permiso;
import base.Empleado;

@Stateless
public class EmpleadoBusinessImpl implements EmpleadoBusiness{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Empleado> getEmpleados(Permiso permiso) {
		String q = "select e from Empleado e join e.user u join u.group g join g.roles r " +
				"where r.perfil = :permiso";		
		return em.createQuery(q).setParameter("permiso", permiso).getResultList();		
	}

	@Override
	public List<Empleado> getEmpleados(Permiso[] permiso) {
		// TODO Auto-generated method stub
		return null;
	}

}
