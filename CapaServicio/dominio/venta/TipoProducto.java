package venta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import entity.NamedEntity;


@Entity
public class TipoProducto extends NamedEntity{
	
	@OneToMany(mappedBy="tipo")
	private List<Producto> productos;
	
	public TipoProducto() {
		this.productos = new ArrayList<Producto>();
	}
	public TipoProducto(String nombre, String descripcion) {
		super(nombre,descripcion);
		this.productos = new ArrayList<Producto>();
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}	
	
}
