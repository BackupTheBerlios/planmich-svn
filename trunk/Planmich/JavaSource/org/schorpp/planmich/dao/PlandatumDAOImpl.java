package org.schorpp.planmich.dao;

import org.schorpp.planmich.domain.Plandatum;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PlandatumDAOImpl extends HibernateDaoSupport implements PlandatumDAO {

	/* (non-Javadoc)
	 * @see org.schorpp.planmich.dao.PlandatumDAO#getPlandatum(java.lang.Integer)
	 */
	public Plandatum getPlandatum(Integer id) {
		return (Plandatum) getHibernateTemplate().get(Plandatum.class, id);
	}
	
	public Integer savePlandatum(Plandatum p) {
		return (Integer) getHibernateTemplate().save(p);
	}

}
