package base;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

import entity.BaseEntity;

@Entity
public class Barrio extends BaseEntity{

	@ManyToOne
	private Localidad localidad;
	@ManyToOne
	private Zona zona;
	@NotEmpty
	@Length(max=50)
	private String nombre;	
	
	public Barrio(Long id) {
		super(id);
	}
	public Barrio() {		
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}	
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
