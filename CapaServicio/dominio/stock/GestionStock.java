package stock;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import produccion.Insumo;

import stock.estrategias.EstrategiaGestion;
import stock.estrategias.EstrategiaGestionStock;

@Embeddable
public class GestionStock implements EstrategiaGestionStock{
	
	private Float costoStockInmovil;
	private Integer stockReposicion;
	private Integer lotePedidoOptimo;
	private Float costoTransaccionPedido;
	private Integer stockMaximo;
	@Enumerated(EnumType.STRING)
	private EstrategiaGestion estrategiaGestion;
	
	public GestionStock() {
		super();
	}
	
	public Integer getStockReposicion() {
		return stockReposicion;
	}
	public void setStockReposicion(Integer stockReposicion) {
		this.stockReposicion = stockReposicion;
	}
	public Integer getLotePedidoOptimo() {
		return lotePedidoOptimo;
	}
	public void setLotePedidoOptimo(Integer lotePedidoOptimo) {
		this.lotePedidoOptimo = lotePedidoOptimo;
	}
	public Float getCostoStockInmovil() {
		return costoStockInmovil;
	}

	public void setCostoStockInmovil(Float costoStockInmovil) {
		this.costoStockInmovil = costoStockInmovil;
	}

	public Float getCostoTransaccionPedido() {
		return costoTransaccionPedido;
	}
	public void setCostoTransaccionPedido(Float costoTransaccionPedido) {
		this.costoTransaccionPedido = costoTransaccionPedido;
	}
	public Integer getStockMaximo() {
		return stockMaximo;
	}
	public void setStockMaximo(Integer stockMaximo) {
		this.stockMaximo = stockMaximo;
	}
	public EstrategiaGestion getEstrategiaGestion() {
		return estrategiaGestion;
	}
	public void setEstrategiaGestion(EstrategiaGestion estrategiaGestion) {
		this.estrategiaGestion = estrategiaGestion;
	}
	
	public String getNombreGestion(){
		return this.estrategiaGestion.getNombre();
	}

	@Override
	public Integer getCantidadReposicion(Insumo i) {
		return this.estrategiaGestion.getEstrategia().getCantidadReposicion(new Insumo());
	}

	@Override
	public boolean reponer(Insumo i) {
		return this.estrategiaGestion.getEstrategia().reponer(new Insumo());
	}

	@Override
	public SemaforoStock getEstado(Insumo insumo) {
		return this.estrategiaGestion.getEstrategia().getEstado(insumo);
	}	
}
