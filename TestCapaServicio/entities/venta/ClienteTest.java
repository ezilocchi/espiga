package venta;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import venta.Cliente.TipoCliente;
import base.Barrio;
import base.CondicionIVA;
import base.Recurso;

import com.bm.testsuite.BaseSessionBeanFixture;

public class ClienteTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	
	public ClienteTest(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		Barrio b1 = new Barrio ();
		b1.setId(1l);
		Barrio b15 = new Barrio ();
		b15.setId(15l);
		Barrio b16 = new Barrio ();
		b16.setId(16l);
		Barrio b22 = new Barrio ();
		b22.setId(22l);
		Barrio b30 = new Barrio ();
		b30.setId(30l);
		Barrio b36 = new Barrio ();
		b36.setId(36l);
		Barrio b40 = new Barrio ();
		b40.setId(40l);
		Barrio b46 = new Barrio ();
		b46.setId(46l);
		
		Recurso r1 = new Recurso ();
		r1.setId(1l);
		Recurso r2 = new Recurso ();
		r2.setId(2l);
		Recurso r3 = new Recurso ();
		r3.setId(3l);
		Recurso r4 = new Recurso ();
		r4.setId(4l);
		Recurso r5 = new Recurso ();
		r5.setId(5l);
		Recurso r6 = new Recurso ();
		r6.setId(6l);
		Recurso r7 = new Recurso ();
		r7.setId(7l);
		Recurso r8 = new Recurso ();
		r8.setId(8l);
		Recurso r9 = new Recurso ();
		r9.setId(9l);
		Recurso r10 = new Recurso ();
		r10.setId(10l);
		
		
		
				
		Cliente ev1 = new Cliente ();
		ev1.setRazonSocial("La Salada");
		ev1.setCuit("21-2059862-8");
		ev1.setMail("lasalada@gmail.com");
		ev1.setTelefono("4670932");
		ev1.getDireccion().setBarrio(b1);
		ev1.getDireccion().setCalle("Ayacucho");
		ev1.getDireccion().setNumero(444);
		ev1.setCondicionIVA(CondicionIVA.NO_RESPONSABLE);
		ev1.addContacto(r1);
		ev1.setTipoCliente(TipoCliente.MINORISTA);
				
		Cliente ev2 = new Cliente ();
		ev2.setRazonSocial("Pastorcito");
		ev2.setCuit("17-11587934-6");
		ev2.setMail("pastorcito@gmail.com");
		ev2.setTelefono("4609863");
		ev2.getDireccion().setBarrio(b15);
		ev2.getDireccion().setCalle("Misericordia");
		ev2.getDireccion().setNumero(777);
		ev2.setCondicionIVA(CondicionIVA.EXENTO);
		ev2.addContacto(r2);
		ev2.setTipoCliente(TipoCliente.MINORISTA);
		
		Cliente ev3 = new Cliente ();
		ev3.setRazonSocial("Bigua");
		ev3.setCuit("27-30768099-4");
		ev3.setMail("biguacompany@gmail.com");
		ev3.setTelefono("4268704");
		ev3.getDireccion().setBarrio(b46);
		ev3.getDireccion().setCalle("Montevideo");
		ev3.getDireccion().setNumero(345);
		ev3.setCondicionIVA(CondicionIVA.MONOTRIBUTO);
		ev3.addContacto(r3);
		ev3.setTipoCliente(TipoCliente.MINORISTA);
		
		
		Cliente ev4 = new Cliente ();
		ev4.setCondicionIVA(CondicionIVA.CONSUMIDOR_FINAL);
		ev4.setCuit("30-32876983-7");
		ev4.setMail("el_rosedal@gmail.com");
		ev4.setRazonSocial("El Rosedal");
		ev4.setTelefono("4297761");
		ev4.getDireccion().setCalle("Rondeau");
		ev4.getDireccion().setNumero(178);
		ev4.getDireccion().setBarrio(b30);
		ev4.addContacto(r4);
		ev4.setTipoCliente(TipoCliente.MINORISTA);
		
		Cliente ev5 = new Cliente ();
		ev5.setCondicionIVA(CondicionIVA.RESPONSABLE_NO_INSCRIPTO);
		ev5.setCuit("31-19045821-4");
		ev5.setMail("gracielaFranceschini@gmail.com");
		ev5.setRazonSocial("Graciela Franceschini");
		ev5.setTelefono("4295601");
		ev5.getDireccion().setCalle("Yrigoyen");
		ev5.getDireccion().setNumero(590);
		ev5.getDireccion().setBarrio(b40);
		ev5.addContacto(r5);
		ev5.setTipoCliente(TipoCliente.MINORISTA);
		
		Cliente ev6 = new Cliente ();
		ev6.setCondicionIVA(CondicionIVA.MONOTRIBUTO);
		ev6.setCuit("30-26888156-4");
		ev6.setMail("brussa@gmail.com");
		ev6.setRazonSocial("Brussa");
		ev6.setTelefono("4233750");
		ev6.getDireccion().setCalle("Intendente Da Silva");
		ev6.getDireccion().setNumero(1789);
		ev6.getDireccion().setBarrio(b36);
		ev6.addContacto(r6);
		ev6.setTipoCliente(TipoCliente.MINORISTA);
		
		Cliente ev7 = new Cliente ();
		ev7.setCondicionIVA(CondicionIVA.EXENTO);
		ev7.setCuit("31-90589454-4");
		ev7.setMail("archeritopan@gmail.com");
		ev7.setRazonSocial("Archerito");
		ev7.setTelefono("4897160");
		ev7.getDireccion().setCalle("Florida");
		ev7.getDireccion().setNumero(531);
		ev7.getDireccion().setBarrio(b16);
		ev7.addContacto(r7);
		ev7.setTipoCliente(TipoCliente.MINORISTA);
		
		Cliente ev8 = new Cliente ();
		ev8.setCondicionIVA(CondicionIVA.MONOTRIBUTO);
		ev8.setCuit("31-12098573-4");
		ev8.setMail("el_balcon@gmail.com");
		ev8.setRazonSocial("Balcon");
		ev8.setTelefono("4309861");
		ev8.getDireccion().setCalle("Duarte Quiroz");
		ev8.getDireccion().setNumero(45);
		ev8.getDireccion().setBarrio(b30);
		ev8.addContacto(r8);
		ev8.setTipoCliente(TipoCliente.MINORISTA);
		
		Cliente ev9 = new Cliente ();
		ev9.setCondicionIVA(CondicionIVA.RESPONSABLE_INSCRIPTO);
		ev9.setCuit("31-30627698-7");
		ev9.setMail("maxikiosco@hotmail.com");
		ev9.setRazonSocial("Maxi Kiosco");
		ev9.setTelefono("4260931");
		ev9.getDireccion().setCalle("Cruz Roja");
		ev9.getDireccion().setNumero(545);
		ev9.getDireccion().setBarrio(b36);
		ev9.addContacto(r9);
		ev9.setTipoCliente(TipoCliente.MINORISTA);
		
		Cliente ev10 = new Cliente ();
		ev10.setRazonSocial("La Reina");
		ev10.setCuit("21-20555071-7");
		ev10.setMail("la_reina@gmail.com");
		ev10.setTelefono("4287651");
		ev10.getDireccion().setBarrio(b40);
		ev10.getDireccion().setCalle("Pio Angulo");
		ev10.getDireccion().setNumero(831);
		ev10.setCondicionIVA(CondicionIVA.MONOTRIBUTO);
		ev10.addContacto(r10);
		ev10.setTipoCliente(TipoCliente.MINORISTA);	

		dao.save(ev1);
		dao.save(ev2);
		dao.save(ev3);
		dao.save(ev4);
		dao.save(ev5);
		dao.save(ev6);
		dao.save(ev7);
		dao.save(ev8);
		dao.save(ev9);
		dao.save(ev10);
		
		t.commit();
	}

}
