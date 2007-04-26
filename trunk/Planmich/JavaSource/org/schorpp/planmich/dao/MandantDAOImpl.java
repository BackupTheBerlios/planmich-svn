package org.schorpp.planmich.dao;

import java.util.List;

import org.schorpp.planmich.domain.Mandant;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MandantDAOImpl extends HibernateDaoSupport implements MandantDAO {

	/* (non-Javadoc)
	 * @see org.schorpp.planmich.dao.MandantDAO#getPlandatum(java.lang.Integer)
	 */
	public Mandant getMandantById(Integer id) {
		return (Mandant) getHibernateTemplate().get(Mandant.class, id);
	}
	
	public Integer saveMandant(Mandant m) {
		return (Integer) getHibernateTemplate().save(m);
	}

	@SuppressWarnings("unchecked")
	public List<Mandant> getMandanten() {
		return getHibernateTemplate().loadAll(Mandant.class);
	}

	public Mandant getMandantByName(String name) {
		List res = getHibernateTemplate().find("from Mandant where name like ?", name);
		if(res.size() > 0)
			return (Mandant) res.get(0);
		else
			return null;
	}

	public void delete(Mandant m) {
		getHibernateTemplate().delete(m);
	}
	
	public void update(Mandant m) {
		getHibernateTemplate().update(m);
	}

}
