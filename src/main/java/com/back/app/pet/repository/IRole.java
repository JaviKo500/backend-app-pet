package com.back.app.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.pet.models.Role;

@Repository
public interface IRole extends JpaRepository<Role, Long>{
	public Role findByTypeAndActive(String type, Boolean active);
}
