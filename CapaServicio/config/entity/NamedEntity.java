package entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.NotEmpty;

@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {	
	
	@Column(unique = true, length = 50)
	@NotEmpty
	private String nombre;
	@Column(unique = false, length = 255)
	private String descripcion;

	public NamedEntity() {
		super();
	}	

	public NamedEntity(String nombre, String desc) {		
		setNombre(nombre);
		setDescripcion(desc);
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String desc) {
		this.descripcion = desc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
}
