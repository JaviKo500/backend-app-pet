package com.back.app.pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.app.pet.models.Foundation;
import com.back.app.pet.repository.IFoundation;

@Service
public class FoundationService {
	
	@Autowired
	IFoundation foundationRepo;
	
	@Transactional
	public Foundation create(Foundation foundation ) {
		return foundationRepo.save(foundation);
	}
	
	@Transactional(readOnly = true)
	public Foundation findById(Long id) {
		return foundationRepo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Foundation findByEmail(String email) {
		return foundationRepo.findByEmailAndActive(email, true);
	}
	
	@Transactional(readOnly = true)
	public List<Foundation> findAll() {
		return foundationRepo.findAll();
	}
	
	@Transactional
	public void delete(Long id) {
		foundationRepo.deleteById(id);
	}
	
	public Foundation changeData(Foundation currentFoundation, Foundation foundation) {
		currentFoundation.setRuc(foundation.getRuc());
		currentFoundation.setEmail(foundation.getEmail());
		currentFoundation.setName(foundation.getName());
		currentFoundation.setDirection(foundation.getDirection());
		currentFoundation.setTelephones(foundation.getTelephones());
		return currentFoundation;
	}
}
