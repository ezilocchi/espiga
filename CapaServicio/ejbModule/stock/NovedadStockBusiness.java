package stock;

import javax.ejb.Local;

@Local
public interface NovedadStockBusiness {

	void save(NovedadStock novedadStock);
}
