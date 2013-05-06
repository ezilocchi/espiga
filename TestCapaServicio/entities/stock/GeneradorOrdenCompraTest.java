package stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import produccion.Insumo;
import utils.FechaUtils;
import venta.Cliente;
import venta.Pedido;
import venta.PedidoDetalle;
import venta.Producto;

import base.Zona;

import com.bm.testsuite.BaseSessionBeanFixture;

public class GeneradorOrdenCompraTest extends BaseSessionBeanFixture<DaoBaseEJB3unit>{
	public GeneradorOrdenCompraTest(){
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
		int cantMinOrdenesXMes = 10;
		int cantMaxOrdenesXMes = 30;
		int cantMaxDetallesXOrden = 5;
		int cantMaxUnidadesInsumo = 10;
		//-----------------------------------------------
		
		int mesActual = mesDesde;
		int anoActual = anoDesde;
		
		List<Proveedor> proveedores;
		List<Insumo> insumos;
		List<Insumo> insumosUnidadMedida;
		
		Query queryProveedores = manager.createQuery("select p from Proveedor p join fetch p.direccion.barrio b join fetch p.insumos");
		proveedores = queryProveedores.getResultList();
		
		Query queryInsumosUnidadMedida = manager.createQuery("select i from Insumo i join fetch i.unidades");
		insumosUnidadMedida = queryInsumosUnidadMedida.getResultList();
		
		HashMap<Insumo, UnidadMedidaInsumoCompra> mapInsumoUnidad = new HashMap<Insumo, UnidadMedidaInsumoCompra>();
		for (Insumo insumoIterator : insumosUnidadMedida) {
			mapInsumoUnidad.put(insumoIterator, insumoIterator.getUnidadPredeterminada());
		}
		
		OrdenCompra ordenCompra = null;
		OrdenCompraDetalle detalleOrdenCompra = null;
		Insumo insumo = null;
		Proveedor proveedor = null;
		
		int proveedorActual = 0;
		int insumoActual = 0;
		
		int cantOrdenesMesActual = 0;
		int cantDetallesOrdenActual = 0;
		int cantInsumoDetalleActual = 0;
		
		Date fecha;
		int diaMes = 0;
		
		while (!((mesActual>mesHasta)&&(anoActual>=anoHasta))) {
			cantOrdenesMesActual = (int) ((cantMaxOrdenesXMes - cantMinOrdenesXMes)*Math.random()) + (cantMinOrdenesXMes);
			if (cantOrdenesMesActual<cantMinOrdenesXMes) {
				cantOrdenesMesActual = cantMinOrdenesXMes;
			}
			
			for (int i = 0; i < cantOrdenesMesActual; i++) {
				//Creacion de la orden de compra
				ordenCompra = new OrdenCompra();
				
				//creacion del proveedor
				proveedorActual = (int) ((proveedores.size()-1)*Math.random());
				if (proveedorActual<0) {
					proveedorActual = 0;
				}
				proveedor = new Proveedor();
				proveedor = proveedores.get(proveedorActual);
				
				//creacion de la lista de insumos del proveedor
				insumos = new ArrayList<Insumo>();
				
				for (Proveedor proveedorIterado : proveedores) {
					if (proveedor.getId()==proveedorIterado.getId()) {
						insumos.add(proveedor.getInsumos().get(0));
					}
				}
				insumos = proveedor.getInsumos();
				
				//seteo del proveedor a la orden
				ordenCompra.setProveedor(proveedor);
				
				//creacion de la fecha de alta de la orden
				diaMes = (int) (28*Math.random()) + 1;
				fecha = new Date();
				if (diaMes>28) {
					diaMes = 28;
				}				
				fecha = FechaUtils.getFecha(diaMes, mesActual, anoActual);
				
				//seteo de la fecha de alta al pedido
				ordenCompra.setFecha(fecha);
				
				//creacion de la fecha de recepcion de la orden
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
				
				//seteo de la fecha de recepcion a la orden
				ordenCompra.setFechaPedido(fecha);
				
				//generacion del detalle del pedido
				cantDetallesOrdenActual = (int) (cantMaxDetallesXOrden*Math.random()) + 1;
				if (cantDetallesOrdenActual<1) {
					cantDetallesOrdenActual = 1;
				}
				
				for (int j = 0; j < cantDetallesOrdenActual; j++) {
					detalleOrdenCompra = new OrdenCompraDetalle();
					
					//creacion del producto
					insumoActual = (int) ((insumos.size()-1)*Math.random());
					if (insumoActual<0) {
						insumoActual=0;
					}
					
					//seteo del producto al detalle
					insumo = new Insumo();
					insumo = insumos.get(insumoActual);
					
					//asignacion del producto al detalle
					detalleOrdenCompra.setInsumo(insumo);
					
					//generacion de la cantidad del insumo
					cantInsumoDetalleActual = (int) (cantMaxUnidadesInsumo*Math.random()) + 1;
					if (cantInsumoDetalleActual<1) {
						cantInsumoDetalleActual=1;
					}
					
					//seteo de la cantidad al detalle
					detalleOrdenCompra.setCantidad(cantInsumoDetalleActual);
					
					//seteo de la unidad de compra
					detalleOrdenCompra.setUnidadMedida(mapInsumoUnidad.get(insumo));
					
					//seteo del precio unitario
					detalleOrdenCompra.setPrecioUnitario(mapInsumoUnidad.get(insumo).getPrecioUnitario());
					
					//seteo del detalle al pedido
					ordenCompra.addDetalle(detalleOrdenCompra);
				}
				
				//seteo del estado del pedido
				ordenCompra.actualizarEstado();
				
				//grabamos el pedido
				dao.save(ordenCompra);
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
