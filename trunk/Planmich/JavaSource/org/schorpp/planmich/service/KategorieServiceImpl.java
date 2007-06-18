package org.schorpp.planmich.service;

import org.schorpp.planmich.dao.KategorieDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;

public class KategorieServiceImpl implements KategorieService {

	private KategorieDAO kategorieDAO;

	public void setKategorieDAO(KategorieDAO kategorieDAO) {
		// Dependency Injection
		this.kategorieDAO = kategorieDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.service.KategorieService#delete(org.schorpp.planmich.domain.Kategorie)
	 */
	public void delete(Kategorie k) {
		kategorieDAO.delete(k);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.service.KategorieService#updateKategorie(org.schorpp.planmich.domain.Kategorie,
	 *      java.lang.String, java.lang.String,
	 *      org.schorpp.planmich.domain.KategorieTyp)
	 */
	public void updateKategorie(Kategorie k, String name, String kommentar,
			KategorieTyp typ) {
		k.setName(name);
		k.setKommentar(kommentar);
		k.setKategorieTyp(typ);

		kategorieDAO.save(k);
	}

}
