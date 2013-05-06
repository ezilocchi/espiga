package stock.filtros;

import java.util.Date;

import stock.OrdenCompra;
import stock.Proveedor;
import entity.Filtrable;

public class OrdenCompraFiltro extends OrdenCompra implements Filtrable{

	private Date fechaDesde;
	private Date fechaPedidoDesde;
	private Date fechaHasta;
	private Date fechaPedidoHasta;	
	
	public OrdenCompraFiltro(Date fechaPedidoDesde, Date fechaPedidoHasta) {
		this();
		this.fechaPedidoDesde = fechaPedidoDesde;
		this.fechaPedidoHasta = fechaPedidoHasta;
	}
	public OrdenCompraFiltro() {
		this.setProveedor(new Proveedor());
	}
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaPedidoDesde() {
		return fechaPedidoDesde;
	}
	public void setFechaPedidoDesde(Date fechaPedidoDesde) {
		this.fechaPedidoDesde = fechaPedidoDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Date getFechaPedidoHasta() {
		return fechaPedidoHasta;
	}
	public void setFechaPedidoHasta(Date fechaPedidoHasta) {
		this.fechaPedidoHasta = fechaPedidoHasta;
	}
	
	
}
