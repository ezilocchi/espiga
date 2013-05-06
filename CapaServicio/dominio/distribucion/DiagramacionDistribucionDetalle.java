package distribucion;

import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import venta.Pedido;
import base.Empleado;
import base.Zona;
import entity.BaseEntity;

@Entity
public class DiagramacionDistribucionDetalle extends BaseEntity{

	@ManyToOne(optional=false, fetch = LAZY)
	private DiagramacionDistribucion diagramacion;
	@ManyToOne(optional=false, fetch = LAZY)
	private Empleado empleado;
	@ManyToOne
	private Vehiculo vehiculo;
	@ManyToOne
	private Zona zona;
	@OneToMany(fetch=LAZY)
	private List<Pedido> pedidos;
	
	public DiagramacionDistribucionDetalle() {
		this.pedidos = new ArrayList<Pedido>();
	}

	public DiagramacionDistribucion getDiagramacion() {
		return diagramacion;
	}
	public void setDiagramacion(DiagramacionDistribucion diagramacion) {
		this.diagramacion = diagramacion;
	}	
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void addPedido(Pedido pedido){		
		this.pedidos.add(pedido);		
	}
	
	public boolean isEmpty(){
		return this.pedidos.isEmpty();
	}
}
