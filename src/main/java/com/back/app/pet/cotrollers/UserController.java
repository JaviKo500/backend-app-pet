package com.back.app.pet.cotrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.pet.models.User;
import com.back.app.pet.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> list() {
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PostMapping
	public User crate(@RequestBody User user) {
		return userService.Create(user);
	}
	
	@DeleteMapping("/{id}")
	public String  delete(@PathVariable Long id) {
		return userService.delete(id); 
	}
	
}
