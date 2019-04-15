package com.restful.web.restproject.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restful.web.restproject.model.User;
import com.restful.web.restproject.model.UserDAOService;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService daoService;

	@GetMapping("/users")
	public List<User> retrevieAllUsers() {
		return daoService.reteriveAllUsers();
	}

	@GetMapping("/users/{id}")
	public User reteriveUser(@PathVariable int id) {
		return daoService.retervieUser(id);

	}
	
	@PostMapping("/users")
	public void addUser(@Valid @RequestBody User usr){
		daoService.saveUser(usr);
		
	}

}
