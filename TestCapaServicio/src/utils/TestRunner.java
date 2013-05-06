package utils;

import junit.framework.Test;
import junit.framework.TestSuite;
import produccion.InsumoTest;
import produccion.RecetaDetalleRecetaTest;
import stock.NovedadStockTest;
import stock.OrdenCompraTest;
import stock.ProveedoresTest;
import stock.TipoNovedadStockTest;
import stock.UnidadMedidaInsumoTest;
import venta.ClienteTest;
import venta.PedidoDetallePedidoTest;
import venta.PedidoDetallePedidoTest_2;
import venta.ProductoTipoProductoTest;
import base.CargoTest;
import base.EmpleadoTest;
import base.ProvinciaLocalidadBarrioTest;
import base.RecursoTest;
import base.RolTest;
import distribucion.VehiculoTest;

public class TestRunner {

	public static Test suite() {
		DBCreatorSqlServer.main(null);
		TestSuite suite = new TestSuite();
		
		
		//VENTA
		suite.addTestSuite(ProductoTipoProductoTest.class);		//LISTO!
		
		//BASE
		suite.addTestSuite(RolTest.class);		
		suite.addTestSuite(CargoTest.class);		
		suite.addTestSuite(ProvinciaLocalidadBarrioTest.class); //LISTO!
		suite.addTestSuite(RecursoTest.class);
		suite.addTestSuite(EmpleadoTest.class);
		
		//VENTA		
		suite.addTestSuite(ClienteTest.class);
		suite.addTestSuite(PedidoDetallePedidoTest.class);
		//suite.addTestSuite(PedidoDetallePedidoTest_2.class);		
		
		//DISTRIBUCION
		suite.addTestSuite(VehiculoTest.class);
		
		
		//PRODUCCION
		suite.addTestSuite(InsumoTest.class); // LISTO!!
		suite.addTestSuite(RecetaDetalleRecetaTest.class);
		
		
		//Stock
		suite.addTestSuite(ProveedoresTest.class);
		suite.addTestSuite(UnidadMedidaInsumoTest.class);
		suite.addTestSuite(TipoNovedadStockTest.class);
		suite.addTestSuite(NovedadStockTest.class);
		suite.addTestSuite(OrdenCompraTest.class);
		
		return suite;
	}
}
