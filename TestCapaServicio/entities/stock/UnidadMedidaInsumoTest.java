package stock;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import produccion.Insumo;

import com.bm.testsuite.BaseSessionBeanFixture;

public class UnidadMedidaInsumoTest extends BaseSessionBeanFixture<DaoBaseEJB3unit>{

	public UnidadMedidaInsumoTest() {
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
		
		UnidadMedidaInsumoCompra unidadMedida1 = new UnidadMedidaInsumoCompra();
		unidadMedida1.setNombre("Bolsa 10Kg Azucar");
		unidadMedida1.setDescripcion("Bolsa de Azucar Ledesma de 10Kg");
		unidadMedida1.setCantidad(10000f);
		unidadMedida1.setInsumo(i5);
		unidadMedida1.setPrecioUnitario(10f);
		
		UnidadMedidaInsumoCompra unidadMedida2 = new UnidadMedidaInsumoCompra();
		unidadMedida2.setNombre("Bolsa 5Kg Azucar");
		unidadMedida2.setDescripcion("Bolsa de Azucar Ledesma de 10Kg");
		unidadMedida2.setCantidad(5000f);
		unidadMedida2.setInsumo(i5);
		unidadMedida2.setPrecioUnitario(6f);
		unidadMedida2.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida3 = new UnidadMedidaInsumoCompra();
		unidadMedida3.setNombre("Bolsa 50Kg Harina");
		unidadMedida3.setCantidad(50f);
		unidadMedida3.setInsumo(i1);
		unidadMedida3.setPrecioUnitario(45f);
		unidadMedida3.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida4 = new UnidadMedidaInsumoCompra();
		unidadMedida4.setNombre("Paquete manteca 5Kg");
		unidadMedida4.setDescripcion("Manteca x 5K");
		unidadMedida4.setCantidad(5000f);
		unidadMedida4.setInsumo(i8);
		unidadMedida4.setPrecioUnitario(8.5f);
		unidadMedida4.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida5 = new UnidadMedidaInsumoCompra();
		unidadMedida5.setNombre("Paquete manteca 1Kg");		
		unidadMedida5.setCantidad(1000f);
		unidadMedida5.setInsumo(i8);
		unidadMedida5.setPrecioUnitario(2.2f);
		
		UnidadMedidaInsumoCompra unidadMedida6 = new UnidadMedidaInsumoCompra();
		unidadMedida6.setNombre("Bolsa 8Kg Levadura");
		unidadMedida6.setDescripcion("Levadura");
		unidadMedida6.setCantidad(8000f);
		unidadMedida6.setInsumo(i3);
		unidadMedida6.setPrecioUnitario(12f);
		unidadMedida6.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida7 = new UnidadMedidaInsumoCompra();
		unidadMedida7.setNombre("Grasa animal 20Kg");
		unidadMedida7.setDescripcion("Grasa");
		unidadMedida7.setCantidad(20f);
		unidadMedida7.setInsumo(i2);
		unidadMedida7.setPrecioUnitario(18.5f);
		unidadMedida7.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida8 = new UnidadMedidaInsumoCompra();
		unidadMedida8.setNombre("Sal 10Kg");
		unidadMedida8.setDescripcion("Sal");
		unidadMedida8.setCantidad(10000f);
		unidadMedida8.setInsumo(i4);
		unidadMedida8.setPrecioUnitario(9f);
		unidadMedida8.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida9 = new UnidadMedidaInsumoCompra();
		unidadMedida9.setNombre("Maple Huevos 36 Unidades");
		unidadMedida9.setDescripcion("Maple Huevos");
		unidadMedida9.setCantidad(12f);
		unidadMedida9.setInsumo(i6);
		unidadMedida9.setPrecioUnitario(10f);
		unidadMedida9.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida10 = new UnidadMedidaInsumoCompra();
		unidadMedida10.setNombre("Aceite Maiz Botella 3 Lts.");
		unidadMedida10.setDescripcion("Botella Aceite Maiz");
		unidadMedida10.setCantidad(3f);
		unidadMedida10.setInsumo(i7);
		unidadMedida10.setPrecioUnitario(13f);
		unidadMedida10.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida11 = new UnidadMedidaInsumoCompra();
		unidadMedida11.setNombre("Tonel Leche 5 Lts.");
		unidadMedida11.setDescripcion("Tonel Leche");
		unidadMedida11.setCantidad(5f);
		unidadMedida11.setInsumo(i9);
		unidadMedida11.setPrecioUnitario(17f);
		unidadMedida11.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida12 = new UnidadMedidaInsumoCompra();
		unidadMedida12.setNombre("Pata de Vaca");
		unidadMedida12.setDescripcion("Pata de Vaca");
		unidadMedida12.setCantidad(1f);
		unidadMedida12.setInsumo(i10);
		unidadMedida12.setPrecioUnitario(50f);
		unidadMedida12.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida13 = new UnidadMedidaInsumoCompra();
		unidadMedida13.setNombre("Bolsa de salvado 10 kg");
		unidadMedida13.setDescripcion("");
		unidadMedida13.setCantidad(10f);
		unidadMedida13.setInsumo(i11);
		unidadMedida13.setPrecioUnitario(50f);
		unidadMedida13.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida14 = new UnidadMedidaInsumoCompra();
		unidadMedida14.setNombre("Tarro de dulde de leche 5 kg");
		unidadMedida14.setDescripcion("");
		unidadMedida14.setCantidad(5000f);
		unidadMedida14.setInsumo(i12);
		unidadMedida14.setPrecioUnitario(5f);
		unidadMedida14.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida15 = new UnidadMedidaInsumoCompra();
		unidadMedida15.setNombre("Barra de chocolate medio kilo");
		unidadMedida15.setDescripcion("marca Águila");
		unidadMedida15.setCantidad(500f);
		unidadMedida15.setInsumo(i13);
		unidadMedida15.setPrecioUnitario(5.9f);
		unidadMedida15.setPredeterminada(true);
		
		UnidadMedidaInsumoCompra unidadMedida16 = new UnidadMedidaInsumoCompra();
		unidadMedida16.setNombre("Lata de dulce de membrillo de 1kg");
		unidadMedida16.setDescripcion("Lata 1Kg");
		unidadMedida16.setCantidad(1000f);
		unidadMedida16.setInsumo(i14);
		unidadMedida16.setPrecioUnitario(7f);
		unidadMedida16.setPredeterminada(false);
		
		UnidadMedidaInsumoCompra unidadMedida17 = new UnidadMedidaInsumoCompra();
		unidadMedida17.setNombre("Lata de dulce de membrillo de 5 kg");
		unidadMedida17.setDescripcion("Lata 5Kg");
		unidadMedida17.setCantidad(5000f);
		unidadMedida17.setInsumo(i14);
		unidadMedida17.setPrecioUnitario(30f);
		unidadMedida17.setPredeterminada(true);
		
		dao.save(unidadMedida1);
		dao.save(unidadMedida2);
		dao.save(unidadMedida3);
		dao.save(unidadMedida4);
		dao.save(unidadMedida5);
		dao.save(unidadMedida6);
		dao.save(unidadMedida7);
		dao.save(unidadMedida8);
		dao.save(unidadMedida9);
		dao.save(unidadMedida10);
		dao.save(unidadMedida11);
		dao.save(unidadMedida12);
		dao.save(unidadMedida13);
		dao.save(unidadMedida14);
		dao.save(unidadMedida15);
		dao.save(unidadMedida16);
		dao.save(unidadMedida17);
		
		
		t.commit();
	}

}

