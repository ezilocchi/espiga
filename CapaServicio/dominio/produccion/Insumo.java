package produccion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.hibernate.validator.Range;

import stock.SemaforoStock;
import stock.UnidadMedidaInsumoCompra;
import stock.estrategias.EstrategiaGestion;
import entity.NamedEntity;


@Entity
public class Insumo extends NamedEntity{
		
	private Integer stockDisponible;
	@Enumerated(EnumType.STRING)
	private UnidadMedidaInsumo unidadMedida;
	@Range(min=0)
	private Float costoStockInmovil;
	@Range(min=0)
	private Integer stockReposicion;
	@Range(min=0)
	private Integer lotePedidoOptimo;
	@Range(min=0)
	private Float costoTransaccionPedido;
	@Range(min=0)
	private Integer stockMaximo;
	@Enumerated(EnumType.STRING)
	private EstrategiaGestion estrategiaGestion;
	@OneToMany(mappedBy = "insumo")
	private List<UnidadMedidaInsumoCompra> unidades;
	
	public Insumo() {
		this.unidades = new ArrayList<UnidadMedidaInsumoCompra>();
	}
	
	public Integer getStockDisponible() {
		return stockDisponible;
	}
	public void setStockDisponible(Integer stockDisponible) {
		this.stockDisponible = stockDisponible;
	}	
	public UnidadMedidaInsumo getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadMedidaInsumo unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public List<UnidadMedidaInsumoCompra> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadMedidaInsumoCompra> unidades) {
		this.unidades = unidades;
	}

	public Float getCostoStockInmovil() {
		return costoStockInmovil;
	}

	public void setCostoStockInmovil(Float costoStockInmovil) {
		this.costoStockInmovil = costoStockInmovil;
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
		if (this.estrategiaGestion != null) {			
			return this.estrategiaGestion.getNombre();
		} else {
			return " - "; 
		}
	}
	
	public Integer getCantidadReposicion() {
		return this.estrategiaGestion.getEstrategia().getCantidadReposicion(this);
	}

	public boolean reponer() {
		return this.estrategiaGestion.getEstrategia().reponer(this);
	}
	
	public SemaforoStock getEstado(){
		return this.estrategiaGestion.getEstrategia().getEstado(this);
	}
	
	public void addUnidadCompra(UnidadMedidaInsumoCompra unidadCompra){
		this.unidades.add(unidadCompra);
		unidadCompra.setInsumo(this);
	}
	
	public UnidadMedidaInsumoCompra getUnidadPredeterminada(){
		for (UnidadMedidaInsumoCompra unidadCompra : this.unidades) {
			if (unidadCompra.isPredeterminada()) {
				return unidadCompra;
			} 
		}
		return null;
	}
	
}
