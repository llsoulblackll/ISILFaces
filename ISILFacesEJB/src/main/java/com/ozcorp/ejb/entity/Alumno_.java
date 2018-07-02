package com.ozcorp.ejb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-01T15:46:59.021-0500")
@StaticMetamodel(Alumno.class)
public class Alumno_ {
	public static volatile SingularAttribute<Alumno, Integer> id;
	public static volatile SingularAttribute<Alumno, String> nombre;
	public static volatile SingularAttribute<Alumno, String> apellido;
	public static volatile SingularAttribute<Alumno, String> sexo;
	public static volatile SingularAttribute<Alumno, Integer> age;
	public static volatile SingularAttribute<Alumno, String> DNI;
}
