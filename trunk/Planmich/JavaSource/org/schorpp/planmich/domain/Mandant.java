package org.schorpp.planmich.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "mandant")
public class Mandant {

	private int id;

	private String name;

	private String kommentar;

	private List<Plandatum> plandaten = new ArrayList<Plandatum>();

	private List<Kategorie> kategorien = new ArrayList<Kategorie>();

	/* Default-Konstruktor */
	public Mandant() {
	}

	/* Konstruktor */
	public Mandant(String name) {
		this.name = name;
	}

	/**
	 * Fügt eine Kategorie hinzu
	 * 
	 * @param Plandatum
	 */
	@Transient
	public void addKategorie(Kategorie k) {
		kategorien.add(k);
	}

	@OneToMany(cascade = { CascadeType.ALL })
	@Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "kategorie_fk")
	public List<Kategorie> getKategorien() {
		return kategorien;
	}

	public void setKategorien(List<Kategorie> kategorien) {
		this.kategorien = kategorien;
	}

	/**
	 * Fügt ein Plandatum hinzu
	 * 
	 * @param Plandatum
	 */
	@Transient
	public void addPlandatum(Plandatum p) {
		plandaten.add(p);
	}

	/**
	 * @return the plandaten
	 */

	@OneToMany(cascade = { CascadeType.ALL })
	@Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "plandatum_fk")
	public List<Plandatum> getPlandaten() {
		return plandaten;
	}

	/**
	 * @param plandaten
	 *            the plandaten to set
	 */
	public void setPlandaten(List<Plandatum> plandaten) {
		this.plandaten = plandaten;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Basic(optional = false)
	@Column(name = "name", unique = true)
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

	public boolean equals(Mandant m) {
		return id == m.getId();
	}

}
