package base;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.validator.Length;

@Embeddable
public class Direccion {

	@Length(max=50)
	private String calle;	
	private Integer numero;
	private Integer piso;
	private Character depto;	
	@ManyToOne
	private Barrio barrio;

	public Direccion() {		
		this.barrio = new Barrio();
	}

	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getPiso() {
		return piso;
	}
	public void setPiso(Integer piso) {
		this.piso = piso;
	}
	public Character getDepto() {
		return depto;
	}
	public void setDepto(Character depto) {
		this.depto = depto;
	}
	public Barrio getBarrio() {
		return barrio;
	}
	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}
	
	@Override
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		ret.append(calle + " " + numero);
		if (barrio != null)
		{
			ret.append(" Barrio " + barrio.getNombre());
		}
		if (piso != null)
		{
			ret.append(" " + piso);
		}
		if (depto != null)
		{
			ret.append(" " + depto);
		}
		return ret.toString();
	}
}
