package com.back.app.pet.cotrollers;

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

import com.back.app.pet.models.User;
import com.back.app.pet.services.UserService;
import com.back.app.pet.utils.ResponseRequest;
import com.back.app.pet.validations.ReviewFields;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	ReviewFields reviewFields;
	@Autowired
	ResponseRequest responseRequest;
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<User> users= userService.findAll();
		if(users.isEmpty()) {
			System.out.println("asa");
			return responseRequest.success(false, "Empty List", users, HttpStatus.OK);
		}
		return responseRequest.success(true, "Users list", users, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		User user = null;
		try {
			user = userService.findById(Long.parseLong(id));
			if(user == null) 
				return responseRequest.success(true, "User not existing", user, HttpStatus.OK);
			user.setPassword("");
			return responseRequest.success(true, "User", user, HttpStatus.OK);
		} catch (Exception e) {
			return responseRequest.error(true, "User not found", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);
		}
		try {
			User newUser = userService.Create(user);
			newUser.setPassword("");
			return responseRequest.success(true, "Created user", newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error creating user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable String id) {
		if (result.hasErrors()) {
			return responseRequest.error(false, "Invalid fields", reviewFields.reviewFields(result), HttpStatus.BAD_REQUEST);
		}
		try {
			User currentUser = userService.findById(Long.parseLong(id));
			currentUser = userService.changeData(currentUser, user);
			userService.Create(currentUser);
			currentUser.setPassword("");
			return responseRequest.success(true, "Updated user", currentUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error updating user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  delete(@PathVariable Long id) {
		try {
			userService.delete(id);
			return responseRequest.success(true, "Deleted user", "Success", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return responseRequest.error(false, "Error deleting user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
