package produccion;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import venta.PedidoDetalle;
import venta.Producto;
import entity.BaseEntity;
import static javax.persistence.CascadeType.REMOVE;


@Entity
public class ProduccionProducto extends BaseEntity{
	
	@ManyToOne(fetch = EAGER)
	private Receta receta;
	@ManyToOne(optional = false)
	private DiagramacionProduccion diagramacion;
	@OneToMany(mappedBy = "produccionProducto", fetch = LAZY, cascade = { PERSIST, REMOVE })
	private List<ProduccionProductoDetalle> detalle;
	@OneToMany(mappedBy = "produccionProducto", fetch = LAZY, cascade = PERSIST)
	private List<ResultadoProductoDetalle> detalleResultado;
	@ManyToOne
	private Producto producto;
	@OneToMany 
	private Set<PedidoDetalle> pedidoDetalles;
	private Integer cantidadDemanda;
	private Integer cantidadExcedente;
	private Integer cantidadProducida;
	
	public ProduccionProducto() {	
		this.detalleResultado = new ArrayList<ResultadoProductoDetalle>();
		this.detalle = new ArrayList<ProduccionProductoDetalle>();
		this.pedidoDetalles = new HashSet<PedidoDetalle>();
		this.cantidadExcedente = 0;
	}	
	
	public ProduccionProducto(PedidoDetalle pedidoDetalle){
		this();
		this.producto = pedidoDetalle.getProducto();
		this.pedidoDetalles.add(pedidoDetalle);
		this.cantidadDemanda = pedidoDetalle.getCantidad();
		
	}
	
	public DiagramacionProduccion getDiagramacion() {
		return diagramacion;
	}
	public void setDiagramacion(DiagramacionProduccion diagramacion) {
		this.diagramacion = diagramacion;
	}	
	public List<ProduccionProductoDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<ProduccionProductoDetalle> detalle) {
		this.detalle = detalle;
	}
	public List<ResultadoProductoDetalle> getDetalleResultado() {
		return detalleResultado;
	}
	public void setDetalleResultado(List<ResultadoProductoDetalle> detalleResultado) {
		this.detalleResultado = detalleResultado;
	}
	public Set<PedidoDetalle> getPedidoDetalles() {
		return pedidoDetalles;
	}
	public void setPedidoDetalles(Set<PedidoDetalle> pedidoDetalles) {
		this.pedidoDetalles = pedidoDetalles;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Receta getReceta() {
		return receta;
	}
	public void setReceta(Receta receta) {
		this.receta = receta;
	}	
	public Integer getCantidadProducida() {
		return cantidadProducida;
	}
	public void setCantidadProducida(Integer cantidadProducida) {
		this.cantidadProducida = cantidadProducida;
	}
	public Integer getCantidadDemanda() {
		return cantidadDemanda;
	}
	public void setCantidadDemanda(Integer cantidadDemanda) {
		this.cantidadDemanda = cantidadDemanda;
	}	
	public Integer getCantidadExcedente() {
		return cantidadExcedente;
	}
	public void setCantidadExcedente(Integer cantidadExcedente) {
		this.cantidadExcedente = cantidadExcedente;
	}

	public Integer getDesvacion(){ //TODO revizar este metodo
		return cantidadDemanda-cantidadProducida;
	}
	
	public void addDetalle(ProduccionProductoDetalle detalle){				
		detalle.setProduccionProducto(this);
		this.detalle.add(detalle);
	}
	public boolean removeDetalle(ProduccionProductoDetalle detalle){
		for (ProduccionProductoDetalle ppd : this.detalle) {
			if(ppd.getEmpleado().equals(detalle.getEmpleado())){
				detalle.setProduccionProducto(null);
				return this.detalle.remove(detalle);
			}
		}
		return false;
	}
	
	public void addDetalleResultado(ResultadoProductoDetalle detalle){				
		detalle.setProduccionProducto(this);
		this.detalleResultado.add(detalle);
	}
	
	public void addPedidoDetalle(PedidoDetalle pedidoDetalle){		
		if(this.pedidoDetalles.add(pedidoDetalle)){
			this.cantidadDemanda += pedidoDetalle.getCantidad();
		}	
	}
	public void removePedidoDetalle(PedidoDetalle pedidoDetalle){
		if(this.pedidoDetalles.remove(pedidoDetalle)){
			this.cantidadDemanda -= pedidoDetalle.getCantidad();
		}		
	}	
	
	public float getTotal(){
		return this.cantidadDemanda+this.cantidadExcedente;
	}
	
	public void cerrar(){
		this.detalleResultado = new ArrayList<ResultadoProductoDetalle>();
		 
		//TODO esto va a cambiar cuando implementemos la parte estadistica
		this.cantidadProducida = (int) this.getTotal();
		for (RecetaDetalle detalle : this.receta.getDetalle()) {			
			this.addDetalleResultado(new ResultadoProductoDetalle(detalle,this.getTotal()/this.receta.getCantidadResultante()*detalle.getCantidad()));
		}		
	}
}
