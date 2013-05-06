package base;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;

import com.bm.testsuite.BaseSessionBeanFixture;


public class ProvinciaLocalidadBarrioTest extends BaseSessionBeanFixture<DaoBaseEJB3unit>{

	public ProvinciaLocalidadBarrioTest() {
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		Provincia p1 = new Provincia();
		Provincia p2 = new Provincia();
		Provincia p3 = new Provincia();
	
		
		p1.setNombre("Córdoba");
		p2.setNombre("Buenos Aires");
		p3.setNombre("Santa Fe");
		
		
		Localidad l1 = new Localidad();
		Localidad l2 = new Localidad();
		Localidad l3 = new Localidad();
		Localidad l4 = new Localidad();
		Localidad l5 = new Localidad();
		Localidad l6 = new Localidad();
		Localidad l7 = new Localidad();
		Localidad l8 = new Localidad();
		Localidad l9 = new Localidad();
		
		
		l1.setNombre("Córdoba");
		l2.setNombre("Alta Gracia");
		l3.setNombre("Bell Ville");
		
		l4.setNombre("Capital Federal");
		l5.setNombre("Bahia Blanca");
		l6.setNombre("La Plata");
		
		l7.setNombre("Santa Fe");
		l8.setNombre("Rosario");
		l9.setNombre("Pergamino");
		
		
		
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
		Barrio b61 = new Barrio ();
		
		
		b1.setNombre("Nueva Cordoba");
		b2.setNombre("Jardin");
		b3.setNombre("Ampliacion San Pablo");
		
		b4.setNombre("El Portal");
		b5.setNombre("La Estacion");
		
		b6.setNombre("Country");
		b7.setNombre("Mainero");
		
		b8.setNombre("Palermo");
		b9.setNombre("Recoleta");
		
		b10.setNombre("La Calesita");
		b11.setNombre("La Rotonda");
		
		b12.setNombre("Junior");
		b13.setNombre("Avellaneda");
		
		b14.setNombre("La Tasita");
		b15.setNombre("Las Americas");
		
		b16.setNombre("Defensor");
		b17.setNombre("Ferro");
		
		b18.setNombre("Platense");
		b19.setNombre("Boedo");
		
		b20.setNombre("Quilmes");
		b21.setNombre("Gral Paz");
		
		b22.setNombre("Caminito");
		b23.setNombre("Alta Cordoba");
		
		b24.setNombre("Forja");
		b25.setNombre("Yofre");
		
		b26.setNombre("Bell");
		b27.setNombre("Talleres");
		
		b28.setNombre("Defensores de Belgrano");
		b29.setNombre("Nunez");
		
		b30.setNombre("Argentinos");
		b31.setNombre("Central");
		
		b32.setNombre("Guemez");
		b33.setNombre("Buenos Aires");
		
		b34.setNombre("Villa Zoila");
		b35.setNombre("Ceferino");
		
		b36.setNombre("Bella Vista");
		b37.setNombre("Villa 21");
		
		b38.setNombre("Fuerte Apache");
		b39.setNombre("Jorge Newbery");
		
		b40.setNombre("Las Violetas");
		b41.setNombre("Villa Las Rosas");
		
		b42.setNombre("Lalala");
		b43.setNombre("Villa Elisa");
		b44.setNombre("Barrio Norte");
		
		b45.setNombre("Catagnino");
		b46.setNombre("Barrio Escuela");
		
		b47.setNombre("Paso de los Andes");
		b48.setNombre("Arguello");
		
		b49.setNombre("Carola Lorenzini");
		b50.setNombre("Los Naranjos");
		
		b51.setNombre("Los Platanos");
		b52.setNombre("Barrio Centro");
		
		b53.setNombre("Muller");
		b54.setNombre("Cerro Las Rosas");
		
		b55.setNombre("Urca");
		b56.setNombre("Las Delicias");
		
		b57.setNombre("Padre Claret");
		b58.setNombre("Los Palomos");
		
		b59.setNombre("Las Achuras");
		b60.setNombre("Tomuer Almeyda");
		b61.setNombre("Malanotte");
		
		l1.addBarrio(b1);
		l1.addBarrio(b2);
		l1.addBarrio(b3);
		
		l2.addBarrio(b4);
		l2.addBarrio(b5);
		
		l3.addBarrio(b6);
		l3.addBarrio(b7);
		
		l4.addBarrio(b8);
		l4.addBarrio(b9);
		
		l5.addBarrio(b10);
		l5.addBarrio(b11);
		
		l6.addBarrio(b12);
		l6.addBarrio(b13);
		
		l7.addBarrio(b14);
		l7.addBarrio(b15);
		
		l8.addBarrio(b16);
		l8.addBarrio(b17);
		
		l9.addBarrio(b18);
		l9.addBarrio(b19);
		
		l1.addBarrio(b20);
		l1.addBarrio(b21);
		
		l1.addBarrio(b22);
		l1.addBarrio(b23);
		
		l1.addBarrio(b24);
		l1.addBarrio(b25);
		
		l1.addBarrio(b26);
		l1.addBarrio(b27);
		
		l1.addBarrio(b28);
		l1.addBarrio(b29);
		
		l1.addBarrio(b30);
		l1.addBarrio(b31);
		
		l1.addBarrio(b32);
		l1.addBarrio(b33);
		
		l1.addBarrio(b34);
		l1.addBarrio(b35);
		
		l1.addBarrio(b36);
		l1.addBarrio(b37);
		
		l1.addBarrio(b38);
		l1.addBarrio(b39);
		
		l2.addBarrio(b40);
		l2.addBarrio(b41);
		
		l2.addBarrio(b42);
		l2.addBarrio(b43);
		
		l2.addBarrio(b44);
		l2.addBarrio(b45);
		
		l2.addBarrio(b46);
		l2.addBarrio(b47);
		
		l2.addBarrio(b48);
		l2.addBarrio(b49);
		
		l2.addBarrio(b50);
		l2.addBarrio(b51);
		
		l2.addBarrio(b52);
		l2.addBarrio(b53);
		
		l2.addBarrio(b54);
		l2.addBarrio(b55);
		
		l2.addBarrio(b56);
		l2.addBarrio(b57);
		
		l2.addBarrio(b58);
		l2.addBarrio(b59);
		
		l3.addBarrio(b60);
		l3.addBarrio(b61);
		
		p1.addLocalidad(l1);
		p1.addLocalidad(l2);
		p1.addLocalidad(l3);
		
		p2.addLocalidad(l4);
		p2.addLocalidad(l5);
		p2.addLocalidad(l6);
		
		p3.addLocalidad(l7);
		p3.addLocalidad(l8);
		p3.addLocalidad(l9);
		
		
		
		dao.save(p1);
		dao.save(p2);
		dao.save(p3);
		
		
		dao.save(l1);
		dao.save(l2);
		dao.save(l3);
		dao.save(l4);
		dao.save(l5);
		dao.save(l6);
		dao.save(l7);
		dao.save(l8);
		dao.save(l9);
		
		
		
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
		z6.addBarrio(b6);

		
		z7.setNombre("Zona Pampeana");
		z7.addBarrio(b60);
		z7.addBarrio(b61);

		
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
		dao.save(b1);
		dao.save(b2);
		dao.save(b3);
		dao.save(b4);
		dao.save(b5);
		dao.save(b6);
		dao.save(b7);
		dao.save(b8);
		dao.save(b9);
		dao.save(b10);
		dao.save(b11);
		dao.save(b12);
		dao.save(b13);
		dao.save(b14);
		dao.save(b15);
		dao.save(b16);
		dao.save(b17);
		dao.save(b18);
		dao.save(b19);
		dao.save(b20);
		dao.save(b21);
		dao.save(b22);
		dao.save(b23);
		dao.save(b24);
		dao.save(b25);
		dao.save(b26);
		dao.save(b27);
		dao.save(b28);
		dao.save(b29);
		dao.save(b30);
		dao.save(b31);
		dao.save(b32);
		dao.save(b33);
		dao.save(b34);
		dao.save(b35);
		dao.save(b36);
		dao.save(b37);
		dao.save(b38);
		dao.save(b39);
		dao.save(b40);
		dao.save(b41);
		dao.save(b42);
		dao.save(b43);
		dao.save(b44);
		dao.save(b45);
		dao.save(b46);
		dao.save(b47);
		dao.save(b48);
		dao.save(b49);
		dao.save(b50);
		dao.save(b51);
		dao.save(b52);
		dao.save(b53);
		dao.save(b54);
		dao.save(b55);
		dao.save(b56);
		dao.save(b57);
		dao.save(b58);
		dao.save(b59);
		dao.save(b60);
		dao.save(b61);
		t.commit();
	}
}