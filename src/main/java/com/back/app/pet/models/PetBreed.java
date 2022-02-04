package com.back.app.pet.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
public class PetBreed implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPetBreed;
	
	@NotEmpty
	private String type;
	private Boolean active;
	
	// Relation race - type (*-1)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pet_type")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private PetType petType;	
	
	public Long getIdPetBreed() {
		return idPetBreed;
	}

	public void setIdPetBreed(Long idPetBreed) {
		this.idPetBreed = idPetBreed;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}
	
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
