package produccion;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import stock.estrategias.EstrategiaGestion;

import com.bm.testsuite.BaseSessionBeanFixture;

public class InsumoTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {

	public InsumoTest(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		
		UnidadMedidaInsumo kg     = UnidadMedidaInsumo.KG;
		UnidadMedidaInsumo gramo  = UnidadMedidaInsumo.GRAMO;
		UnidadMedidaInsumo litro  = UnidadMedidaInsumo.LITRO;		
		UnidadMedidaInsumo unidad = UnidadMedidaInsumo.UNIDAD;
		
		Insumo i1  = new Insumo();
		Insumo i2  = new Insumo();
		Insumo i3  = new Insumo();
		Insumo i4  = new Insumo();
		Insumo i5  = new Insumo();
		Insumo i6  = new Insumo();
		Insumo i7  = new Insumo();
		Insumo i8  = new Insumo();
		Insumo i9  = new Insumo();
		Insumo i10 = new Insumo();
		Insumo i11 = new Insumo();
		Insumo i12 = new Insumo();
		Insumo i13 = new Insumo();
		Insumo i14 = new Insumo();
		
		i1.setNombre("Harina 000");
		i1.setDescripcion("Sin levadura");
		i1.setUnidadMedida(kg);
		i1.setStockDisponible(10000);
		i1.setEstrategiaGestion(EstrategiaGestion.MANUAL);
		i1.setStockMaximo(50000);
		i1.setStockReposicion(1000);
		
		i2.setNombre("Grasa");
		i2.setDescripcion("Grasa Animal");
		i2.setUnidadMedida(kg);
		i2.setStockDisponible(2500);
		i2.setEstrategiaGestion(EstrategiaGestion.MANUAL);
		i2.setStockMaximo(5000);
		i2.setStockReposicion(800);
		
		i3.setNombre("Levadura");
		i3.setDescripcion("Levadura Vegetal");
		i3.setUnidadMedida(gramo);
		i3.setStockDisponible(5000);
		i3.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i3.setStockReposicion(10000);
		i3.setStockMaximo(50000);
		
		i4.setNombre("Sal");
		i4.setDescripcion("Sal Fina granel");
		i4.setUnidadMedida(gramo);
		i4.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i4.setStockDisponible(50000);
		i4.setStockReposicion(10000);
		i4.setStockMaximo(50000);
		
		i5.setNombre("Azucar");
		i5.setDescripcion("Azucar blanca");
		i5.setUnidadMedida(gramo);
		i5.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i5.setStockDisponible(5000);
		i5.setStockReposicion(10000);
		i5.setStockMaximo(50000);
		
		i6.setNombre("Huevos");
		i6.setDescripcion("Huevos");
		i6.setUnidadMedida(unidad);
		i6.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i6.setStockDisponible(300);
		i6.setStockReposicion(10000);
		i6.setStockMaximo(50000);
		
		i7.setNombre("Aceite Maiz");
		i7.setDescripcion("Aceite");
		i7.setUnidadMedida(litro);
		i7.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i7.setStockDisponible(22350);
		i7.setStockReposicion(10000);
		i7.setStockMaximo(50000);
		
		i8.setNombre("Manteca");
		i8.setDescripcion("Manteca");
		i8.setUnidadMedida(gramo);
		i8.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i8.setStockDisponible(50000);
		i8.setStockReposicion(10000);
		i8.setStockMaximo(50000);
		
		i9.setNombre("Leche");
		i9.setDescripcion("Leche de vaca");
		i9.setUnidadMedida(litro);
		i9.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i9.setStockDisponible(50);		
		i9.setStockReposicion(10000);
		i9.setStockMaximo(50000);
		
		i10.setNombre("Pata de Vaca");
		i10.setDescripcion("Pata de carne vacuna");
		i10.setUnidadMedida(unidad);
		i10.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i10.setStockDisponible(10);
		i10.setStockReposicion(10000);
		i10.setStockMaximo(50000);
		
		i11.setNombre("Salvado");
		i11.setDescripcion("Salvado en hebras");
		i11.setUnidadMedida(kg);
		i11.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i11.setStockDisponible(15680);
		i11.setStockReposicion(10000);
		i11.setStockMaximo(50000);
		
		i12.setNombre("Dulce de Leche");
		i12.setDescripcion("Dulce de leche repostero");
		i12.setUnidadMedida(gramo);
		i12.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i12.setStockDisponible(30000);
		i12.setStockReposicion(10000);
		i12.setStockMaximo(50000);
		
		i13.setNombre("Chocolate Águila");
		i13.setDescripcion("Chocolate Águila amargo");
		i13.setUnidadMedida(gramo);
		i13.setEstrategiaGestion(EstrategiaGestion.SIN_ESTRATEGIA);
		i13.setStockDisponible(30000);
		i13.setStockReposicion(10000);
		i13.setStockMaximo(50000);
		
		i14.setNombre("Dulce Membrillo");
		i14.setDescripcion("Dulce Membrillo");
		i14.setUnidadMedida(gramo);
		i14.setEstrategiaGestion(EstrategiaGestion.MANUAL);
		i14.setStockDisponible(2000);
		i14.setStockReposicion(10000);
		i14.setStockMaximo(50000);
		
		dao.save(i1);
		dao.save(i2);
		dao.save(i3);
		dao.save(i4);
		dao.save(i5);
		dao.save(i6);
		dao.save(i7);
		dao.save(i8);
		dao.save(i9);
		dao.save(i10);
		dao.save(i11);
		dao.save(i12);
		dao.save(i13);
		dao.save(i14);
		
		t.commit();
	}
}
