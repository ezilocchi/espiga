package reporting.engine;


import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reporting.ReportHelper;
import utils.ReflectionTool;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;


public class EntityReportFactory extends ReportFactory{	

	@SuppressWarnings("unchecked")
	@Override
	public Report buildReport(List c, Map<String, String> data) throws JRException {
		FastReportBuilder rb = new FastReportBuilder();
		Iterator it = c.iterator();
		Object example = null;
		if (it.hasNext()) {			
			example = it.next();
		} else {
			return null;
		}
		Integer pageWidth = ReportHelper.getInstance().getPageWidth();
		Set<String> fields = data.keySet();
		
		int columnWidth = pageWidth/fields.size();
		rb.setTemplateFile("reporting/template/TemplateSimpleABM.jrxml");
		for (String field : fields) {
			try {
				AbstractColumn column = ColumnBuilder.getNew()  
					.setColumnProperty(field, ReflectionTool.getPropertyClass(example, field).getName())
					.setTitle(data.get(field))
					.setWidth(columnWidth)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
				rb.addColumn(column);
				
			} catch (ColumnBuilderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		oddRowStyle.setBackgroundColor(ReportHelper.getInstance().getOddRowColor());	
		rb.setOddRowBackgroundStyle(oddRowStyle);
		rb.setPrintBackgroundOnOddRows(true);
		rb.setUseFullPageWidth(true);
			
        DynamicReport dr = rb.build(); 
        JRDataSource ds = new JRBeanCollectionDataSource(c);
        InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/logo.png");        
        parameters.put("LOGO_IMAGE", inputStream);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds, parameters);
        Report report = new ReportEntity(jp);
		return report;
	}
}
