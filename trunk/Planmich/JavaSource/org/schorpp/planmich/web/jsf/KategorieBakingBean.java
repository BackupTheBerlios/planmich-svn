package org.schorpp.planmich.web.jsf;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.MandantService;

public class KategorieBakingBean extends BaseBean {

	private MandantService service;
	private MandantDAO mandantDAO;
	private String name;
	private String kommentar;
	private boolean soll = true;
	private Integer mandantId;
	
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

	public boolean getSoll() {
		return soll;
	}

	public void setSoll(boolean soll) {
		this.soll = soll;
	}

	public void setService(MandantService service) {
		this.service = service;
	}
	
	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}
	
	public void addKategorie() {
		Kategorie k = new Kategorie();
		k.setName(name);
		k.setKommentar(kommentar);
		k.setSoll(soll);
		
		Mandant m = service.getMandantById(mandantId);
		m.addKategorie(k);
		mandantDAO.update(m);
	}

}
