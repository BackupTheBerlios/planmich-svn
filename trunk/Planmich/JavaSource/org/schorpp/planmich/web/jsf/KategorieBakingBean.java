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
	
	private Integer id;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void addKategorie() {
		Kategorie k = new Kategorie();
		k.setName(name);
		k.setKommentar(kommentar);
		k.setKategorieTyp(typ);

		Mandant m = service.getMandantById(mandantId);
		m.addKategorie(k);
		mandantDAO.saveMandant(m);

		displayInfo("Kategorie " + name + " wurde hinzugefügt.");

	}
	
	public void delete() {
		service.deleteKategorie(id);
	}

}
