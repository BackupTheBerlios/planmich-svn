package org.schorpp.planmich.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

	public T findById(ID id);
	
	public List<T> findAll();
	
	public List<T> findByExample(T instance);
	
	public T save(T entity);
	
	public void delete(T entity);
}
