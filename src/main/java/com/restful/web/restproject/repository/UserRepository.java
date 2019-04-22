package com.restful.web.restproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restful.web.restproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
