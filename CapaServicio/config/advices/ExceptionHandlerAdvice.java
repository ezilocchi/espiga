package advices;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class ExceptionHandlerAdvice {

	@AroundInvoke
	public Object logging(InvocationContext invocationContext){
		try {
			return invocationContext.proceed();
		} catch (Exception e) {
			//TODO hacer logging de la excepcion en lugar del print trace
			e.printStackTrace();
			System.out.println("*************************************************");
			BusinessException be = ExceptionHandler.getInstance().handleException(e);			
			throw be;
		}
	}

}
