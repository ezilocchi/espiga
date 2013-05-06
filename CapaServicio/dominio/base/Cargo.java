package base;

import javax.persistence.Entity;

import security.UserGroup;

@Entity
public class Cargo extends UserGroup {	
	
	public Cargo() {
		super();
	}
	public Cargo(String nombre, String descripcion) {
		super(nombre,descripcion);
	}
}
