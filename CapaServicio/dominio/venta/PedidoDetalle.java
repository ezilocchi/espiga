package venta;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.validator.Min;

import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import entity.BaseEntity;


@Entity
public class PedidoDetalle extends BaseEntity{

	@ManyToOne	
	private Pedido pedido;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Producto producto;
	@Enumerated(EnumType.STRING)
	private EstadoPedidoDetalle estado;
	@Min(value = 1)
	private Integer cantidad;
	@Min(value = 0)
	private Float precioVentaUnitario;
	
	public PedidoDetalle() {
		super();
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public EstadoPedidoDetalle getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedidoDetalle estado) {
		this.estado = estado;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Float getPrecioVentaUnitario() {
		return precioVentaUnitario;
	}
	public void setPrecioVentaUnitario(Float precioVentaUnitario) {
		this.precioVentaUnitario = precioVentaUnitario;
	}
	
	public Float getSubTotal(){
		return this.cantidad * this.precioVentaUnitario;
	}
	
	public void actualizarEstado(){
		if(this.estado == null){
			this.estado = EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION;
			return;
		}
		this.estado.getEstado().performAction(this);
	}
	
	@Override
	public boolean equals(Object object) {		
		if (object instanceof BaseEntity && ((BaseEntity)object).getId()!=null) {
			return super.equals(object);
		}
		if (object instanceof PedidoDetalle) {
			PedidoDetalle detalle = (PedidoDetalle) object;
			if(detalle.getProducto()!= null && detalle.getProducto().equals(this.getProducto())){
				return true;
			}
		}
		return false;
	}
	
	
}
