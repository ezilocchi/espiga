package base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

import entity.BaseEntity;

@Entity
public class Provincia extends BaseEntity {	
	
	@NotEmpty
	@Length(max=50)
	@Column(unique=true)		
	private String nombre;
	@OneToMany(mappedBy="provincia")
	private Set<Localidad> localidades;
	
	public Provincia(){
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<Localidad> getLocalidades() {
		return localidades;
	}
	public void setLocalidades(Set<Localidad> localidades) {
		this.localidades = localidades;
	}
	
	public void addLocalidad(Localidad localidad){
		if(this.localidades == null){
			this.localidades = new HashSet<Localidad>();
		}
		this.localidades.add(localidad);
		localidad.setProvincia(this);
	}
}
