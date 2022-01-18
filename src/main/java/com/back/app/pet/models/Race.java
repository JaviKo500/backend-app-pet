package com.back.app.pet.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Race implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idRace;
	private String name;
	
	// Relation pet - race (1-1)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pet")
	private Pet pet;
	
	
	public Long getIdBreed() {
		return idRace;
	}

	public void setIdBreed(Long idBreed) {
		this.idRace = idBreed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Long getIdRace() {
		return idRace;
	}

	public void setIdRace(Long idRace) {
		this.idRace = idRace;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
