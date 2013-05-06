package reporting;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import produccion.Insumo;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.ReflectiveReportBuilder;

public class TestReport {

	public static void buildReport(Collection<Insumo> c, OutputStream outputStream) throws ColumnBuilderException, ClassNotFoundException, JRException{
//		FastReportBuilder rb = new FastReportBuilder();
				
//		rb.setTemplateFile("reporting/engine/TemplateSimpleABM.jrxml");
//        DynamicReport dr = rb.addColumn("Nombre", "nombre", String.class.getName(),30)
//                        .addColumn("Descripción", "descripcion", String.class.getName(),30)
//                        .setPrintBackgroundOnOddRows(true)                      
//                        .setUseFullPageWidth(true)                        
//                        .build();       
        ReflectiveReportBuilder rb = new ReflectiveReportBuilder(c, new String[]{"nombre","descripcion","stockDisponible"});
		rb.setTemplateFile("reporting/engine/TemplateSimpleABM.jrxml");
        DynamicReport dr = rb.build();
        JRDataSource ds = new JRBeanCollectionDataSource(c);   
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);        
        JasperExportManager.exportReportToPdfStream(jp, outputStream);
      //JasperExportManager.exportReportToPdfFile(jp, "c:/test.pdf"); Escribe un archivo en disco
	}
	public static void main(String[] args) throws JRException {
		File file = new File("config/reporting/template/TemplateSimpleABM.jrxml");
		System.out.println(file.exists());
		System.out.println(file.getAbsolutePath());
		List<Insumo> insumos = new ArrayList<Insumo>();
		Insumo insumo = new Insumo();
		insumo.setId(888l);
		insumo.setNombre("Mi Insumo");
		insumo.setDescripcion("nuevo insumo");
		insumo.setStockDisponible(432);
		insumos.add(insumo);
		
		
		ReflectiveReportBuilder rb = new ReflectiveReportBuilder(insumos, new String[]{"nombre","descripcion","stockDisponible"});
        rb.setTemplateFile("config/reporting/template/TemplateSimpleABM.jrxml");
        
        DynamicReport dr = rb.build();           
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), new HashMap());
        //JasperExportManager.exportReportToPdfFile(jp, "c:/test.pdf"); Escribe un archivo en disco
        JasperExportManager.exportReportToPdfFile(jp, "C:/temp.pdf");
	}
}
