package org.schorpp.planmich.service;

import java.util.List;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface KategorieService {

	/**
	 * Löscht die übergebenen Kategorie aus der Datenbank
	 * 
	 * @param k
	 */
	@Transactional
	public void delete(Kategorie k);

	/**
	 * Aktualisiert die Kategorie
	 * 
	 * @author mascho
	 * @param k
	 * @param name
	 * @param kommentar
	 * @param typ
	 */
	@Transactional(readOnly = false)
	public void updateKategorie(Kategorie k, String name, String kommentar,
			KategorieTyp typ);

	public List<Kategorie> getAll();
}
