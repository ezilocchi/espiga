package stock.estrategias;

import produccion.Insumo;
import stock.SemaforoStock;

public class Manual implements EstrategiaGestionStock{

	@Override
	public Integer getCantidadReposicion(Insumo insumo) {
		return insumo.getStockMaximo() - insumo.getStockDisponible();
	}

	@Override
	public boolean reponer(Insumo insumo) {
		if(insumo.getStockDisponible() == null || insumo.getStockReposicion() == null){
			return false;
		}
		return (insumo.getStockDisponible()-insumo.getStockReposicion())<0;		
	}

	@Override
	public SemaforoStock getEstado(Insumo insumo) {	
		if(insumo.getStockDisponible() == null || insumo.getStockReposicion() == null){
			return SemaforoStock.VERDE;
		}
		if(this.reponer(insumo)){
			return SemaforoStock.ROJO;
		}else if(insumo.getStockMaximo()<= insumo.getStockDisponible()){
			return SemaforoStock.VERDE;
		}else{
			return SemaforoStock.AMARILLO;			
		}
	}

	
}
