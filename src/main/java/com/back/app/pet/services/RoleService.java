package com.back.app.pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.app.pet.models.Role;
import com.back.app.pet.repository.IRole;

@Service
public class RoleService {
	@Autowired
	IRole roleRepo;
	
	@Transactional
	public Role create(Role role) {
		return roleRepo.save(role);
	}
	
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		return roleRepo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return roleRepo.findAll();
	}
	
	@Transactional
	public void delete(Long id) {
		roleRepo.deleteById(id);
	}
	
	public Role changeData(Role currentRole, Role role) {
		currentRole.setType(role.getType());
		currentRole.setDescription(role.getDescription());
		return currentRole;
	}
}
