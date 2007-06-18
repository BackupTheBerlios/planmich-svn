package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.MandantService;

public class MandantBakingBean extends BaseBean {

	private MandantService service;

	private String name;

	private String kommentar;

	private final Map<String, Kategorie> categoriesMap = new HashMap<String, Kategorie>();

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

	public List<SelectItem> getKategorien() {
		final List categories = ((Mandant) getFromSession("Mandant"))
				.getKategorien();
		final List<SelectItem> ret = new ArrayList<SelectItem>();

		// erster EintragI ist leer
		ret.add(new SelectItem("dummy", ""));

		for (int i = 0; i < categories.size(); i++) {
			final Kategorie aktKategorie = (Kategorie) categories.get(i);
			final String id = String.valueOf(aktKategorie.getId());
			ret.add(new SelectItem(id, aktKategorie.getName()));
			categoriesMap.put(id, aktKategorie); // aktuelles
			// Kategorie-Objekt in
			// Hashmap ablegen zum
			// sp�teren wiederfinden.
		}

		return ret;
	}

	public void setService(MandantService service) {
		this.service = service;
	}

	public void addMandant() {
		final Mandant m = new Mandant();
		m.setName(name);
		m.setKommentar(kommentar);

		service.add(m);
	}

}
