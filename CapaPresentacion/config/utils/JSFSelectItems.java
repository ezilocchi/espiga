package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JSFSelectItems<ENTITY> {

	private Map<String,ENTITY> content;
	private Map<String,String> items;
	private String[] itemsSelected;
	private List<ENTITY> entitiesSelected;	
		
	public JSFSelectItems() {
		this.items = new LinkedHashMap<String, String>();
		this.content = new HashMap<String, ENTITY>();
		this.entitiesSelected = new LinkedList<ENTITY>();
		//this.items.put("", "-1");
	}
	
	public JSFSelectItems(Collection<ENTITY> collection, String labelProperty) {
		this();
		if(collection==null){
			return;
		}
		for (ENTITY entity : collection) {
			String label = ReflectionTool.getValue(entity, labelProperty).toString();
			String key = label;
			this.items.put(label, key);
			this.content.put(key, entity);
		}
	}	

	public Map<String, String> getItems() {
		return items;
	}
	public void setItems(Map<String, String> items) {
		this.items = items;
	}
	public String[] getItemsSelected() {
		return itemsSelected;
	}
	public void setItemsSelected(String[] itemsSelected) {		
		this.itemsSelected = itemsSelected;
		this.entitiesSelected.clear();
		for (int i = 0; i < itemsSelected.length; i++) {
			this.entitiesSelected.add(this.content.get(itemsSelected[i]));
		}
	}
	public List<ENTITY> getEntitiesSelected() {
		return entitiesSelected;
	}
}
