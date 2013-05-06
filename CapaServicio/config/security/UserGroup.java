package security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import entity.NamedEntity;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserGroup extends NamedEntity{

	@ManyToMany(cascade = MERGE)
	private List<Rol> roles;
	
	public UserGroup(String nombre, String desc) {		
		super(nombre, desc);
		this.roles = new ArrayList<Rol>();
	}	
	public UserGroup() {
		this.roles = new ArrayList<Rol>();
	}
	
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	public void addRol(Rol rol){
		this.roles.add(rol);
	}
}
