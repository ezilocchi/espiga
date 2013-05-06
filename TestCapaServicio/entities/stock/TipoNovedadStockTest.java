package stock;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;

import com.bm.testsuite.BaseSessionBeanFixture;

public class TipoNovedadStockTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {

	public TipoNovedadStockTest() {
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave(){
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		DaoBaseEJB3unit dao = this.getBeanToTest();
		
		TipoNovedadStock tipo1 = new TipoNovedadStock();
		tipo1.setNombre("Perdida");
		tipo1.setDescripcion("Extravio de insunmo");
		tipo1.setIncrementa(false);
		
		TipoNovedadStock tipo2 = new TipoNovedadStock();
		tipo2.setNombre("Vencimiento");
		tipo2.setDescripcion("Paso la fecha de vencimiento");
		tipo2.setIncrementa(false);
		
		TipoNovedadStock tipo3 = new TipoNovedadStock();
		tipo3.setNombre("Bonificacion");		
		tipo3.setIncrementa(true);
		
		TipoNovedadStock tipo4 = new TipoNovedadStock();
		tipo4.setNombre("Oferta");
		tipo4.setDescripcion("Oferta, varios por menor precio");
		tipo4.setIncrementa(true);

		dao.save(tipo1);
		dao.save(tipo2);
		dao.save(tipo3);
		dao.save(tipo4);
		
		t.commit();
	}

}
