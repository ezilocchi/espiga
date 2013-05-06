package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.lang.model.type.NullType;

public class ReflectionTool {

	/**
	 * Este método devuelve el valor de la propiedad 'property' del objeto
	 * 'source', pudiendo ser 'property': propiedad.propiedad.propiedad....
	 * 
	 * @param source: Objeto del cual se desea obtener el valor de la propiedad
	 * @param property: Propiedad del objeto source
	 * @return El valor que la propiedad que tenga el objeto ingresado
	 */
	public static Object getValue(Object source, final String property) {
		Object result = null;
		String[] subProperties = null;
		subProperties = propertyParser(property);
		for (int i = 0; i < subProperties.length - 1; i++) {
			source = internalGet(source, subProperties[i]);
		}
		result = internalGet(source, subProperties[subProperties.length - 1]);
		return result;
	}
	
	/**
	 * 
	 * @param source: Objeto del cual se desea asignar el valor de la propiedad
	 * @param property: Propiedad del objeto source
	 * @param value: valor a ser asignado en la propiedad 'property'
	 */
	public static void setValue(Object source, final String property, final Object value) {
		Object result = null;
		String[] subProperties = null;
		subProperties = propertyParser(property);
		for (int i = 0; i < subProperties.length - 1; i++) {
			source = internalGet(source, subProperties[i]);
		}
		internalSet(source, subProperties[subProperties.length - 1],value);
	}
	
	/**
	 * 
	 * @param source: Objeto del cual se desea asignar el valor de la propiedad
	 * @param property: Propiedad del objeto source
	 * @param value: valor a ser asignado en la propiedad 'property'
	 */
	public static void setValueNull(Object source, final String property, final Class clazz) {
		Object result = null;
		String[] subProperties = null;
		subProperties = propertyParser(property);
		for (int i = 0; i < subProperties.length - 1; i++) {
			source = internalGet(source, subProperties[i]);
		}
		internalSetNull(source, subProperties[subProperties.length - 1],clazz);
//		internalForcedSet(source, property);
	}

	/**
	 * getForced.
	 * 
	 * Este método devuelve el valor de la propiedad 'property' del objeto
	 * 'source' de manera forzada, pudiendo ser 'property': propiedad.propiedad.propiedad....
	 * 
	 * @param source
	 * @param property
	 * 
	 * @return Object	 	 
	 */
	public static Object getForced(Object source, final String property) {
		Object result = null;
		String[] subProperties = null;
		subProperties = propertyParser(property);
		for (int i = 0; i < subProperties.length - 1; i++) {
			source = internalForcedGet(source, subProperties[i]);
		}
		result = internalForcedGet(source,subProperties[subProperties.length - 1]);
		return result;
	}
	
	/**
	 * createMethod.
	 * 
	 * @param property: 
	 * @param methodPrefix: 
	 * 
	 * @return String: El nombre del metodo
	 */
	public static String createMethod(final String property, final String methodPrefix) {
		String result = null;
		result = methodPrefix + Character.toUpperCase(property.charAt(0)) + property.substring(1);
		return result;
	}
	
	private static Object internalGet(final Object source, final String property) {
		Object result = null;
		Method method = null;
		String methodName = null;
		
			try {
				if (source != null) {
					methodName = createMethod(property, "get");
					try {
						method = source.getClass().getMethod(methodName,(Class[]) null);
					} catch (NoSuchMethodException e) {
						methodName = createMethod(property, "is");
						method = source.getClass().getMethod(methodName,(Class[]) null);
					}
					result = method.invoke(source, new Object[] {});
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}
	
	private static Object internalSet(final Object source, final String property, final Object value) {
		Object result = null;
		Method method = null;
		String methodName = null;
		try {
			if (source != null) {
				methodName = createMethod(property, "set");
				method = source.getClass().getMethod(methodName,new Class[]{value.getClass()});				
				result = method.invoke(source, value);
			}
		} catch (Exception e) {
			throw new RuntimeException("La propiedad <" + property + "> es NULA!");
		}
		return result;
	}
	
	private static Object internalSetNull(final Object source, final String property, final Class clazz) {
		Object result = null;
		Method method = null;
		String methodName = null;
		try {
			if (source != null) {
				methodName = createMethod(property, "set");
				method = source.getClass().getMethod(methodName,new Class[]{clazz});
				Object[]args = new Object[1];
				args[0] = null;
				result = method.invoke(source, args);
			}
		} catch (Exception e) {
			throw new RuntimeException("La propiedad <" + property + "> es NULA!");
		}
		return result;
	}
	
	private static Object internalForcedGet(final Object source, final String property) {
		Object result = null;
		Field field = null;
		try {
			if (source != null) {
				field = source.getClass().getDeclaredField(property);
				field.setAccessible(true);
				result = field.get(source);
			}
		} catch (Exception e) {
			throw new RuntimeException("La propiedad <" + property + "> es NULA!");
		}
		return result;
	}
	public static Object internalForcedSet(final Object source, final String property) {
		Object result = null;
		Field field = null;
		try {
			if (source != null) {
				field = source.getClass().getDeclaredField(property);
				field.setAccessible(true);
				field.set(source, null);				
			}
		} catch (Exception e) {
			throw new RuntimeException("La propiedad <" + property + "> es NULA!");
		}
		return result;
	}
	
	private static String[] propertyParser(final String property) {
		String[] result = null;
		result = property.split("[.]");
		return result;
	}
	
	/**
	 * 
	 * @param target El objeto sobre el cual se quiere conocer la clase de una propiedad
	 * @param property La propiedad en cuestion
	 * @return La clase a la que pertenece la propiedad
	 */
	@SuppressWarnings("unchecked")	
	public static Class getPropertyClass(Object target, String property) {
		Object value = ReflectionTool.getValue(target, property);		
		return value.getClass();
	}
	
	public static String getFinalProperty(String property){
		int index = 0;
		if(property.indexOf('.')<0){
			return getTitle(property);
		}
		int aux = 0;
		while (aux>=0) {			
			aux = property.indexOf('.', aux+1);
			if(aux<0){
				return getTitle(property.substring(index+1));
			}
			index = property.indexOf('.', index+1);
		}
		return getTitle(property);
	}
	
	private static String getTitle(String string){
		char primera = string.toUpperCase().charAt(0);
		String segunda = string.substring(1);
		return primera+segunda;
	}
}
