package venta;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotNull;

import base.Zona;

import venta.estados.pedido.EstadoPedido;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import entity.BaseEntity;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;


@Entity
public class Pedido extends BaseEntity{

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fecha;
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fechaEntrega;		
	@Enumerated(EnumType.STRING)
	private EstadoPedido estado;
	@OneToMany(mappedBy = "pedido", cascade = { PERSIST, MERGE, REMOVE }, fetch = LAZY)
	private List<PedidoDetalle> detalle;
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	@ManyToOne
	private Zona zona;
	@ManyToOne
	private Factura factura;

	public Pedido() {
		this.detalle = new ArrayList<PedidoDetalle>();		
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}		
	public List<PedidoDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<PedidoDetalle> detalle) {
		this.detalle = detalle;
	}
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}	
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void addDetalle(PedidoDetalle detalle){				
		detalle.setPedido(this);
		this.detalle.add(detalle);		
	}
	public void removeDetalle(PedidoDetalle detalle){
		this.detalle.remove(detalle);
		detalle.setPedido(null);
	}
	
	public Float getTotal(){
		float total = 0;
		if(this.detalle == null){
			return total;
		}
		for(PedidoDetalle detalle : this.detalle){
			total+=detalle.getPrecioVentaUnitario()*detalle.getCantidad();
		}
		return total;
	}

	
	public void actualizarEstado() {
		if(this.estado == null){
			this.estado = EstadoPedido.CREADO;
			for (PedidoDetalle detalle : this.getDetalle()) {
				detalle.setEstado(EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION);
			}
		}else{
			this.estado.getEstado().performAction(this);			
		}
	}
	
	public boolean isIncompleate(){
		return this.estado.equals(EstadoPedido.PARCIALMENTE_PRODUCIDO);
	}
}
