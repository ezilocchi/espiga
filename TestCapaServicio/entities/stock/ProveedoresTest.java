package stock;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import produccion.Insumo;

import base.Barrio;

import com.bm.testsuite.BaseSessionBeanFixture;

public class ProveedoresTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {

	public ProveedoresTest() {
		super(DaoBaseEJB3unit.class,new Class[]{});
	}

	public void testSave(){
		Insumo i1  = new Insumo();i1.setId(1L);
		Insumo i2  = new Insumo();i2.setId(2L);
		Insumo i3  = new Insumo();i3.setId(3L);
		Insumo i4  = new Insumo();i4.setId(4L);
		Insumo i5  = new Insumo();i5.setId(5L);
		Insumo i6  = new Insumo();i6.setId(6L);
		Insumo i7  = new Insumo();i7.setId(7L);
		Insumo i8  = new Insumo();i8.setId(8L);
		Insumo i9  = new Insumo();i9.setId(9L);
		Insumo i10 = new Insumo();i10.setId(10L);
		Insumo i11 = new Insumo();i11.setId(11L);
		Insumo i12 = new Insumo();i12.setId(12L);
		Insumo i13 = new Insumo();i13.setId(13L);
		Insumo i14 = new Insumo();i14.setId(14L);
		
		
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		DaoBaseEJB3unit dao = this.getBeanToTest();
		
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
		
		
		Proveedor proveedor1 = new Proveedor();
		proveedor1.setRazonSocial("Mi Molino");
		proveedor1.setCuit("20-55988769-2");
		proveedor1.setMail("mimolino@gmail.com");
		proveedor1.setTelefono("4652378");		
		proveedor1.getDireccion().setBarrio(b1);
		proveedor1.getDireccion().setCalle("9 de Julio");
		proveedor1.getDireccion().setNumero(2654);
		proveedor1.addInsumo(i1);
		proveedor1.addInsumo(i2);
		
		Proveedor proveedor2 = new Proveedor();
		proveedor2.setRazonSocial("La Barata");
		proveedor2.setCuit("21-53984767-4");
		proveedor2.setMail("labarata@gmail.com");
		proveedor2.setTelefono("4642577");		
		proveedor2.getDireccion().setBarrio(b3);
		proveedor2.getDireccion().setCalle("25 de Mayo");
		proveedor2.getDireccion().setNumero(3165);
		proveedor2.addInsumo(i3);
		proveedor2.addInsumo(i4);
		proveedor2.addInsumo(i9);
		
		Proveedor proveedor3 = new Proveedor();
		proveedor3.setRazonSocial("Repartidores del Sur");
		proveedor3.setCuit("20-55988769-2");
		proveedor3.setMail("repartidores.sur@gmail.com");
		proveedor3.setTelefono("4652378");		
		proveedor3.getDireccion().setBarrio(b1);
		proveedor3.getDireccion().setCalle("9 de Julio");
		proveedor3.getDireccion().setNumero(2654);
		proveedor3.addInsumo(i1);
		proveedor3.addInsumo(i3);
		proveedor3.addInsumo(i5);
		
		Proveedor proveedor4 = new Proveedor();
		proveedor4.setRazonSocial("Diarco");
		proveedor4.setCuit("20-55988769-2");
		proveedor4.setMail("diarco@gmail.com");
		proveedor4.setTelefono("4652378");		
		proveedor4.getDireccion().setBarrio(b1);
		proveedor4.getDireccion().setCalle("9 de Julio");
		proveedor4.getDireccion().setNumero(2654);
		proveedor4.addInsumo(i2);		
		proveedor4.addInsumo(i6);		
		
		Proveedor proveedor5 = new Proveedor();
		proveedor5.setRazonSocial("Schestel y asociados");
		proveedor5.setCuit("20-55988769-2");
		proveedor5.setMail("schestel@gmail.com");
		proveedor5.setTelefono("4652378");		
		proveedor5.getDireccion().setBarrio(b1);
		proveedor5.getDireccion().setCalle("9 de Julio");
		proveedor5.getDireccion().setNumero(2654);
		proveedor5.addInsumo(i4);		
		proveedor5.addInsumo(i7);		
		proveedor5.addInsumo(i11);
		proveedor5.addInsumo(i12);
		proveedor5.addInsumo(i13);
		proveedor5.addInsumo(i14);
		
		Proveedor proveedor6 = new Proveedor();
		proveedor6.setRazonSocial("corcat srl");
		proveedor6.setCuit("20-55988769-2");
		proveedor6.setMail("corcat.srl@gmail.com");
		proveedor6.setTelefono("4652378");		
		proveedor6.getDireccion().setBarrio(b1);
		proveedor6.getDireccion().setCalle("9 de Julio");
		proveedor6.getDireccion().setNumero(2654);
		proveedor6.addInsumo(i2);		
		proveedor6.addInsumo(i3);
		proveedor6.addInsumo(i5);		
		proveedor6.addInsumo(i8);
		proveedor6.addInsumo(i14);
		
		dao.save(proveedor1);
		dao.save(proveedor2);
		dao.save(proveedor3);
		dao.save(proveedor4);
		dao.save(proveedor5);
		dao.save(proveedor6);
		
		t.commit();
	}
}
