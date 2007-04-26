package org.schorpp.planmich.dao;

import java.util.List;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Plandatum;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class KategorieDAOImpl extends HibernateDaoSupport implements KategorieDAO {

	/* (non-Javadoc)
	 * @see org.schorpp.planmich.dao.PlandatumDAO#getKategorie(java.lang.Integer)
	 */
	public Plandatum getKategorie(Integer id) {
		return (Plandatum) getHibernateTemplate().get(Plandatum.class, id);
	}
	
	public Integer saveKategorie(Kategorie k) {
		return (Integer) getHibernateTemplate().save(k);
	}

	public void delete(Kategorie k) {
		getHibernateTemplate().delete(k);
	}

	public Kategorie getKategorieById(Integer id) {
		return (Kategorie) getHibernateTemplate().get(Kategorie.class, id);
	}

	public Kategorie getKategorieByName(String name) {
		List res = getHibernateTemplate().find("from Kategorie where name like ?", name);
		if(res.size() > 0)
			return (Kategorie) res.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<Kategorie> getKategorien() {
		return getHibernateTemplate().loadAll(Kategorie.class);
	}

}
