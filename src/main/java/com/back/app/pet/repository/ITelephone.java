package com.back.app.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.pet.models.Telephone;

@Repository
public interface ITelephone extends JpaRepository<Telephone, Long>{

}
