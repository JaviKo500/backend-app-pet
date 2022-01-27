package com.back.app.pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.app.pet.models.PetType;
import com.back.app.pet.repository.IPetType;

@Service
public class PetTypeService {
	
	@Autowired
	IPetType petTypeRepo;
	
	//vamos a hacer un cambio a la bd 
	@Transactional
	public PetType create(PetType petType) {
		return petTypeRepo.save(petType);
	}
	
	@Transactional(readOnly = true) 
	public PetType findById(Long id) {
		return petTypeRepo.findById(id).orElse(null);		
	}
	
	@Transactional(readOnly = true)
	public List<PetType> findAll() {
		return petTypeRepo.findAll();		
	}
	
	@Transactional
	public void delete(Long id) {
		petTypeRepo.deleteById(id);
	}
	
	

}
