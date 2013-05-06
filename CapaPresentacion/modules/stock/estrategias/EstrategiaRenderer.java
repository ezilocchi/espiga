package stock.estrategias;

import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.richfaces.component.html.HtmlInputText;

public abstract class EstrategiaRenderer {
	
	protected List<UIComponent> components;	

	public static void render(EstrategiaGestion estrategiaGestion, HtmlPanelGrid panelGrid){
		List<UIComponent> comps = panelGrid.getChildren();
		EstrategiaRenderer renderer = null;
		switch (estrategiaGestion) {
			case MANUAL:
				renderer = new EstrategiaManual();				
				break;
		}
		renderer.setComponents(comps);
		renderer.render();		
	}
	
	protected void addLine(String label, String property){		
		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValue(label);
		components.add(outputText);
		
		HtmlInputText inputText = new HtmlInputText();
		inputText.setValueExpression("value", EstrategiaRenderer.getValueExpression(property));
		components.add(inputText);	
	}
	
	
	public static ValueExpression getValueExpression(String property){
		FacesContext context = FacesContext.getCurrentInstance();		
		ELContext elContext = context.getELContext();
		Application app = context.getApplication();
		ExpressionFactory elFactory = app.getExpressionFactory(); 
		ValueExpression valueExp = elFactory.createValueExpression(elContext, 
										"#{gestionStockInsumosMB.entity."+property+"}",
										Object.class);
		return valueExp;
	}
	
	public abstract void render();

	public List<UIComponent> getComponents() {
		return components;
	}
	public void setComponents(List<UIComponent> components) {
		this.components = components;
	}
}
