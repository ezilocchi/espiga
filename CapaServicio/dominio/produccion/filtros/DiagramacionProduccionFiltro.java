package produccion.filtros;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import entity.Filtrable;
import produccion.DiagramacionProduccion;

public class DiagramacionProduccionFiltro extends DiagramacionProduccion implements Filtrable{
	
	private Date fechaEntregaDesde;
	private Date fechaEntregaHasta;	
	private Date fechaDesde;
	private Date fechaHasta;
	
	private Date fechaElaboracionDesde;
	private Date fechaElaboracionHasta;	
	private Date fechaCierreDesde;
	private Date fechaCierreHasta;	
	
	public DiagramacionProduccionFiltro() {
		super();
	}

	public DiagramacionProduccionFiltro(Date fechaEntregaDesde, Date fechaEntregaHasta) {
		this.fechaEntregaDesde = fechaEntregaDesde;
		this.fechaEntregaHasta = fechaEntregaHasta;
	}
	
	public Date getFechaEntregaDesde() {
		return fechaEntregaDesde;
	}
	public void setFechaEntregaDesde(Date fechaEntregaDesde) {
		this.fechaEntregaDesde = fechaEntregaDesde;
	}
	public Date getFechaEntregaHasta() {
		return fechaEntregaHasta;
	}
	public void setFechaEntregaHasta(Date fechaEntregaHasta) {
		this.fechaEntregaHasta = fechaEntregaHasta;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Date getFechaElaboracionDesde() {
		return fechaElaboracionDesde;
	}

	public void setFechaElaboracionDesde(Date fechaElaboracionDesde) {
		this.fechaElaboracionDesde = fechaElaboracionDesde;
	}

	public Date getFechaElaboracionHasta() {
		return fechaElaboracionHasta;
	}

	public void setFechaElaboracionHasta(Date fechaElaboracionHasta) {
		this.fechaElaboracionHasta = fechaElaboracionHasta;
	}

	public Date getFechaCierreDesde() {
		return fechaCierreDesde;
	}

	public void setFechaCierreDesde(Date fechaCierreDesde) {
		this.fechaCierreDesde = fechaCierreDesde;
	}

	public Date getFechaCierreHasta() {
		return fechaCierreHasta;
	}

	public void setFechaCierreHasta(Date fechaCierreHasta) {
		this.fechaCierreHasta = fechaCierreHasta;
	}

	@Override
	public void setId(Long id) {
		if(id!=null && id>0){
			super.setId(id);			
		}else{
			super.setId(null);
		}
	}	
	
	
	
}
