package monitoreo;

import java.util.ArrayList;
import java.util.List;

public class Mensaje {

	private String descripcion;
	private String link;
	private List<String> detalle;
	
	public Mensaje() {
		this.detalle = new ArrayList<String>();		
	}	
	public Mensaje(String desrcipcion, String link) {
		this();
		this.descripcion = desrcipcion;
		this.link = link;
	}
	public Mensaje(String desrcipcion, String link, List<String> detalle) {	
		this.descripcion = desrcipcion;
		this.link = link;
		this.detalle = detalle;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<String> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<String> detalle) {
		this.detalle = detalle;
	}
	
	public void addDetalle(String detalle){
		this.detalle.add(detalle);
	}
}
