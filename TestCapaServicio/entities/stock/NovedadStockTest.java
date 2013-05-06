package stock;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import produccion.Insumo;
import utils.FechaUtils;

import bsh.util.Util;

import com.bm.testsuite.BaseSessionBeanFixture;

public class NovedadStockTest extends BaseSessionBeanFixture<DaoBaseEJB3unit>{

	public NovedadStockTest() {
		super(DaoBaseEJB3unit.class,new Class[]{});
	}

	public void testSave(){
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		DaoBaseEJB3unit dao = this.getBeanToTest();
		
		TipoNovedadStock t1 = new TipoNovedadStock();t1.setId(1l);
		TipoNovedadStock t2 = new TipoNovedadStock();t1.setId(2l);
		TipoNovedadStock t3 = new TipoNovedadStock();t1.setId(3l);
		
		Insumo i1  = new Insumo();i1.setId(1L);
		Insumo i2  = new Insumo();i2.setId(2L);
		Insumo i3  = new Insumo();i3.setId(3L);
		Insumo i4  = new Insumo();i4.setId(4L);
		Insumo i5  = new Insumo();i5.setId(5L);
		Insumo i6  = new Insumo();i6.setId(6L);
		Insumo i7  = new Insumo();i7.setId(7L);
		Insumo i8  = new Insumo();i8.setId(8L);
		Insumo i9  = new Insumo();i9.setId(9L);
		Insumo i10 = new Insumo();i10.setId(1L);
		UnidadMedidaInsumoCompra unidadMedida1 = new UnidadMedidaInsumoCompra();
		unidadMedida1.setId(1l);
		UnidadMedidaInsumoCompra unidadMedida2 = new UnidadMedidaInsumoCompra();
		unidadMedida2.setId(3l);
		UnidadMedidaInsumoCompra unidadMedida3 = new UnidadMedidaInsumoCompra();
		unidadMedida3.setId(4l);
		UnidadMedidaInsumoCompra unidadMedida4 = new UnidadMedidaInsumoCompra();
		unidadMedida4.setId(6l);
		UnidadMedidaInsumoCompra unidadMedida5 = new UnidadMedidaInsumoCompra();
		unidadMedida5.setId(8l);
		UnidadMedidaInsumoCompra unidadMedida6 = new UnidadMedidaInsumoCompra();
		unidadMedida6.setId(7l);
		
		NovedadStock novedad1 = new NovedadStock();
		novedad1.setFecha(FechaUtils.getFecha(1, 10, 2009));
		novedad1.setTipo(t1);
		novedad1.setCantidad(5);
		novedad1.setDescripcion("");
		novedad1.setInsumo(i5);
		novedad1.setUnidadMedida(unidadMedida1);
		
		NovedadStock novedad2 = new NovedadStock();
		novedad2.setFecha(FechaUtils.getFecha(7, 10, 2009));
		novedad2.setTipo(t1);
		novedad2.setCantidad(5);
		novedad2.setDescripcion("");
		novedad2.setInsumo(i1);
		novedad2.setUnidadMedida(unidadMedida2);
		
		NovedadStock novedad3 = new NovedadStock();
		novedad3.setFecha(FechaUtils.getFecha(1, 10, 2009));
		novedad3.setTipo(t1);
		novedad3.setCantidad(5);
		novedad3.setDescripcion("");
		novedad3.setInsumo(i2);
		novedad2.setUnidadMedida(unidadMedida6);
		
		NovedadStock novedad4 = new NovedadStock();
		novedad4.setFecha(FechaUtils.getFecha(1, 10, 2009));
		novedad4.setTipo(t1);
		novedad4.setCantidad(5);
		novedad4.setDescripcion("");
		novedad4.setInsumo(i3);
		novedad2.setUnidadMedida(unidadMedida4);
		
		NovedadStock novedad5 = new NovedadStock();
		novedad5.setFecha(FechaUtils.getFecha(1, 10, 2009));
		novedad5.setTipo(t1);
		novedad5.setCantidad(5);
		novedad5.setDescripcion("");
		novedad5.setInsumo(i4);
		novedad2.setUnidadMedida(unidadMedida5);
		
		dao.save(novedad1);
		dao.save(novedad2);
		dao.save(novedad3);
		dao.save(novedad4);
		dao.save(novedad5);
		
		t.commit();
	}
}
