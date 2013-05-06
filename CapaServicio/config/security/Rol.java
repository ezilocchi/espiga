package security;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import entity.BaseEntity;

@Entity
public class Rol extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	private Permiso perfil;	
	
	public Rol(Permiso perfil) {
		super();
		this.perfil = perfil;
	}
	
	public Rol(Long id) {
		super(id);
	}

	public Rol() {
		super();
	}
	
	public Permiso getPerfil() {
		return perfil;
	}
	public void setPerfil(Permiso perfil) {
		this.perfil = perfil;
	}
	
	public String getNombre(){
		return this.perfil.name();
	}
}
