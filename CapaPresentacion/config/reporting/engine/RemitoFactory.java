package reporting.engine;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reporting.ReportHelper;
import venta.Pedido;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DJBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import distribucion.DiagramacionDistribucionDetalle;

public class RemitoFactory extends ReportFactory{
	
	public RemitoFactory(){
		super();
	}

	@Override
	public Report buildReport(List c, Map<String, String> data)throws JRException {
		if (c.size()==0) {			
			return null;
		} 
		
		Integer pageWidth = ReportHelper.getInstance().getPageWidth();
		Set<String> fields = data.keySet();
		
		int columnWidth = pageWidth/fields.size();		
		FastReportBuilder maestroBuilder = new FastReportBuilder();
		
//		Object example = null;
		boolean flag = false;
		for (Object object : c) {
			DiagramacionDistribucionDetalle detalle = (DiagramacionDistribucionDetalle) object;			
			for (Pedido pedido : detalle.getPedidos()) {
				
				FastReportBuilder rb = new FastReportBuilder();
				rb.setTemplateFile("reporting/template/Remito.jrxml");
				Style oddRowStyle = new Style();
				oddRowStyle.setBorder(Border.NO_BORDER);
				oddRowStyle.setTransparency(Transparency.OPAQUE);
				oddRowStyle.setBackgroundColor(ReportHelper.getInstance().getOddRowColor());	
				rb.setOddRowBackgroundStyle(oddRowStyle);
				rb.setPrintBackgroundOnOddRows(true);				
				rb.addAutoText("Cliente: "+pedido.getCliente().getRazonSocial(),AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);				
				rb.addAutoText("Cuit: "+pedido.getCliente().getCuit(),AutoText.POSITION_HEADER,AutoText.ALIGMENT_RIGHT);
				rb.addAutoText("Dirección: "+pedido.getCliente().getDireccion(),AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);
				rb.addAutoText("TOTAL: $ "+pedido.getTotal()+"    ",AutoText.POSITION_FOOTER,AutoText.ALIGMENT_RIGHT,560,new Style("ColumnHeaderStyle"));
				
				try {
					AbstractColumn column = ColumnBuilder.getNew()					
					.setColumnProperty("producto.tipo.nombre", String.class.getName())
					.setTitle("Tipo")
					.setWidth(120)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
					rb.addColumn(column);
					
					column = ColumnBuilder.getNew()					
					.setColumnProperty("producto.nombre", String.class.getName())
					.setTitle("Producto")
					.setWidth(200)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
					rb.addColumn(column);
					
					column = ColumnBuilder.getNew()					
					.setColumnProperty("cantidad", Integer.class.getName())
					.setTitle("Cantidad")
					.setWidth(60)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
					rb.addColumn(column);
					
					column = ColumnBuilder.getNew()					
					.setColumnProperty("producto.unidadMedida.nombre", String.class.getName())
					.setTitle("Unidad")
					.setWidth(60)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
					rb.addColumn(column);
					
					column = ColumnBuilder.getNew()					
					.setColumnProperty("precioVentaUnitario", Float.class.getName())
					.setTitle("Precio")
					.setWidth(60)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
					rb.addColumn(column);
					
					column = ColumnBuilder.getNew()					
					.setColumnProperty("subTotal", Float.class.getName())
					.setTitle("Sub Total")
					.setWidth(60)
					.setStyle(new Style("ColumnContentStyle"))
					.setHeaderStyle(new Style("ColumnHeaderStyle"))
					.build();
					rb.addColumn(column);
				} catch (ColumnBuilderException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DynamicReport subReport = rb.build();				
				try {
					String rep = pedido.getId().toString();
					maestroBuilder.addConcatenatedReport(subReport, new ClassicLayoutManager(), rep, DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION,flag);
					this.parameters.put(rep, pedido.getDetalle());
					flag = true;
				} catch (DJBuilderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
			
        DynamicReport dr = maestroBuilder.build(); 
        JRDataSource ds = new JRBeanCollectionDataSource(c);
        InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/logo.png");        
        parameters.put("LOGO_IMAGE", inputStream);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds, parameters);
//        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), parameters);
        Report report = new ReportEntity(jp);
		return report;
	}

}
