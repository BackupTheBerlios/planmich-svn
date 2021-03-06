package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.schorpp.planmich.domain.Turnus;
import org.schorpp.planmich.service.MandantService;
import org.schorpp.planmich.service.PlandatumService;

public class PlandatumBakingBean extends BaseBean {

	private MandantService mandantService;

	private PlandatumService plandatumService;

	private MandantDAO mandantDAO;

	private String name;

	private String kommentar;

	private Date wertstellung;

	private double betrag;

	private String kategorieAuswahl;

	private int turnusAuswahl;

	private boolean editMode = false;

	private Plandatum p;

	private Map<String, Kategorie> categoriesMap = new HashMap<String, Kategorie>();

	private Map<Integer, Turnus> turnusMap = new HashMap<Integer, Turnus>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public void setService(MandantService service) {
		this.mandantService = service;
	}

	public void setPlandatumService(PlandatumService plandatumService) {
		this.plandatumService = plandatumService;
	}

	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}

	public List getTypListe() {
		KategorieTyp[] values = KategorieTyp.values();

		List<SelectItem> items = new ArrayList<SelectItem>();

		for (KategorieTyp element : values) {
			SelectItem temp = new SelectItem(element);
			if (turnusAuswahl == element.ordinal())
				temp.setValue(temp);
			items.add(temp);
		}
		return items;
	}

	/*
	 * Erzeugt eine Liste mit SelectItems zur Auswahl der Kategorie
	 */
	public List getKategorieListe() {
		Mandant m = (Mandant)getFromSession("Mandant");
		mandantDAO.attach(m);
		
		List categories = m.getKategorien();
		List<SelectItem> ret = new ArrayList<SelectItem>();

		// erster Eintrag ist leer
		ret.add(new SelectItem("dummy", ""));

		for (int i = 0; i < categories.size(); i++) {
			Kategorie aktKategorie = (Kategorie) categories.get(i);
			String id = String.valueOf(aktKategorie.getId());
			SelectItem temp = new SelectItem(id, aktKategorie.getName());
			if (kategorieAuswahl != null && kategorieAuswahl.equals(aktKategorie.getName()))
				temp.setValue(kategorieAuswahl);
			ret.add(temp);
			categoriesMap.put(id, aktKategorie);
		}

		return ret;
	}

	/*
	 * Erzeugt eine Liste von SelectItems zur Auswahl des Turnus
	 */
	public List getTurnusListe() {

		final FacesContext context = FacesContext.getCurrentInstance();

		final TurnusListe liste = (TurnusListe) context.getApplication()
				.createValueBinding("#{turnusListe}").getValue(context);

		final List turnus = liste.turnus;

		final List<SelectItem> ret = new ArrayList<SelectItem>();

		for (int i = 0; i < turnus.size(); i++) {
			Turnus aktTurnus = (Turnus) turnus.get(i);
			ret.add(new SelectItem(i, aktTurnus.getName()));
			turnusMap.put(i, aktTurnus);
		}

		return ret;
	}

	public void addPlandatum() {
		final Plandatum p = new Plandatum();
		p.setName(name);
		p.setKommentar(kommentar);
		p.setWertstellung(wertstellung);
		p.setBetrag(betrag);
		p.setKategorie(categoriesMap.get(kategorieAuswahl));
		p.setTurnus(turnusAuswahl);

		final Mandant m = (Mandant)getFromSession("Mandant");
		mandantDAO.attach(m);
		m.addPlandatum(p);
		mandantDAO.save(m);

		displayInfo("Plandatum " + name + " wurde hinzugef�gt.");

	}

	/**
	 * L�scht das selektierte Plandatum aus der Liste der Plandaten
	 * 
	 */
	public void deletePlandatum() {
		mandantService.deletePlandatum((Mandant)getFromSession("Mandant"), p);
	}

	/**
	 * Aktualisiert das Plandatum indem die alte aus der Liste der Kategorien
	 * gel�scht wird und die Kategorie mit den neuen Daten angelegt wird.
	 * 
	 */
	public void updatePlandatum() {

		Kategorie k = categoriesMap.get(kategorieAuswahl);
		
		plandatumService.updatePlandatum(p, name, kommentar, wertstellung,
				turnusAuswahl, betrag, k);

		displayInfo("Plandatum " + name + " wurde aktualisiert.");
	}

	public Date getWertstellung() {
		return wertstellung;
	}

	public void setWertstellung(Date wertstellung) {
		this.wertstellung = wertstellung;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	public String getKategorie() {
		return kategorieAuswahl;
	}

	public void setKategorie(String kategorieAuswahl) {
		this.kategorieAuswahl = kategorieAuswahl;
	}

	public int getTurnus() {
		return turnusAuswahl;
	}

	public void setTurnus(int turnusAuswahl) {
		this.turnusAuswahl = turnusAuswahl;
	}

	/**
	 * W�hlt das Plandatum aus und bef�llt die Eigenschaften
	 * 
	 * @return
	 */
	public String selectPlandatum() {
		this.name = p.getName();
		this.betrag = p.getBetrag();
		this.kommentar = p.getKommentar();
		this.wertstellung = p.getWertstellung();
		this.turnusAuswahl = p.getTurnus();
		this.kategorieAuswahl = String.valueOf(p.getKategorie().getId());

		editMode = true;

		return "success";
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Plandatum getPlandatum() {
		return p;
	}

	public void setPlandatum(Plandatum p) {
		this.p = p;
	}

	/**
	 * Wird aufgerufen, wenn Cancel Button gedr�ckt wird
	 * 
	 */

	public void cancelAction() {
		redirect("/pages/plandatum/uebersicht.jsp");
	}

	/**
	 * Setzt das Plandatum auf Default Werte
	 */

	public String clearPlandatum() {
		this.p = new Plandatum();
		this.name = null;
		this.kommentar = null;
		this.kategorieAuswahl = "";
		this.editMode = false;
		this.turnusAuswahl = 0;
		this.betrag = 0;
		this.wertstellung = new Date();

		return "neuesPlandatum";
	}

	
	public List<Plandatum> getPlandaten() {
		Mandant m = (Mandant)getFromSession("Mandant");
		mandantDAO.attach(m);
		
		return m.getPlandaten();
	}
}
