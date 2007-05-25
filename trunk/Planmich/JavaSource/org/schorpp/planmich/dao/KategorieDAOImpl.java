package org.schorpp.planmich.dao;

import java.util.List;

import org.schorpp.planmich.domain.Kategorie;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class KategorieDAOImpl extends HibernateDaoSupport implements KategorieDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.dao.KategorieDAO#getPlandatum(java.lang.Integer)
	 */
	public Kategorie getKategorieById(Integer id) {
		return (Kategorie) getHibernateTemplate().get(Kategorie.class, id);
	}

	public void saveKategorie(Kategorie m) {
		getHibernateTemplate().save(m);
	}

	@SuppressWarnings("unchecked")
	public List<Kategorie> getKategorien() {
		return getHibernateTemplate().loadAll(Kategorie.class);
	}

	public Kategorie getKategorieByName(String name) {
		List res = getHibernateTemplate().find(
				"from Kategorie where name like ?", name);
		if (res.size() > 0) {
			return (Kategorie) res.get(0);
		} else {
			return null;
		}
	}

	public void delete(Kategorie k) {
		getHibernateTemplate().delete(k);
	}

	public void delete(Integer id) {
		delete(getKategorieById(id));
	}
	
	public void save(Kategorie k) {
		getHibernateTemplate().saveOrUpdate(k);
	}

}
