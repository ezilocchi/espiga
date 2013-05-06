package venta;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import produccion.Receta;

@Stateless
public class RecetaBusinessImpl implements RecetaBusiness{

	@PersistenceContext	
	private EntityManager em;	

	@Override
	public void save(Receta receta, List<Receta> others) {
		if(receta.getPredeterminada()){
			for (Receta r : others) {
				if(r.getPredeterminada()){
					r.setPredeterminada(false);
					em.merge(r);
					break;
				}
			}
		}
		em.persist(receta);
	}
	
	public void update(Receta receta, List<Receta> others){
		if(receta.getPredeterminada() && !receta.equals(receta)){
			for (Receta r : others) {
				if(r.getPredeterminada()){
					r.setPredeterminada(false);
					em.merge(r);
					break;
				}
			}
		}
		em.merge(receta);
	}

}
