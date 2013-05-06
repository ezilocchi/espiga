package stock;

import integracion.ExceptionHandler;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import persistencia.ServiceParametricas;
import presentacion.NavigationRules;
import presentacion.SimpleAMBMB;
import produccion.Insumo;
import utils.JSFSelectItem;
import utils.JSFTable;

public class GestionNovedadMB extends SimpleAMBMB<NovedadStock>{
	
	private JSFSelectItem<Insumo> insumos;
	private JSFSelectItem<TipoNovedadStock> tipos;
	private JSFSelectItem<UnidadMedidaInsumoCompra> unidades;
	@EJB
	private ServiceParametricas serviceParametricas;
	@EJB
	private NovedadStockBusiness business;
	
	public GestionNovedadMB() {		
		super(NavigationRules.gestionarNovedades,"Listado Insumos");
		super.reportColumns.put("nombre", "Nombre");
		super.reportColumns.put("descripcion", "Descripcion");
	}
	
	public JSFSelectItem<Insumo> getInsumos() {
		return insumos;
	}
	public void setInsumos(JSFSelectItem<Insumo> insumos) {
		this.insumos = insumos;
	}
	public JSFSelectItem<UnidadMedidaInsumoCompra> getUnidades() {
		return unidades;
	}
	public void setUnidades(JSFSelectItem<UnidadMedidaInsumoCompra> unidades) {
		this.unidades = unidades;
	}
	public JSFSelectItem<TipoNovedadStock> getTipos() {
		return tipos;
	}
	public void setTipos(JSFSelectItem<TipoNovedadStock> tipos) {
		this.tipos = tipos;
	}
	
	@Override
	protected List<NovedadStock> buscar() {
		TipoNovedadStock tipo = this.tipos.getEntitySelected();
		Insumo insumo = this.insumos.getEntitySelected();
		if(tipo != null){
			this.tipos.cleanSelection();
			return this.serviceParametricas.listDetalle(new NovedadStock(), tipo, "tipo");
		}else if(insumo != null){
			this.insumos.cleanSelection();
			return this.serviceParametricas.listDetalle(new NovedadStock(), insumo, "insumo");
		}else {
			return super.buscar();
		}		
	}

	@Override
	public String init() {
		this.insumos = new JSFSelectItem<Insumo>(super.dao.list(new Insumo()),"nombre",this,"entity.insumo");
		this.tipos = new JSFSelectItem<TipoNovedadStock>(super.dao.list(new TipoNovedadStock()),"nombre",this,"entity.tipo");
		this.unidades = new JSFSelectItem<UnidadMedidaInsumoCompra>();
		this.entities = new JSFTable<NovedadStock>(this.dao.list(new NovedadStock()));
		return super.init();
	}

	@Override
	protected void initEntity() {
		this.insumos.cleanSelection();
		this.unidades = new JSFSelectItem<UnidadMedidaInsumoCompra>();
	}

	@Override
	protected void save() throws Exception {
		this.business.save(entity);
	}
	
	public void selectInsumo(ActionEvent event){
		Insumo insumo = this.insumos.getEntitySelected();		
		List<UnidadMedidaInsumoCompra> list = this.serviceParametricas.listDetalle(new UnidadMedidaInsumoCompra(), insumo, "insumo");
		System.out.println(list);
		this.unidades = new JSFSelectItem<UnidadMedidaInsumoCompra>(list, "nombre", this, "entity.unidadMedida");
	}
	
}
