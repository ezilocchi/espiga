package stock;

import java.util.List;

import javax.ejb.Local;

import produccion.Insumo;

@Local
public interface GestionStockBusiness {

	List<Insumo> list(Insumo example);
}
