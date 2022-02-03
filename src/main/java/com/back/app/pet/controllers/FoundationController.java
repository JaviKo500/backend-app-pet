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

import com.back.app.pet.models.Foundation;
import com.back.app.pet.services.FoundationService;
import com.back.app.pet.utils.ResponseRequest;
import com.back.app.pet.validations.ReviewFields;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/foundation")
public class FoundationController {
	@Autowired
	FoundationService foundationService;
	@Autowired
	ReviewFields reviewFields;
	@Autowired
	ResponseRequest responseRequest;
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<Foundation> foundations= foundationService.findAll();
		if(foundations.isEmpty()) {
			return responseRequest.success(false, "Empty List", foundations, HttpStatus.OK);
		}
		return responseRequest.success(true, "Foundations list", foundations, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		Foundation foundation = null;
		try {
			foundation = foundationService.findById(Long.parseLong(id));
			if(foundation == null) 
				return responseRequest.success(false, "Foundation not existing", foundation, HttpStatus.OK);
			return responseRequest.success(true, "Foundation", foundation, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(true, "Foundation not found", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		Foundation foundation = null;
		try {
			foundation = foundationService.findByEmail(email);
			if(foundation == null) 
				return responseRequest.success(true, "Foundation not existing", foundation, HttpStatus.OK);
			return responseRequest.success(true, "Foundation", foundation.getEmail(), HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(true, "Foundation not found", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Foundation foundation, BindingResult result) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);
		}
		try {
			Foundation newFoundation = foundationService.create(foundation);
			return responseRequest.success(true, "Created foundation", newFoundation, HttpStatus.CREATED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error creating foundation", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Foundation foundation, BindingResult result, @PathVariable String id) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);
		}
		try {
			Foundation currentFoundation = foundationService.findById(Long.parseLong(id));
			currentFoundation = foundationService.changeData(currentFoundation, foundation);
			foundationService.create(currentFoundation);
			return responseRequest.success(true, "Updated foundation", currentFoundation, HttpStatus.CREATED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error updating foundation", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			foundationService.delete(Long.parseLong(id));
			return responseRequest.success(true, "Deleted foundation", "Success", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error deleting foundation", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
