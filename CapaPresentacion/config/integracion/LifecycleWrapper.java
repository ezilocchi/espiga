package integracion;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;

public class LifecycleWrapper extends Lifecycle{
	
	private Lifecycle other;

	public LifecycleWrapper(Lifecycle lifecycle){
		this.other = lifecycle;
	}
	
	@Override
	public void addPhaseListener(PhaseListener arg0) {
		this.other.addPhaseListener(arg0);		
	}

	@Override
	public void execute(FacesContext arg0) throws FacesException {
		try {
			this.other.execute(arg0);			
		} catch (Exception e) {
			//TODO tratar excepciones
		}
	}

	@Override
	public PhaseListener[] getPhaseListeners() {
		return this.other.getPhaseListeners();
	}

	@Override
	public void removePhaseListener(PhaseListener arg0) {
		this.other.removePhaseListener(arg0);
	}

	@Override
	public void render(FacesContext arg0) throws FacesException {
		this.other.render(arg0);		
	}

}
