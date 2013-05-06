package base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;


import entity.BaseEntity;

@Entity
public class Zona extends BaseEntity{

	@NotEmpty
	@Column(unique=true)	
	@Length(max=30)
	private String nombre;
	@ManyToOne
	private Empleado empleado;
	@OneToMany(mappedBy="zona")
	private Set<Barrio> barrios;
	
	public Zona() {
		super();
	}	

	public Zona(Long id) {
		super(id);
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
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
		barrio.setZona(this);
	}
}
