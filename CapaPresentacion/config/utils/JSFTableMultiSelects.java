package utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

public class JSFTableMultiSelects<ENTITY> {

	public DataModel model;
	public List<WrapperEntity<ENTITY>> content;
	
	public JSFTableMultiSelects() {
		this.content = new LinkedList<WrapperEntity<ENTITY>>();
		this.model = new ListDataModel(content);
	}
	public JSFTableMultiSelects(Collection<ENTITY> list) {
		this();
		if(list == null){
			return;
		}
		for (ENTITY entity : list) {
			this.content.add(new  WrapperEntity<ENTITY>(entity));
		}		
	}
	
	public DataModel getModel() {
		return model;
	}
	public void setModel(DataModel model) {
		this.model = model;
	}	
	public List<WrapperEntity<ENTITY>> getContent() {
		return content;
	}
	public void addEntity(ENTITY entity){
		this.content.add(new WrapperEntity<ENTITY>(entity));
	}
	public void addWapper(WrapperEntity<ENTITY> wrapper){
		this.content.add(wrapper);
	}
	
	public WrapperEntity<ENTITY> getSelectedEntity(){		
		return (WrapperEntity<ENTITY>) this.model.getRowData();
	}
	public List<ENTITY> getSelectedEntities(){
		List<ENTITY> list = new LinkedList<ENTITY>();
		for (WrapperEntity<ENTITY> wrapper : this.content) {
			if(wrapper.isSelected()){
				list.add(wrapper.getEntity());
			}
		}		
		return list;
	}
	
	public void selectAll(boolean op){
		for (WrapperEntity<ENTITY> item : this.content) {
			item.setSelected(op);
		}
	}
	
	public boolean removeEntity(ENTITY entity){
		return this.content.remove(new WrapperEntity<ENTITY>(entity));
	}
}
