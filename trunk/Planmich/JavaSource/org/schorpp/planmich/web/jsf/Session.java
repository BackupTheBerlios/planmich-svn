package org.schorpp.planmich.web.jsf;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.MandantService;

public class Session extends BaseBean {

	private int mandantId;

	private MandantService service;

	public int getMandantId() {
		return mandantId;
	}

	public void setMandantId(int mandantId) {
		this.mandantId = mandantId;
		saveInSession("Mandant", mandantId);

		String forward = (String) getFromSession("Redirect");
		if (forward != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			UIViewRoot vr = facesContext.getApplication().getViewHandler()
					.createView(facesContext, forward);
			facesContext.setViewRoot(vr);
			facesContext.renderResponse();
		}

	}

	public Mandant getMandant() {
		return service.getMandantById(mandantId);
	}

	public void setService(MandantService service) {
		this.service = service;
	}

	public void selectMandant() {

	}

}
