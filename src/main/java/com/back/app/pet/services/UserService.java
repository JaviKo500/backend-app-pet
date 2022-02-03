package com.back.app.pet.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.app.pet.models.User;
import com.back.app.pet.repository.IUser;

@Service
public class UserService {
	
	@Autowired
	IUser userRepo;

	@Transactional
	public User create(User user ) {
		return userRepo.save(user);
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return userRepo.findByEmailAndActive(email, true);
	}
	
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepo.findAll().stream().map(user -> {
			user.setPassword("");
			return user;
		}).toList();
	}
	
	@Transactional
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
	
	public User changeData(User currentUser, User user) {
		currentUser.setEmail(user.getEmail());
		currentUser.setNames(user.getNames());
		currentUser.setUserName(user.getUserName());
		currentUser.setTelephone(user.getTelephone());
		if(!user.getPassword().isBlank()) {
			currentUser.setPassword(user.getPassword());
		}
		return currentUser;
	}
}
