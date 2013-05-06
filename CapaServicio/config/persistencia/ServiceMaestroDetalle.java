package persistencia;

import java.util.List;

import javax.ejb.Local;

import org.hibernate.criterion.DetachedCriteria;

import entity.BaseEntity;

@Local
public interface ServiceMaestroDetalle {

	void save(BaseEntity entity) throws Exception;
	void update(BaseEntity entity) throws Exception;
	void delete(BaseEntity entity) throws Exception;
	<A> A refresh(A entity);
	<A extends BaseEntity> A getById(A entity);
	
	<A> List<A> list(A example);
	<A> List<A> list(A example, String... properties);
	<DETALLE,MAESTRO extends BaseEntity> List<DETALLE> listDetalle(DETALLE example, MAESTRO maestro, String property);
	public <DETALLE,MAESTRO extends BaseEntity> List<DETALLE> listDetalle(DETALLE example, MAESTRO maestro, String property, String... propertyFetched );
	<A> List<A> listNull(A entity, String... property);
	
	<MAESTRO> List<MAESTRO> filtterMaestro(MAESTRO maestro, String... properties);
	<DETALLE> List<DETALLE> filtterDetalle(DETALLE detalle, String... properties);
	
	<A> List<A> listCriteria(DetachedCriteria crit, Class<A> a);
}
