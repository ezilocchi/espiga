package distribucion;

import static javax.persistence.CascadeType.PERSIST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.NotNull;

import venta.Pedido;
import venta.estados.pedido.EstadoPedido;
import distribucion.estados.EstadoDiagramacion;
import entity.BaseEntity;
import static javax.persistence.CascadeType.REMOVE;


@Entity
public class DiagramacionDistribucion extends BaseEntity{

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fecha;
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fechaEntrega;
	@Enumerated(EnumType.STRING)
	private EstadoDiagramacion estado;	
	@OneToMany(mappedBy="diagramacion", fetch = FetchType.LAZY, cascade = { PERSIST, REMOVE })
	private List<DiagramacionDistribucionDetalle> detalle;
	@Transient
	private DiagramacionDistribucionDetalle pedidosSinEmpleado;
	@Transient
	private DiagramacionDistribucionDetalle pedidosSinTerminar;
	
	public DiagramacionDistribucion() {
		this.detalle = new ArrayList<DiagramacionDistribucionDetalle>();
		this.pedidosSinEmpleado = new DiagramacionDistribucionDetalle();
		this.pedidosSinTerminar = new DiagramacionDistribucionDetalle();
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}	
	public List<DiagramacionDistribucionDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DiagramacionDistribucionDetalle> detalle) {
		this.detalle = detalle;
	}
	public EstadoDiagramacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoDiagramacion estado) {
		this.estado = estado;
	}
	public DiagramacionDistribucionDetalle getPedidosSinZona() {
		return pedidosSinEmpleado;
	}

	public void setPedidosSinZona(DiagramacionDistribucionDetalle pedidosSinZona) {
		this.pedidosSinEmpleado = pedidosSinZona;
	}

	public DiagramacionDistribucionDetalle getPedidosSinTerminar() {
		return pedidosSinTerminar;
	}

	public void setPedidosSinTerminar(
			DiagramacionDistribucionDetalle pedidosSinTerminar) {
		this.pedidosSinTerminar = pedidosSinTerminar;
	}

	public void addDetalle(DiagramacionDistribucionDetalle detalle){				
		detalle.setDiagramacion(this);
		this.detalle.add(detalle);	
	}	
	
	public void addPedido(Pedido pedido){
		if(pedido.getEstado().equals(EstadoPedido.PARCIALMENTE_PRODUCIDO)){
			this.pedidosSinTerminar.addPedido(pedido);
			return;
		}
		if(pedido.getZona() == null || pedido.getZona().getEmpleado() == null){
			this.pedidosSinEmpleado.addPedido(pedido);
		}else{
			for (DiagramacionDistribucionDetalle detalle : this.detalle) {
				if(detalle.getEmpleado().equals(pedido.getZona().getEmpleado())){
					detalle.addPedido(pedido);
					return;
				}
			}			
			this.pedidosSinEmpleado.addPedido(pedido);
		}	
	}
	
	public void actualizarEstado(){
		if(this.estado == null){
			this.estado = EstadoDiagramacion.INICIADA;
			for (DiagramacionDistribucionDetalle dd : this.detalle) {
				for (Pedido pedido : dd.getPedidos()) {
					pedido.setEstado(EstadoPedido.PENDIENTE_ENTREGA);				
				}
			}
		}else {
			this.estado.getEstado().performAction(this);
		}		
	}
	
	public List<Vehiculo> getVehiculosAsignados(){
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		for (DiagramacionDistribucionDetalle detalle : this.detalle) {
			if(detalle.getVehiculo() != null){
				vehiculos.add(detalle.getVehiculo());
			}
		}
		return vehiculos;
	}
	
	public void asignarPedido(Pedido pedido){
		this.pedidosSinEmpleado.getPedidos().remove(pedido);		
	}
	public void desasignarPedido(Pedido pedido){
		this.pedidosSinEmpleado.getPedidos().add(pedido);				
	}
	
	public List<DiagramacionDistribucionDetalle> getDetallesAsignados(){
		List<DiagramacionDistribucionDetalle> result = new ArrayList<DiagramacionDistribucionDetalle>();
		for (DiagramacionDistribucionDetalle diagramacionDistribucionDetalle : this.detalle) {
			if(!diagramacionDistribucionDetalle.getPedidos().isEmpty()){
				result.add(diagramacionDistribucionDetalle);
			}
		}
		return result;
	}
}
