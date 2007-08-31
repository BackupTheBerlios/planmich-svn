package org.schorpp.planmich.service;

import java.util.List;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;

public class MandantServiceImpl implements MandantService {

	private MandantDAO mandantDao;

	public void createMandant(String name) {
		mandantDao.save(new Mandant(name));
	}

	public void setMandantDAO(MandantDAO mandantDao) {
		// Dependency Injection
		this.mandantDao = mandantDao;
	}

	public void add(Mandant m) {
		mandantDao.save(m);
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

	public void deleteKategorie(Integer mandantId, Kategorie k) {
		getMandantById(mandantId).getKategorien().remove(k);
	}

	public void deletePlandatum(Integer mandantId, Plandatum p) {
		getMandantById(mandantId).getPlandaten().remove(p);
	}
	
	public void update(Mandant m) {
		mandantDao.save(m);
	}

}
