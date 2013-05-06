package persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import advices.ExceptionHandlerAdvice;
import entity.BaseEntity;

@Stateless(name="ServiceMaestroDetalle")
public class ServiceMaestroDetalleImpl implements ServiceMaestroDetalle{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void delete(BaseEntity entity) {
		em.remove(em.merge(entity));
	}
	
	@Override
	public <A> A refresh(A entity) {
		em.refresh(entity);
		return entity;
	}

	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void save(BaseEntity entity) {
		em.persist(entity);
	}

	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void update(BaseEntity entity) {
		em.merge(entity);		
	}

	@Override
	public <A extends BaseEntity> A getById(A entity) {
		return (A) em.find(entity.getClass(), entity.getId());
	}

	@Override
	public <A> List<A> list(A example) {
		Session session = (Session) em.getDelegate();	    
		return (List<A>) session.createCriteria(example.getClass()).add(Example.create(example)).list();
	}

	@Override
	public <A> List<A> list(A example, String... property) {
		Session session = (Session) em.getDelegate();
	    Criteria c = session.createCriteria(example.getClass());
	    c.add(Example.create(example));
	    for (int i = 0; i < property.length; i++) {	  
	    	c.setFetchMode(property[i], FetchMode.JOIN);
		}	    
	    c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<A>) c.list();
	}	
	
	@Override
	public <DETALLE,MAESTRO extends BaseEntity> List<DETALLE> listDetalle(DETALLE example, MAESTRO maestro, String property){
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(example.getClass());
		c.createAlias(property, "maestro");
		c.add(Restrictions.eq("maestro.id", maestro.getId()));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public <A> List<A> listNull(A entity, String... property){	
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(entity.getClass());
		c.add(Example.create(entity));
		for (int i = 0; i < property.length; i++) {
			c.add(Restrictions.isNull(property[i]));
		}    
		return (List<A>)c.list(); 
	}
	
	public <DETALLE,MAESTRO extends BaseEntity> List<DETALLE> listDetalle(DETALLE example, MAESTRO maestro, String property, String... propertyFetched ){
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(example.getClass());
		c.createAlias(property, "maestro");
		c.add(Restrictions.eq("maestro.id", maestro.getId()));
		for (int i = 0; i < propertyFetched.length; i++) {
			c.setFetchMode(propertyFetched[i], FetchMode.JOIN);
		}
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public <MAESTRO> List<MAESTRO> filtterMaestro(MAESTRO maestro, String... properties){	
		List<MAESTRO> list = CriteriaHelper.createCriteria((Session)em.getDelegate(),maestro,properties).list();	    
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public <DETALLE> List<DETALLE> filtterDetalle(DETALLE detalle, String... properties){	
		List<DETALLE> list = CriteriaHelper.createCriteria((Session)em.getDelegate(),detalle,properties).list();	    
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public <A> List<A> listCriteria(DetachedCriteria crit, Class<A> a){
		Session session = (Session)em.getDelegate();		
		List result = crit.getExecutableCriteria(session).list();			    
		return result;
	}
}
