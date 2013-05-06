package monitoreo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import produccion.Insumo;
import utils.FechaUtils;
import venta.Pedido;
import venta.estados.pedido.EstadoPedido;

@Stateless
public class VerificadorMonitorImpl implements VerificadorMonitor{

	@PersistenceContext
	private EntityManager em;
	
	public List<Insumo> checkStock(){
		List<Insumo> list = em.createQuery("select i from Insumo i").getResultList();
		List<Insumo> result = new ArrayList<Insumo>();
		for (Insumo insumo : list) {
			if(insumo.reponer()){
				result.add(insumo);
			}
		}
		return result;
	}
	
	public List<Pedido> checkPedidoPendiente(EstadoPedido estado){
		List<Pedido> list = em.createQuery("select p from Pedido p where p.estado = :estadoPedido and p.fechaEntrega = :fec")
								.setParameter("estadoPedido", estado)
								.setParameter("fec", FechaUtils.getFecha(new Date(System.currentTimeMillis())))
								.getResultList();
		
		return list;
	}
	
	
}
