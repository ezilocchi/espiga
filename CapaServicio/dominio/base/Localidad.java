package base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

import entity.BaseEntity;

@Entity
public class Localidad extends BaseEntity{

	@NotEmpty
	@Length(max=50)	
	@Column(unique=true)
	private String nombre;
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	private Provincia provincia;
	@OneToMany(mappedBy="localidad")
	private Set<Barrio> barrios;
	
	public Localidad() {
		this.provincia = new Provincia();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}	
	public Set<Barrio> getBarrios() {
		return barrios;
	}
	public void setBarrios(Set<Barrio> barrios) {
		this.barrios = barrios;
	}
	
	public void addBarrio(Barrio barrio){
		if(this.barrios == null){
			this.barrios = new HashSet<Barrio>();
		}
		this.barrios.add(barrio);
		barrio.setLocalidad(this);
	}
}
