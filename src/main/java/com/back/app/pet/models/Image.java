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
public class Image implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImg;
	private String image;
	
	// Relation pet - image (1-1)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pet")
	private Pet pet;
	
	// TODO: relation bidirectional with pet
	
	public Long getIdImg() {
		return idImg;
	}

	public void setIdImg(Long idImg) {
		this.idImg = idImg;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
