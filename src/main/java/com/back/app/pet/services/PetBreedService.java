package com.back.app.pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.app.pet.models.PetBreed;
import com.back.app.pet.repository.IPetBreed;

@Service
public class PetBreedService {
	
	@Autowired
	IPetBreed petBreedRepo;
	
	@Transactional
	public PetBreed create(PetBreed petBreed ) {
		return petBreedRepo.save(petBreed);
	}
	
	@Transactional(readOnly = true)
	public PetBreed findById(Long id) {
		return petBreedRepo.findById(id).orElse(null);
	}
	
	
	@Transactional(readOnly = true)
	public List<PetBreed> findAll() {
		return petBreedRepo.findAll();
	}
	
	@Transactional
	public void delete(Long id) {
		petBreedRepo.deleteById(id);
	}
}
