package org.schorpp.planmich.web.jsf;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

public class MandantSelectionCheck implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Umleiten auf /pages/selectMandant.jsp
	 */
	private void toMandantSelection() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot vr = (UIViewRoot) facesContext.getApplication()
				.getViewHandler().createView(facesContext,
						"/pages/selectMandant.jsp");
		facesContext.setViewRoot(vr);
		facesContext.renderResponse();
	}

	/**
	 * Checks, if the current user ia allowed to view the wanted page. If not
	 * allowed, redirect user to login page.
	 */
	public void beforePhase(PhaseEvent phaseEvent) {

		FacesContext facesContext = phaseEvent.getFacesContext();

		// catch a view definition in xml
		String renderedViewId = facesContext.getViewRoot().getViewId();

		if(getFromSession("Mandant") == null)
			toMandantSelection();
		
		// if (!renderedViewId.equals("/login.jsp") &&
		// !renderedViewId.equals("/error.jsp")) {
		// // hier muss gepr�ft werden, ob der Benutzer die Seite zugreifen
		// // darf
		// // dazu aus der Session ein Benutzer Objekt holen, dass in der login
		// // action
		// // gesetzt wird. Falls kein Benutzer Objekt verf�gbar ist, oder der
		// // Benutzer
		// // nicht die notwendigen Berechtigungen hat, kann er auf die Logon
		// // Seite zur�ckgeschickt werden
		//
		// Model login = (Model) facesContext.getApplication()
		// .createValueBinding("#{loginBean}").getValue(facesContext);
		//
		// if (!login.isLoggedIn())
		// toLogon();
		// else {
		// facesContext.getViewRoot().setViewId(renderedViewId);
		// }
		//			
		// }

		//facesContext.getViewRoot().setViewId(renderedViewId);
		
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	public void afterPhase(PhaseEvent arg0) {

	}
	
	protected Object getFromSession(String name) {
		return getRequest().getSession(true).getAttribute(name);
	}
	
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}
