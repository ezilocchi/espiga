package stock;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.NotNull;

import produccion.Insumo;
import stock.estados.EstadoDetalleOrdenCompra;
import entity.BaseEntity;

@Entity
public class OrdenCompraDetalle extends BaseEntity{

	@ManyToOne
	private OrdenCompra ordenCompra;
	@ManyToOne
	private Insumo insumo;
	@ManyToOne
	private UnidadMedidaInsumoCompra unidadMedida;
	@Enumerated(EnumType.STRING)
	private EstadoDetalleOrdenCompra estado;
	@NotNull
	private Integer cantidad;
	@NotNull
	private Float precioUnitario;
	@Transient
	private boolean cancelado;
	@Transient
	private boolean pendiente;
	
	public OrdenCompraDetalle() {
		this.insumo = new Insumo();
		this.unidadMedida = new UnidadMedidaInsumoCompra();		
	}
	
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}	
	public UnidadMedidaInsumoCompra getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadMedidaInsumoCompra unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public EstadoDetalleOrdenCompra getEstado() {
		return estado;
	}
	public void setEstado(EstadoDetalleOrdenCompra estado) {
		this.estado = estado;
	}
	
	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		if(cancelado){
			this.pendiente = false;
		}
		this.cancelado = cancelado;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		if(pendiente){
			this.cancelado = false;
		}
		this.pendiente = pendiente;
	}

	public Float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public int getIngreso(){
		//TODO reeveer
		return (int)(this.cantidad * this.unidadMedida.getCantidad());
	}
	
	public float getSubTotal(){
		return this.cantidad * this.precioUnitario;
	}
}
