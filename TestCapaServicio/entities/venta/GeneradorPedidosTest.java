package venta;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import utils.FechaUtils;
import venta.estados.pedido.EstadoPedido;

import base.Zona;

import com.bm.testsuite.BaseSessionBeanFixture;

public class GeneradorPedidosTest extends BaseSessionBeanFixture<DaoBaseEJB3unit>{

	public GeneradorPedidosTest(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		EntityManager manager = super.getEntityManager();
		
		//------Parametrizacion del Generador------------
		int mesDesde = 6;
		int anoDesde = 2009;
		int mesHasta = 6;
		int anoHasta = 2010;
		int cantMinPedidosXMes = 10;
		int cantMaxPedidosXMes = 30;
		int cantMaxDetallesXPedido = 5;
		int cantMaxUnidadesProducto = 10;
		//-----------------------------------------------
		
		int mesActual = mesDesde;
		int anoActual = anoDesde;
		
		List<Producto> productos;
		List<Cliente> clientes;
		
		Query queryClientes = manager.createQuery("select c from Cliente c join fetch c.direccion.barrio b join fetch b.zona");
		clientes = queryClientes.getResultList();
		
		Query queryProductos = manager.createQuery("select p from Producto p join fetch p.tipo");
		productos = queryProductos.getResultList();
		
		Pedido pedido = null;
		PedidoDetalle detallePedido = null;
		Producto producto = null;
		Cliente cliente = null;
		Zona zona = null;
		
		int clienteActual = 0;
		int productoActual = 0;
		
		int cantPedidosMesActual = 0;
		int cantDetallesPedidoActual = 0;
		int cantProductoDetalleActual = 0;
		
		Date fecha;
		int diaMes = 0;
		
		while (!((mesActual>mesHasta)&&(anoActual>=anoHasta))) {
			cantPedidosMesActual = (int) ((cantMaxPedidosXMes - cantMinPedidosXMes)*Math.random()) + (cantMinPedidosXMes);
			if (cantPedidosMesActual<cantMinPedidosXMes) {
				cantPedidosMesActual = cantMinPedidosXMes;
			}
			
			for (int i = 0; i < cantPedidosMesActual; i++) {
				//creacion del pedido
				pedido = new Pedido();
				
				//creacion del cliente
				clienteActual = (int) ((clientes.size()-1)*Math.random());
				if (clienteActual<0) {
					clienteActual = 0;
				}
				cliente = new Cliente();
				cliente = clientes.get(clienteActual);

				//seteo del cliente al pedido
				pedido.setCliente(cliente);
				
				//creacion de la fecha de alta del pedido
				diaMes = (int) (28*Math.random()) + 1;
				fecha = new Date();
				if (diaMes>28) {
					diaMes = 28;
				}				
				fecha = FechaUtils.getFecha(diaMes, mesActual, anoActual);
				
				//seteo de la fecha de alta al pedido
				pedido.setFecha(fecha);
				
				//creacion de la fecha de entrega del pedido
				diaMes ++;
				fecha = new Date();
				if ((diaMes > 28) && (mesActual==2)) {
					if (mesActual==12) {
						fecha = FechaUtils.getFecha(1, 1, anoActual+1);
					} else {
						fecha = FechaUtils.getFecha(1, mesActual+1, anoActual);
					}
				} else {
					fecha = FechaUtils.getFecha(diaMes, mesActual, anoActual);
				}
				
				//seteo de la fecha de entrega del pedido
				pedido.setFechaEntrega(fecha);
				
				//creacion de la zona
				zona = new Zona();
				zona = cliente.getDireccion().getBarrio().getZona();
				
				//seteo de la zona al pedido
				pedido.setZona(zona);
				
				//generacion del detalle del pedido
				cantDetallesPedidoActual = (int) (cantMaxDetallesXPedido*Math.random()) + 1;
				if (cantDetallesPedidoActual<1) {
					cantDetallesPedidoActual = 1;
				}
				
				for (int j = 0; j < cantDetallesPedidoActual; j++) {
					detallePedido = new PedidoDetalle();
					
					//creacion del producto
					productoActual = (int) ((productos.size()-1)*Math.random());
					if (productoActual<0) {
						productoActual=0;
					}
					
					//seteo del producto al detalle
					producto = new Producto();
					producto = productos.get(productoActual);
					
					//asignacion del producto al detalle
					detallePedido.setProducto(producto);
					
					//asignacion del precio de venta unitario
					detallePedido.setPrecioVentaUnitario(producto.getPrecioLista());
					
					//generacion de la cantidad del producto
					cantProductoDetalleActual = (int) (cantMaxUnidadesProducto*Math.random()) + 1;
					if (cantProductoDetalleActual<1) {
						cantProductoDetalleActual=1;
					}
					
					//seteo de la cantidad al detalle
					detallePedido.setCantidad(cantProductoDetalleActual);
					
					//seteo del detalle al pedido
					pedido.addDetalle(detallePedido);
				}
				
				//seteo del estado del pedido
				pedido.actualizarEstado();
				
				//grabamos el pedido
				dao.save(pedido);
			}
			
			mesActual++;
			if (mesActual>12) {
				mesActual=1;
				anoActual++;
			}
		}
		t.commit();
	}
}
