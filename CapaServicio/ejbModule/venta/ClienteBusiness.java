package venta;

import java.util.List;

import javax.ejb.Local;

import base.Recurso;

@Local
public interface ClienteBusiness {
	
	public List<Pedido> getPedidosByCliente(Cliente cliente, Pedido pedido, boolean facturar);
	List<Recurso> getRecursosByCliente(Cliente cliente);
	void save(Cliente cliente);
	void update(Cliente cliente);
	public void saveFactura(Factura factura);
}
