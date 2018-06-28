package com.ozcorp.ejb.facade.local;

import javax.ejb.Local;

import com.ozcorp.ejb.entity.Alumno;

@Local
public interface AlumnoFacadeLocal extends EntityFacade<Alumno> {
}
