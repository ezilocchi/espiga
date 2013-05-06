package produccion;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import base.Empleado;
import entity.BaseEntity;

@Entity
public class ProduccionProductoDetalle extends BaseEntity{

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch=EAGER,optional=false)
	private ProduccionProducto produccionProducto;
	@ManyToOne(fetch=LAZY)
	private Empleado empleado;
	private Long cantidadProducir;
	
	public ProduccionProductoDetalle() {
		
	}	

	public ProduccionProductoDetalle(Empleado empleado) {	
		this.empleado = empleado;
	}


	public ProduccionProducto getProduccionProducto() {
		return produccionProducto;
	}
	public void setProduccionProducto(ProduccionProducto produccionProducto) {
		this.produccionProducto = produccionProducto;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Long getCantidadProducir() {
		return cantidadProducir;
	}
	public void setCantidadProducir(Long cantidadProducir) {
		this.cantidadProducir = cantidadProducir;
	}
	@Override
	public boolean equals(Object object) {		
		if (object instanceof BaseEntity && ((BaseEntity)object).getId()!=null) {
			return super.equals(object);
		}
		if (object instanceof ProduccionProductoDetalle) {
			ProduccionProductoDetalle detalle = (ProduccionProductoDetalle) object;
			if(detalle.getEmpleado()!= null && detalle.getEmpleado().equals(this.getEmpleado())){
				return true;
			}
		}
		return false;
	}
}
