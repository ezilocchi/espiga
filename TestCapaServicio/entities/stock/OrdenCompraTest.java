package stock;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import produccion.Insumo;
import utils.FechaUtils;

import com.bm.testsuite.BaseSessionBeanFixture;

public class OrdenCompraTest extends BaseSessionBeanFixture<DaoBaseEJB3unit> {

	public OrdenCompraTest() {
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave(){
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		DaoBaseEJB3unit dao = this.getBeanToTest();
		
		Proveedor proveedor1 = new Proveedor();
		proveedor1.setId(1l);
		Proveedor proveedor2 = new Proveedor();
		proveedor2.setId(3l);
		Proveedor proveedor3 = new Proveedor();
		proveedor3.setId(5l);
		
		Insumo i1  = new Insumo();i1.setId(1L);
		Insumo i2  = new Insumo();i2.setId(2L);
		Insumo i3  = new Insumo();i3.setId(3L);
		Insumo i4  = new Insumo();i4.setId(4L);
		Insumo i5  = new Insumo();i5.setId(5L);
		
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
		
		OrdenCompra compra1 = new OrdenCompra();
		compra1.setFecha(FechaUtils.getFecha(9, 02, 2010));
		compra1.setFechaPedido(FechaUtils.getFecha(13, 02, 2010));
		compra1.setProveedor(proveedor1);		
		
		OrdenCompraDetalle detalle1 = new OrdenCompraDetalle();
		detalle1.setUnidadMedida(unidadMedida2);
		detalle1.setInsumo(i1);
		detalle1.setPrecioUnitario(50f);
		detalle1.setCantidad(12);
		compra1.addDetalle(detalle1);
		
		OrdenCompraDetalle detalle2 = new OrdenCompraDetalle();
		detalle2.setUnidadMedida(unidadMedida4);
		detalle2.setInsumo(i3);
		detalle2.setPrecioUnitario(30f);
		detalle2.setCantidad(25);
		compra1.addDetalle(detalle2);
		
		OrdenCompraDetalle detalle3 = new OrdenCompraDetalle();	
		detalle3.setUnidadMedida(unidadMedida5);
		detalle3.setInsumo(i4);
		detalle3.setPrecioUnitario(30f);
		detalle3.setCantidad(25);
		compra1.addDetalle(detalle3);
		
		OrdenCompraDetalle detalle4 = new OrdenCompraDetalle();	
		detalle4.setUnidadMedida(unidadMedida6);
		detalle4.setInsumo(i2);
		detalle4.setPrecioUnitario(30f);
		detalle4.setCantidad(25);
		compra1.addDetalle(detalle4);
		
		compra1.actualizarEstado();// Se actualiza el estado a CREADA de la orden y sus detalles
		
		OrdenCompra compra2 = new OrdenCompra();
		compra2.setFecha(FechaUtils.getFecha(9, 02, 2010));
		compra2.setFechaPedido(FechaUtils.getFecha(13, 02, 2010));
		compra2.setProveedor(proveedor2);		
		
		detalle1 = new OrdenCompraDetalle();	
		detalle1.setUnidadMedida(unidadMedida4);
		detalle1.setInsumo(i3);
		detalle1.setPrecioUnitario(50f);
		detalle1.setCantidad(17);
		compra2.addDetalle(detalle1);
		
		detalle2 = new OrdenCompraDetalle();	
		detalle2.setUnidadMedida(unidadMedida6);
		detalle2.setInsumo(i2);
		detalle2.setPrecioUnitario(30f);
		detalle2.setCantidad(20);
		compra2.addDetalle(detalle2);
		
		compra2.actualizarEstado();// Se actualiza el estado a CREADA de la orden y sus detalles
		
		OrdenCompra compra3 = new OrdenCompra();
		compra3.setFecha(FechaUtils.getFecha(9, 02, 2010));
		compra3.setFechaPedido(FechaUtils.getFecha(13, 02, 2010));
		compra3.setProveedor(proveedor3);		
		
		detalle1 = new OrdenCompraDetalle();
		detalle1.setUnidadMedida(unidadMedida1);
		detalle1.setInsumo(i5);
		detalle1.setPrecioUnitario(50f);
		detalle1.setCantidad(33);
		compra3.addDetalle(detalle1);
		
		detalle2 = new OrdenCompraDetalle();	
		detalle2.setUnidadMedida(unidadMedida5);
		detalle2.setInsumo(i4);
		detalle2.setPrecioUnitario(30f);
		detalle2.setCantidad(14);
		compra3.addDetalle(detalle2);
		
		compra3.actualizarEstado();// Se actualiza el estado a CREADA de la orden y sus detalles
		
		
		
		dao.save(compra1);
		dao.save(compra2);
		dao.save(compra3);
		
		t.commit();
	}

}
