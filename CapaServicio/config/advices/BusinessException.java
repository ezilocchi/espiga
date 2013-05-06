package advices;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

public class BusinessException extends RuntimeException{

	private List<BusinessExceptionMsg> messages;	
	
	public BusinessException(BusinessExceptionMsg msg, Throwable cause) {
		super(cause);
		this.messages = new ArrayList<BusinessExceptionMsg>();
		this.messages.add(msg);
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
		this.messages = new ArrayList<BusinessExceptionMsg>();
	}
	
	public BusinessException(BusinessExceptionMsg msg) {		
		this.messages = new ArrayList<BusinessExceptionMsg>();
		this.messages.add(msg);
	}

	public BusinessException(String msm) {
		super(msm);
		BusinessExceptionMsg msg = new BusinessExceptionMsg(msm, msm, FacesMessage.SEVERITY_ERROR);
		this.messages = new ArrayList<BusinessExceptionMsg>();
		this.messages.add(msg);
	}

	public List<BusinessExceptionMsg> getMessages() {
		return messages;
	}

	public void addMessage(BusinessExceptionMsg message){
		this.messages.add(message);
	}		
}
