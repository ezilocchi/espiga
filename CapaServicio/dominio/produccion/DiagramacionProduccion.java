package produccion;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import produccion.estados.EstadoDiagramacion;
import venta.Pedido;
import venta.PedidoDetalle;
import venta.Producto;
import venta.estados.pedido.EstadoPedido;
import venta.estados.pedidoDetalle.EstadoPedidoDetalle;
import entity.BaseEntity;

@Entity
public class DiagramacionProduccion extends BaseEntity {

	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Temporal(TemporalType.DATE)
	private Date fechaElaboracion;
	@Temporal(TemporalType.DATE)
	private Date fechaCierre;
	@Temporal(TemporalType.DATE)
	private Date fechaEntrega;
	@Enumerated(EnumType.STRING)
	private EstadoDiagramacion estado;
	@OneToMany(mappedBy = "diagramacion", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	private List<ProduccionProducto> detalle;	

	// TODO Trabajar relaciones ManyToMany de forma unidireccional
	@ManyToMany(cascade = MERGE)	
	private Set<Pedido> pedidos;

	public DiagramacionProduccion() {
		this.pedidos = new HashSet<Pedido>();
		this.detalle = new ArrayList<ProduccionProducto>();
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public Date getFechaElaboracion() {
		return fechaElaboracion;
	}
	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public EstadoDiagramacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoDiagramacion estado) {
		this.estado = estado;
	}
	public List<ProduccionProducto> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<ProduccionProducto> detalle) {
		this.detalle = detalle;
	}
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	private void addDetalle(ProduccionProducto detalle) {		
		detalle.setDiagramacion(this);
		this.detalle.add(detalle);
	}

	public void addPedido(Pedido pedido) {		
		if(pedidos.add(pedido)){
			// TODO implementar el mapeo de el detalle (produccionProducto) como un Map en lugar de List
			Map<Producto, ProduccionProducto> map = new HashMap<Producto, ProduccionProducto>();
			for (ProduccionProducto item : this.detalle) {
				map.put(item.getProducto(), item);
			}
			for (PedidoDetalle detalle : pedido.getDetalle()) {
				if (detalle.getEstado().equals(EstadoPedidoDetalle.PENDIENTE_DIAGRAMACION)) {
					if (map.get(detalle.getProducto()) == null) {
						this.addDetalle(new ProduccionProducto(detalle));
					} else {
						ProduccionProducto pp = map.get(detalle.getProducto());
						pp.addPedidoDetalle(detalle);
					}					
				}
			}
		}		
	}

	public void removePedido(Pedido pedido) {
		if(this.pedidos.remove(pedido)){
			// TODO implementar el mapeo de el detalle (produccionProducto) como un Map en lugar de List
			Map<Producto, ProduccionProducto> map = new HashMap<Producto, ProduccionProducto>();
			for (ProduccionProducto item : this.detalle) {
				map.put(item.getProducto(), item);			
			}
			for (PedidoDetalle detalle : pedido.getDetalle()) {
				ProduccionProducto pp = map.get(detalle.getProducto());
				pp.removePedidoDetalle(detalle);			
			}
		}		
	}

	public void addPedidoDetalle(PedidoDetalle pedidoDetalle) {
		// TODO implementar el mapeo de el detalle (produccionProducto) como un Map en lugar de List
		Map<Producto, ProduccionProducto> map = new HashMap<Producto, ProduccionProducto>();
		for (ProduccionProducto item : this.detalle) {
			map.put(item.getProducto(), item);
		}

		if (map.get(pedidoDetalle.getProducto()) == null) {		
			this.addDetalle(new ProduccionProducto(pedidoDetalle));
		} else {
			ProduccionProducto pp = map.get(pedidoDetalle.getProducto());
			pp.addPedidoDetalle(pedidoDetalle);
		}
		this.pedidos.add(pedidoDetalle.getPedido());
	}

	public void removePedidoDetalle(PedidoDetalle pedidoDetalle) {
		for (ProduccionProducto item : this.detalle) {
			if(item.getProducto().equals(pedidoDetalle.getProducto())){
				item.removePedidoDetalle(pedidoDetalle);
				return;
			}
		}
	}
	
	public void actualizarEstado(){
		if(this.estado == null){
			this.estado = EstadoDiagramacion.INICIADA;
			this.setFecha(new Date(System.currentTimeMillis()));
			for (ProduccionProducto pp : this.getDetalle()) {
				for (PedidoDetalle pd : pp.getPedidoDetalles()) {
					pd.setEstado(EstadoPedidoDetalle.DIAGRAMADO);
				}
			}
			for (Pedido pedido : this.getPedidos()) {
				if(!pedido.getEstado().equals(EstadoPedido.PARCIALMENTE_PRODUCIDO)){
					pedido.actualizarEstado();					
				}
			}
		}else{			
			this.estado.getEstado().performAction(this);
		}
	}
	
	public void cerrar(){
		for (ProduccionProducto pp : this.getDetalle()) {
			pp.cerrar();
		}
	}
	
	public List<Entry<Insumo, Float>> getTotalInsumosEstimados(){
		Map<Insumo, Float> insumos = new HashMap<Insumo, Float>();
		
		for (ProduccionProducto detalle : this.getDetalle()) {
			for (RecetaDetalle recetaDetalle : detalle.getReceta().getDetalle()) {
				Insumo insumo = recetaDetalle.getInsumo();				
				float resultado = detalle.getTotal()/detalle.getReceta().getCantidadResultante()*recetaDetalle.getCantidad();				
				if(insumos.get(insumo)==null){
					insumos.put(insumo, resultado);
				}else{
					Float acu = insumos.get(insumo);
					insumos.put(insumo, acu+=resultado);
				}
			}
		}				
		return new ArrayList<Entry<Insumo, Float>>(insumos.entrySet());
	}
	
	public List<Entry<Insumo, Float>> getTotalInsumosConsumidos(){
		Map<Insumo, Float> insumos = new HashMap<Insumo, Float>();
		
		for (ProduccionProducto detalle : this.getDetalle()) {
			for (ResultadoProductoDetalle resultadoProduccion : detalle.getDetalleResultado()) {
				Insumo insumo = resultadoProduccion.getRecetaDetalle().getInsumo();
				Float resultado = resultadoProduccion.getCantidad();
				if(insumos.get(insumo)==null){
					insumos.put(insumo, resultado);
				}else{
					Float acu = insumos.get(insumo);
					insumos.put(insumo, acu+=resultado);
				}
			}
		}				
		return new ArrayList<Entry<Insumo, Float>>(insumos.entrySet());
	}
}
