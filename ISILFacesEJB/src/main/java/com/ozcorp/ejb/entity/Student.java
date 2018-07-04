package com.ozcorp.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*, uniqueConstraints = {
@UniqueConstraint(columnNames = { "DNI" })
}*/
@Entity
@Table(name = "Student")
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Sex")
	private String sex;
	
	@Column(name = "Age")
	private Integer age;
	
	@Column(name = "ProfilePicture")
	private String profilePicture;
	
	@Column(name = "DNI")
	private String DNI;

	public Student() {
	}
	
	public Student(Integer id, String nombre, String apellido, String sexo, Integer age, String profilePicture, String dNI) {
		this.id = id;
		this.name = nombre;
		this.lastName = apellido;
		this.sex = sexo;
		this.age = age;
		this.profilePicture = profilePicture;
		DNI = dNI;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public String getApellido() {
		return lastName;
	}

	public void setApellido(String apellido) {
		this.lastName = apellido;
	}

	public String getSexo() {
		return sex;
	}

	public void setSexo(String sexo) {
		this.sex = sexo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

}
