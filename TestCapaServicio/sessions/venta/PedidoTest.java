package venta;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import utils.FechaUtils;
import venta.estados.pedido.EstadoPedido;

import com.bm.testsuite.BaseSessionBeanFixture;

public class PedidoTest extends BaseSessionBeanFixture<PedidoBusinessImpl>{

	public PedidoTest() {
		super(PedidoBusinessImpl.class, new Class[]{});
	}
	
	public void testConsulta(){
		String query = "select dp.producto, sum(dp.cantidad) " +
					   "from PedidoDetalle dp " +
					   "where dp.pedido.fechaEntrega = :fecha and " +
					   "dp.pedido.estado.nombre = :estado " +
					   "group by dp.producto";
		EntityManager em = super.getEntityManager();
		Query q = em.createQuery(query);
		Date date = FechaUtils.getFecha(23, 9, 2009);
		List list = q.setParameter("fecha", date).setParameter("estado", EstadoPedido.CANCELADO).getResultList();
		System.out.println(list);
	}
	
}
