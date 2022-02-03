package com.back.app.pet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.pet.models.Foundation;
import com.back.app.pet.models.Telephone;
import com.back.app.pet.services.FoundationService;
import com.back.app.pet.services.TelephoneService;
import com.back.app.pet.utils.ResponseRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/phone")
public class TelephoneController {
	
	@Autowired
	TelephoneService telephoneService;
	@Autowired
	FoundationService foundationService;
	@Autowired
	ResponseRequest responseRequest;
	
	@PostMapping("/{idFound}")
	public ResponseEntity<?> create(@PathVariable String idFound, @RequestBody List<Telephone> telephones) {
		try {
			Foundation foundation = foundationService.findById(Long.parseLong(idFound));
			if(foundation == null) {
				return responseRequest.error(false, "Foundation not existing", "Review data", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			telephones.forEach(phone -> {
				phone.setFoundation(foundation);
				telephoneService.create(phone);
			});
			return responseRequest.success(true, "Ok", "Success", HttpStatus.CREATED);
		} catch (Exception e) {
			foundationService.delete(Long.parseLong(idFound));
			return responseRequest.error(false, "Error in foundation", "Review fields", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
