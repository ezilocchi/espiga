package utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class JSFSelectItem<ENTITY> {

	private Map<String,ENTITY> content;
	private Map<String,String> items;
	private String itemSelected;
	private Object managedBean;
	private String labelProperty;
	private String targetProperty;
		
	public JSFSelectItem() {
		this.items = new LinkedHashMap<String, String>();
		this.content = new HashMap<String, ENTITY>();
		//this.items.put("", "-1"); Si se usa <rich:comboBox> no hace falta, ya esta resuelto		
	}
	
	public JSFSelectItem(Collection<ENTITY> collection, String labelProperty) {
		this();
		this.labelProperty = labelProperty;
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

	public JSFSelectItem(Collection<ENTITY> collection, String labelProperty, Object managedBean, String targetProperty) {
		this(collection,labelProperty);		
		this.managedBean = managedBean;		
		this.targetProperty = targetProperty;
	}

	public Map<String, String> getItems() {
		return items;
	}
	public void setItems(Map<String, String> items) {
		this.items = items;
	}
	public String getItemSelected() {
		if(this.managedBean != null){
			ENTITY entity =  (ENTITY) ReflectionTool.getValue(this.managedBean, this.targetProperty);
			Object object = ReflectionTool.getValue(entity, this.labelProperty);
			return object!=null?object.toString():null;	
		}else{
			return itemSelected;
		}
	}
	public void setItemSelected(String itemSelected) {
		if (this.managedBean != null) {
			try {
				ReflectionTool.setValue(managedBean, targetProperty, this.content.get(itemSelected));
			} catch (Exception e) {
				try {
					PropertyUtils.setProperty(managedBean, targetProperty, null);
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.itemSelected = itemSelected;
		} else {
			this.itemSelected = itemSelected;
		}
//		if (this.managedBean != null) {
//			try {
//				ReflectionTool.setValue(managedBean, targetProperty, this.content.get(itemSelected));
//			} catch (Exception e) {
//				ReflectionTool.setValue(managedBean, targetProperty, null);
//			}
//			this.itemSelected = itemSelected;
//		} else {
//			this.itemSelected = itemSelected;
//		}
	}
	public ENTITY getEntitySelected() {
		return this.content.get(itemSelected);
	}	
	public Collection<ENTITY> getContent(){
		return this.content.values();
	}
	public void cleanSelection(){
		this.itemSelected = "";
	}
	
	public void remove(String value){
		this.items.remove(value);
		this.content.remove(value);
	}
}
