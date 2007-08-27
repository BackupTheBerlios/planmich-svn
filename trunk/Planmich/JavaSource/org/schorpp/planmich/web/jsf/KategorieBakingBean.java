package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.KategorieService;
import org.schorpp.planmich.service.MandantService;

public class KategorieBakingBean extends BaseBean {

	private MandantService mandantService;

	private KategorieService kategorieService;

	private MandantDAO mandantDAO;

	private int id;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setMandantService(MandantService service) {
		this.mandantService = service;
	}

	public void setKategorieService(KategorieService service) {
		this.kategorieService = service;
	}

	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}

	public List getTypListe() {
		final KategorieTyp[] values = KategorieTyp.values();

		final List<SelectItem> items = new ArrayList<SelectItem>();

		for (KategorieTyp element : values)
			items.add(new SelectItem(element));
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
	 * Legt eine neue Kategorie an und speicher diese in der Liste der
	 * Kategorien ab
	 */
	public void addKategorie() {

		mandantId = (Integer) getFromSession("Mandant");
		final Mandant m = mandantService.getMandantById(mandantId);

		k = new Kategorie();

		k.setName(name);
		k.setKommentar(kommentar);
		k.setKategorieTyp(typ);

		m.addKategorie(k);
		mandantDAO.save(m);

		displayInfo("Kategorie " + name + " wurde hinzugefügt.");
	}

	/**
	 * Aktualisiert die Kategorie indem die alte aus der Liste der Kategorien
	 * gelöscht wird und die Kategorie mit den nueen Daten angelegt wird.
	 * 
	 */
	public void updateKategorie() {

		kategorieService.updateKategorie(k, name, kommentar, typ);

		displayInfo("Kategorie " + name + " wurde aktualisiert.");
	}

	/**
	 * Löscht die selektierte Kategorie aus der Liste der Kategorien
	 * 
	 */
	public void deleteKategorie() {
		mandantService.deleteKategorie(mandantId, k);
	}

	/**
	 * Wählt die Kategorie aus und befüllt die Eigenschaften
	 * 
	 * @return
	 */
	public String selectKategorie() {
		this.setId(k.getId());
		this.setName(k.getName());
		this.setKommentar(k.getKommentar());
		this.setKategorieTyp(k.getKategorieTyp());

		this.editMode = true;

		return "success";
	}

	/**
	 * Setzt die Kategorie auf ein Default Werte
	 */

	public String clearKategorie() {
		this.k = new Kategorie();
		this.name = null;
		this.kommentar = null;
		this.typ = KategorieTyp.Ausgabe;
		this.editMode = false;

		return "neueKategorie";
	}

	/**
	 * Wird aufgerufen, wenn Cancel Button gedrückt wird
	 * 
	 */

	public void cancelAction() {
		redirect("/pages/kategorie/uebersicht.jsp");
	}

}
