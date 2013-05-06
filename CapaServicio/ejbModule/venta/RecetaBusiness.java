package venta;

import java.util.List;

import javax.ejb.Local;

import produccion.Receta;

@Local
public interface RecetaBusiness {

	void save(Receta receta,List<Receta> others);
	void update(Receta receta, List<Receta> others);
}
