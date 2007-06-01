package org.schorpp.planmich.service;

import java.util.List;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;
import org.schorpp.planmich.domain.Mandant;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, readOnly = true)
public interface MandantService {

	@Transactional(readOnly = false)
	public void createMandant(String name);

	@Transactional(readOnly = false)
	public void add(Mandant m);

	/**
	 * Liefert eine Liste aller Mandanten
	 * 
	 * @return die Mandanten
	 */
	@Transactional(readOnly = true)
	List<Mandant> getAll();

	/**
	 * Liefer den Mandanten zu angegebenen Id zurück
	 * 
	 * @param id -
	 *            die ID des Mandanten
	 * @return Mandant
	 */
	@Transactional(readOnly = true)
	Mandant getMandantById(Integer id);

	/**
	 * Liefer den Mandanten zum angegebenen Namen zurück
	 * 
	 * @param name -
	 *            der Name des Mandanten
	 * @return Mandant
	 */
	@Transactional(readOnly = true)
	Mandant getMandantByName(String name);

	/**
	 * Köscht den +bergebenen Mandant aus der Datenbank
	 * 
	 * @param m
	 */
	@Transactional
	public void delete(Mandant m);

	@Transactional
	public void deleteKategorie(Integer mandantId, Kategorie k);

}
