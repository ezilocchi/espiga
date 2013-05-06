package produccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotNull;

import venta.Producto;
import entity.NamedEntity;

@Entity
public class Receta extends NamedEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	private Producto producto;	
	@OneToMany(mappedBy="receta",fetch = FetchType.LAZY)
	private List<RecetaDetalle> detalle;
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fechaAlta;
	private Boolean predeterminada;
	private Integer cantidadResultante;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
	public List<RecetaDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<RecetaDetalle> detalle) {
		this.detalle = detalle;
	}
	public Integer getCantidadResultante() {
		return cantidadResultante;
	}
	public void setCantidadResultante(Integer cantidadResultante) {
		this.cantidadResultante = cantidadResultante;
	}	
	public Boolean getPredeterminada() {
		return predeterminada;
	}
	public void setPredeterminada(Boolean predeterminada) {
		this.predeterminada = predeterminada;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public void addDetalle(RecetaDetalle detalle){
		if(this.detalle == null){
			this.detalle = new ArrayList<RecetaDetalle>();
		}
		this.detalle.add(detalle);
		detalle.setReceta(this);
	}
}
