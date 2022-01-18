package com.back.app.pet.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Type implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idType;
	private String petType;
	
	// Relation race - type (*-1) 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_race")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Race race;

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	public String getPetType() {
		return petType;
	}


	public void setPetType(String petType) {
		this.petType = petType;
	}

	
	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
