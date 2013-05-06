package base;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import persistencia.ServiceMaestroDetalle;
import utils.JSFTable;

public class BuscadorBarrioMB {
	
	@EJB(beanName="ServiceMaestroDetalle")
	private ServiceMaestroDetalle service;
	
	private Provincia provincia;
	private JSFTable<Provincia> provincias;
	private Localidad localidad;
	private JSFTable<Localidad> localidades;
	private JSFTable<Barrio> barrios;
	
	private boolean render;
	
	public BuscadorBarrioMB() {
		super();
	}

	public JSFTable<Provincia> getProvincias() {
		return provincias;
	}
	public void setProvincias(JSFTable<Provincia> provincias) {
		this.provincias = provincias;
	}
	public JSFTable<Localidad> getLocalidades() {
		return localidades;
	}
	public void setLocalidades(JSFTable<Localidad> localidades) {
		this.localidades = localidades;
	}
	public JSFTable<Barrio> getBarrios() {
		return barrios;
	}
	public void setBarrios(JSFTable<Barrio> barrios) {
		this.barrios = barrios;
	}	
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public boolean isRender() {
		return render;
	}
	public void setRender(boolean render) {
		this.render = render;
	}
	//***** FIN GETTER & SETTERS *****

	public void selectProvincia(ActionEvent event){
		this.provincia = this.provincias.getSelectedRow();
		List<Localidad> list = this.service.listDetalle(new Localidad(), this.provincia, "provincia");
		this.localidades = new JSFTable<Localidad>(list);
		this.barrios = new JSFTable<Barrio>();
	}
	public void selectLocalidad(ActionEvent event){
		this.localidad = this.localidades.getSelectedRow();
		List<Barrio> list = this.service.listDetalle(new Barrio(), this.localidad, "localidad");
		this.barrios = new JSFTable<Barrio>(list);
	}	
	
	public void hide(ActionEvent event){
		this.render = false;
	}
	
	public void show(ActionEvent event){
		List<Provincia> list = this.service.list(new Provincia());
		this.provincias = new JSFTable<Provincia>(list);
		this.render = true;
	}	
}
