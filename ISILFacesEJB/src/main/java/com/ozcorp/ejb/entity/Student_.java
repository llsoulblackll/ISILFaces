package com.ozcorp.ejb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-03T02:23:38.842-0500")
@StaticMetamodel(Student.class)
public class Student_ {
	public static volatile SingularAttribute<Student, Integer> id;
	public static volatile SingularAttribute<Student, String> name;
	public static volatile SingularAttribute<Student, String> lastName;
	public static volatile SingularAttribute<Student, String> sex;
	public static volatile SingularAttribute<Student, Integer> age;
	public static volatile SingularAttribute<Student, String> profilePicture;
	public static volatile SingularAttribute<Student, String> DNI;
}
