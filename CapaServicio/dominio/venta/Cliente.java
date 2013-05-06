package venta;

import static javax.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

import base.Barrio;
import base.CondicionIVA;
import base.Direccion;
import base.Persona;
import base.Recurso;
import entity.BaseEntity;

@Entity
public class Cliente extends BaseEntity{

	@Length(max=50)
	@NotEmpty
	private String razonSocial;	
	@NotEmpty	
	private String cuit;	
	@Length(max=50)
	@Email
	private String mail;
	@NotNull
	@Enumerated(EnumType.STRING)	
	private CondicionIVA condicionIVA;
	@Length(max=50)
	private String telefono;
	@Length(max=50)
	private String fax;
	@Embedded 
	private Direccion direccion;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	
	//TODO Trabajar relaciones ManyToMany de forma unidireccional
	@ManyToMany(fetch = LAZY)
	private Set<Recurso> contactos;
		
	public Cliente() {
		this.direccion = new Direccion();
		this.direccion.setBarrio(new Barrio());
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public CondicionIVA getCondicionIVA() {
		return condicionIVA;
	}
	public void setCondicionIVA(CondicionIVA condicionIVA) {
		this.condicionIVA = condicionIVA;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public Set<Recurso> getContactos() {
		return contactos;
	}
	public void setContactos(Set<Recurso> contactos) {
		this.contactos = contactos;
	}

	public void addContacto(Recurso contacto){
		if(this.contactos == null){
			this.contactos = new HashSet<Recurso>();
		}
		this.contactos.add(contacto);
	}
	public void removeContacto(Persona contacto){
		if(this.contactos != null){
			this.contactos.remove(contacto);
		}		
	}
	
	public enum TipoCliente{
		MINORISTA("Minorista"), 
		SUCURSAL("Sucursal"), 
		FRANQUICIADO("Franquiciado");
		private String name;	

		private TipoCliente(String name) {
			this.name = name;
		}
		public String getName()		{
			return name;
		}
		@Override
		public String toString() {			
			return name;
		}		
	}
	
	public String getDir(){
		return this.direccion!=null?this.direccion.toString():"";
	}
}
