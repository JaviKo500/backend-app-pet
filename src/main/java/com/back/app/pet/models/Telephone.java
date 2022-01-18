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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idTelephone;
	
	@Column(unique = true)
	private String number;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fundation")
	private Fundation fundation;
	
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
	
	public Fundation getFundation() {
		return fundation;
	}

	public void setFundation(Fundation fundation) {
		this.fundation = fundation;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
