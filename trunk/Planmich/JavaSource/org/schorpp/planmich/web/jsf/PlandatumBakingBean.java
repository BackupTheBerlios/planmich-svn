package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	
	public void addPlandatum() {
		Plandatum p = new Plandatum();
		p.setName(name);
		p.setKommentar(kommentar);
		p.setWertstellung(wertstellung);
		
		Mandant m = service.getMandantById(mandantId);
		m.addPlandatum(p);
		mandantDAO.save(m);
		
		displayInfo("Plandatum " + name + " wurde hinzugefügt.");
		
	}

	public Date getWertstellung() {
		return wertstellung;
	}

	public void setWertstellung(Date wertstellung) {
		this.wertstellung = wertstellung;
	}

}
