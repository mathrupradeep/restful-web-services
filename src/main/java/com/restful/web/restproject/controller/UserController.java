package com.restful.web.restproject.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.restful.web.restproject.exceptions.EntityNotFoundException;
import com.restful.web.restproject.model.User;
import com.restful.web.restproject.model.dao.UserDAOService;

@RestController
public class UserController {

	@Autowired
	private UserDAOService daoService;

	@GetMapping("/users")
	public List<User> retrevieAllUsers() {
		List<User> users = daoService.reteriveAllUsers();
		if (users == null || users.isEmpty())
			throw new EntityNotFoundException("No Users Found");
		else
			return users;
	}

	@GetMapping("/users/{id}")
	public User reteriveUser(@PathVariable int id) {
		User user = daoService.retervieUser(id);
		if (user == null) {
			throw new EntityNotFoundException("id -" + id);
		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User usr) {
		User user = daoService.saveUser(usr);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = daoService.deleteUser(id);
		if (user == null) {
			throw new EntityNotFoundException("User Id - " + id + " not found");
		}
	}

}
