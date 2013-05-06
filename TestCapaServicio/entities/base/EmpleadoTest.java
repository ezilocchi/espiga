
package base;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import security.UserSecurity;
import venta.Producto;

import com.bm.testsuite.BaseSessionBeanFixture;

public class EmpleadoTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	
	public EmpleadoTest () {
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		DaoBaseEJB3unit dao = this.getBeanToTest();
		
		Producto p1 = new Producto();
		p1.setId(1L);
		
		Producto p2 = new Producto();
		p2.setId(2L);
		
		Producto p3 = new Producto();
		p3.setId(3L);
		
		Producto p4 = new Producto();
		p4.setId(4L);
		
		Producto p5 = new Producto();
		p5.setId(5L);
		
		Producto p6 = new Producto();
		p6.setId(6L);
		
		Producto p7 = new Producto();
		p7.setId(7L);
		
		Producto p8 = new Producto();
		p8.setId(8L);
		
		Producto p9 = new Producto();
		p9.setId(9L);
		
		Producto p10 = new Producto();
		p10.setId(10L);
		
		Producto p11 = new Producto();
		p11.setId(11L);
		
		Producto p12 = new Producto();
		p12.setId(12L);
		
		Producto p13 = new Producto();
		p13.setId(13L);
		
		Producto p14 = new Producto();
		p14.setId(14L);
		
		Producto p15 = new Producto();
		p15.setId(15L);
		
		Producto p16 = new Producto();
		p16.setId(16L);
		
		Producto p17 = new Producto();
		p17.setId(17L);
		
		Producto p18 = new Producto();
		p18.setId(18L);
		
		Producto p19 = new Producto();
		p19.setId(19L);
		
		Producto p20 = new Producto();
		p20.setId(20L);
		
		Producto p21 = new Producto();
		p21.setId(21L);
		
		Producto p22 = new Producto();
		p22.setId(22L);
		
		
		
		
		Cargo c1 = new Cargo ();
		c1.setId(1l);
		Cargo c2 = new Cargo ();
		c2.setId(2l);
		Cargo c3 = new Cargo ();
		c3.setId(3l);
		Cargo c4 = new Cargo ();
		c4.setId(4l);
		Cargo c5 = new Cargo ();
		c5.setId(5l);
		Cargo c6 = new Cargo ();
		c6.setId(6l);
		Cargo c7 = new Cargo ();
		c7.setId(7l);
		Cargo c8 = new Cargo ();
		c8.setId(8l);
		Cargo c9 = new Cargo ();
		c9.setId(9l);

		
		TipoDocumento td1 = TipoDocumento.DNI;
		TipoDocumento td2 = TipoDocumento.CEDULA_FEDERAL;
		TipoDocumento td3 = TipoDocumento.LE;
		
		
		
		Barrio b1 = new Barrio ();
		b1.setId(1l);
		Barrio b2 = new Barrio ();
		b2.setId(2l);
		Barrio b3 = new Barrio ();
		b3.setId(3l);
		Barrio b4 = new Barrio ();
		b4.setId(4l);
		Barrio b5 = new Barrio ();
		b5.setId(5l);
		Barrio b6 = new Barrio ();
		b6.setId(6l);
		Barrio b7 = new Barrio ();
		b7.setId(7l);
		Barrio b8 = new Barrio ();
		b8.setId(8l);
		Barrio b9 = new Barrio ();
		b9.setId(9l);
		Barrio b10 = new Barrio ();
		b10.setId(10l);
		Barrio b11 = new Barrio ();
		b11.setId(11l);
		Barrio b12 = new Barrio ();
		b12.setId(12l);
		Barrio b13 = new Barrio ();
		b13.setId(13l);
		Barrio b14 = new Barrio ();
		b14.setId(14l);
		Barrio b15 = new Barrio ();
		b15.setId(15l);

		
		
		Empleado e1 = new Empleado ();
		UserSecurity user1 = new UserSecurity("dclaro","xxx");
		user1.setGroup(c1);
		e1.setUser(user1);
		e1.setApellido("Claro");
		e1.setCelular("156790624");
		e1.setMail("eldani@hotmail.com");
		e1.setNombres("Daniel");
		e1.setNroDocumento(32478034l);
		e1.setTelefono("4294058");
		e1.getDireccion().setBarrio(b1);
		e1.getDireccion().setCalle("Pasaje Marcasoli");
		e1.getDireccion().setNumero(381);
		e1.setTipoDocumento(td1);
		
		
		Empleado e2 = new Empleado ();
		UserSecurity user2 = new UserSecurity("mdsgariglio","xxx");
		user2.setGroup(c2);
		e2.setUser(user2);
		e2.setApellido("Sgariglio");
		e2.setCelular("153683000");
		e2.setMail("toto_83@hotmail.com");
		e2.setNombres("Mauricio Dario");
		e2.setNroDocumento(32389700l);
		e2.setTelefono("4263250");
		e2.getDireccion().setBarrio(b2);
		e2.getDireccion().setCalle("Pasaje Marcasoli");
		e2.getDireccion().setNumero(381);
		e2.setTipoDocumento(td1);
		
		// Sucursal 2
		Empleado e3 = new Empleado ();
		UserSecurity user3 = new UserSecurity("jrfiorelli","xxx");
		user3.setGroup(c3);
		e3.setUser(user3);
		e3.setApellido("Fiorelli");
		e3.setCelular("1557779823");
		e3.setMail("romanfiorelli@hotmail.com");
		e3.setNombres("Juan Roman");
		e3.setNroDocumento(28756400l);
		e3.setTelefono("49876542");
		e3.getDireccion().setBarrio(b3);
		e3.getDireccion().setCalle("27 de Abril");
		e3.getDireccion().setNumero(345);
		e3.getDireccion().setPiso(1);
		e3.setTipoDocumento(td2);
		e3.addProducto(p1);
		e3.addProducto(p9);
		e3.addProducto(p5);
		e3.addProducto(p7);
		e3.addProducto(p22);
		
		
		Empleado e4 = new Empleado ();
		UserSecurity user4 = new UserSecurity("jaalvarez","xxx");
		user4.setGroup(c3);
		e4.setUser(user4);
		e4.setApellido("Alvarez");
		e4.setCelular("153768940");
		e4.setMail("alvarez_jose@hotmail.com");
		e4.setNombres("Jose Alberto");
		e4.setNroDocumento(25678092l);
		e4.setTelefono("4278504");
		e4.getDireccion().setBarrio(b4);
		e4.getDireccion().setCalle("27 de Abril");
		e4.getDireccion().setNumero(345);
		e4.setTipoDocumento(td1);
		e4.addProducto(p4);
		e4.addProducto(p12);
		e4.addProducto(p16);
		e4.addProducto(p19);
		e4.addProducto(p8);
		
		
		// Sucursal 3
		Empleado e5 = new Empleado ();
		UserSecurity user5 = new UserSecurity("davalos","xxx");
		user5.setGroup(c3);
		e5.setUser(user5);
		e5.setApellido("Avalos");
		e5.setCelular("0351-155888134");
		e5.setMail("daniavalos@hotmail.com");
		e5.setNombres("Daniel");
		e5.setNroDocumento(32356888l);
		e5.setTelefono("4558972");
		e5.getDireccion().setBarrio(b5);
		e5.getDireccion().setCalle("San Lorenzo");
		e5.getDireccion().setNumero(800);
		e5.setTipoDocumento(td1);
		e5.addProducto(p15);
		e5.addProducto(p11);
		e5.addProducto(p3);
		e5.addProducto(p21);
		e5.addProducto(p17);
		e5.addProducto(p13);
		e5.addProducto(p2);
		
		
		Empleado e6 = new Empleado ();
		UserSecurity user6 = new UserSecurity("fquinteros","xxx");
		user6.setGroup(c5);
		e6.setUser(user6);
		e6.setApellido("Quinteros");
		e6.setCelular("0351-152788634");
		e6.setMail("ferquinteros@hotmail.com");
		e6.setNombres("Fernando");
		e6.setNroDocumento(25895711l);
		e6.setTelefono("4604498");
		e6.getDireccion().setBarrio(b6);
		e6.getDireccion().setCalle("San Lorenzo");
		e6.getDireccion().setNumero(800);
		e6.setTipoDocumento(td1);
		e6.addProducto(p6);
		e6.addProducto(p14);
		e6.addProducto(p10);
		e6.addProducto(p18);
		e6.addProducto(p20);
		
		
		// Sucursal 4
		Empleado e7 = new Empleado ();
		UserSecurity user7 = new UserSecurity("vgrossi","xxx");
		user7.setGroup(c6);
		e7.setUser(user7);
		e7.setApellido("Grossi");
		e7.setCelular("0351-157245097");
		e7.setMail("titagrossi@hotmail.com");
		e7.setNombres("Virginia");
		e7.setNroDocumento(20555832l);
		e7.setTelefono("4117843");
		e7.getDireccion().setBarrio(b7);
		e7.getDireccion().setCalle("Brasil");
		e7.getDireccion().setNumero(111);
		e7.setTipoDocumento(td1);
		e7.addProducto(p1);
		e7.addProducto(p9);
		e7.addProducto(p5);
		e7.addProducto(p7);
		e7.addProducto(p22);
		
		
		
		Empleado e8 = new Empleado ();
		UserSecurity user8 = new UserSecurity("rlarrasa","xxx");
		user8.setGroup(c7);
		e8.setUser(user8);
		e8.setApellido("Larrasa");
		e8.setCelular("0351-156777021");
		e8.setMail("robertlarra@hotmail.com");
		e8.setNombres("Roberto");
		e8.setNroDocumento(29568021l);
		e8.setTelefono("4628943");
		e8.getDireccion().setBarrio(b8);
		e8.getDireccion().setCalle("Brasil");
		e8.getDireccion().setNumero(111);
		e8.setTipoDocumento(td1);
		e8.addProducto(p6);
		e8.addProducto(p14);
		e8.addProducto(p10);
		e8.addProducto(p18);
		e8.addProducto(p20);
		
		
		
		//Sucursal 5
		Empleado e9 = new Empleado ();
		UserSecurity user9 = new UserSecurity("srossi","xxx");
		user9.setGroup(c4);
		e9.setUser(user9);
		e9.setApellido("Rossi");
		e9.setCelular("0351-152489111");
		e9.setMail("sebarossi@hotmail.com");
		e9.setNombres("Sebastian");
		e9.setNroDocumento(27890666l);
		e9.setTelefono("4227893");
		e9.getDireccion().setBarrio(b9);
		e9.getDireccion().setCalle("Caseros");
		e9.getDireccion().setNumero(76);
		e9.setTipoDocumento(td1);
		Zona z2 = dao.getById(new Zona(2l));				
		e9.addZona(z2);
		
		
		
		Empleado e10 = new Empleado ();
		UserSecurity user10 = new UserSecurity("jaimetta","xxx");
		user10.setGroup(c9);
		e10.setUser(user10);
		e10.setApellido("Aimetta");
		e10.setCelular("0351-156000672");
		e10.setMail("elgringoaimetta@hotmail.com");
		e10.setNombres("Juan");
		e10.setNroDocumento(30549715l);
		e10.setTelefono("4579061");
		e10.getDireccion().setBarrio(b10);
		e10.getDireccion().setCalle("Caseros");
		e10.getDireccion().setNumero(76);
		e10.setTipoDocumento(td2);	

		
		//Distribuidores
		Empleado e11 = new Empleado ();		
		UserSecurity user11 = new UserSecurity("jivalentinis","xxx");
		user11.setGroup(c4);
		e11.setUser(user11);
		e11.setApellido("Valentinis");
		e11.setCelular("153457162");
		e11.setMail("tesacaelojo@gmail.com");
		e11.setNombres("Juan Ignacio");
		e11.setNroDocumento(32389733l);
		e11.setTelefono("4604350");
		e11.getDireccion().setBarrio(b11);
		e11.getDireccion().setCalle("Obispo Oro");
		e11.getDireccion().setNumero(179);
		e11.setTipoDocumento(td1);
		Zona z1 = dao.getById(new Zona(1l));				
		e11.addZona(z1);

		
		Empleado e12 = new Empleado ();
		UserSecurity user12 = new UserSecurity("palamberti","xxx");
		user12.setGroup(c4);
		e12.setUser(user12);
		e12.setApellido("Lamberti");
		e12.setCelular("156163166");
		e12.setMail("lamber@hotmail.com");
		e12.setNombres("Pablo Andres");
		e12.setNroDocumento(32232567l);
		e12.setTelefono("4657631");
		e12.getDireccion().setBarrio(b12);
		e12.getDireccion().setCalle("Avenida Fuerza Aerea");
		e12.getDireccion().setNumero(2020);
		e12.setTipoDocumento(td1);
		Zona z10 = dao.getById(new Zona(10l));				
		e12.addZona(z10);

		
		Empleado e13 = new Empleado ();
		UserSecurity user13 = new UserSecurity("fsbobbio","xxx");
		user13.setGroup(c4);
		e13.setUser(user13);
		e13.setApellido("Bobbio");
		e13.setCelular("152456879");
		e13.setMail("gordobobbio@hotmail.com");
		e13.setNombres("Federico Sebastian");
		e13.setNroDocumento(32111789l);
		e13.setTelefono("4267893");
		e13.getDireccion().setBarrio(b13);
		e13.getDireccion().setCalle("Carrilobo");
		e13.getDireccion().setNumero(100);
		e13.setTipoDocumento(td3);
		Zona z4 = dao.getById(new Zona(4l));				
		e13.addZona(z4);
		
		
		
		Empleado e14 = new Empleado ();	
		UserSecurity user14 = new UserSecurity("ezilocchi","xxx");
		user14.setGroup(c8);
		e14.setUser(user14);
		e14.setApellido("Zilochi");
		e14.setCelular("150567934");
		e14.setMail("elzilochi@hotmail.com");
		e14.setNombres("Emiliano");
		e14.setNroDocumento(305698432l);
		e14.setTelefono("4612398");
		e14.getDireccion().setBarrio(b14);
		e14.getDireccion().setCalle("Hawaii");
		e14.getDireccion().setNumero(546);
		e14.setTipoDocumento(td3);
		
		
		Empleado e15 = new Empleado ();	
		UserSecurity user15 = new UserSecurity("dcoraglia","xxx");
		user15.setGroup(c4);
		e15.setUser(user15);
		e15.setApellido("Cornaglia");
		e15.setCelular("0351-153678033");
		e15.setMail("pinicornaglia@hotmail.com");
		e15.setNombres("Daniel");
		e15.setNroDocumento(30560896l);
		e15.setTelefono("0351-4698834");
		e15.setTipoDocumento(td1);
		e15.getDireccion().setCalle("Independencia");
		e15.getDireccion().setNumero(543);
		e15.getDireccion().setBarrio(b15);
		Zona z3 = dao.getById(new Zona(3l));				
		e15.addZona(z3);

		dao.save(user1);
		dao.save(user2);
		dao.save(user3);
		dao.save(user4);
		dao.save(user5);
		dao.save(user6);
		dao.save(user7);
		dao.save(user8);
		dao.save(user9);
		dao.save(user10);
		dao.save(user11);
		dao.save(user12);
		dao.save(user13);
		dao.save(user14);
		dao.save(user15);
		
		dao.save(e1);
		dao.save(e2);
		dao.save(e3);
		dao.save(e4);
		dao.save(e5);
		dao.save(e6);
		dao.save(e7);
		dao.save(e8);
		dao.save(e9);
		dao.save(e10);
		dao.save(e11);
		dao.save(e12);
		dao.save(e13);
		dao.save(e14);
		dao.save(e15);
		dao.update(z1);
		dao.update(z2);
		dao.update(z3);
		dao.update(z4);
		dao.update(z10);
		
		t.commit();
	}

}
