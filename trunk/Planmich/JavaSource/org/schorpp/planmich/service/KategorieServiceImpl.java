package org.schorpp.planmich.service;

import java.util.List;

import org.schorpp.planmich.dao.KategorieDAO;
import org.schorpp.planmich.domain.Kategorie;

public class KategorieServiceImpl implements KategorieService {

	private KategorieDAO kategorieDao;
	 
	public void createKategorie(String name, String kommentar) {
		kategorieDao.saveKategorie(new Kategorie(name, kommentar));
	}
	
	public void setKategorieDAO(KategorieDAO kategorieDao) {
		// Dependency Injection
		this.kategorieDao = kategorieDao;
	}

	public void add(Kategorie m) {
		kategorieDao.saveKategorie(m);
	}

	public Kategorie getKategorieById(Integer id) {
		return kategorieDao.getKategorieById(id);
	}

	public List<Kategorie> getAll() {
		return kategorieDao.getKategorien();
	}

	public Kategorie getKategorieByName(String name) {
		return kategorieDao.getKategorieByName(name);
	}

	public void delete(Kategorie m) {
		kategorieDao.delete(m);
	}

}
