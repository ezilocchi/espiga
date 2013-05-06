package base;

import javax.ejb.Local;

@Local
public interface InsumoServices {

	void save(Object insumo);
	void update(Object insumo);
	void delete(Object insumo);
}
