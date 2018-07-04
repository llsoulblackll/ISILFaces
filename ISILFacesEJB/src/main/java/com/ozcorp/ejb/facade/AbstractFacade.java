package com.ozcorp.ejb.facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


public abstract class AbstractFacade<T> {
	
	private Class<T> entityType;
	//protected CriteriaBuilder criteriaBuilder;
	
	public AbstractFacade(Class<T> entityType) {
		this.entityType = entityType;
		//CANNOT GET CRITERIA BUILDER SINCE FOR SOME REASON ENTITY MANAGER IS NOT YET INJECTED
		//criteriaBuilder = getEntityManager().getCriteriaBuilder();
	}
	
	public int insert(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		return 0;
	}

	public boolean update(T entity) {
		getEntityManager().merge(entity);
		return true;
	}

	public boolean delete(T entity) {
		getEntityManager().remove(entity);
		return true;
	}

	public T find(Object id) {
		return getEntityManager().find(entityType, id);
	}

	public List<T> findAll() {
		CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityType);
		criteriaQuery.select(criteriaQuery.from(entityType));
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

	protected abstract EntityManager getEntityManager();
}
