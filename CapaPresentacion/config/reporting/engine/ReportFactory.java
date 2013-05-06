package reporting.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

public abstract class ReportFactory {
	
	protected Map<String,Object> parameters;
	
	public ReportFactory() {
		this.parameters = new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public abstract Report buildReport(List c, Map<String, String> data) throws JRException;
	 
	
	@SuppressWarnings("unchecked")
	public Report buildReport(List c, Map<String, String> data, Map<String,String> parameters) throws JRException{
		this.parameters.putAll(parameters);
		return this.buildReport(c, data);
	}
	
	public void addParam(String param, Object value){
		this.parameters.put(param, value);
	}
}
