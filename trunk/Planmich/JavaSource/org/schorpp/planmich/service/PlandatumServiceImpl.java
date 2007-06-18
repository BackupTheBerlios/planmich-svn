package org.schorpp.planmich.service;

import java.util.Date;

import org.schorpp.planmich.dao.PlandatumDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Plandatum;

public class PlandatumServiceImpl implements PlandatumService {

	private PlandatumDAO plandatumDAO;

	public void setPlandatumDAO(PlandatumDAO plandatumDAO) {
		// Dependency Injection
		this.plandatumDAO = plandatumDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.service.PlandatumService#delete(org.schorpp.planmich.domain.Plandatum)
	 */
	public void delete(Plandatum p) {
		plandatumDAO.delete(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.service.PlandatumService#updatePlandatum(org.schorpp.planmich.domain.Plandatum,
	 *      java.lang.String, java.lang.String,
	 *      org.schorpp.planmich.domain.KategorieTyp)
	 */
	public void updatePlandatum(Plandatum p, String name, String kommentar,
			Date wertstellung, int turnus, double betrag, Kategorie k) {
		p.setName(name);
		p.setKommentar(kommentar);
		p.setWertstellung(wertstellung);
		p.setTurnus(turnus);
		p.setBetrag(betrag);
		p.setKategorie(k);
		plandatumDAO.save(p);
	}

}
