package produccion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import persistencia.DaoBaseEJB3unit;
import persistencia.ServiceEntity;
import stock.UnidadMedidaInsumoCompra;
import utils.FechaUtils;
import venta.Pedido;
import venta.Producto;

import base.Empleado;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;
import com.bm.testsuite.BaseSessionBeanFixture;

public class GeneradorDiagramacionProduccion extends BaseSessionBeanFixture<DaoBaseEJB3unit>{

	public GeneradorDiagramacionProduccion(){
		super(DaoBaseEJB3unit.class,new Class[]{});
	}
	
	public void testSave() throws Exception{
		EntityTransaction t =  super.getEntityManager().getTransaction();
		t.begin();
		ServiceEntity dao = this.getBeanToTest();
		EntityManager manager = super.getEntityManager();
		
		//obtenemos todos los panaderos---------------------------
		List<Empleado> empleados = null;
		Query queryEmpleados = manager.createQuery("select e from Empleado e join fetch e.productos");
		empleados = queryEmpleados.getResultList();
		
			//colocamos los empleados en un map, por producto
		HashMap<Producto, Empleado> mapEmpleadoInsumo = new HashMap<Producto, Empleado>();
		for (Empleado empleado : empleados) {
			if (empleado.getProductos()!=null) {
				for (int i = 0; i < empleado.getProductos().size(); i++) {
					mapEmpleadoInsumo.put(empleado.getProductos().get(i), empleado);
					
				}
			}
		}
		//--------------------------------------------------------
		
		//Armado de un array con las fechas de entrega de pedidos-------------------
		ArrayList<Date> fechasPedidos = new ArrayList<Date>();
		Date fechaAux = null;
		Calendar calAux = null;
		
		int dia = 0;
		int mes = 0;
		int ano = 0;
		
		List<Pedido> totalPedidos = null;
		Query queryTotalPedidos = manager.createQuery("select p from Pedido p order by p.fechaEntrega");
		totalPedidos = queryTotalPedidos.getResultList();
		
		fechaAux = FechaUtils.getFecha(01, 01, 1900);
		
		for (Pedido pedido : totalPedidos) {
			
			Calendar calFecha1 = Calendar.getInstance();
			calFecha1.setTime(fechaAux);
			Calendar calFecha2 = Calendar.getInstance();
			calFecha2.setTime(pedido.getFechaEntrega());
			
			int dia1 = calFecha1.get(Calendar.DAY_OF_MONTH);;
			int mes1 = calFecha1.get(Calendar.MONTH)+1;
			int ano1 = calFecha1.get(Calendar.YEAR);
			
			int dia2 = calFecha2.get(Calendar.DAY_OF_MONTH);;
			int mes2 = calFecha2.get(Calendar.MONTH)+1;
			int ano2 = calFecha2.get(Calendar.YEAR);
			
			if ((dia1!=dia2)||(mes1!=mes2)||(ano1!=ano2)) {
				calAux = Calendar.getInstance();
				calAux.setTime(pedido.getFechaEntrega());
				
				dia = calAux.get(Calendar.DAY_OF_MONTH);
				mes = calAux.get(Calendar.MONTH)+1;
				ano = calAux.get(Calendar.YEAR);
				
				dia --;
				if (dia<1) {
					dia = 1;
					mes --;
				}
				if (mes<1) {
					mes = 1;
					ano --;
				}
				
				fechasPedidos.add(FechaUtils.getFecha(dia, mes, ano));
			}
			
			fechaAux = pedido.getFechaEntrega();
		}
		//--------------------------------------------------------------------------
		
		//comenzamos a recorrer cada fecha, ingresando una diagramacion para cada dia---------
		Date fechaDiagramacion = null;
		DiagramacionProduccion diagramacionProduccion = null;

			//creamos una lista con los pedidos de ese dia
		List<Pedido> pedidosFecha = null;
		Query queryPedidosFecha = null;
		
		for (Date fechaPedido : fechasPedidos) {
			diagramacionProduccion = new DiagramacionProduccion();
			
			calAux.setTime(fechaPedido);
			
			dia = calAux.get(Calendar.DAY_OF_MONTH);
			mes = calAux.get(Calendar.MONTH)+1;
			ano = calAux.get(Calendar.YEAR);
			
			fechaAux = FechaUtils.getManana(FechaUtils.getFechaHoraCero(FechaUtils.getFecha(dia, mes, ano))); 
						
//			queryPedidosFecha = manager.createQuery("select p from Pedido p join fetch p.detalle where p.fechaEntrega = :fecha");
//			queryPedidosFecha.setParameter("fecha", fechaAux);
//			pedidosFecha = queryPedidosFecha.getResultList();
			List<Receta> recetas = manager.createQuery("select r from Receta r join fetch r.detalle ").getResultList();
			
			queryPedidosFecha = manager.createQuery("select p from Pedido p join fetch p.detalle ");			
			pedidosFecha = queryPedidosFecha.getResultList();
			
			for (Pedido pedido : pedidosFecha) {
				diagramacionProduccion.addPedido(pedido);
			}			
			
			for (ProduccionProducto detalle : diagramacionProduccion.getDetalle()) {
				for (Receta receta : recetas) {
					if(receta.getProducto().equals(detalle.getProducto())){
						detalle.setReceta(receta);
						break;
					}
				}
				for (RecetaDetalle rd : detalle.getReceta().getDetalle()) {					
					float resultado = detalle.getTotal()/detalle.getReceta().getCantidadResultante()*rd.getCantidad();
					ResultadoProductoDetalle productoDetalle =new ResultadoProductoDetalle();
					int nro = (int) (10*Math.random());
					float nro2 = (float) (nro*0.1);
					float result = (int)resultado*nro2;					
					productoDetalle.setCantidad(resultado+result);
					productoDetalle.setRecetaDetalle(rd);
					detalle.addDetalleResultado(productoDetalle);
				}
				int resultado = detalle.getCantidadDemanda();
				int nro = (int) (10*Math.random());
				float nro2 = (float) (nro*0.1);
				int result = (int)(resultado*nro2);
				detalle.setCantidadProducida(resultado+result);
			}
			
			diagramacionProduccion.setFecha(FechaUtils.getFecha(dia, mes, ano));
			diagramacionProduccion.setFechaElaboracion(diagramacionProduccion.getFecha());
			diagramacionProduccion.setFechaEntrega(fechaPedido);
			diagramacionProduccion.actualizarEstado();
			
			dao.save(diagramacionProduccion);
			for (ProduccionProducto detalle : diagramacionProduccion.getDetalle()) {
				dao.save(detalle);
			}
		}
		//------------------------------------------------------------------------------------
		
		t.commit();
	}
}
