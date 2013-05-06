package reporting.engine;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.context.FacesContext;

import produccion.Insumo;
import produccion.ProduccionProducto;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reporting.ReportHelper;
import utils.FechaUtils;
import venta.Producto;
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

public class ResultadoProduccionFactory extends ReportFactory{	

	@SuppressWarnings("unchecked")	
	public Report buildReport(Collection<ProduccionProducto> productos, Collection<Entry<Insumo, Float>> insumos) throws JRException {
		if (productos.size()==0) {			
			return null;
		}			
		FastReportBuilder maestroBuilder = new FastReportBuilder();	
		
		//Productos
		FastReportBuilder rbProductos = new FastReportBuilder();
		rbProductos.setTemplateFile("reporting/template/ResultadoProduccion.jrxml");
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		oddRowStyle.setBackgroundColor(ReportHelper.getInstance().getOddRowColor());	
		rbProductos.setOddRowBackgroundStyle(oddRowStyle);
		rbProductos.setPrintBackgroundOnOddRows(true);
	
		rbProductos.addAutoText("Productos",AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);
		
		boolean flag = false;		
		
		try {
			AbstractColumn column = ColumnBuilder.getNew()					
			.setColumnProperty("producto.tipo.nombre", String.class.getName())
			.setTitle("Tipo")
			.setWidth(200)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))
			.build();
			rbProductos.addColumn(column);
			
