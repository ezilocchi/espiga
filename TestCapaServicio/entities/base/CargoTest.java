package base;

import javax.persistence.EntityTransaction;

import persistencia.ServiceEntity;
import persistencia.DaoBaseEJB3unit;
import security.Rol;

import com.bm.testsuite.BaseSessionBeanFixture;

public class CargoTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	
	public CargoTest(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		Cargo c1 = new Cargo();
		Cargo c2 = new Cargo();
		Cargo c3 = new Cargo();
		Cargo c4 = new Cargo();
		Cargo c5 = new Cargo ();
		Cargo c6 = new Cargo ();
		Cargo c7 = new Cargo();
		Cargo c8 = new Cargo ();
		Cargo c9 = new Cargo();
		
		c1.setNombre("Gerente Comercial");
		c1.setDescripcion("Administra las areas de Compras y Ventas");
		c1.addRol(new Rol(6l));
		c1.addRol(new Rol(7l));
		c1.addRol(new Rol(2l));
		
		c2.setNombre("Gerente de Produccion y Distribución");
		c2.setDescripcion("Administra el area de Produccion");
		c2.addRol(new Rol(4l));
		c2.addRol(new Rol(5l));
		
		c3.setNombre("Panadero");
		c3.setDescripcion("Efectua la produccion de pan");
		c3.addRol(new Rol(3l));
		
		c4.setNombre("Repartidor");
		c4.setDescripcion("Entrega y toma pedidos a clientes");
		c4.addRol(new Rol(2l));
		
		c5.setNombre("Repostero");
		c5.setDescripcion("Efectua la produccion de facturas");
		c5.addRol(new Rol(3l));
		
		c6.setNombre("Asitente Panadero");
		c6.setDescripcion("Colabora con las actividades del panadero");
		c6.addRol(new Rol(3l));
		
		c7.setNombre("Asistente Repostero");
		c7.setDescripcion("Colabora con las actividades del repostero");
		c7.addRol(new Rol(3l));
		
		c8.setNombre("Gerente General");
		c8.setDescripcion("Encargado de empaquetar los pedidos");
		c8.addRol(new Rol(7l));
		
		c9.setNombre("Administrador del sistema");
		c9.setDescripcion("Encargado de las parametrizaciones del sistema y la alta de usuarios");
		c9.addRol(new Rol(1l));
		
			
		dao.save(c1);
		dao.save(c2);
		dao.save(c3);
		dao.save(c4);
		dao.save(c5);
		dao.save(c6);
		dao.save(c7);
		dao.save(c8);
		dao.save(c9);

		
		t.commit();
	}

}
