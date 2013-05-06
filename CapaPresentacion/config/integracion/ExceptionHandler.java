package integracion;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import advices.BusinessException;
import advices.BusinessExceptionMsg;

public class ExceptionHandler {

	private static ExceptionHandler instance;

	private ExceptionHandler() {

	}

	public synchronized static ExceptionHandler getInstance() {
		if (ExceptionHandler.instance == null) {
			ExceptionHandler.instance = new ExceptionHandler();
		}
		return instance;
	}

	public void handleException(Exception e){		
		FacesContext context = FacesContext.getCurrentInstance();		
		if((e.getCause()!=null && e.getCause().getClass().equals(BusinessException.class)) || e.getClass().equals((BusinessException.class))){
			BusinessException be = (BusinessException) e.getCause();
			
			for (BusinessExceptionMsg item : be.getMessages()) {
				FacesMessage msg = new FacesMessage(item.getSeverity(),item.getSummary(),item.getDetail());				
				String clientId = null;

				if(item.getProperty()!=null){
					UIComponent component = context.getViewRoot();
					String compId = item.getBeanClass().getSimpleName()+"_"+item.getProperty();
					UIComponent target = this.findComponent(component, compId);
					clientId = target.getClientId(context);
				}								
				context.addMessage(clientId, msg);
			}
		}else{
			//TODO excepcion desconocida TRATAR
		}
		//TODO ver responseComplete y renderResponse
		//context.responseComplete();
	}

	//TODO optimizar el algoritmo llamando findComponent solo en aquellos componentes que sean uiform
	private UIComponent findComponent(UIComponent c, String id) {
		if (id.equals(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getChildren().iterator();
		while (kids.hasNext()) {
			UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}
}
