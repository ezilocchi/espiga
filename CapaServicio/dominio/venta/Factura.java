package venta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import entity.BaseEntity;

@Entity
public class Factura extends BaseEntity{

	@Temporal(TemporalType.DATE)
	private Date fecha;
	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy="factura")
	private List<Pedido> detalle;
	
	public Factura() {
		this.detalle = new ArrayList<Pedido>();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pedido> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<Pedido> detalle) {
		this.detalle = detalle;
	}
	
	public void addPedidos(List<Pedido> pedidos){
		for (Pedido p : pedidos) {
			this.addPedido(p);
		}
	}
	public void addPedido(Pedido pedido){
		this.detalle.add(pedido);
		pedido.setFactura(this);
	}
	public void removePedido(Pedido pedido){
		this.detalle.remove(pedido);
		pedido.setFactura(null);
	}
	
	public Float getTotal(){
		float total = 0;		
		for(Pedido detalle : this.detalle){
			total+=detalle.getTotal();
		}
		return total;
	}
	
}
