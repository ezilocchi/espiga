package persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.BaseEntity;

@Stateless
public class ServiceParametricasImpl implements ServiceParametricas{
	
	@PersistenceContext
	private EntityManager em;

	public <DETALLE,MAESTRO extends BaseEntity> List<DETALLE> listDetalle(DETALLE example, MAESTRO maestro, String property){
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(example.getClass());
		c.createAlias(property, "maestro");
		c.add(Restrictions.eq("maestro.id", maestro.getId()));
		return c.list();
	}
}
