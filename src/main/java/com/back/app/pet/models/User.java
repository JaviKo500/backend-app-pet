package com.back.app.pet.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idUser;
	
	@NotEmpty
	private String names;
	
	@Column(unique = true)
	@Email
	private String email;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	private String telephone;
	private Boolean active;
	
	// Relation user - role (*-1) 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_role")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Role role;
	
	// Relation user - foundation (1-1)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_foundation")
	private Foundation foundation;
	
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@PrePersist
	// save date creation user
	public void prePersistCreateAt() {
		this.createAt = new Date();
	}
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
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
