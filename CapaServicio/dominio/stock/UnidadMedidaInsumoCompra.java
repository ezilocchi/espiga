package stock;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.NotNull;

import produccion.Insumo;
import entity.NamedEntity;

@Entity
public class UnidadMedidaInsumoCompra extends NamedEntity {
	
	@ManyToOne	
	private Insumo insumo;
	private Float precioUnitario;
	@NotNull
	private Float cantidad;
	private boolean predeterminada;

	public UnidadMedidaInsumoCompra() {
		this.precioUnitario = 0f;
	}
	
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public Float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public boolean isPredeterminada() {
		return predeterminada;
	}
	public void setPredeterminada(boolean predeterminada) {
		this.predeterminada = predeterminada;
	}
		
}
