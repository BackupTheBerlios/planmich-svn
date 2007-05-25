package org.schorpp.planmich.service;

import java.util.List;

import org.schorpp.planmich.dao.KategorieDAO;
import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Mandant;

public class MandantServiceImpl implements MandantService {

	private MandantDAO mandantDao;
	
	private KategorieDAO kategorieDao;

	public void createMandant(String name) {
		mandantDao.saveMandant(new Mandant(name));
	}

	public void setMandantDAO(MandantDAO mandantDao) {
		// Dependency Injection
		this.mandantDao = mandantDao;
	}

	public void setKategorieDAO(KategorieDAO kategorieDao) {
		// Dependency Injection
		this.kategorieDao = kategorieDao;
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

	public void deleteKategorie(Integer id) {
		kategorieDao.delete(id);
	}

}