			column = ColumnBuilder.getNew()					
			.setColumnProperty("producto.nombre", String.class.getName())
			.setTitle("Nombre")
			.setWidth(200)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))
			.build();
			rbProductos.addColumn(column);
			
			column = ColumnBuilder.getNew()					
			.setColumnProperty("total", Float.class.getName())
			.setTitle("Total")
			.setWidth(80)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))
			.build();
			
			rbProductos.addColumn(column);				
			column = ColumnBuilder.getNew()					
			.setColumnProperty("producto.unidadMedida.nombre", String.class.getName())
			.setTitle("Unidad")
			.setWidth(80)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))			
			.build();
			rbProductos.addColumn(column);
		} catch (ColumnBuilderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DynamicReport subReport = rbProductos.build();			
		
		FastReportBuilder rbInsumos = new FastReportBuilder();
		rbInsumos.setTemplateFile("reporting/template/ResultadoProduccion.jrxml");
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		oddRowStyle.setBackgroundColor(ReportHelper.getInstance().getOddRowColor());	
		rbInsumos.setOddRowBackgroundStyle(oddRowStyle);
		rbInsumos.setPrintBackgroundOnOddRows(true);
		
		rbInsumos.addAutoText("Insumos",AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);
		
		try {
			AbstractColumn column = ColumnBuilder.getNew()					
			.setColumnProperty("key.nombre", String.class.getName())
			.setTitle("Insumo")
			.setWidth(200)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))
			.build();
			rbInsumos.addColumn(column);
			
			column = ColumnBuilder.getNew()					
			.setColumnProperty("value", Float.class.getName())
			.setTitle("Stock Necesario")
			.setWidth(140)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))
			.build();
			rbInsumos.addColumn(column);		
			
			column = ColumnBuilder.getNew()					
			.setColumnProperty("key.stockDisponible", Integer.class.getName())
			.setTitle("Stock Disponible")
			.setWidth(140)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))
			.build();
			rbInsumos.addColumn(column);
			
			column = ColumnBuilder.getNew()					
			.setColumnProperty("key.unidadMedida.nombre", String.class.getName())
			.setTitle("Unidad")
			.setWidth(80)
			.setStyle(new Style("ColumnContentStyle"))
			.setHeaderStyle(new Style("ColumnHeaderStyle"))
			.build();
			rbInsumos.addColumn(column);
							
		} catch (ColumnBuilderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DynamicReport subReport2 = rbInsumos.build();
			
		try {
			
			maestroBuilder.addConcatenatedReport(subReport, new ClassicLayoutManager(), "productos", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION,false);
			maestroBuilder.addConcatenatedReport(subReport2, new ClassicLayoutManager(), "insumos", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION,true);
			this.parameters.put("productos", productos);
			this.parameters.put("insumos", insumos);
			flag = true;
		} catch (DJBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
			
        DynamicReport dr = maestroBuilder.build(); 
        JRDataSource ds = new JRBeanCollectionDataSource(Arrays.asList(new Object(),new Object()));
//        InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/logo.png");        
//        parameters.put("LOGO_IMAGE", inputStream);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds, parameters);
//        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), parameters);
        Report report = new ReportEntity(jp);
		return report;
	}

	@Override
	public Report buildReport(List c, Map<String, String> fields) throws JRException {
		if (c.size()==0) {			
			return null;
		}			
		FastReportBuilder maestroBuilder = new FastReportBuilder();		
		boolean flag = false;		
		for (Object object : c) {
			DiagramacionDistribucionDetalle detalle = (DiagramacionDistribucionDetalle) object;
			FastReportBuilder rb = new FastReportBuilder();
			rb.setTemplateFile("reporting/template/Remito.jrxml");
			Style oddRowStyle = new Style();
			oddRowStyle.setBorder(Border.NO_BORDER);
			oddRowStyle.setTransparency(Transparency.OPAQUE);
			oddRowStyle.setBackgroundColor(ReportHelper.getInstance().getOddRowColor());	
			rb.setOddRowBackgroundStyle(oddRowStyle);
			rb.setPrintBackgroundOnOddRows(true);
			
			rb.addAutoText("Fecha: "+FechaUtils.getFechaPattern(detalle.getDiagramacion().getFechaEntrega(), "dd/MM/yyyy"),AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);
			rb.addAutoText("Chofer: "+detalle.getEmpleado().getNombres()+", "+detalle.getEmpleado().getApellido(),AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);				
//			rb.addAutoText("Zona: "+detalle.getZona()!=null?detalle.getZona().getNombre():" ",AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);
//				try {
//					rb.addImageBanner("reporting/template/logo.png", 30, 30, ImageBanner.ALIGN_RIGHT);
//				} catch (Exception e2) {
//					// TODO Auto-generated catch block
//					System.out.println(e2.getMessage());
//				}
			
			try {
				AbstractColumn column = ColumnBuilder.getNew()					
				.setColumnProperty("cliente.razonSocial", String.class.getName())
				.setTitle("Cliente")
				.setWidth(200)
				.setStyle(new Style("ColumnContentStyle"))
				.setHeaderStyle(new Style("ColumnHeaderStyle"))
				.build();
				rb.addColumn(column);
				
				column = ColumnBuilder.getNew()					
				.setColumnProperty("cliente.dir", String.class.getName())
				.setTitle("Dirección")
				.setWidth(200)
				.setStyle(new Style("ColumnContentStyle"))
				.setHeaderStyle(new Style("ColumnHeaderStyle"))
				.build();
				rb.addColumn(column);
				
				column = ColumnBuilder.getNew()					
				.setColumnProperty("total", Float.class.getName())
				.setTitle("Total")
				.setWidth(160)
				.setStyle(new Style("ColumnContentStyle"))
				.setHeaderStyle(new Style("ColumnHeaderStyle"))
				.build();
				rb.addColumn(column);				
			} catch (ColumnBuilderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DynamicReport subReport = rb.build();			
//				rb.addAutoText("Cliente: "+pedido.getCliente().getRazonSocial(),AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);
//				rb.addAutoText("Dirección: "+pedido.getCliente().getDireccion(),AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT);
			
			
			try {
				String rep = detalle.getId().toString();
				maestroBuilder.addConcatenatedReport(subReport, new ClassicLayoutManager(), rep, DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION,flag);
				this.parameters.put(rep, detalle.getPedidos());
				flag = true;
			} catch (DJBuilderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
