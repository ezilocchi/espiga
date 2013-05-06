package produccion;

import static javax.persistence.FetchType.EAGER;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import entity.BaseEntity;



@Entity
public class ResultadoProductoDetalle extends BaseEntity{

	@ManyToOne(fetch=EAGER,optional=false)
	private ProduccionProducto produccionProducto;
	@ManyToOne(fetch=EAGER,optional=false)
	private RecetaDetalle recetaDetalle;
	private Float cantidad;
	
	public ResultadoProductoDetalle() {
		
	}	
		
	public ResultadoProductoDetalle(RecetaDetalle recetaDetalle, Float cantidad) {
		
		this.recetaDetalle = recetaDetalle;
		this.cantidad = cantidad;
	}


	public RecetaDetalle getRecetaDetalle() {
		return recetaDetalle;
	}
	public void setRecetaDetalle(RecetaDetalle recetaDetalle) {
		this.recetaDetalle = recetaDetalle;
	}	
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public ProduccionProducto getProduccionProducto() {
		return produccionProducto;
	}
	public void setProduccionProducto(ProduccionProducto produccionProducto) {
		this.produccionProducto = produccionProducto;
	}
	
}
