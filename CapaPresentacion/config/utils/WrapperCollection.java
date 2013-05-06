package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WrapperCollection<ENTITY,DETAIL> extends WrapperEntity<ENTITY>{

	private List<WrapperEntity<DETAIL>> detail;	
	
	public WrapperCollection(ENTITY entity) {
		super(entity);
		this.detail = new ArrayList<WrapperEntity<DETAIL>>();
	}

	public WrapperCollection(ENTITY entity, Collection<DETAIL> detail) {
		super(entity);
		this.detail = new ArrayList<WrapperEntity<DETAIL>>();
		for (DETAIL item : detail) {
			this.detail.add(new WrapperEntity<DETAIL>(item));
		}		
	}
	
	public void addDetail(DETAIL detail){
		this.detail.add(new WrapperEntity<DETAIL>(detail));
	}
	public List<WrapperEntity<DETAIL>> getDetail() {
		return detail;
	}	
	
	public void setSelectedAll(boolean selected) {
		for (WrapperEntity<DETAIL> item : this.detail) {
			item.setSelected(selected);
		}
		super.setSelected(selected);
	}	
}
