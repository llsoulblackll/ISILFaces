package com.ozcorp.ejb.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ozcorp.ejb.entity.Alumno;
import com.ozcorp.ejb.facade.local.AlumnoFacadeLocal;

@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> implements AlumnoFacadeLocal {
	
	@PersistenceContext(name = "ISILFacesPU")
	private EntityManager em;
	
	public AlumnoFacade() {
		super(Alumno.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
}
