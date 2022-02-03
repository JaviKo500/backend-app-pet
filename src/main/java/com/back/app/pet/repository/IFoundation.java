package com.back.app.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.pet.models.Foundation;

@Repository
public interface IFoundation extends JpaRepository<Foundation, Long>{
	
	public Foundation findByEmailAndActive(String email, Boolean active);
}
