package stock;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import produccion.Insumo;
import base.Recurso;
import entity.BaseEntity;

@Entity
public class NovedadStock extends BaseEntity{
		
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fecha;
	@ManyToOne	
	private TipoNovedadStock tipo;
	@ManyToOne
	private Insumo insumo;
	@ManyToOne
	private UnidadMedidaInsumoCompra unidadMedida;
	@ManyToOne(fetch = FetchType.LAZY)	
	private Recurso responsable;
	@Length(max=255)
	private String descripcion;
	private Integer cantidad;
	
	public NovedadStock() {
		super();
	}
	public NovedadStock(String descripcion, Integer cantidad, Date fecha,
			TipoNovedadStock tipo) {
		super();
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public TipoNovedadStock getTipo() {
		return tipo;
	}
	public void setTipo(TipoNovedadStock tipo) {
		this.tipo = tipo;
	}
	public Recurso getResponsable() {
		return responsable;
	}
	public void setResponsable(Recurso responsable) {
		this.responsable = responsable;
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
}
