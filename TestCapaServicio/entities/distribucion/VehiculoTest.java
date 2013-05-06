package distribucion;

import java.util.Date;

import javax.persistence.EntityTransaction;

import persistencia.ServiceEntity;
import persistencia.DaoBaseEJB3unit;
import utils.FechaUtils;

import com.bm.testsuite.BaseSessionBeanFixture;

public class VehiculoTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	
	public VehiculoTest() {
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	@SuppressWarnings("deprecation")
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		
		Vehiculo v1 = new Vehiculo ();
		v1.setFechaAdquisicion(FechaUtils.getFecha(20, 3, 2002));
		v1.setMarca("Ford");
		v1.setModelo("Courier");
		v1.setDominio("BBB546");
		v1.setAnio(2000);
		
		Vehiculo v2 = new Vehiculo ();
		v2.setFechaAdquisicion(FechaUtils.getFecha(15, 4, 2009));
		v2.setMarca("Fiat");
		v2.setModelo("Express");
		v2.setDominio("CCC987");
		v2.setAnio(2005);
		
		Vehiculo v3 = new Vehiculo ();
		v3.setFechaAdquisicion(FechaUtils.getFecha(8, 7, 1995));
		v3.setMarca("Kia");
		v3.setModelo("Sultan");
		v3.setDominio("DDD753");

		v3.setAnio(1980);

		
		Vehiculo v4 = new Vehiculo ();
		v4.setFechaAdquisicion(FechaUtils.getFecha(23, 12, 1996));
		v4.setMarca("Audi");
		v4.setModelo("Citri");
		v4.setDominio("EEE468");
		v4.setAnio(1995);
		
		Vehiculo v5 = new Vehiculo ();
		v5.setFechaAdquisicion(FechaUtils.getFecha(26, 8, 2001));
		v5.setMarca("Toyota");
		v5.setModelo("Tactic"); // TODO revisar porque deberia saltar error
		v5.setDominio("FFF123");
		v5.setAnio(2003);
		
		Vehiculo v6 = new Vehiculo ();
		v6.setFechaAdquisicion(FechaUtils.getFecha(20, 6, 2004));
		v6.setMarca("Chevrolet");
		v6.setModelo("Meriva");
		v6.setDominio("AAA365");
		v6.setAnio(2002);
		
		Vehiculo v7 = new Vehiculo ();
		v7.setFechaAdquisicion(FechaUtils.getFecha(1, 9, 2009));
		v7.setMarca("Nissan");
		v7.setModelo("Tilda");
		v7.setDominio("HAA000");
		v7.setAnio(2009);
		
		Vehiculo v8 = new Vehiculo ();
		v8.setFechaAdquisicion(FechaUtils.getFecha(26, 8, 2007));
		v8.setMarca("Peugeot");
		v8.setModelo("Boxer");
		v8.setDominio("EKI133");
		v8.setAnio(2004);
		
		Vehiculo v9 = new Vehiculo ();
		v9.setFechaAdquisicion(FechaUtils.getFecha(1, 1, 2003));
		v9.setMarca("Renault");
		v9.setModelo("Kangoo");
		v9.setDominio("DEC009");
		v9.setAnio(2001);
		
		Vehiculo v10 = new Vehiculo ();
		v10.setFechaAdquisicion(FechaUtils.getFecha(13, 4, 1995));
		v10.setMarca("Renault");
		v10.setModelo("Trafic");
		v10.setDominio("ABV578");
		v10.setAnio(1993);
		
		
		

		dao.save(v1);
		dao.save(v2);
		dao.save(v3);
		dao.save(v4);
		dao.save(v5);
		dao.save(v6);
		dao.save(v7);
		dao.save(v8);
		dao.save(v9);
		dao.save(v10);
		
		
		
		t.commit();
	}
}
