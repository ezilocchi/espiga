package base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import security.UserSecurity;
import venta.Producto;
import static javax.persistence.CascadeType.MERGE;

@Entity
public class Empleado extends Persona{	
	
	@OneToMany(mappedBy = "empleado", cascade = MERGE)
	private Set<Zona> zonas;
	
	@ManyToMany(cascade = MERGE)
	private List<Producto> productos;
	
	@OneToOne
	private UserSecurity user;	
	
	public Empleado() {
		this.zonas = new HashSet<Zona>();
		this.productos = new ArrayList<Producto>();
	}
	
	public Set<Zona> getZonas() {
		return zonas;
	}
	public void setZonas(Set<Zona> zonas) {
		this.zonas = zonas;
	}
	public UserSecurity getUser() {
		return user;
	}
	public void setUser(UserSecurity user) {
		this.user = user;
	}	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public void addZona(Zona zona){		
		this.zonas.add(zona);
		zona.setEmpleado(this);
	}
	public void removeZona(Zona zona){
		this.zonas.remove(zona);
		zona.setEmpleado(null);
	}
	
	public void addProducto(Producto producto){		
		this.productos.add(producto);		
	}
	public void removeProducto(Producto producto){
		this.productos.remove(producto);		
	}
	
	public String getDescripcionZonas(){
		if(this.zonas!=null && this.zonas.size()>0){
			StringBuilder sb = new StringBuilder();		
			for (Zona z : this.zonas) {
				sb.append(z.getNombre()+", ");
			}
			return sb.toString();
		}else{
			return " - ";
		}		
	}
	
	public String getCargo(){
		if(this.user != null && this.user.getGroup() != null){
			return this.user.getGroup().getNombre();
		}else{
			return " - ";
		}		
	}
}
