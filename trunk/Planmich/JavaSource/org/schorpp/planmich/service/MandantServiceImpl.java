package org.schorpp.planmich.service;

import java.util.List;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;

public class MandantServiceImpl implements MandantService {

	private MandantDAO mandantDao;

	public void createMandant(String name) {
		mandantDao.saveMandant(new Mandant(name));
	}

	public void setMandantDAO(MandantDAO mandantDao) {
		// Dependency Injection
		this.mandantDao = mandantDao;
	}
	
	public void add(Mandant m) {
		mandantDao.saveMandant(m);
	}

	public Mandant getMandantById(Integer id) {
		return mandantDao.getMandantById(id);
	}

	public List<Mandant> getAll() {
		return mandantDao.getMandanten();
	}

	public Mandant getMandantByName(String name) {
		return mandantDao.getMandantByName(name);
	}

	public void delete(Mandant m) {
		mandantDao.delete(m);
	}

	public void deleteKategorie(Mandant m, Kategorie k) {
		List kategorien = m.getKategorien();
		kategorien.remove(k); 
		//m.setChild(entriesList); //not required 
		mandantDao.save(m);
	}

	public void updateKategorie(Mandant m, Kategorie k) {
		
		int pos = m.getKategorien().indexOf(k);
		
		List<Kategorie> kategorien = m.getKategorien();
		for(Kategorie kategorie:kategorien) {
			if(kategorie.equals(k))
				System.out.println("Treffer");
		}
		
		mandantDao.saveMandant(m);
		
	}

}
