package distribucion;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotEmpty;

import entity.BaseEntity;



@Entity
public class Vehiculo extends BaseEntity{
	
	@Length(max=50)
	@NotEmpty
	private String marca;
	@Length(max=50)
	@NotEmpty
	private String modelo;
	@Min(value = 1950)
	private Integer anio; 
	@Length(max=6)
	@NotEmpty
	private String dominio;
	@Temporal(TemporalType.DATE)
	private Date fechaAdquisicion;	
	
	public Vehiculo() {
		super();
	}

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}
	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}
	
}
