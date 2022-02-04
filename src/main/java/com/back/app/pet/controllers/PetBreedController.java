package com.back.app.pet.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.pet.models.PetBreed;
import com.back.app.pet.services.PetBreedService;
import com.back.app.pet.utils.ResponseRequest;
import com.back.app.pet.validations.ReviewFields;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/petbreed")
public class PetBreedController {
	
	@Autowired
	PetBreedService petBreedService;
	@Autowired
	ResponseRequest responseRequest;
	@Autowired
	ReviewFields reviewFields;
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<PetBreed> petBreeds = petBreedService.findAll();
		if(petBreeds.isEmpty()) {
			return responseRequest.success(false,"Empty list", petBreeds, HttpStatus.OK);
		}
		return responseRequest.success(true,"List Pet Breeds", petBreeds, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		PetBreed petBreed = null;
		try {
			petBreed = petBreedService.findById(Long.parseLong(id));
			if(petBreed == null) {
				return responseRequest.success(false,"Pet Breed not existing", petBreed, HttpStatus.OK);
			}
			return responseRequest.success(true,"Pet Breed", petBreed, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(false,"Pet Breed not found", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PetBreed petBreed, BindingResult result){
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);			
		}
		try {
			PetBreed newPetBreed = petBreedService.create(petBreed);
			return responseRequest.success(true,"Created Pet breed", newPetBreed, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(false,"Error creating Pet breed", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PetBreed petBreed, BindingResult result, @PathVariable String id) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);			
		}
		try {
			PetBreed currentPetBreed = petBreedService.findById(Long.parseLong(id));
			currentPetBreed.setType(petBreed.getType());
			currentPetBreed.setPetType(petBreed.getPetType());
			petBreedService.create(currentPetBreed);
			return responseRequest.success(true,"Updated Pet breed", currentPetBreed, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(false,"Error updating Pet breed", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			petBreedService.delete(Long.parseLong(id));
			return responseRequest.success(true,"Deleted Pet breed", "Success", HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(false,"Error deleting Pet breed", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
