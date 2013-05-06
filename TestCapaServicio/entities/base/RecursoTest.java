package base;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;

import com.bm.testsuite.BaseSessionBeanFixture;

public class RecursoTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	
	public RecursoTest(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		// Recursos
		Recurso r1 = new Recurso ();
		Recurso r2 = new Recurso ();
		Recurso r3 = new Recurso ();
		Recurso r4 = new Recurso ();
		Recurso r5 = new Recurso ();
		Recurso r6 = new Recurso ();
		Recurso r7 = new Recurso ();
		Recurso r8 = new Recurso ();
		Recurso r9 = new Recurso ();
		Recurso r10 = new Recurso ();
		Recurso r11 = new Recurso ();
		Recurso r12 = new Recurso ();
		Recurso r13 = new Recurso ();
		Recurso r14 = new Recurso ();
		Recurso r15 = new Recurso ();
		
		//Cargos que pueden ser asignados a cada recurso
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
		
		// Tipo de Documento de cada recurso
		TipoDocumento td1 = TipoDocumento.DNI;		
		TipoDocumento td2 = TipoDocumento.LC;		
		TipoDocumento td3 = TipoDocumento.LE;
		
		
		// Barrios de cada recurso
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
				
		r1.setApellido("Quiroga");
		r1.setCelular("0351-156000988");
		r1.setMail("crisquiroga@hotmail.com");
		r1.setNombres("Cristian");
		r1.setNroDocumento(32300987l);
		r1.setTelefono("4268763");
		r1.setCargo(c1);
		r1.setTipoDocumento(td1);
		r1.getDireccion().setCalle("Cordoba");
		r1.getDireccion().setNumero(333);
		r1.getDireccion().setDepto('F');
		r1.getDireccion().setPiso(8);
		r1.getDireccion().setBarrio(b1);
		
		r2.setApellido("Valentinis");
		r2.setCelular("0351-152456000");
		r2.setMail("nesti@hotmail.com");
		r2.setNombres("Nestor");
		r2.setNroDocumento(30627600l);
		r2.setTelefono("4604350");
		r2.setCargo(c2);
		r2.setTipoDocumento(td1);
		r2.getDireccion().setCalle("Obispo Oro");
		r2.getDireccion().setNumero(179);
		r2.getDireccion().setDepto('D');
		r2.getDireccion().setPiso(7);
		r2.getDireccion().setBarrio(b2);
		
		r3.setApellido("Ibarra");
		r3.setCelular("011-1588650983");
		r3.setMail("el_negro@hotmail.com");
		r3.setNombres("Hugo Benjamin");
		r3.setNroDocumento(25765988l);
		r3.setTelefono("478946752");
		r3.setCargo(c3);
		r3.setTipoDocumento(td2);
		r3.getDireccion().setCalle("La Rivera");
		r3.getDireccion().setNumero(1);
		r3.getDireccion().setBarrio(b3);
		r3.getDireccion().setDepto('F');
		r3.getDireccion().setPiso(8);
		
		r4.setApellido("Mouche");
		r4.setCelular("011-1561235670");
		r4.setMail("zurdo@hotmail.com");
		r4.setNombres("Mauro");
		r4.setNroDocumento(31455890l);
		r4.setTelefono("464896632");
		r4.setCargo(c4);
		r4.setTipoDocumento(td1);
		r4.getDireccion().setCalle("Rivera Indarte");
		r4.getDireccion().setNumero(145);
		r4.getDireccion().setBarrio(b4);
		r4.getDireccion().setDepto('A');
		r4.getDireccion().setPiso(3);
		
		r5.setApellido("Ortega");
		r5.setCelular("0351-152566090");
		r5.setMail("elborracho@hotmail.com");
		r5.setNombres("Ariel Arnaldo");
		r5.setNroDocumento(25666090l);
		r5.setTelefono("460328976");
		r5.setCargo(c5);
		r5.setTipoDocumento(td3);
		r5.getDireccion().setCalle("Pecho frio");
		r5.getDireccion().setNumero(56);
		r5.getDireccion().setBarrio(b5);
		r5.getDireccion().setDepto('H');
		r5.getDireccion().setPiso(2);
		
		r6.setApellido("Costanza");
		r6.setCelular("0351-155728040");
		r6.setMail("organizador@hotmail.com");
		r6.setNombres("Mariano");
		r6.setNroDocumento(31489712l);
		r6.setTelefono("4225609");
		r6.setCargo(c6);
		r6.setTipoDocumento(td1);
		r6.getDireccion().setCalle("Las Heras");
		r6.getDireccion().setNumero(1000);
		r6.getDireccion().setDepto('A');
		r6.getDireccion().setPiso(13);
		r6.getDireccion().setBarrio(b6);
		
		r7.setApellido("Juarez");
		r7.setCelular("0351-152777034");
		r7.setMail("fidodido@hotmail.com");
		r7.setNombres("Diego");
		r7.setNroDocumento(32199762l);
		r7.setTelefono("4865400");
		r7.setCargo(c7);
		r7.setTipoDocumento(td1);
		r7.getDireccion().setCalle("Malvinas Argentinas");
		r7.getDireccion().setNumero(764);
		r7.getDireccion().setBarrio(b7);
		r7.getDireccion().setDepto('J');
		r1.getDireccion().setPiso(4);
		
		r8.setApellido("Castillo");
		r8.setCelular("0351-155924072");
		r8.setMail("florsesienta@hotmail.com");
		r8.setNombres("Florencia");
		r8.setNroDocumento(30333873l);
		r8.setTelefono("4659072");
		r8.setCargo(c8);
		r8.setTipoDocumento(td2);
		r8.getDireccion().setCalle("Contreras");
		r8.getDireccion().setNumero(971);
		r8.getDireccion().setBarrio(b8);
		r8.getDireccion().setDepto('C');
		r8.getDireccion().setPiso(14);
		
		r9.setApellido("Manzanares");
		r9.setCelular("0351-152708301");
		r9.setMail("manzanita@hotmail.com");
		r9.setNombres("Rodolfo");
		r9.setNroDocumento(12589722l);
		r9.setTelefono("4604360");
		r9.setCargo(c7);
		r9.setTipoDocumento(td3);
		r9.getDireccion().setCalle("Fredy Rincon");
		r9.getDireccion().setNumero(222);
		r9.getDireccion().setDepto('G');
		r9.getDireccion().setPiso(1);
		r9.getDireccion().setBarrio(b9);
		
		r10.setApellido("Ontivero");
		r10.setCelular("0351-155902688");
		r10.setMail("alicia_ont@hotmail.com");
		r10.setNombres("Alicia");
		r10.setNroDocumento(18650278l);
		r10.setTelefono("4221927");
		r10.setCargo(c2);
		r10.setTipoDocumento(td1);
		r10.getDireccion().setCalle("Marcos Juarez");
		r10.getDireccion().setNumero(489);
		r10.getDireccion().setBarrio(b10);
		r10.getDireccion().setDepto('I');
		r10.getDireccion().setPiso(1);
		
		
		r11.setApellido("Almeyda");
		r11.setCelular("011-1569004443");
		r11.setMail("tomuer@hotmail.com");
		r11.setNombres("Gabriel");
		r11.setNroDocumento(12555890l);
		r11.setTelefono("011-46890764");
		r11.setCargo(c4);
		r11.setTipoDocumento(td3);
		r11.getDireccion().setCalle("La veteranita");
		r11.getDireccion().setDepto('F');
		r11.getDireccion().setNumero(100);
		r11.getDireccion().setPiso(6);
		r11.getDireccion().setBarrio(b11);
		
		
		r12.setApellido("Casa Ruggieri");
		r12.setCelular("0351-152888651");
		r12.setMail("jesicasas@hotmail.com");
		r12.setNombres("Jessica Jimena");
		r12.setNroDocumento(30546812l);
		r12.setTelefono("0351-4604589");
		r12.setCargo(c8);
		r12.setTipoDocumento(td1);
		r12.getDireccion().setCalle("Bouchardo");
		r12.getDireccion().setNumero(543);
		r12.getDireccion().setBarrio(b1);
		r12.getDireccion().setDepto('K');
		r12.getDireccion().setPiso(3);
		
		
		r13.setApellido("Lopez");
		r13.setCelular("0351-156766245");
		r13.setMail("consulopez@hotmail.com");
		r13.setNombres("Consuelo");
		r13.setNroDocumento(33222789l);
		r13.setTelefono("0351-4263465");
		r13.setCargo(c2);
		r13.setTipoDocumento(td1);
		r13.getDireccion().setCalle("Independencia");
		r13.getDireccion().setDepto('A');
		r13.getDireccion().setNumero(134);
		r13.getDireccion().setPiso(2);
		r13.getDireccion().setBarrio(b13);
		
		
		r14.setApellido("Vignolo");
		r14.setCelular("0351-153678033");
		r14.setMail("solcito@hotmail.com");
		r14.setNombres("Maria Sol");
		r14.setNroDocumento(32300567l);
		r14.setTelefono("0351-4604550");
		r14.setCargo(c1);
		r14.setTipoDocumento(td1);
		r14.getDireccion().setCalle("Santa Rosa");
		r14.getDireccion().setDepto('D');
		r14.getDireccion().setNumero(201);
		r14.getDireccion().setPiso(7);
		r14.getDireccion().setBarrio(b14);
		
		
		r15.setApellido("Ferrero");
		r15.setCelular("0351-155389733");
		r15.setMail("el_mosquito@hotmail.com");
		r15.setNombres("Juan Carlos");
		r15.setNroDocumento(28199076l);
		r15.setTelefono("0351-4640011");
		r15.setCargo(c5);
		r15.setTipoDocumento(td3);
		r15.getDireccion().setCalle("Extremadura");
		r15.getDireccion().setNumero(543);
		r15.getDireccion().setBarrio(b15);
		r15.getDireccion().setDepto('E');
		r15.getDireccion().setPiso(9);
		
		dao.save(r1);
		dao.save(r2);
		dao.save(r3);
		dao.save(r4);
		dao.save(r5);
		dao.save(r6);
		dao.save(r7);
		dao.save(r8);
		dao.save(r9);
		dao.save(r10);
		dao.save(r11);
		dao.save(r12);
		dao.save(r13);
		dao.save(r14);
		dao.save(r15);
		
		
		t.commit();
		
	}
	


}
