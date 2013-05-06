package produccion;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import utils.FechaUtils;
import venta.Producto;

import com.bm.testsuite.BaseSessionBeanFixture;

public class RecetaDetalleRecetaTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	public RecetaDetalleRecetaTest(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		Insumo insumoHarina  = new Insumo();insumoHarina.setId(1L);
		Insumo insumoGrasa  = new Insumo();insumoGrasa.setId(2L);
		Insumo insumoLevadura  = new Insumo();insumoLevadura.setId(3L);
		Insumo insumoSal  = new Insumo();insumoSal.setId(4L);
		Insumo insumoAzucar  = new Insumo();insumoAzucar.setId(5L);
		Insumo insumoHuevos  = new Insumo();insumoHuevos.setId(6L);
		Insumo insumoAceiteMaiz  = new Insumo();insumoAceiteMaiz.setId(7L);
		Insumo insumoManteca  = new Insumo();insumoManteca.setId(8L);
		Insumo insumoLeche  = new Insumo();insumoLeche.setId(9L);
		Insumo insumoPataDeVaca = new Insumo(); insumoPataDeVaca.setId(10L);
		Insumo insumoSalvado = new Insumo();insumoSalvado.setId(11L);
		Insumo insumoDulceDeLeche = new Insumo();insumoDulceDeLeche.setId(12L);
		Insumo insumoChocolate = new Insumo(); insumoChocolate.setId(13L);
		
		//-------------RECETA 1----------------------------------------
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		Receta r = new Receta();
		r.setNombre("Receta de Pan Frances");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		
		Producto p = new Producto();
		p.setId(1L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		RecetaDetalle d1 = new RecetaDetalle();
		RecetaDetalle d2 = new RecetaDetalle();
		RecetaDetalle d3 = new RecetaDetalle();
		RecetaDetalle d4 = new RecetaDetalle();
		
		d1.setInsumo(insumoHarina);d1.setCantidad(10F);
		d2.setInsumo(insumoLevadura);d2.setCantidad(8.56F);
		d3.setInsumo(insumoAceiteMaiz);d3.setCantidad(0.25F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 2----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pan Mignon");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		
		p = new Producto();
		p.setId(5L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoHarina);d1.setCantidad(9F);
		d2.setInsumo(insumoGrasa);d2.setCantidad(9F);
		d3.setInsumo(insumoLevadura);d3.setCantidad(0.5F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 3----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pan Negro");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(9L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoSalvado);d1.setCantidad(0.3F);
		d2.setInsumo(insumoAceiteMaiz);d2.setCantidad(0.2F);
		d3.setInsumo(insumoHarina);d3.setCantidad(9F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 4----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Facturas con dulce de leche rendidora");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(13L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		d4 = new RecetaDetalle();
		
		d1.setInsumo(insumoHarina);d1.setCantidad(10F);
		d2.setInsumo(insumoAzucar);d2.setCantidad(1000F);
		d3.setInsumo(insumoManteca);d3.setCantidad(4.5F);
		d4.setInsumo(insumoDulceDeLeche);d4.setCantidad(1000F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);r.addDetalle(d4);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 5----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Media Luna Dulce");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(17L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoManteca);d1.setCantidad(1000F);
		d2.setInsumo(insumoAzucar);d2.setCantidad(1000F);
		d3.setInsumo(insumoHarina);d3.setCantidad(8F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 6----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Media Luna de grasa salada");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(2L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoGrasa);d1.setCantidad(1F);
		d2.setInsumo(insumoHarina);d2.setCantidad(9F);
		d3.setInsumo(insumoSal);d3.setCantidad(500F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 7----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Selva Negra");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(6L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		d4 = new RecetaDetalle();
		
		d1.setInsumo(insumoChocolate);d1.setCantidad(2000F);
		d2.setInsumo(insumoHarina);d2.setCantidad(8F);
		d3.setInsumo(insumoManteca);d3.setCantidad(500F);
		d4.setInsumo(insumoAzucar);d4.setCantidad(1000F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);r.addDetalle(d4);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 8----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Mil Hojas");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(10L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoHarina);d1.setCantidad(8F);
		d2.setInsumo(insumoAzucar);d2.setCantidad(3000F);
		d3.setInsumo(insumoDulceDeLeche);d3.setCantidad(2000F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 9----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pan de Salvado");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(false);
		p = new Producto();
		p.setId(9L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoSalvado);d1.setCantidad(0.3F);
		d2.setInsumo(insumoLevadura);d2.setCantidad(200F);
		d3.setInsumo(insumoHarina);d3.setCantidad(9F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 10----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta Bombas de crema");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(18L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoHuevos);d1.setCantidad(7F);
		d2.setInsumo(insumoGrasa);d2.setCantidad(1F);
		d3.setInsumo(insumoHarina);d3.setCantidad(8F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 11----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Palmeritas");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(3L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoGrasa);d1.setCantidad(1F);
		d2.setInsumo(insumoManteca);d2.setCantidad(1000F);
		d3.setInsumo(insumoHarina);d3.setCantidad(8F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 12----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pan Criollo");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(7L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoHarina);d1.setCantidad(8F);
		d2.setInsumo(insumoSal);d2.setCantidad(1000F);
		d3.setInsumo(insumoManteca);d3.setCantidad(1000F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 13----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pepitas");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(11L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoAzucar);d1.setCantidad(1000F);
		d2.setInsumo(insumoChocolate);d2.setCantidad(2000F);
		d3.setInsumo(insumoHarina);d3.setCantidad(8F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 14----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pretzel");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(15L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoHarina);d1.setCantidad(8F);
		d2.setInsumo(insumoSal);d2.setCantidad(2000F);
		d3.setInsumo(insumoLeche);d3.setCantidad(2F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 15----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pata Flambeada 10 Personas - Vaca");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(19L);
		
		r.setProducto(p);
		r.setCantidadResultante(1);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoPataDeVaca);d1.setCantidad(1F);
		d2.setInsumo(insumoSal);d2.setCantidad(500F);
		d3.setInsumo(insumoAceiteMaiz);d3.setCantidad(1F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 16----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Pata Flambeada 20 Personas - Vacas");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(4L);
		
		r.setProducto(p);
		r.setCantidadResultante(1);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoPataDeVaca);d1.setCantidad(2F);
		d2.setInsumo(insumoSal);d2.setCantidad(900F);
		d3.setInsumo(insumoAceiteMaiz);d3.setCantidad(1.5F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 17----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Empanadas Arabes");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(8L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoAzucar);d1.setCantidad(5F);
		d2.setInsumo(insumoGrasa);d2.setCantidad(1F);
		d3.setInsumo(insumoManteca);d3.setCantidad(4F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 18----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Empanadas Criollas Dulces");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(12L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoHarina);d1.setCantidad(1F);
		d2.setInsumo(insumoAzucar);d2.setCantidad(6F);
		d3.setInsumo(insumoGrasa);d3.setCantidad(3F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 19----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Empanadas Criollas Saladas");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(16L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoGrasa);d1.setCantidad(1F);
		d2.setInsumo(insumoSal);d2.setCantidad(6F);
		d3.setInsumo(insumoAzucar);d3.setCantidad(7F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 20----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Colacioness");
		r.setDescripcion("Receta Clasica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(20L);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoAceiteMaiz);d1.setCantidad(7F);
		d2.setInsumo(insumoGrasa);d2.setCantidad(2F);
		d3.setInsumo(insumoHuevos);d3.setCantidad(8F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 21----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Facturas con crema");
		r.setDescripcion("Receta Artesanal");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(21L);
		r.setPredeterminada(true);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoGrasa);d1.setCantidad(1F);
		d2.setInsumo(insumoLevadura);d2.setCantidad(5F);
		d3.setInsumo(insumoSal);d3.setCantidad(7F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 22----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta de Chalinas de Salvado");
		r.setDescripcion("Receta clásica");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(22L);
		r.setPredeterminada(true);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoLeche);d1.setCantidad(8F);
		d2.setInsumo(insumoHarina);d2.setCantidad(2F);
		d3.setInsumo(insumoGrasa);d3.setCantidad(4F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 23----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta artesanal de Colaciones");
		r.setDescripcion("Receta Artesanal");
		r.setPredeterminada(false);
		p = new Producto();
		p.setId(20L);
		r.setPredeterminada(false);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoAceiteMaiz);d1.setCantidad(7F);
		d2.setInsumo(insumoGrasa);d2.setCantidad(2F);
		d3.setInsumo(insumoHuevos);d3.setCantidad(8F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 24----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta Holandesa de Colaciones");
		r.setDescripcion("Receta Holandesa, especial para días de humedad");
		r.setPredeterminada(false);
		p = new Producto();
		p.setId(20L);
		r.setPredeterminada(false);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoSal);d1.setCantidad(9F);
		d2.setInsumo(insumoHarina);d2.setCantidad(1F);
		d3.setInsumo(insumoLevadura);d3.setCantidad(2F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 25----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta artesanal de Media Luna Dulce");
		r.setDescripcion("Receta Artesanal");
		r.setPredeterminada(false);
		p = new Producto();
		p.setId(17L);
		r.setPredeterminada(false);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoGrasa);d1.setCantidad(9F);
		d2.setInsumo(insumoHarina);d2.setCantidad(2F);
		d3.setInsumo(insumoHuevos);d3.setCantidad(1F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
		
		//-------------RECETA 26----------------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		r = new Receta();
		r.setNombre("Receta Tiramisú como de la abuela");
		r.setDescripcion("Receta Artesanal, bien casera");
		r.setPredeterminada(true);
		p = new Producto();
		p.setId(14L);
		r.setPredeterminada(true);
		
		r.setProducto(p);
		r.setCantidadResultante(10);
		r.setFechaAlta(FechaUtils.getFecha(15,9,2009));
		
		d1 = new RecetaDetalle();
		d2 = new RecetaDetalle();
		d3 = new RecetaDetalle();
		
		d1.setInsumo(insumoGrasa);d1.setCantidad(9F);
		d2.setInsumo(insumoHarina);d2.setCantidad(2F);
		d3.setInsumo(insumoHuevos);d3.setCantidad(1F);
		
		r.addDetalle(d1);r.addDetalle(d2);r.addDetalle(d3);
		
		dao.save(r);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		
		t.commit();
		//-------------------------------------------------------------
	}
}
