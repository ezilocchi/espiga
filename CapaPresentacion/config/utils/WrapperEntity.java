package utils;

import entity.BaseEntity;

public class WrapperEntity<ENTITY> {

	private ENTITY entity;
	private boolean selected;
	public WrapperEntity(ENTITY entity) {		
		this.entity = entity;
		this.selected = false;
	}
	public ENTITY getEntity() {
		return entity;
	}
	public void setEntity(ENTITY entity) {
		this.entity = entity;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof WrapperEntity)) {
			return false;
		}
		WrapperEntity other = (WrapperEntity) object;
		
		return this.entity.equals(other.getEntity());		
	}
	@Override
	public int hashCode() {	
		return this.entity.hashCode();
	}
}
