package stock;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Example;

import produccion.Insumo;

@Stateless
public class GestionStockBusinessImpl implements GestionStockBusiness{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Insumo> list(Insumo example) {
		Session session = (Session) em.getDelegate();	    
		return session.createCriteria(example.getClass()).add(Example.create(example)).list();
	}
}
