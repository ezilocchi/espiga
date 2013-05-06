package venta;

import javax.persistence.EntityTransaction;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import utils.FechaUtils;
import venta.estados.pedido.EstadoPedido;

import base.Zona;

import com.bm.testsuite.BaseSessionBeanFixture;

public class PedidoDetallePedidoTest extends
		BaseSessionBeanFixture<DaoBaseEJB3unit> {
	public PedidoDetallePedidoTest() {
		super(DaoBaseEJB3unit.class, new Class[] {});
	}

	public void testSave() throws Exception {
		EntityTransaction t = super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();

		Cliente ev1 = new Cliente();
		ev1.setId(1l);
		Cliente ev2 = new Cliente();
		ev2.setId(2l);
		Cliente ev3 = new Cliente();
		ev3.setId(3l);

		Producto pr1 = new Producto();
		pr1.setId(1l);
		Producto pr2 = new Producto();
		pr2.setId(2l);
		Producto pr3 = new Producto();
		pr3.setId(3l);
		Producto pr4 = new Producto();
		pr4.setId(4l);
		Producto pr5 = new Producto();
		pr5.setId(5l);
		Producto pr6 = new Producto();
		pr6.setId(6l);
		Producto pr7 = new Producto();
		pr7.setId(7l);
		Producto pr8 = new Producto();
		pr8.setId(8l);
		Producto pr9 = new Producto();
		pr9.setId(9l);
		Producto pr10 = new Producto();
		pr10.setId(10l);
		Producto pr11 = new Producto();
		pr11.setId(11l);

		// Estado creado = new Estado(EstadosPedido.CREADO);
		// Estado penProd = new Estado(EstadosPedido.PENDIENTE_PRODUCCION);
		// Estado anul = new Estado(EstadosPedido.ANULADO);
		// Estado entregado = new Estado (EstadosPedido.ENTREGADO);
		// Estado pendienteEntrega = new Estado
		// (EstadosPedido.PENDIENTE_ENTREGA);
		// Estado producido = new Estado (EstadosPedido.PRODUCIDO);
		// Estado cancelado = new Estado (EstadosPedido.CANCELADO);

		Pedido p1 = new Pedido();
		Pedido p2 = new Pedido();
		Pedido p3 = new Pedido();
		Pedido p4 = new Pedido();
		Pedido p5 = new Pedido();
		Pedido p6 = new Pedido();
		Pedido p7 = new Pedido();
		Pedido p8 = new Pedido();
		Pedido p9 = new Pedido();
		Pedido p10 = new Pedido();
		Pedido p11 = new Pedido();
		Pedido p12 = new Pedido();
		Pedido p13 = new Pedido();
		Pedido p14 = new Pedido();
		Pedido p15 = new Pedido();

		Zona z1 = new Zona();
		z1.setId(1l);
		Zona z2 = new Zona();
		z2.setId(2l);
		Zona z4 = new Zona();
		z4.setId(4l);
		Zona z6 = new Zona();
		z6.setId(6l);
		Zona z8 = new Zona();
		z8.setId(8l);
		Zona z10 = new Zona();
		z10.setId(10l);

		p1.setFecha(FechaUtils.getFecha(25, 03, 2010));
		p1.setFechaEntrega(FechaUtils.getFecha(6, 04, 2010));
		p1.setCliente(ev1);
		p1.setZona(z1);

		PedidoDetalle pd11 = new PedidoDetalle();
		PedidoDetalle pd12 = new PedidoDetalle();
		PedidoDetalle pd13 = new PedidoDetalle();
		PedidoDetalle pd14 = new PedidoDetalle();

		pd11.setProducto(pr1);
		pd11.setCantidad(20);
		pd11.setPrecioVentaUnitario(2.0f);

		pd12.setProducto(pr2);
		pd12.setCantidad(3);
		pd12.setPrecioVentaUnitario(8.0F);

		pd13.setProducto(pr9);
		pd13.setCantidad(10);
		pd13.setPrecioVentaUnitario(2.00F);

		pd14.setProducto(pr3);
		pd14.setCantidad(5);
		pd14.setPrecioVentaUnitario(10.00F);

		p1.addDetalle(pd11);
		p1.addDetalle(pd12);
		p1.addDetalle(pd13);
		p1.addDetalle(pd14);

		p2.setFecha(FechaUtils.getFecha(24, 3, 2010));
		p2.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p2.setEstado(EstadoPedido.DIAGRAMADO);
		p2.setCliente(ev1);
		p2.setZona(z1);

		PedidoDetalle pd21 = new PedidoDetalle();
		PedidoDetalle pd22 = new PedidoDetalle();
		PedidoDetalle pd23 = new PedidoDetalle();
		PedidoDetalle pd24 = new PedidoDetalle();

		pd21.setProducto(pr3);
		pd21.setCantidad(20);
		pd21.setPrecioVentaUnitario(2.05F);

		pd22.setProducto(pr4);
		pd22.setCantidad(1);
		pd22.setPrecioVentaUnitario(224.50F);

		pd23.setProducto(pr5);
		pd23.setCantidad(2);
		pd23.setPrecioVentaUnitario(35.00F);

		pd24.setProducto(pr10);
		pd24.setCantidad(3);
		pd24.setPrecioVentaUnitario(30.20F);

		p2.addDetalle(pd21);
		p2.addDetalle(pd22);
		p2.addDetalle(pd23);
		p2.addDetalle(pd24);

		p3.setFecha(FechaUtils.getFecha(30, 9, 2009));
		p3.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p3.setCliente(ev2);
		p3.setZona(z10);

		PedidoDetalle pd31 = new PedidoDetalle();
		PedidoDetalle pd32 = new PedidoDetalle();
		PedidoDetalle pd33 = new PedidoDetalle();
		PedidoDetalle pd34 = new PedidoDetalle();

		pd31.setProducto(pr2);
		pd31.setCantidad(5);
		pd31.setPrecioVentaUnitario(7.65F);

		pd32.setProducto(pr6);
		pd32.setCantidad(3);
		pd32.setPrecioVentaUnitario(27.50F);

		pd33.setProducto(pr9);
		pd33.setCantidad(5);
		pd33.setPrecioVentaUnitario(3.00F);

		pd34.setProducto(pr1);
		pd34.setCantidad(12);
		pd34.setPrecioVentaUnitario(2.30F);

		p3.addDetalle(pd31);
		p3.addDetalle(pd32);
		p3.addDetalle(pd33);
		p3.addDetalle(pd34);

		p4.setFecha(FechaUtils.getFecha(27, 9, 2009));
		p4.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p4.setCliente(ev2);
		p4.setZona(z10);
		// Deberia arrojar error

		p5.setFecha(FechaUtils.getFecha(27, 9, 2009));
		p5.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p5.setCliente(ev3);
		p5.setZona(z4);

		PedidoDetalle pd51 = new PedidoDetalle();
		PedidoDetalle pd52 = new PedidoDetalle();
		PedidoDetalle pd53 = new PedidoDetalle();
		PedidoDetalle pd54 = new PedidoDetalle();

		pd51.setProducto(pr1);
		pd51.setCantidad(15);
		pd51.setPrecioVentaUnitario(2.0F);

		pd52.setProducto(pr3);
		pd52.setCantidad(8);
		pd52.setPrecioVentaUnitario(10.0F);

		pd53.setProducto(pr5);
		pd53.setCantidad(15);
		pd53.setPrecioVentaUnitario(2.00F);

		pd54.setProducto(pr7);
		pd54.setCantidad(10);
		pd54.setPrecioVentaUnitario(3.55F);

		p5.addDetalle(pd51);
		p5.addDetalle(pd52);
		p5.addDetalle(pd53);
		p5.addDetalle(pd54);

		p6.setFecha(FechaUtils.getFecha(25, 9, 2009));
		p6.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p6.setCliente(ev3);
		p6.setZona(z4);

		PedidoDetalle pd61 = new PedidoDetalle();
		PedidoDetalle pd62 = new PedidoDetalle();
		PedidoDetalle pd63 = new PedidoDetalle();
		PedidoDetalle pd64 = new PedidoDetalle();

		pd61.setProducto(pr2);
		pd61.setCantidad(4);
		pd61.setPrecioVentaUnitario(7.60F);

		pd62.setProducto(pr3);
		pd62.setCantidad(5);
		pd62.setPrecioVentaUnitario(8.00F);

		pd63.setProducto(pr4);
		pd63.setCantidad(1);
		pd63.setPrecioVentaUnitario(224.30F);

		pd64.setProducto(pr9);
		pd64.setCantidad(5);
		pd64.setPrecioVentaUnitario(3.90F);

		p6.addDetalle(pd61);
		p6.addDetalle(pd62);
		p6.addDetalle(pd63);
		p6.addDetalle(pd64);

		p7.setFecha(FechaUtils.getFecha(30, 9, 2009));
		p7.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p7.setCliente(ev3);
		p7.setZona(z4);

		PedidoDetalle pd71 = new PedidoDetalle();
		PedidoDetalle pd72 = new PedidoDetalle();
		PedidoDetalle pd73 = new PedidoDetalle();
		PedidoDetalle pd74 = new PedidoDetalle();

		pd71.setProducto(pr3);
		pd71.setCantidad(4);
		pd71.setPrecioVentaUnitario(5.75F);

		pd72.setProducto(pr6);
		pd72.setCantidad(2);
		pd72.setPrecioVentaUnitario(25.40F);

		pd73.setProducto(pr1);
		pd73.setCantidad(2);
		pd73.setPrecioVentaUnitario(8.00F);

		pd74.setProducto(pr10);
		pd74.setCantidad(3);
		pd74.setPrecioVentaUnitario(31.19F);

		p7.addDetalle(pd71);
		p7.addDetalle(pd72);
		p7.addDetalle(pd73);
		p7.addDetalle(pd74);

		p8.setFecha(FechaUtils.getFecha(30, 9, 2009));
		p8.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p8.setCliente(ev2);
		p8.setZona(z10);

		PedidoDetalle pd81 = new PedidoDetalle();
		PedidoDetalle pd82 = new PedidoDetalle();
		PedidoDetalle pd83 = new PedidoDetalle();
		PedidoDetalle pd84 = new PedidoDetalle();

		pd81.setProducto(pr5);
		pd81.setCantidad(99);
		pd81.setPrecioVentaUnitario(0.73F);

		pd82.setProducto(pr3);
		pd82.setCantidad(12);
		pd82.setPrecioVentaUnitario(6.22F);

		pd83.setProducto(pr2);
		pd83.setCantidad(2);
		pd83.setPrecioVentaUnitario(2.39F);

		pd84.setProducto(pr9);
		pd84.setCantidad(5);
		pd84.setPrecioVentaUnitario(2.39F);

		p8.addDetalle(pd81);
		p8.addDetalle(pd82);
		p8.addDetalle(pd83);
		p8.addDetalle(pd84);

		p9.setFecha(FechaUtils.getFecha(28, 9, 2009));
		p9.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p9.setCliente(ev2);
		p9.setZona(z10);

		PedidoDetalle pd91 = new PedidoDetalle();
		PedidoDetalle pd92 = new PedidoDetalle();
		PedidoDetalle pd93 = new PedidoDetalle();
		PedidoDetalle pd94 = new PedidoDetalle();

		pd91.setProducto(pr5);
		pd91.setCantidad(13);
		pd91.setPrecioVentaUnitario(0.77F);

		pd92.setProducto(pr1);
		pd92.setCantidad(45);
		pd92.setPrecioVentaUnitario(2.20F);

		pd93.setProducto(pr4);
		pd93.setCantidad(1);
		pd93.setPrecioVentaUnitario(321.35F);

		pd94.setProducto(pr9);
		pd94.setCantidad(8);
		pd94.setPrecioVentaUnitario(3.35F);

		p9.addDetalle(pd91);
		p9.addDetalle(pd92);
		p9.addDetalle(pd93);
		p9.addDetalle(pd94);

		p10.setFecha(FechaUtils.getFecha(28, 9, 2009));
		p10.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p10.setCliente(ev1);
		p10.setZona(z1);

		PedidoDetalle pd101 = new PedidoDetalle();
		PedidoDetalle pd102 = new PedidoDetalle();
		PedidoDetalle pd103 = new PedidoDetalle();
		PedidoDetalle pd104 = new PedidoDetalle();

		pd101.setProducto(pr3);
		pd101.setCantidad(3);
		pd101.setPrecioVentaUnitario(6.23F);

		pd102.setProducto(pr5);
		pd102.setCantidad(86);
		pd102.setPrecioVentaUnitario(0.95F);

		pd103.setProducto(pr6);
		pd103.setCantidad(2);
		pd103.setPrecioVentaUnitario(24.00F);

		pd104.setProducto(pr10);
		pd104.setCantidad(2);
		pd104.setPrecioVentaUnitario(31.35F);

		p10.addDetalle(pd101);
		p10.addDetalle(pd102);
		p10.addDetalle(pd103);
		p10.addDetalle(pd104);

		p11.setFecha(FechaUtils.getFecha(28, 9, 2009));
		p11.setFechaEntrega(FechaUtils.getFecha(13, 04, 2010));
		p11.setCliente(ev2);
		p11.setZona(z10);

		PedidoDetalle pd111 = new PedidoDetalle();
		PedidoDetalle pd112 = new PedidoDetalle();
		PedidoDetalle pd113 = new PedidoDetalle();
		PedidoDetalle pd114 = new PedidoDetalle();

		pd111.setProducto(pr4);
		pd111.setCantidad(2);
		pd111.setPrecioVentaUnitario(225.47F);

		pd112.setProducto(pr1);
		pd112.setCantidad(35);
		pd112.setPrecioVentaUnitario(1.96F);

		pd113.setProducto(pr6);
		pd113.setCantidad(7);
		pd113.setPrecioVentaUnitario(30.56F);

		pd114.setProducto(pr9);
		pd114.setCantidad(10);
		pd114.setPrecioVentaUnitario(2.56F);

		p11.addDetalle(pd111);
		p11.addDetalle(pd112);
		p11.addDetalle(pd113);
		p11.addDetalle(pd114);

		p12.setFecha(FechaUtils.getFecha(19, 9, 2009));
		p12.setFechaEntrega(FechaUtils.getFecha(21, 9, 2009));
		p12.setCliente(ev3);
		p12.setZona(z4);

		PedidoDetalle pd121 = new PedidoDetalle();
		PedidoDetalle pd122 = new PedidoDetalle();
		PedidoDetalle pd123 = new PedidoDetalle();
		PedidoDetalle pd124 = new PedidoDetalle();

		pd121.setProducto(pr3);
		pd121.setCantidad(19);
		pd121.setPrecioVentaUnitario(0.97F);

		pd122.setProducto(pr6);
		pd122.setCantidad(2);
		pd122.setPrecioVentaUnitario(30.12F);

		pd123.setProducto(pr4);
		pd123.setCantidad(3);
		pd123.setPrecioVentaUnitario(245.76F);

		pd124.setProducto(pr1);
		pd124.setCantidad(20);
		pd124.setPrecioVentaUnitario(1.90F);

		p12.addDetalle(pd121);
		p12.addDetalle(pd122);
		p12.addDetalle(pd123);
		p12.addDetalle(pd124);

		p13.setFecha(FechaUtils.getFecha(20, 4, 2009));
		p13.setFechaEntrega(FechaUtils.getFecha(21, 9, 2009));
		p13.setCliente(ev1);
		p13.setZona(z1);

		PedidoDetalle pd131 = new PedidoDetalle();
		PedidoDetalle pd132 = new PedidoDetalle();
		PedidoDetalle pd133 = new PedidoDetalle();
		PedidoDetalle pd134 = new PedidoDetalle();

		pd131.setProducto(pr4);
		pd131.setCantidad(1);
		pd131.setPrecioVentaUnitario(234.45F);

		pd132.setProducto(pr2);
		pd132.setCantidad(65);
		pd132.setPrecioVentaUnitario(0.65F);

		pd133.setProducto(pr5);
		pd133.setCantidad(99);
		pd133.setPrecioVentaUnitario(5.25F);

		pd134.setProducto(pr7);
		pd134.setCantidad(9);
		pd134.setPrecioVentaUnitario(2.25F);

		p13.addDetalle(pd131);
		p13.addDetalle(pd132);
		p13.addDetalle(pd133);
		p13.addDetalle(pd134);

		p14.setFecha(FechaUtils.getFecha(18, 4, 2009));
		p14.setFechaEntrega(FechaUtils.getFecha(21, 9, 2009));
		p14.setCliente(ev3);
		p14.setZona(z4);

		PedidoDetalle pd141 = new PedidoDetalle();
		PedidoDetalle pd142 = new PedidoDetalle();
		PedidoDetalle pd143 = new PedidoDetalle();
		PedidoDetalle pd144 = new PedidoDetalle();

		pd141.setProducto(pr2);
		pd141.setCantidad(2000);
		pd141.setPrecioVentaUnitario(0.15F);

		pd142.setProducto(pr4);
		pd142.setCantidad(1);
		pd142.setPrecioVentaUnitario(345.89F);

		pd143.setProducto(pr6);
		pd143.setCantidad(3);
		pd143.setPrecioVentaUnitario(22.22F);

		pd144.setProducto(pr9);
		pd144.setCantidad(7);
		pd144.setPrecioVentaUnitario(4.29F);

		p14.addDetalle(pd141);
		p14.addDetalle(pd142);
		p14.addDetalle(pd143);
		p14.addDetalle(pd144);

		p15.setFecha(FechaUtils.getFecha(17, 9, 2009));
		p15.setFechaEntrega(FechaUtils.getFecha(21, 9, 2009));
		p15.setCliente(ev3);
		p15.setZona(z4);

		PedidoDetalle pd151 = new PedidoDetalle();
		PedidoDetalle pd152 = new PedidoDetalle();
		PedidoDetalle pd153 = new PedidoDetalle();
		PedidoDetalle pd154 = new PedidoDetalle();

		pd151.setProducto(pr1);
		pd151.setCantidad(55);
		pd151.setPrecioVentaUnitario(1.93F);

		pd152.setProducto(pr3);
		pd152.setCantidad(14);
		pd152.setPrecioVentaUnitario(5.49F);

		pd153.setProducto(pr5);
		pd153.setCantidad(15);
		pd153.setPrecioVentaUnitario(2.77F);

		pd154.setProducto(pr10);
		pd154.setCantidad(3);
		pd154.setPrecioVentaUnitario(30.77F);

		p15.addDetalle(pd151);
		p15.addDetalle(pd152);
		p15.addDetalle(pd153);
		p15.addDetalle(pd154);

		p1.actualizarEstado();
		p2.actualizarEstado();
		p3.actualizarEstado();
		p4.actualizarEstado();
		p5.actualizarEstado();
		p6.actualizarEstado();
		p7.actualizarEstado();
		p8.actualizarEstado();
		p9.actualizarEstado();
		p10.actualizarEstado();
		p11.actualizarEstado();
		p12.actualizarEstado();
		p13.actualizarEstado();
		p14.actualizarEstado();
		p15.actualizarEstado();

		dao.save(p1);
		dao.save(p2);
		dao.save(p3);
		dao.save(p4);
		dao.save(p5);
		dao.save(p6);
		dao.save(p7);
		dao.save(p8);
		dao.save(p9);
		dao.save(p10);
		dao.save(p11);
		dao.save(p12);
		dao.save(p13);
		dao.save(p14);
		dao.save(p15);

		t.commit();
	}

}
