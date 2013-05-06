package stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import stock.estados.EstadoDetalleOrdenCompra;
import stock.estados.EstadoOrdenCompra;
import venta.PedidoDetalle;
import entity.BaseEntity;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;

@Entity
public class OrdenCompra extends BaseEntity{

	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Temporal(TemporalType.DATE)
	private Date fechaPedido;
	@ManyToOne
	private Proveedor proveedor;
	@OneToMany(mappedBy="ordenCompra", cascade = { PERSIST, MERGE })
	private List<OrdenCompraDetalle> detalle;
	@Enumerated(EnumType.STRING)
	private EstadoOrdenCompra  estado;
	
	public OrdenCompra() {
		this.proveedor = new Proveedor();
		this.detalle = new ArrayList<OrdenCompraDetalle>();
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public List<OrdenCompraDetalle> getDetalle() {
		return detalle;
	}
	public EstadoOrdenCompra getEstado() {
		return estado;
	}
	public void setEstado(EstadoOrdenCompra estado) {
		this.estado = estado;
	}
	public void setDetalle(List<OrdenCompraDetalle> detalle) {
		this.detalle = detalle;
	}
	public void addDetalle(OrdenCompraDetalle detalle){
		this.detalle.add(detalle);
		detalle.setOrdenCompra(this);
	}
	public void removeDetalle(OrdenCompraDetalle detalle){
		this.detalle.remove(detalle);
		detalle.setOrdenCompra(null);
	}
	
	public void actualizarEstado() {
		if(this.estado == null){
			this.estado = EstadoOrdenCompra.CREADA;
			for (OrdenCompraDetalle detalle : this.detalle) {
				detalle.setEstado(EstadoDetalleOrdenCompra.CREADO);
			}
		}else{
			this.estado.getEstado().performAction(this);
		}				
	}
	
	public Float getTotal(){
		float total = 0;
		if(this.detalle == null || this.detalle.size() == 0){
			return total;
		}
		for(OrdenCompraDetalle detalle : this.detalle){
			total+=detalle.getPrecioUnitario()*detalle.getCantidad();
		}
		return total;
	}
}
