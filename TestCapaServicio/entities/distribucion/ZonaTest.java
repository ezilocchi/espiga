package distribucion;

import javax.persistence.EntityTransaction;

import persistencia.ServiceEntity;
import persistencia.DaoBaseEJB3unit;

import base.Barrio;
import base.Zona;

import com.bm.testsuite.BaseSessionBeanFixture;

@Deprecated

public class ZonaTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
  public ZonaTest(){
	  super(DaoBaseEJB3unit.class,new Class[]{});
  }
  
  public void testSave() throws Exception{
	    EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		Zona z1 = new Zona(); 
		Zona z2 = new Zona(); 
		Zona z3 = new Zona();
		Zona z4 = new Zona ();
		Zona z5 = new Zona(); 
		Zona z6 = new Zona(); 
		Zona z7 = new Zona();
		Zona z8 = new Zona ();
		Zona z9 = new Zona ();
		Zona z10 = new Zona ();
		
		Barrio b1 = new Barrio ();
		Barrio b2 = new Barrio ();
		Barrio b3 = new Barrio ();		
		Barrio b4 = new Barrio ();
		Barrio b5 = new Barrio ();	
		Barrio b6 = new Barrio ();
		
		Barrio b7 = new Barrio ();		
		Barrio b8 = new Barrio ();
		Barrio b9 = new Barrio ();		
		Barrio b10 = new Barrio ();
		Barrio b11 = new Barrio ();		
		Barrio b12 = new Barrio ();
		
		Barrio b13 = new Barrio ();		
		Barrio b14 = new Barrio ();
		Barrio b15 = new Barrio ();		
		Barrio b16 = new Barrio ();
		Barrio b17 = new Barrio ();
		Barrio b18 = new Barrio ();
		
		Barrio b19 = new Barrio ();		
		Barrio b20 = new Barrio ();
		Barrio b21 = new Barrio ();		
		Barrio b22 = new Barrio ();
		Barrio b23 = new Barrio ();		
		Barrio b24 = new Barrio ();
		
		Barrio b25 = new Barrio ();		
		Barrio b26 = new Barrio ();
		Barrio b27 = new Barrio ();		
		Barrio b28 = new Barrio ();
		Barrio b29 = new Barrio ();		
		Barrio b30 = new Barrio ();
		
		Barrio b31 = new Barrio ();		
		Barrio b32 = new Barrio ();
		Barrio b33 = new Barrio ();		
		Barrio b34 = new Barrio ();
		Barrio b35 = new Barrio ();		
		Barrio b36 = new Barrio ();
		
		Barrio b37 = new Barrio ();		
		Barrio b38 = new Barrio ();
		Barrio b39 = new Barrio ();		
		Barrio b40 = new Barrio ();
		Barrio b41 = new Barrio ();		
		Barrio b42 = new Barrio ();
		
		Barrio b43 = new Barrio ();		
		Barrio b44 = new Barrio ();
		Barrio b45 = new Barrio ();		
		Barrio b46 = new Barrio ();
		Barrio b47 = new Barrio ();		
		Barrio b48 = new Barrio ();
		
		Barrio b49 = new Barrio ();		
		Barrio b50 = new Barrio ();
		Barrio b51 = new Barrio ();		
		Barrio b52 = new Barrio ();
		Barrio b53 = new Barrio ();		
		Barrio b54 = new Barrio ();
		
		Barrio b55 = new Barrio ();		
		Barrio b56 = new Barrio ();
		Barrio b57 = new Barrio ();		
		Barrio b58 = new Barrio ();
		Barrio b59 = new Barrio ();		
		Barrio b60 = new Barrio ();
		
		b1.setId(1l);
		b2.setId(2l);
		b3.setId(3l);
		b4.setId(4l);
		b5.setId(5l);
		b6.setId(6l);
		b7.setId(7l);
		b8.setId(8l);
		b9.setId(9l);
		b10.setId(10l);
		b11.setId(11l);
		b12.setId(12l);
		b13.setId(13l);
		b14.setId(14l);
		b15.setId(15l);
		b16.setId(16l);
		b17.setId(17l);
		b18.setId(18l);
		b19.setId(19l);
		b20.setId(20l);
		b21.setId(21l);
		b22.setId(22l);
		b23.setId(23l);
		b24.setId(24l);
		b25.setId(25l);
		b26.setId(26l);
		b27.setId(27l);
		b28.setId(28l);
		b29.setId(29l);
		b30.setId(30l);
		b31.setId(31l);
		b32.setId(32l);
		b33.setId(33l);
		b34.setId(34l);
		b35.setId(35l);
		b36.setId(36l);
		b37.setId(37l);
		b38.setId(38l);
		b39.setId(39l);
		b40.setId(40l);
		b41.setId(41l);
		b42.setId(42l);
		b43.setId(43l);
		b44.setId(44l);
		b45.setId(45l);
		b46.setId(46l);
		b47.setId(47l);
		b48.setId(48l);
		b49.setId(49l);
		b50.setId(50l);
		b51.setId(51l);
		b52.setId(52l);
		b53.setId(53l);
		b54.setId(54l);
		b55.setId(55l);
		b56.setId(56l);
		b57.setId(57l);
		b58.setId(58l);
		b59.setId(59l);
		b60.setId(60l);

		
		z1.setNombre("Zona Norte");
		z1.addBarrio(b1);
		z1.addBarrio(b2);
		z1.addBarrio(b3);
		z1.addBarrio(b18);
		z1.addBarrio(b19);
		z1.addBarrio(b20);
		z1.addBarrio(b21);
		z1.addBarrio(b22);
		z1.addBarrio(b23);
		z1.addBarrio(b24);
		z1.addBarrio(b25);
		z1.addBarrio(b26);
		
		z2.setNombre("Zona Sur");
		z2.addBarrio(b28);
		z2.addBarrio(b29);
		z2.addBarrio(b30);
		z2.addBarrio(b31);
		z2.addBarrio(b32);
		z2.addBarrio(b33);
		z2.addBarrio(b34);
		z2.addBarrio(b35);
		z2.addBarrio(b36);
		z2.addBarrio(b37);
		z2.addBarrio(b38);
		z2.addBarrio(b39);
		
		z3.setNombre("Centro");
		z3.addBarrio(b4);
		z3.addBarrio(b5);
		z3.addBarrio(b40);
		z3.addBarrio(b41);
		z3.addBarrio(b42);
		z3.addBarrio(b43);
		z3.addBarrio(b44);
		z3.addBarrio(b45);
		
		z4.setNombre("Zona Residencial");
		z4.addBarrio(b46);
		z4.addBarrio(b47);
		z4.addBarrio(b48);
		z4.addBarrio(b49);
		z4.addBarrio(b50);
		z4.addBarrio(b51);
		z4.addBarrio(b52);
		
		z5.setNombre("Zona Alta");
		z5.addBarrio(b53);
		z5.addBarrio(b54);
		z5.addBarrio(b55);
		z5.addBarrio(b56);
		z5.addBarrio(b57);
		z5.addBarrio(b58);
		z5.addBarrio(b59);
		
		z6.setNombre("Zona de las Sierras");
		z6.addBarrio(b6);
		z6.addBarrio(b7);

		
		z7.setNombre("Zona Pampeana");
		z7.addBarrio(b60);


		
		z8.setNombre("Zona Conurbana");
		z8.addBarrio(b8);
		z8.addBarrio(b9);
		
		z9.setNombre("Zona Bajo Flores");
		z9.addBarrio(b10);
		z9.addBarrio(b11);

		
		z10.setNombre("Zona Palermo");
		z10.addBarrio(b12);
		z10.addBarrio(b13);
		z10.addBarrio(b14);
		z10.addBarrio(b15);
		z10.addBarrio(b16);
		z10.addBarrio(b17);
		
		dao.save(z1);
		dao.save(z2);
		dao.save(z3);
		dao.save(z4);
		dao.save(z5);
		dao.save(z6);
		dao.save(z7);
		dao.save(z8);
		dao.save(z9);
		dao.save(z10);
		
		//Los barrios ya estan guardados, en el test ProvinciaLocalidadBarrioTest
//		dao.update(b1);
//		dao.update(b2);
//		dao.update(b3);
//		dao.update(b4);
//		dao.update(b5);
//		dao.update(b6);
//		dao.update(b7);
//		dao.update(b8);
//		dao.update(b9);
//		dao.update(b10);
//		dao.update(b11);
//		dao.update(b12);
//		dao.update(b13);
//		dao.update(b14);
//		dao.update(b15);
//		dao.update(b16);
//		dao.update(b17);
//		dao.update(b18);
//		dao.update(b19);
//		dao.update(b20);
//		dao.update(b21);
//		dao.update(b22);
//		dao.update(b23);
//		dao.update(b24);
//		dao.update(b25);
//		dao.update(b26);
//		dao.update(b27);
//		dao.update(b28);
//		dao.update(b29);
//		dao.update(b30);
//		dao.update(b31);
//		dao.update(b32);
//		dao.update(b33);
//		dao.update(b34);
//		dao.update(b35);
//		dao.update(b36);
//		dao.update(b37);
//		dao.update(b38);
//		dao.update(b39);
//		dao.update(b40);
//		dao.update(b41);
//		dao.update(b42);
//		dao.update(b43);
//		dao.update(b44);
//		dao.update(b45);
//		dao.update(b46);
//		dao.update(b47);
//		dao.update(b48);
//		dao.update(b49);
//		dao.update(b50);
//		dao.update(b51);
//		dao.update(b52);
//		dao.update(b53);
//		dao.update(b54);
//		dao.update(b55);
//		dao.update(b56);
//		dao.update(b57);
//		dao.update(b58);
//		dao.update(b59);
//		dao.update(b60);
//		
		
		t.commit();
  }
}
