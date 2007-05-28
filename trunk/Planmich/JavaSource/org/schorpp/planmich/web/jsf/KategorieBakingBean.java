package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.MandantService;


public class KategorieBakingBean extends BaseBean {

	private MandantService service;

	private MandantDAO mandantDAO;

	private String name;

	private String kommentar;

	private KategorieTyp typ = KategorieTyp.Ausgabe;

	private Integer mandantId;
	
	private Kategorie k;
	
	private boolean editMode = false;

	public KategorieBakingBean() {
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

	public KategorieTyp getKategorieTyp() {
		return typ;
	}

	public void setKategorieTyp(KategorieTyp t) {
		this.typ = t;
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

		for (int i = 0; i < values.length; i++) {
			items.add(new SelectItem(values[i]));
		}
		return items;
	}

	public Kategorie getKategorie() {
		return k;
	}

	public void setKategorie(Kategorie k) {
		this.k = k;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	
	/**
	 * Legt eine neue Kategorie an und speicher diese in der Liste 
	 * der Kategorien ab
	 */
	public void addKategorie() {
		
		Mandant m = service.getMandantById(mandantId);
		
		k = new Kategorie();
		
		k.setName(name);
		k.setKommentar(kommentar);
		k.setKategorieTyp(typ);

		m.addKategorie(k);
		mandantDAO.saveMandant(m);

		displayInfo("Kategorie " + name + " wurde hinzugefügt.");
	}
	
	/**
	 * Aktualisiert die Kategorie indem die alte aus der Liste der 
	 * Kategorien gelöscht wird und die Kategorie mit den nueen Daten angelegt wird.
	 * 
	 */
	public void updateKategorie() {
		Mandant m = service.getMandantById(mandantId);
		
		m.getKategorien().remove(k);
		
		mandantDAO.saveMandant(m);
		
		k = new Kategorie();
		
		k.setName(name);
		k.setKommentar(kommentar);
		k.setKategorieTyp(typ);

		m.addKategorie(k);
		mandantDAO.saveMandant(m);

		displayInfo("Kategorie " + name + " wurde aktualisiert.");
	}
	
	/**
	 * Löscht die selektierte Kategorie aus der Liste der Kategorien
	 *
	 */
	public void deleteKategorie() {
		Mandant m = service.getMandantById(mandantId);
		service.deleteKategorie(m, k);
	}
	
	/**
	 * Wählt die Kategorie aus und befüllt die Eigenschaften
	 * @return
	 */
	public String selectKategorie() {
		this.setName(k.getName());
		this.setKommentar(k.getKommentar());
		this.setKategorieTyp(k.getKategorieTyp());
		
		this.editMode = true;
		
		return "success";
	}

}
