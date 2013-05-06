package venta.filtros;

import java.util.Date;

import entity.Filtrable;

import venta.Pedido;

public class PedidoFiltro extends Pedido implements Filtrable{

	private Date fechaEntregaDesde;
	private Date fechaEntregaHasta;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public PedidoFiltro(Date fechaEntregaDesde, Date fechaEntregaHasta) {	
		this.fechaEntregaDesde = fechaEntregaDesde;
		this.fechaEntregaHasta = fechaEntregaHasta;
	}
	public PedidoFiltro() {
		super();
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
}
