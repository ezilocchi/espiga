package persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ejb3unit.hibernate.Criteria;
import org.ejb3unit.hibernate.Session;
import org.ejb3unit.hibernate.criterion.Example;
import org.hibernate.criterion.DetachedCriteria;

import entity.BaseEntity;



@Stateless
public class DaoBaseEJB3unit implements ServiceEntity{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public <A> List<A> list(A entity) {
		Session session = (Session) em.getDelegate();	    
		return (List<A>) session.createCriteria(entity.getClass()).add(Example.create(entity)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> List<A> listFeched(A entity, String... property) {
		Session session = (Session) em.getDelegate();
	    Criteria c = session.createCriteria(entity.getClass());
	    c.add(Example.create(entity));
	    for (int i = 0; i < property.length; i++) {
	    	//c.setFetchMode(property[i], FetchMode.JOIN);
	    	c.createCriteria(property[i]);
		}	    
	    	    
		return (List<A>) c.list();
	}

	@Override
	public void save(Object entity) {		
		this.em.persist(entity);
	}

	@Override
	public void delete(Object entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <A extends BaseEntity> A getById(A entity) {
		return (A) em.find(entity.getClass(), entity.getId());
	}

	@Override
	public <A> A refresh(A entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object entity) throws Exception {
		em.merge(entity);		
	}

	@Override
	public <A> List<A> listNull(A entity, String... property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> List<A> listFiltter(A entity, String... properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> List<A> listCriteria(DetachedCriteria crit, Class<A> a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
