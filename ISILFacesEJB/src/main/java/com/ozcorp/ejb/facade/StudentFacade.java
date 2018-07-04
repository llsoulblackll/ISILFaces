package com.ozcorp.ejb.facade;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ozcorp.ejb.entity.Student;
import com.ozcorp.ejb.facade.local.StudentFacadeLocal;
import com.ozcorp.util.aws.Prediction;

@Stateless
public class StudentFacade extends AbstractFacade<Student> implements StudentFacadeLocal {
	
	@PersistenceContext(unitName = "ISILFacesPU")
	private EntityManager em;
	
	public StudentFacade() {
		super(Student.class);
	}

	//SINCE THIS IS AN INSTANCE METHOD AND THE INSTANCE HAS NOT YET BEEN CREATED IT WILL RETURN NULL ON PARENT CONSTRUCTOR
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Prediction> findAllPredictions() {
		return findAll().stream().map(s -> new Prediction(s, 0)).collect(Collectors.toList());
	}
	
}
