package base;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Recurso extends Persona{

	@ManyToOne
	private Cargo cargo;

	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}	
}
