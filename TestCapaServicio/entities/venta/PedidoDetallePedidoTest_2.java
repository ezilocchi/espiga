package venta;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import utils.FechaUtils;
import venta.estados.pedido.EstadoPedido;

import base.Zona;

import com.bm.testsuite.BaseSessionBeanFixture;

public class PedidoDetallePedidoTest_2 extends BaseSessionBeanFixture<DaoBaseEJB3unit> {
	public PedidoDetallePedidoTest_2(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t;
		ServiceEntity dao;
		
		Cliente c1 = new Cliente();c1.setId(8l);
		Cliente c2 = new Cliente();c2.setId(10l);
		Cliente c3 = new Cliente();c3.setId(3l);
		Cliente c4 = new Cliente();c4.setId(4l);
		Cliente c5 = new Cliente();c5.setId(5l);
		
		Producto pr1 = new Producto();pr1.setId(1l);
		Producto pr2 = new Producto();pr2.setId(2l);
		Producto pr3 = new Producto();pr3.setId(3l);
		Producto pr4 = new Producto();pr4.setId(4l);
		Producto pr5 = new Producto();pr5.setId(5l);
		Producto pr6 = new Producto();pr6.setId(6l);
		Producto pr7 = new Producto();pr7.setId(7l);
		Producto pr8 = new Producto();pr8.setId(8l);
		Producto pr9 = new Producto();pr9.setId(9l);
		Producto pr10 = new Producto();pr10.setId(10l);
		
		Pedido p;
		
		PedidoDetalle d1; 
		PedidoDetalle d2;
		PedidoDetalle d3;
		PedidoDetalle d4;
		
		Zona z1 = new Zona ();
		z1.setId(1l);
		Zona z2 = new Zona ();
		z2.setId(2l);
		Zona z3 = new Zona ();
		z3.setId(3l);
		Zona z4 = new Zona ();
		z4.setId(4l);
		Zona z5 = new Zona ();
		z5.setId(5l);
		Zona z7 = new Zona ();
		z7.setId(7l);
		Zona z10 = new Zona ();
		z10.setId(10l);
		//-----------------------PEDIDO 1---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c1);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z2);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr4);
		d1.setCantidad(10);
		d1.setPrecioVentaUnitario(3.10F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr2);
		d2.setCantidad(15);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr10);
		d3.setCantidad(300);
		d3.setPrecioVentaUnitario(5F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr9);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(4.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 2---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c1);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z2);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr1);
		d1.setCantidad(20);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr3);
		d2.setCantidad(10);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr6);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr7);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 3---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c1);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z2);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr5);
		d1.setCantidad(100);
		d1.setPrecioVentaUnitario(5.0F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr4);
		d2.setCantidad(200);
		d2.setPrecioVentaUnitario(4.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr2);
		d3.setCantidad(20);
		d3.setPrecioVentaUnitario(2F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr8);
		d4.setCantidad(40);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 4---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c2);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z3);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr3);
		d1.setCantidad(90);
		d1.setPrecioVentaUnitario(2.0F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr4);
		d2.setCantidad(100);
		d2.setPrecioVentaUnitario(1.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr6);
		d3.setCantidad(40);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr5);
		d4.setCantidad(50);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 5---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c2);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z3);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr10);
		d1.setCantidad(200);
		d1.setPrecioVentaUnitario(3.50F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr7);
		d2.setCantidad(500);
		d2.setPrecioVentaUnitario(1.0F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr6);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr8);
		d4.setCantidad(3050);
		d4.setPrecioVentaUnitario(3.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 6---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c2);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z3);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr3);
		d1.setCantidad(200);
		d1.setPrecioVentaUnitario(3.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr2);
		d2.setCantidad(700);
		d2.setPrecioVentaUnitario(2.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr6);
		d3.setCantidad(400);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr9);
		d4.setCantidad(300);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 7---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c3);
		p.setFecha(FechaUtils.getFecha(24, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(25, 3, 2010));
		p.setEstado(EstadoPedido.ENTREGADO);
		p.setZona(z4);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr1);
		d1.setCantidad(200);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr9);
		d2.setCantidad(150);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr5);
		d3.setCantidad(40);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr7);
		d4.setCantidad(120);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 8---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c3);
		p.setFecha(FechaUtils.getFecha(24, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(25, 3, 2010));
		p.setEstado(EstadoPedido.ENTREGADO);
		p.setZona(z4);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr10);
		d1.setCantidad(80);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr5);
		d2.setCantidad(100);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr6);
		d3.setCantidad(75);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr2);
		d4.setCantidad(50);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 9---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c3);
		p.setFecha(FechaUtils.getFecha(24, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(25, 3, 2010));
		p.setEstado(EstadoPedido.ENTREGADO);
		p.setZona(z4);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr1);
		d1.setCantidad(20);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr3);
		d2.setCantidad(10);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr6);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr7);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 10---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c4);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z2);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr7);
		d1.setCantidad(300);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr5);
		d2.setCantidad(100);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr4);
		d3.setCantidad(240);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr2);
		d4.setCantidad(70);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
//		dao.save(d1);
//		dao.save(d2);
//		dao.save(d3);
//		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 11---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c4);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z2);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr10);
		d1.setCantidad(30);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr1);
		d2.setCantidad(130);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr3);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr7);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 12---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c4);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z2);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr9);
		d1.setCantidad(20);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr8);
		d2.setCantidad(10);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr7);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr6);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 13---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c5);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z3);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr1);
		d1.setCantidad(20);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr3);
		d2.setCantidad(10);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr6);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr7);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 14---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c5);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z3);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr8);
		d1.setCantidad(20);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr4);
		d2.setCantidad(10);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr1);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr2);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
		
		//-----------------------PEDIDO 15---------------------------------
		t =  super.getEntityManager().getTransaction();
		t.begin();
		dao = this.getBeanToTest();
		
		p = new Pedido();
		
		p.setCliente(c5);
		p.setFecha(FechaUtils.getFecha(25, 3, 2010));
		p.setFechaEntrega(FechaUtils.getFecha(26, 3, 2010));
		p.setZona(z3);
		
		d1 = new PedidoDetalle();
		d1.setProducto(pr10);
		d1.setCantidad(20);
		d1.setPrecioVentaUnitario(2.20F);
		
		d2 = new PedidoDetalle();
		d2.setProducto(pr3);
		d2.setCantidad(10);
		d2.setPrecioVentaUnitario(5.50F);
		
		d3 = new PedidoDetalle();
		d3.setProducto(pr5);
		d3.setCantidad(15);
		d3.setPrecioVentaUnitario(4F);
		
		d4 = new PedidoDetalle();
		d4.setProducto(pr7);
		d4.setCantidad(30);
		d4.setPrecioVentaUnitario(2.0F);
		
		p.addDetalle(d1);
		p.addDetalle(d2);
		p.addDetalle(d3);
		p.addDetalle(d4);
		
		p.actualizarEstado();
		dao.save(p);
		
		dao.save(d1);
		dao.save(d2);
		dao.save(d3);
		dao.save(d4);
		
		t.commit();
		//----------------------------------------------------------------
	}
}
