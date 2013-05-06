package base;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import security.Permiso;
import security.Rol;

import com.bm.testsuite.BaseSessionBeanFixture;

public class RolTest extends BaseSessionBeanFixture<DaoBaseEJB3unit>{

	public RolTest() {
		super(DaoBaseEJB3unit.class,new Class[]{});
		// TODO Auto-generated constructor stub
	}
	
	public void testSave(){
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		DaoBaseEJB3unit dao = this.getBeanToTest();
		Rol r1 = new Rol(Permiso.ADMINISTRADOR);
		Rol r2 = new Rol(Permiso.DISTRIBUIDOR);
		Rol r3 = new Rol(Permiso.PANADERO);
		Rol r4 = new Rol(Permiso.RESPONSABLE_DE_PRODUCCION);
		Rol r5 = new Rol(Permiso.RESPONSABLE_DE_DISTRIBUCION);
		Rol r6 = new Rol(Permiso.RESPONSABLE_DE_STOCK);
		Rol r8 = new Rol(Permiso.SUPER_USUARIO);
		
		dao.save(r1);
		dao.save(r2);
		dao.save(r3);	
		dao.save(r4);
		dao.save(r5);
		dao.save(r6);
		dao.save(r8);
		
		t.commit();
	}

}
