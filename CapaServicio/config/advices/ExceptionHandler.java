package advices;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;

//TODO implementar el resour boundle para internacionalizacion en los metodos que insertan literales
public class ExceptionHandler {

	private static ExceptionHandler instance;
	
	private Map<Class<? extends Exception>, Integer> options;
	
	private ExceptionHandler(){		
		this.options = new HashMap<Class<? extends Exception>, Integer>();
		this.options.put(BusinessException.class, 0);
		this.options.put(InvalidStateException.class, 1);
		this.options.put(PersistenceException.class, 2);
		this.options.put(ConstraintViolationException.class, 3);
		
	}

	public synchronized static ExceptionHandler getInstance() {
		if(ExceptionHandler.instance == null){
			ExceptionHandler.instance = new ExceptionHandler();
		}
		return instance;
	}
	
	public BusinessException handleException(Exception e){		
		Integer op = this.options.get(e.getClass());
		if(op == null){
			op = -1;
		}		
		
		switch (op) {
		case 0:
			return (BusinessException) e;
			
		case 1:
			return this.invalidStateException((InvalidStateException) e);

		case 2:
			return this.persistenceException((PersistenceException) e);
			
		case 3:			
			
		default:
			BusinessException be = new BusinessException(e);
			//TODO implementar message boundle
			be.addMessage(new BusinessExceptionMsg("Error desconocido","Error desconocido",FacesMessage.SEVERITY_FATAL));
			return be;
		}		
	}
	
	
	private BusinessException invalidStateException(InvalidStateException exception){
		BusinessException businessException = new BusinessException(exception);
		InvalidValue [] invalidValues = exception.getInvalidValues();
		for (int i = 0; i < invalidValues.length; i++) {
			String property = invalidValues[i].getPropertyName();			
			String msg = invalidValues[i].getMessage();			
			businessException.addMessage(new BusinessExceptionMsg("",msg,property,FacesMessage.SEVERITY_ERROR,invalidValues[i].getBeanClass()));
		}
		//TODO armar los mensajes en funcion de los valores no validos HIBERNATE VALIDATOR
		return businessException;
	}
	
	private BusinessException persistenceException(PersistenceException exception){
		//TODO trabajr los tipos de excepciones que pueden causar una PersistenceException		
		Integer op = this.options.get(exception.getCause().getClass());
		switch (op) {
		case 3:
			ConstraintViolationException e = (ConstraintViolationException) exception.getCause();
			if(e.getConstraintName() == null || e.getConstraintName().isEmpty()){
				String msg = e.getSQLException().getMessage();
				String[] words = msg.split("\\s+");
				String value = words[2];
				//TODO implementar message boundle
				return new BusinessException(new BusinessExceptionMsg("Ya existe: "+value+" ingresar uno diferente","Ya existe: "+value+" ingresar uno diferente",FacesMessage.SEVERITY_ERROR),e);
			}
			return new BusinessException(new BusinessExceptionMsg(e.getSQLException().getMessage(),e.getSQLException().getMessage(),FacesMessage.SEVERITY_ERROR),e);		
		default:
			//TODO implementar message boundle
			return new BusinessException(new BusinessExceptionMsg("Error desconocido","Error desconocido",FacesMessage.SEVERITY_FATAL));
		}				
	}	

}

