package org.schorpp.planmich.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericDAOImpl<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID> {

	private Class<T> getPersistenceClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		return (T) getHibernateTemplate().get(getPersistenceClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getHibernateTemplate().loadAll(getPersistenceClass());
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T instance) {
		Criteria kriterium = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(instance.getClass());
		
		return kriterium.list();
	}
	
	public T save(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		
		return entity;
	}
	
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
}
