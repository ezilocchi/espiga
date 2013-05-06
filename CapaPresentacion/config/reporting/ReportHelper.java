package reporting;

import java.awt.Color;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReportHelper {

	private Document document;
	
	private static ReportHelper instance;

	public static ReportHelper getInstance() {
		if(ReportHelper.instance == null){
			ReportHelper.instance = new ReportHelper();
		}
		return instance;
	}
	
	private ReportHelper(){
		InputStream inputStream = ReportHelper.class.getResourceAsStream("./template/TemplateSimpleABM.jrxml");
		SAXReader reader = new SAXReader();
		try {
			this.document = reader.read(inputStream);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return el color que esta definido el el template de estilos
	 */
	@SuppressWarnings("unchecked")
	public Color getOddRowColor(){		
		String backcolor = null;		
		Element root = this.document.getRootElement();			
		Iterator<Element> it =  root.elementIterator();
		while (it.hasNext()) {
			Element element = it.next();
			Attribute a = element.attribute("name");
			if (a!=null && a.getValue().equalsIgnoreCase("ColumnHeaderStyle")) {
				backcolor = element.attribute("backcolor").getValue();					
			} 
		}		
		if(backcolor == null){
			return new Color(0,0,0);
		}else{
			String r = backcolor.substring(1, 3);
			String g = backcolor.substring(3, 5);
			String b = backcolor.substring(5, 7);			
			return new Color(Integer.valueOf(r, 16), Integer.valueOf(g, 16), Integer.valueOf(b, 16));
		}
	}
	
	public Integer getPageWidth(){
		Element root = this.document.getRootElement();			
		Attribute a = root.attribute("columnWidth");		
		if (a!=null) {
			return Integer.parseInt(a.getValue());					
		}else{
			return null;
		}
	}
}
