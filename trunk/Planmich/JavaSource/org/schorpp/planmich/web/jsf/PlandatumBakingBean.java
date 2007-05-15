package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.schorpp.planmich.service.MandantService;

public class PlandatumBakingBean extends BaseBean {

	private MandantService service;
	private MandantDAO mandantDAO;
	private String name;
	private String kommentar;
	private Integer mandantId;
	private Date wertstellung;
	private double betrag;
	private String kategorieAuswahl;
	
	private Map<String, Kategorie> categoriesMap = new HashMap<String, Kategorie>();
	
	public PlandatumBakingBean() {
		mandantId = (Integer) getFromSession("Mandant");
	}

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
		this.service = service;
	}
	
	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}
	
	public List getTypListe() {
		KategorieTyp[] values = KategorieTyp.values();
		
		List<SelectItem> items = new ArrayList<SelectItem>();
		
		for(int i=0; i<values.length; i++) {
			items.add(new SelectItem(values[i]));
		}
		return items;
	}
	
	public List getKategorieListe() {
		List categories = ((Mandant) mandantDAO.getMandantById(mandantId)).getKategorien();
		List<SelectItem> ret = new ArrayList<SelectItem>();
		
		// erster EintragI ist leer
		ret.add(new SelectItem("dummy", ""));
		
		for(int i=0; i<categories.size(); i++) {
			Kategorie aktKategorie = (Kategorie) categories.get(i);
			String id = String.valueOf(aktKategorie.getId());
			ret.add(new SelectItem(id, aktKategorie.getName()));
			categoriesMap.put(id, aktKategorie); // aktuelles Kategorie-Objekt in Hashmap ablegen zum spï¿½teren wiederfinden.
		}
		
		return ret;
	}
	
	public void addPlandatum() {
		Plandatum p = new Plandatum();
		p.setName(name);
		p.setKommentar(kommentar);
		p.setWertstellung(wertstellung);
		p.setBetrag(betrag);
		p.setKategorie((Kategorie) categoriesMap.get(kategorieAuswahl));
		
		Mandant m = service.getMandantById(mandantId);
		m.addPlandatum(p);
		mandantDAO.saveMandant(m);
		
		displayInfo("Plandatum " + name + " wurde hinzugefügt.");
		
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

}
