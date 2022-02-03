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

import com.back.app.pet.models.Role;
import com.back.app.pet.services.RoleService;
import com.back.app.pet.utils.ResponseRequest;
import com.back.app.pet.validations.ReviewFields;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	@Autowired
	ReviewFields reviewFields;
	@Autowired
	ResponseRequest responseRequest;
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<Role> roles= roleService.findAll();
		if(roles.isEmpty()) {
			return responseRequest.success(false, "Empty List", roles, HttpStatus.OK);
		}
		return responseRequest.success(true, "Roles list", roles, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		Role role = null;
		try {
			role = roleService.findById(Long.parseLong(id));
			if(role == null) 
				return responseRequest.success(true, "Role not existing", role, HttpStatus.OK);
			return responseRequest.success(true, "Role", role, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(true, "Role not found", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Role role, BindingResult result) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);
		}
		try {
			Role newRole = roleService.create(role);
			return responseRequest.success(true, "Created role", newRole, HttpStatus.CREATED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error creating role", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Role role, BindingResult result, @PathVariable String id) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);
		}
		try {
			Role currentRole = roleService.findById(Long.parseLong(id));
			currentRole = roleService.changeData(currentRole, role);
			roleService.create(currentRole);
			return responseRequest.success(true, "Updated role", currentRole, HttpStatus.CREATED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error updating role", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  delete(@PathVariable Long id) {
		try {
			roleService.delete(id);
			return responseRequest.success(true, "Deleted role", "Success", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error deleting role", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
