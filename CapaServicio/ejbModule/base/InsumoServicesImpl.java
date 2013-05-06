package base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InsumoServicesImpl implements InsumoServices{
	
	@PersistenceContext
	private EntityManager em;

	@Override	
	public void delete(Object insumo) {
		em.remove(em.merge(insumo));		
	}

	@Override
	public void save(Object insumo) {
		try{			
			em.persist(insumo);		
		}catch (Exception e) {
			String a = e.getMessage();
		}
	}

	@Override
	public void update(Object insumo) {
		em.merge(insumo);
	}

}
