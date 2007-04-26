package org.schorpp.planmich.service;
import java.util.List;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.REPEATABLE_READ, readOnly=true)
public interface KategorieService {

	@Transactional(readOnly = false)
	public void createKategorie(String name, String kommentar);

	@Transactional(readOnly = false)
	public void add(Kategorie k);
	
	
	/**
	 * Liefert eine Liste aller Kategorien
	 * @return 
	 */
	@Transactional(readOnly = true) 
	List<Kategorie> getAll();
	
	/**
	 * Liefer die Kategorie zu angegebenen Id zurück
	 * @param id - die ID der Kategorie
	 * @return  
	 */
	@Transactional(readOnly = true)
	Kategorie getKategorieById(Integer id);
	
	
	/**
	 * Liefer den Kategorie zum angegebenen Namen zurück
	 * @param name - der Name des Kategorie
	 * @return 
	 */
	@Transactional(readOnly = true)
	Kategorie getKategorieByName(String name);

	/**
	 * Löscht den übergebenen Kategorie aus der Datenbank
	 * @param m
	 */
	public void delete(Kategorie m);
	
	
}
