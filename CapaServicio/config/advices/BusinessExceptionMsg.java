package advices;

import javax.faces.application.FacesMessage.Severity;

public class BusinessExceptionMsg {

	private String summary;
	private String detail;
	private String property;
	private Severity severity;	
	private Class beanClass;
	
	public BusinessExceptionMsg() {
		super();
	}	

	public BusinessExceptionMsg(String summary, String detail, String property,
			Severity severity, Class beanClass) {
		super();
		this.summary = summary;
		this.detail = detail;
		this.property = property;
		this.severity = severity;
		this.beanClass = beanClass;
	}
	public BusinessExceptionMsg(String summary, String detail, Severity severity) {
		super();
		this.summary = summary;
		this.detail = detail;
		this.severity = severity;
	}

	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	public Class getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}
	
}
