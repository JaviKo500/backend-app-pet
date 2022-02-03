package com.back.app.pet.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.app.pet.models.Telephone;
import com.back.app.pet.repository.ITelephone;

@Service
public class TelephoneService {
	@Autowired
	ITelephone telephoneRepo;
	
	@Transactional
	public void create(Telephone telephone) {
		telephoneRepo.save(telephone);
	}
	
	@Transactional
	public void delete(Long id) {
		telephoneRepo.deleteById(id);
	}
}
