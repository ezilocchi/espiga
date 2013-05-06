package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

//	private static final long serialVersionUID = 1446169700230534445L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private BaseEntityStateEnum status;

	public BaseEntity() {
		super();
		status = BaseEntityStateEnum.ACTIVE;
	}		
		
	public BaseEntity(Long id) {
		this();
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BaseEntityStateEnum getStatus() {
		return status;
	}
	public void setStatus(BaseEntityStateEnum status) {
		this.status = status;
	}	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) object;
		if ((this.id != null && other.id != null) && this.id.equals(other.id)) {
			return true;
		}		
		return super.equals(object);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id="+ (id != null ? id : "NOT PERSISTED") + "]";
	}
}
