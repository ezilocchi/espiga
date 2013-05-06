package persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import utils.ReflectionTool;
import entity.BaseEntity;
import entity.Filtrable;

public class CriteriaHelper {

	public static Criteria createCriteria(Session session , Object entity, String... properties){
		Class criteriaClass = null;
		if(entity instanceof Filtrable){
			criteriaClass = entity.getClass().getSuperclass();
		}else{
			criteriaClass = entity.getClass();
		}
		Criteria criteria = session.createCriteria(criteriaClass);
		List<Field> fields = new ArrayList<Field>();
		Class clazz = criteriaClass;
		do{
			for (Field field : clazz.getDeclaredFields()) {
				fields.add(field);
			}
			clazz = clazz.getSuperclass();
		}while(!clazz.equals(BaseEntity.class));		 
		
		for (Field field : fields) {
			Class classField = field.getType();
			int key = classType(classField);
			Object value = ReflectionTool.getValue(entity, field.getName());
							
			if(key<4 ){
				Criterion criterion = null;
				switch (key) {
				case 2:
					if(value != null){						
						criterion = Restrictions.ilike(field.getName(), value.toString(), MatchMode.START);
						criteria.add(criterion);
					}
					break;
				case 3:					
					if(value != null){
						criterion = Restrictions.eq(field.getName(), value);
						criteria.add(criterion);
					}
					break;
				case 0:
					if(entity instanceof Filtrable){
						String desde = field.getName()+"Desde";
						String hasta = field.getName()+"Hasta";
						Date fechaDesde = null;
						Date fechaHasta = null;
						try {
							fechaDesde = (Date) ReflectionTool.getValue(entity, desde);
							fechaHasta = (Date) ReflectionTool.getValue(entity, hasta);							
							if(fechaDesde!=null){
								criterion = Restrictions.ge(field.getName(), fechaDesde);
								criteria.add(criterion);
							}
							if(fechaHasta!=null){
								criterion = Restrictions.le(field.getName(), fechaHasta);
								criteria.add(criterion);
							}
						} catch (Exception e) {}
					}
					else{
						if(value != null){
							criterion = Restrictions.eq(field.getName(), value);
						}
					}
					break;
				case 1:
					if(value != null){						
						criterion = Restrictions.eq(field.getName(), value);
						criteria.add(criterion);
					}
					break;
				}				
			}
					
		}
		for (int i = 0; i < properties.length; i++) {	    	
			criteria.setFetchMode(properties[i], FetchMode.JOIN);
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria;
	}
	
	public static int classType(Class clazz){	
		if(clazz.equals(String.class)){
			return 2;
		}else if(clazz.equals(Integer.class) || 
				clazz.equals(Double.class) || 
				clazz.equals(Float.class) || 
				clazz.equals(Long.class) || 
				clazz.equals(Boolean.class) || 
				clazz.equals(Byte.class) || 
				clazz.equals(Character.class) ){
			return 3;
		} else if(clazz.equals(Date.class)){
			return 0;
		} else if(clazz.isEnum()){
			return 1;
		} else{
			return 4;
		}
	}
	
	public static DetachedCriteria createDetachedCriteria(Object entity, String... properties){
		Class criteriaClass = null;
		if(entity instanceof Filtrable){
			criteriaClass = entity.getClass().getSuperclass();
		}else{
			criteriaClass = entity.getClass();
		}
		DetachedCriteria criteria =	DetachedCriteria.forClass(criteriaClass);
		//Criteria criteria = session.createCriteria(criteriaClass);
		List<Field> fields = new ArrayList<Field>();
		Class clazz = criteriaClass;
		do{
			for (Field field : clazz.getDeclaredFields()) {
				fields.add(field);
			}
			clazz = clazz.getSuperclass();
		}while(!clazz.equals(BaseEntity.class));		 
		
		for (Field field : fields) {
			Class classField = field.getType();
			int key = classType(classField);
			Object value = ReflectionTool.getValue(entity, field.getName());
							
			if(key<3 ){
				Criterion criterion = null;
				switch (key) {
				case 2:
					if(value != null){						
						criterion = Restrictions.ilike(field.getName(), value.toString(), MatchMode.START);
						criteria.add(criterion);
					}
					break;
				case 3:					
					if(value != null){
						criterion = Restrictions.eq(field.getName(), value);
						criteria.add(criterion);
					}
					break;
				case 0:
					if(entity instanceof Filtrable){
						String desde = field.getName()+"Desde";
						String hasta = field.getName()+"Hasta";
						Date fechaDesde = null;
						Date fechaHasta = null;
						try {
							fechaDesde = (Date) ReflectionTool.getValue(entity, desde);
							fechaHasta = (Date) ReflectionTool.getValue(entity, hasta);							
							if(fechaDesde!=null){
								criterion = Restrictions.ge(field.getName(), fechaDesde);
								criteria.add(criterion);
							}
							if(fechaHasta!=null){
								criterion = Restrictions.le(field.getName(), fechaHasta);
								criteria.add(criterion);
							}
						} catch (Exception e) {}
					}
					else{
						if(value != null){
							criterion = Restrictions.eq(field.getName(), value);
						}
					}
					break;
				case 1:
					if(value != null){						
						criterion = Restrictions.eq(field.getName(), value);
						criteria.add(criterion);
					}
					break;
				}				
			}
					
		}
		for (int i = 0; i < properties.length; i++) {	    	
			criteria.setFetchMode(properties[i], FetchMode.JOIN);
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

}
