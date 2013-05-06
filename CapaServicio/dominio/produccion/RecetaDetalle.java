package produccion;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import entity.BaseEntity;

@Entity
public class RecetaDetalle extends BaseEntity{

	@ManyToOne
	private Receta receta;
	@ManyToOne(optional=false,fetch = FetchType.EAGER)
	private Insumo insumo;	
	private Float cantidad;
	
	public Receta getReceta() {
		return receta;
	}
	public void setReceta(Receta receta) {
		this.receta = receta;
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
}
