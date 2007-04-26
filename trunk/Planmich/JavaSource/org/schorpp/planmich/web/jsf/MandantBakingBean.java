package org.schorpp.planmich.web.jsf;

import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.MandantService;


public class MandantBakingBean {

	private MandantService service;
	private String name;
	private String kommentar;

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
	
	public void addMandant() {
		Mandant m = new Mandant();
		m.setName(name);
		m.setKommentar(kommentar);
		
		service.add(m);
	}

}
