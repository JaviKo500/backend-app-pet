package com.back.app.pet.validations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ReviewFields {
	public List<String> reviewFields(BindingResult result) {
		return result.getFieldErrors().stream()
						.map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
						.collect(Collectors.toList());
	}
	
}
