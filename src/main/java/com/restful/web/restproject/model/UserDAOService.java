package com.restful.web.restproject.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	private static List<User> users = new ArrayList<User>();
	
	private AtomicInteger userCount = new AtomicInteger(3);

	static {
		users.add(new User(1, "A", new Date()));
		users.add(new User(2, "B", new Date()));
		users.add(new User(3, "C", new Date()));

	}

	public List<User> reteriveAllUsers() {
		return users;
	}

	public User retervieUser(int id) {
		return users.stream().filter(u -> u.getId() == id).findAny().orElse(null);
	}
	
	public void saveUser(User user){
		user.setId(userCount.incrementAndGet());
		users.add(user);
	}
}
