package persistencia;

import java.util.List;

import javax.ejb.Local;

import org.hibernate.criterion.DetachedCriteria;

import entity.BaseEntity;

@Local
public interface ServiceEntity {

	void save(Object entity) throws Exception;
	void update(Object entity) throws Exception;
	void delete(Object entity) throws Exception;
	<A> A refresh(A entity);
	<A extends BaseEntity> A getById(A entity);
	<A> List<A> list(A entity);
	<A> List<A> listFiltter(A entity, String... properties);
	<A> List<A> listFeched(A entity, String... property);
	<A> List<A> listNull(A entity, String... property);
	
	<A> List<A> listCriteria(DetachedCriteria crit, Class<A> a);
}
