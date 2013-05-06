package reporting.engine;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reporting.ReportHelper;
import utils.ReflectionTool;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DJBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class MaestroDetalleReportFactory extends ReportFactory{	

	@SuppressWarnings("unchecked")	
	public Report buildReport(Collection c, String field, String[] subReportFields, String subReportCollection) throws JRException {
		FastReportBuilder rb = new FastReportBuilder();
		Iterator it = c.iterator();
		Object example = null;
		if (it.hasNext()) {	
			example = it.next();
		} else {
			return null;
		}
		Integer pageWidth = ReportHelper.getInstance().getPageWidth();
		
		rb.setTemplateFile("reporting/template/TemplateSimpleABM.jrxml");
		AbstractColumn column = null;
		try {
			column = ColumnBuilder.getNew()  
				.setColumnProperty(field, ReflectionTool.getPropertyClass(example, field).getName())
				.setTitle(ReflectionTool.getFinalProperty(field))
				.setWidth(50)
				.setStyle(new Style("ColumnContentStyle"))
				.setHeaderStyle(new Style("ColumnHeaderStyle"))
				.build();
		} catch (ColumnBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.addColumn(column);
		rb.addField("detalle", List.class.getName());
		
		FastReportBuilder rbs = new FastReportBuilder();
		int columnWidth = pageWidth/subReportFields.length;
		for (int i = 0; i < subReportFields.length; i++) {
			try {
				AbstractColumn columnSubReport = ColumnBuilder.getNew()  
//					.setColumnProperty(subReportFields[i], ReflectionTool.getPropertyClass(example, subReportFields[i]).getName())
					.setColumnProperty(subReportFields[i], Float.class.getName())
//					.setTitle(ReflectionTool.getFinalProperty(subReportFields[i]))
					.setTitle("Cantidad")
					.setWidth(columnWidth)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
				rbs.addColumn(columnSubReport);				
			} catch (ColumnBuilderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		try {
			rb.addConcatenatedReport(rbs.build(), new ClassicLayoutManager(), "detalle", DJConstants.DATA_SOURCE_ORIGIN_FIELD, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
		} catch (DJBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		GroupBuilder gb1 = new GroupBuilder();
//
//		define the criteria column to group by (columnState)
//		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) rb.getColumn(0))				
//				.setGroupLayout(GroupLayout.VALUE_IN_HEADER) // tells the group how to be shown, there are manyposibilities, see the GroupLayout for more.
//				.setFooterVariablesHeight(new Integer(20))
//				.setFooterHeight(new Integer(50),true)
//				.setHeaderVariablesHeight(new Integer(35))
//				.build();
//		rb.addGroup(g1);
		
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		oddRowStyle.setBackgroundColor(ReportHelper.getInstance().getOddRowColor());	
		rb.setOddRowBackgroundStyle(oddRowStyle);
		rb.setPrintBackgroundOnOddRows(true);
		rb.setUseFullPageWidth(true);
		rb.setAllowDetailSplit(true);		
			
        DynamicReport dr = rb.build(); 
        JRDataSource ds = new JRBeanCollectionDataSource(c);
        InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/logo.png");
        parameters.put("LOGO_IMAGE", inputStream);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds, parameters);
        Report report = new ReportEntity(jp);
		return report;
	}

	@Override
	public Report buildReport(List c, Map<String, String> fields) throws JRException {
		// TODO Auto-generated method stub
		return null;
	}
}
