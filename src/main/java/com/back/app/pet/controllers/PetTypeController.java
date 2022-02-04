package com.back.app.pet.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.pet.models.PetType;
import com.back.app.pet.services.PetTypeService;
import com.back.app.pet.utils.ResponseRequest;
import com.back.app.pet.validations.ReviewFields;

@RestController
@RequestMapping("api/v1/pettype")

public class PetTypeController {

	@Autowired
	PetTypeService petTypeService;
	@Autowired
	ResponseRequest responseRequest;
	@Autowired
	ReviewFields reviewFields;
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<PetType> petTypes = petTypeService.findAll();
		if(petTypes.isEmpty()) {
			return responseRequest.success(false,"Empty list", petTypes, HttpStatus.OK);
		}
		return responseRequest.success(true,"List Pet Types", petTypes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		PetType petType = null;
		try {
			petType = petTypeService.findById(Long.parseLong(id));
			if(petType == null) {
				return responseRequest.success(false,"Pet Type not existing", petType, HttpStatus.OK);
			}
			return responseRequest.success(true,"Pet Type", petType, HttpStatus.OK);		
		} catch (Exception e) {
			return responseRequest.error(false,"Pet type not found", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PetType petType, BindingResult result){
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);			
		}
		try {
			PetType newPetType = petTypeService.create(petType);
			return responseRequest.success(true,"Created Pet Type", newPetType, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(false,"Error creating Pet type", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PetType petType, BindingResult result, @PathVariable String id) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);			
		}
		try {
			PetType currentPetType = petTypeService.findById(Long.parseLong(id));
			currentPetType.setType(petType.getType());
			petTypeService.create(currentPetType);
			return responseRequest.success(true,"Updated Pet Type", currentPetType, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(false,"Error updating Pet type", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			petTypeService.delete(Long.parseLong(id));
			return responseRequest.success(true,"Deleted Pet Type", "Success", HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(false,"Error deleting Pet type", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
