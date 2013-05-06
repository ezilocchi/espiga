package base;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

import entity.BaseEntity;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona extends BaseEntity{

	@Length(max=50)
	@NotEmpty
	private String apellido;
	@Length(max=50)
	@NotEmpty
	private String nombres;
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipoDocumento;
	private Long nroDocumento;
	@Embedded
	private Direccion direccion;
	@Length(max=50)
	private String telefono;
	@Length(max=50)
	private String celular;
	@Length(max=50)
	@Email
	private String mail;	
		
	public Persona() {
		this.direccion = new Direccion();
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Long getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getDir(){
		return this.direccion.toString();
	}
}
