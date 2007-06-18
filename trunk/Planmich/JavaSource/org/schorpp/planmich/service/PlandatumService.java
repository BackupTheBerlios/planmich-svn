package org.schorpp.planmich.service;

import java.util.Date;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Plandatum;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, readOnly = true)
public interface PlandatumService {

	/**
	 * Löscht das übergebene Plandatum aus der Datenbank
	 * 
	 * @param Plandatum
	 */
	@Transactional
	public void delete(Plandatum p);

	/**
	 * Aktualisiert das Plandatum
	 * 
	 * @author mascho
	 * @param k
	 * @param name
	 * @param kommentar
	 */
	@Transactional(readOnly = false)
	public void updatePlandatum(Plandatum p, String name, String kommentar,
			Date wertstellung, int turnus, double betrag, Kategorie k);

}
