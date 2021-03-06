package org.schorpp.planmich.service;

import java.util.List;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
	 * Liefer den Mandanten zu angegebenen Id zur�ck
	 * 
	 * @param id -
	 *            die ID des Mandanten
	 * @return Mandant
	 */
	@Transactional(readOnly = true)
	Mandant getMandantById(Integer id);

	/**
	 * Liefer den Mandanten zum angegebenen Namen zur�ck
	 * 
	 * @param name -
	 *            der Name des Mandanten
	 * @return Mandant
	 */
	@Transactional(readOnly = true)
	Mandant getMandantByName(String name);

	/**
	 * K�scht den +bergebenen Mandant aus der Datenbank
	 * 
	 * @param m
	 */
	@Transactional
	public void delete(Mandant m);

	@Transactional
	public void deleteKategorie(Mandant mandant, Kategorie k);

	@Transactional
	public void deletePlandatum(Mandant mandant, Plandatum p);

	@Transactional(readOnly = false)
	public void update(Mandant m);

}
