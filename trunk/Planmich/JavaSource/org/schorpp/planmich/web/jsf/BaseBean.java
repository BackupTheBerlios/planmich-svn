package org.schorpp.planmich.web.jsf;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class BaseBean {

	protected ResourceBundle messages = ResourceBundle.getBundle("org.schorpp.planmich.web.resources.resources");
	
	
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	protected void saveInSession(String name, Object objekt) {
		getRequest().getSession(true).setAttribute(name, objekt);
	}

	protected Object getFromSession(String name) {
		return getRequest().getSession(true).getAttribute(name);
	}
	
	protected void displayError(String error) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, error, error));
	}
	
	protected void displayInfo(String error) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO, error, error));
	}
	
}
