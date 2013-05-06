package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import utils.ReflectionTool;
import advices.ExceptionHandlerAdvice;
import entity.BaseEntity;
import entity.Filtrable;

@Stateless(name="ServiceEntity")
public class ServiceEntityImpl implements ServiceEntity{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void delete(Object entity) {
		em.remove(em.merge(entity));
	}

	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void save(Object entity) {
		em.persist(entity);
	}

	@Override	
	@Interceptors(ExceptionHandlerAdvice.class)
	public void update(Object entity) {
		em.merge(entity);
	}
	
	@Override
	public <A> A refresh(A entity) {
		em.refresh(entity);
		return entity;
	}
	
	//TODO resolver warning
	@Override	
	public <A extends BaseEntity> A getById(A entity) {
		return (A) em.find(entity.getClass(), entity.getId());
	}

	@Override
	@SuppressWarnings("unchecked")	
	public <A> List<A> list(A entity){	
		Session session = (Session) em.getDelegate();	    
		return (List<A>) session.createCriteria(entity.getClass()).add(Example.create(entity)).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public <A> List<A> listFiltter(A entity, String... properties){	
		List<A> list = CriteriaHelper.createCriteria((Session)em.getDelegate(),entity,properties).list();	    
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")	
	public <A> List<A> listFeched(A entity, String... property){		
	    Session session = (Session) em.getDelegate();
	    Criteria c = session.createCriteria(entity.getClass());
	    c.add(Example.create(entity));
	    for (int i = 0; i < property.length; i++) {	    	
	    	c.setFetchMode(property[i], FetchMode.JOIN);
		}
	    c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<A>) c.list();
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
	
	@Override
	@SuppressWarnings("unchecked")	
	public <A> List<A> listCriteria(DetachedCriteria crit, Class<A> a){
		Session session = (Session)em.getDelegate();		
		List result = crit.getExecutableCriteria(session).list();			    
		return result;
	}
}
