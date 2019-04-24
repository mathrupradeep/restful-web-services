package com.restful.web.restproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restful.web.restproject.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
