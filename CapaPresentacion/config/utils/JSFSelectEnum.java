package utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import venta.estados.pedido.EstadoPedido;

//TODO Constructor sin parametros mediante refletion
public class JSFSelectEnum <T extends Enum<T>>{
	
	private String itemSelected;
	private Object managedBean;
	private String targetProperty;
	private Map<String, T> items;
	
	/**
	 * 
	 * @param valores: Un vector con todas los valores de la enumeracion T (MiEnum.values())
	 */
	public JSFSelectEnum(T valores[]) {
		if(valores == null || valores.length == 0){
			return;
		}		
		this.items = new HashMap<String, T>();
		for (int i = 0; i < valores.length; i++) {
			this.items.put(valores[i].toString(), valores[i]);			
		}		
	}
	
	/**
	 * 
	 * @param valores: Un vector con todas los valores de la enumeracion T (MiEnum.values())
	 * @param managedBean: 
	 * @param targetProperty
	 */
	public JSFSelectEnum(T valores[], Object managedBean, String targetProperty) {
		this(valores);
		this.managedBean = managedBean;
		this.targetProperty = targetProperty;
	}
	
	public Map<String, T> getItems() {
		return items;
	}
	public void setItems(Map<String, T> items) {
		this.items = items;
	}
	
	public String getItemSelected() {
		if(this.managedBean != null){
			T entity =  (T)ReflectionTool.getValue(this.managedBean, this.targetProperty);
			return entity!=null?entity.toString():null;	
		}else{
			return itemSelected;
		}		
	}
	public void setItemSelected(String value) {
		if (this.managedBean != null) {
			try {
				ReflectionTool.setValue(managedBean, targetProperty, this.getSelectedEnum(value));
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
			this.itemSelected = value;
		} else {
			this.itemSelected = value;
		}
		
	}

	public T getSelectedEnum(){		
		return this.getSelectedEnum(itemSelected);
	}
	private T getSelectedEnum(String value){
		return this.items.get(value);		
	}
	
	public void cleanSelection(){
		this.itemSelected = null;
	}
}
