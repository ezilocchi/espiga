package presentacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import monitoreo.MonitorMensajesMB;

import org.richfaces.component.html.HtmlToolBar;

import persistencia.ServiceEntity;
import security.UserSecurity;

public class MBSesion {
	
	@EJB
	private ServiceEntity service;
	private HtmlToolBar menu;
	private UserSecurity user;	
	private MonitorMensajesMB mensajesMB;
	private List<String> integrantes;

	public MBSesion() {
		this.integrantes = new ArrayList<String>();		
		this.integrantes.add("Consuelo López ");
		this.integrantes.add("Zilcchi Emiliano");
		this.integrantes.add("Pablo Andres Lamberti");
		this.integrantes.add("Juan Ignacio Valentinis");
		this.integrantes.add("Jésica Casas Ruggieri");
		this.integrantes.add("Federico Bobbio ");
	}
	
	@PostConstruct
	public void init(){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();		
		UserSecurity u = new UserSecurity();
		u.setLogin(context.getRemoteUser());
		this.user = this.service.list(u).get(0);		
	}

	public HtmlToolBar getMenu() {
		return menu;
	}
	public void setMenu(HtmlToolBar menu) {
		this.menu = menu;
	}	
	public MonitorMensajesMB getMensajesMB() {
		return mensajesMB;
	}
	public void setMensajesMB(MonitorMensajesMB mensajesMB) {
		this.mensajesMB = mensajesMB;
	}
	
	public List<String> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<String> integrantes) {
		this.integrantes = integrantes;
	}

	public UserSecurity getUser() {
		return user;
	}
	public void setUser(UserSecurity user) {
		this.user = user;
	}
	
	public void closeSession(ActionEvent event){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) context.getSession(false);
		if (session != null){
			session.invalidate();
		}
		try {
			context.dispatch("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
