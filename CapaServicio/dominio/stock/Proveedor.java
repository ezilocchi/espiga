package stock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

import produccion.Insumo;
import base.Direccion;
import entity.BaseEntity;

@Entity
public class Proveedor extends BaseEntity{

	@Length(max=50)
	@NotEmpty
	private String razonSocial;
	@NotEmpty
//	@Pattern(regex="##-########-#")
	private String cuit;	
	@Column(unique=true)
	@Length(max=50)
	@NotEmpty
	@Email
	private String mail;
	@Length(max=50)
	private String telefono;
	@Embedded
	private Direccion direccion;
	@ManyToMany
	private List<Insumo> insumos;
	
	public Proveedor() {
		this.direccion = new Direccion();
		this.insumos = new ArrayList<Insumo>();
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCuit()
	{
		return cuit;
	}
	public void setCuit(String cuit)
	{
		this.cuit = cuit;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public List<Insumo> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}	
	
	public void addInsumo(Insumo insumo){
		this.insumos.add(insumo);		
	}
}
