package org.schorpp.planmich.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "kategorie")
@Transactional(readOnly = false)
public class Kategorie implements Comparable<Kategorie> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Basic(optional = false)
	@Column(name = "name", unique = true)
	private String name;

	@Basic(optional = true)
	private String kommentar;

	@Basic(optional = false)
	private KategorieTyp typ;

	/* Default-Konstruktor */
	public Kategorie() {
	}

	/* Konstruktor */
	public Kategorie(String name) {
		this.name = name;
	}

	public Kategorie(String name, String kommentar) {
		this.name = name;
		this.kommentar = kommentar;
	}

	/**
	 * @return the id
	 */

	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */

	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the kommentar
	 */
	public String getKommentar() {
		return kommentar;
	}

	/**
	 * @param kommentar
	 *            the kommentar to set
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public KategorieTyp getKategorieTyp() {
		return typ;
	}

	public void setKategorieTyp(KategorieTyp t) {
		this.typ = t;
	}

	@Override
	public boolean equals(Object b) {
		return this.id == ((Kategorie) b).id;
	}

	public int compareTo(Kategorie o) {
		if (this.id == ((Kategorie) o).getId())
			return 0;

		return -1;
	}

}
