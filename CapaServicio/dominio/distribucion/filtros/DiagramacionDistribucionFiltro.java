package distribucion.filtros;

import java.util.Date;

import distribucion.DiagramacionDistribucion;
import entity.Filtrable;

public class DiagramacionDistribucionFiltro extends DiagramacionDistribucion implements Filtrable{

	private Date fechaEntregaDesde;
	private Date fechaEntregaHasta;
	private Date fechaDesde;
	private Date fechaHasta;	
	
	public DiagramacionDistribucionFiltro() {
	}
	
	public DiagramacionDistribucionFiltro(Date fechaEntregaDesde, Date fechaEntregaHasta) {
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
	
	@Override
	public void setId(Long id) {
		if(id!=null && id>0){
			super.setId(id);			
		}else{
			super.setId(null);
		}
	}	
	
}
