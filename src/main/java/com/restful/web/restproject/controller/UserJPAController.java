package com.restful.web.restproject.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.restful.web.restproject.model.Post;
import com.restful.web.restproject.model.User;
import com.restful.web.restproject.model.dao.UserDAOService;
import com.restful.web.restproject.repository.PostRepository;
import com.restful.web.restproject.repository.UserRepository;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/jpa/users")
	public List<User> retrevieAllUsers() {
		List<User> users = userRepository.findAll();
		if (users == null || users.isEmpty())
			throw new EntityNotFoundException("No Users Found");
		else
			return users;
	}

	@GetMapping("/jpa/users/{id}")
	public User reteriveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new EntityNotFoundException("id -" + id);
		}
		return user.get();
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllPostByUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new EntityNotFoundException("id -" + id);
		}
		return user.get().getPosts();
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User usr) {
		User user = userRepository.save(usr);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

}
