package com.back.app.pet.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pet implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPet;
	private String name;
	private Integer age;
	
	public Long getId() {
		return idPet;
	}
	public void setId(Long id) {
		this.idPet = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
