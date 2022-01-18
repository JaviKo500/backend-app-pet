package com.back.app.pet.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Fundation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idFundation;
	
	@Column(unique = true)
	private String ruc;
	
	@Column(unique = true)
	private String email;
	
	private String name;
	private String logo;
	private String image;
	private String direction;
	
	private Boolean active;
	
	// relation bidirectional 
	
	@JsonIgnoreProperties( value = {"fundation", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fundation", cascade = CascadeType.ALL)
	private List<Telephone> telephones;
	
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@PrePersist
	// save date creation user
	public void prePersistCreateAt() {
		this.createAt = new Date();
	}
	
	public Long getIdFundation() {
		return idFundation;
	}

	public void setIdFundation(Long idFundation) {
		this.idFundation = idFundation;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public List<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
