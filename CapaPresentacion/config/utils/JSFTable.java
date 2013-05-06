package utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

public class JSFTable <ENTITY>{

	private DataModel model;
	private List<ENTITY> list;
	
	public JSFTable(List<ENTITY> list) {		
		this.list = list;
		this.model = new ListDataModel(list);
	}
	
	public JSFTable() {
		this.list = new ArrayList<ENTITY>();
		this.model = new ListDataModel(list);
	}

	public void addRow(ENTITY entity){
		if(this.list == null){
			this.list = new ArrayList<ENTITY>();
		}
		this.list.add(entity);
	}
	public void removeRow(ENTITY entity){
		this.list.remove(entity);
	}

	public DataModel getModel() {
		return model;
	}
	public void setModel(DataModel model) {
		this.model = model;
	}
	
	public List<ENTITY> getEntities(){
		return this.list;
	}
	
	public boolean contains(ENTITY entity){
		return this.list.contains(entity);
	}
	
	public ENTITY getSelectedRow(){
		return (ENTITY) this.model.getRowData();
	}
}
