package org.schorpp.planmich.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plandatum")
public class Plandatum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Basic(optional = false)
	private String name;
	
	@Basic(optional = true)
	private String kommentar;
	
	@Basic(optional = false)
	private double betrag;
	
	@Basic(optional = false)
	private Date wertstellung;
	
	@Basic(optional = false)
	private int wiederholung;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Kategorie kategorie;

	// Default Konstruktor
	public Plandatum() 
	{}
	
	
	public Plandatum(String name) {
		this.name = name;
	}
	

	// Getters und Setters

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	public int getWiederholung() {
		return wiederholung;
	}

	public void setWiederholung(int wiederholung) {
		this.wiederholung = wiederholung;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getWertstellung() {
		return wertstellung;
	}

	public void setWertstellung(Date wertstellung) {
		this.wertstellung = wertstellung;
	}


	public Kategorie getKategorie() {
		return kategorie;
	}


	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}


	public String getKommentar() {
		return kommentar;
	}


	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	
}
