package venta;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import advices.BusinessException;
import advices.ExceptionHandlerAdvice;
import base.Recurso;

@Stateless
public class ClienteBusinessImpl implements ClienteBusiness{

	@PersistenceContext	
	private EntityManager em;	
	
	@Override
	public List<Pedido> getPedidosByCliente(Cliente cliente, Pedido pedido, boolean facturar){
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Pedido.class);	
		c.add(Example.create(pedido));
		c.add(Restrictions.isNull("factura"));
		c.createAlias("cliente", "maestro");
		c.createAlias("detalle", "d", CriteriaSpecification.LEFT_JOIN);		
		c.add(Restrictions.eq("maestro.id", cliente.getId()));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Pedido> list = c.list();
		return list;
		 
	}

	@Override
	public List<Recurso> getRecursosByCliente(Cliente cliente) {
		cliente = em.merge(cliente);
		List<Recurso> list = new ArrayList<Recurso>(cliente.getContactos());
		return list;
	}
	
	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void save(Cliente cliente){
		for (Recurso r : cliente.getContactos()) {
			em.persist(r);
		}		
		em.persist(cliente);
	}

	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void update(Cliente cliente){
		this.em.merge(cliente);
	}
	
	@Override
	@Interceptors(ExceptionHandlerAdvice.class)
	public void saveFactura(Factura factura){
		if(factura.getDetalle().size() == 0){
			throw new BusinessException("La factura debe contener por lo menos un Pedido");
		}
		this.em.persist(factura);
		for (Pedido pedido : factura.getDetalle()) {
			this.em.merge(pedido);
		}	
	}
}
