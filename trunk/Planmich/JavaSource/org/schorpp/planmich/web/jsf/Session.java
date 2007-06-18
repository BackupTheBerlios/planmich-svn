package org.schorpp.planmich.web.jsf;

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

		final String forward = (String) getFromSession("Redirect");
		if (forward != null)
			redirect(forward);

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
