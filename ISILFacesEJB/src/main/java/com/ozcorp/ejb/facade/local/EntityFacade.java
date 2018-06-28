package com.ozcorp.ejb.facade.local;

import java.util.List;

public interface EntityFacade<T> {
	
	int insert(T entity);
	boolean update(T entity);
	boolean delete(T entity);
	T find(Object id);
	List<T> findAll();
}
