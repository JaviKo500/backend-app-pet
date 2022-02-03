package com.back.app.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.pet.models.User;

@Repository
public interface IUser extends JpaRepository<User, Long>{
	public User findByEmailAndActive(String email, Boolean active);
}
