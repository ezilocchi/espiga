package stock;

import javax.persistence.Entity;

import org.hibernate.validator.NotNull;

import entity.NamedEntity;


@Entity
public class TipoNovedadStock extends NamedEntity{
	
	@NotNull
	private Boolean incrementa;
	
	public TipoNovedadStock() {
		super();
	}

	public Boolean getIncrementa() {
		return incrementa;
	}
	public void setIncrementa(Boolean incrementa) {
		this.incrementa = incrementa;
	}
}
