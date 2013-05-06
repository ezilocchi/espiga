package venta;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;

import com.bm.testsuite.BaseSessionBeanFixture;

public class ProductoTipoProductoTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	public ProductoTipoProductoTest(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		UnidadMedidaPoducto kg     = UnidadMedidaPoducto.KG;
//		UnidadMedidaPoducto gramo  = UnidadMedidaPoducto.GRAMO;
//		UnidadMedidaPoducto litro  = UnidadMedidaPoducto.LITRO;
//		UnidadMedidaPoducto cm3    = UnidadMedidaPoducto.CM3;
		UnidadMedidaPoducto decena = UnidadMedidaPoducto.DECENA;
		UnidadMedidaPoducto docena = UnidadMedidaPoducto.DOCENA;
		UnidadMedidaPoducto unidad = UnidadMedidaPoducto.UNIDAD;
		
		TipoProducto t1 = new TipoProducto();
		TipoProducto t2 = new TipoProducto();
		TipoProducto t3 = new TipoProducto();
		TipoProducto t4 = new TipoProducto();
		TipoProducto t5 = new TipoProducto();
		TipoProducto t6 = new TipoProducto();
		TipoProducto t7 = new TipoProducto();
		TipoProducto t8 = new TipoProducto();
		
		t1.setNombre("Pan");
		t1.setDescripcion("Pan varios");
		
		t2.setNombre("Postres");
		t2.setDescripcion("Tortas y otros postres");
		
		t3.setNombre("Masas Finas");
		t3.setDescripcion("Masas Finas");
		
		t4.setNombre("Dulces secos");
		t4.setDescripcion("Galletas y otros");
		
		t5.setNombre("Comidas");
		t5.setDescripcion("Patas Flambeadas - Empanadas");
		
		t6.setNombre("Facturas");
		t6.setDescripcion("Varios tipos");
		
		t7.setNombre("Criollos");
		t7.setDescripcion("Varios tipos");
		
		t8.setNombre("Chalinas");
		t8.setDescripcion("Varias");
		
		Producto p1 = new Producto(); Producto p11 = new Producto();
		Producto p2 = new Producto(); Producto p12 = new Producto();
		Producto p3 = new Producto(); Producto p13 = new Producto();
		Producto p4 = new Producto(); Producto p14 = new Producto();
		Producto p5 = new Producto(); Producto p15 = new Producto();
		Producto p6 = new Producto(); Producto p16 = new Producto();
		Producto p7 = new Producto(); Producto p17 = new Producto();
		Producto p8 = new Producto(); Producto p18 = new Producto();
		Producto p9 = new Producto(); Producto p19 = new Producto();
		Producto p10 = new Producto();Producto p20 = new Producto();
		Producto p21 = new Producto();Producto p22 = new Producto();
		
		p1.setNombre("Pan Frances");
		p1.setDescripcion("Pan Frances");
		p1.setTipo(t1);
		p1.setUnidadMedida(kg);
		p1.setPrecioLista(1.90F);
		
		p2.setNombre("Pan Mignon");
		p2.setDescripcion("Pan Mignon");
		p2.setTipo(t1);
		p2.setUnidadMedida(kg);
		p2.setPrecioLista(1.80F);
		
		p3.setNombre("Pan Negro");
		p3.setDescripcion("Pan Negro");
		p3.setTipo(t1);
		p3.setUnidadMedida(kg);
		p3.setPrecioLista(2.60F);
		
		p4.setNombre("Facturas con dulce de Leche");
		p4.setDescripcion("Forma de media luna");
		p4.setTipo(t6);
		p4.setUnidadMedida(docena);
		p4.setPrecioLista(5.00F);
		
		p5.setNombre("Media Luna Dulce");
		p5.setDescripcion("Media Luna");
		p5.setTipo(t6);
		p5.setUnidadMedida(docena);
		p5.setPrecioLista(4.50F);
		
		p6.setNombre("Media Luna Salada");
		p6.setDescripcion("");
		p6.setTipo(t6);
		p6.setUnidadMedida(docena);
		p6.setPrecioLista(6.00F);
		
		p7.setNombre("Selva Negra de 2 kg");
		p7.setDescripcion("Torta");
		p7.setTipo(t2);
		p7.setUnidadMedida(unidad);
		p7.setPrecioLista(28.00F);
		
		p8.setNombre("Mil Hojas 1,5 kg");
		p8.setDescripcion("Torta de hojaldre con dulce de leche");
		p8.setTipo(t2);
		p8.setUnidadMedida(unidad);
		p8.setPrecioLista(25.00F);
		
		p9.setNombre("Tiramisu");
		p9.setDescripcion("Postre");
		p9.setTipo(t2);
		p9.setUnidadMedida(kg);
		p9.setPrecioLista(19.00F);
		
		p10.setNombre("Bombas de crema");
		p10.setDescripcion("Rellenas con crema y bañadas con caramelo");
		p10.setTipo(t3);
		p10.setUnidadMedida(kg);
		p10.setPrecioLista(9.00F);
		
		p11.setNombre("Palmeritas");
		p11.setDescripcion("Galleta");
		p11.setTipo(t4);
		p11.setUnidadMedida(kg);
		p11.setPrecioLista(8.00F);
		
		p12.setNombre("Pan Criollo");
		p12.setDescripcion("Comun");
		p12.setTipo(t7);
		p12.setUnidadMedida(kg);
		p12.setPrecioLista(3.50F);
		
		p13.setNombre("Pepitas");
		p13.setDescripcion("Galleta");
		p13.setTipo(t4);
		p13.setUnidadMedida(kg);
		p13.setPrecioLista(6.00F);
		
		p14.setNombre("Pretzel");
		p14.setDescripcion("Galleta");
		p14.setTipo(t4);
		p14.setUnidadMedida(kg);
		p14.setPrecioLista(11.40F);
		
		p15.setNombre("Pata Flambeada 10 Personas - Vaca");
		p15.setDescripcion("Pata con 40 panes y 4 salsas");
		p15.setTipo(t5);
		p15.setUnidadMedida(unidad);
		p15.setPrecioLista(150.00F);
		
		p16.setNombre("Pata Flambeada 20 Personas - Vacas");
		p16.setDescripcion("Pata con 80 panes y 6 salsas");
		p16.setTipo(t5);
		p16.setUnidadMedida(unidad);
		p16.setPrecioLista(210.00F);
		
		p17.setNombre("Empanadas Arabes");
		p17.setDescripcion("Empanadas Arabes");
		p17.setTipo(t5);
		p17.setUnidadMedida(docena);
		p17.setPrecioLista(15.00F);
		
		p18.setNombre("Empanadas Criollas Dulces");
		p18.setDescripcion("Empanadas Criollas");
		p18.setTipo(t5);
		p18.setUnidadMedida(docena);
		p18.setPrecioLista(14.00F);
		
		p19.setNombre("Empanadas Criollas Saladas");
		p19.setDescripcion("Empanadas Criollas");
		p19.setTipo(t5);
		p19.setUnidadMedida(docena);
		p19.setPrecioLista(14.00F);
		
		p20.setNombre("Colaciones");
		p20.setDescripcion("Colaciones");
		p20.setTipo(t3);
		p20.setUnidadMedida(kg);
		p20.setPrecioLista(8.50F);
		
		p21.setNombre("Factura con crema");
		p21.setDescripcion("Crema pastelera");
		p21.setTipo(t6);
		p21.setUnidadMedida(docena);
		p21.setPrecioLista(12.00F);
		
		p22.setNombre("Chalinas de salvado");
		p22.setDescripcion("");
		p22.setTipo(t8);
		p22.setUnidadMedida(docena);
		p22.setPrecioLista(12.00F);
		
//		dao.save(kg);
//		dao.save(gramo);
//		dao.save(unidad);
//		dao.save(litro);
//		dao.save(cm3);
//		dao.save(decena);
//		dao.save(docena);
		
		dao.save(t1);
		dao.save(t2);
		dao.save(t3);
		dao.save(t4);
		dao.save(t5);
		dao.save(t6);
		dao.save(t7);
		dao.save(t8);
		
		
		//Por favor, no alterar este orden, que va a romper la consistencia de otros test!
		dao.save(p1);
		dao.save(p6); 
		dao.save(p11);
		dao.save(p16);
		dao.save(p2);
		dao.save(p7); 
		dao.save(p12);
		dao.save(p17);
		dao.save(p3);
		dao.save(p8); 
		dao.save(p13);
		dao.save(p18);
		dao.save(p4);
		dao.save(p9); 
		dao.save(p14);
		dao.save(p19);
		dao.save(p5);
		dao.save(p10);
		dao.save(p15);
		dao.save(p20);
		dao.save(p21);
		dao.save(p22);
		
		t.commit();
	}
	
}
