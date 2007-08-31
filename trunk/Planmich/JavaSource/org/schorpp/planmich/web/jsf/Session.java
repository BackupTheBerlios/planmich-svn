package org.schorpp.planmich.web.jsf;

import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.MandantService;

public class Session extends BaseBean {

	private Mandant mandant;

	private MandantService service;

	public Mandant getMandant() {
		return mandant;
	}

	public void setMandant(Mandant mandant) {
		this.mandant = mandant;
		saveInSession("Mandant", mandant);

		final String forward = (String) getFromSession("Redirect");
		if (forward != null)
			redirect(forward);

	}


	public void setService(MandantService service) {
		this.service = service;
	}

	public void selectMandant() {

	}

}
