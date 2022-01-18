package com.back.app.pet.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Telephone implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idTelephone;
	
	@Column(unique = true)
	private String number;
	
	public Long getIdTelephone() {
		return idTelephone;
	}

	public void setIdTelephone(Long idTelephone) {
		this.idTelephone = idTelephone;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
