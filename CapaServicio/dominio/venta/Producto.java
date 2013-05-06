package venta;

import static javax.persistence.FetchType.EAGER;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.hibernate.validator.Min;
import org.hibernate.validator.NotNull;

import entity.NamedEntity;

@Entity
public class Producto extends NamedEntity{

	@NotNull
	@Min(value=0)
	private Float precioLista;
	@ManyToOne(fetch=EAGER, optional=false)	
	private TipoProducto tipo;
	@Enumerated(EnumType.STRING)
	private UnidadMedidaPoducto unidadMedida;	
	
	public Producto() {
		this.tipo = new TipoProducto();		
	}
	
	public Producto(String nombre, String descripcion, Float precioLista,
			TipoProducto tipo, UnidadMedidaPoducto unidadMedida) {
		super(nombre,descripcion);
		this.precioLista = precioLista;
		this.tipo = tipo;
		this.unidadMedida = unidadMedida;
	}

	public Float getPrecioLista() {
		return precioLista;
	}
	public void setPrecioLista(Float precioLista) {
		this.precioLista = precioLista;
	}
	public TipoProducto getTipo() {
		return tipo;
	}
	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}
	public UnidadMedidaPoducto getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadMedidaPoducto unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
}
