package reporting.engine;

import net.sf.jasperreports.engine.JasperPrint;


public abstract class Report {
	
	protected JasperPrint jasperPrint;
	
	public Report(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public abstract void print();
}
