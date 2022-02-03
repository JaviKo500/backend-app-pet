package com.back.app.pet.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Telephone implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTelephone;
	
	@Column(unique = true)
	private String number;
	
	@JsonIgnoreProperties( value = {"telephones", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_foundation")
	private Foundation foundation;
	
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
	
	public Foundation getFoundation() {
		return foundation;
	}

	public void setFoundation(Foundation foundation) {
		this.foundation = foundation;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
