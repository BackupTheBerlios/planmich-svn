package org.schorpp.planmich.web.jsf;

import java.util.List;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Mandant;

public class MandantenListe {

	private MandantDAO mandantDAO;

	public List<Mandant> getMandanten() {
		return mandantDAO.getMandanten();
	}

	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}

}
