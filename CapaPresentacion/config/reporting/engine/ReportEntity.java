package reporting.engine;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportEntity extends Report{

	public ReportEntity(JasperPrint jasperPrint) {
		super(jasperPrint);
	}

	@Override
	public void print() {		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		ServletOutputStream outputStream = null;		
		try {
			outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(super.jasperPrint, outputStream);			
			response.setContentType("application/pdf");
			response.setContentLength(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		 
		
		try {		
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FacesContext.getCurrentInstance().responseComplete();		
	}
}
