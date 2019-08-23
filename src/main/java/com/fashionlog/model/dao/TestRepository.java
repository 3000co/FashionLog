package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fashionlog.model.dto.Comment;
import com.fashionlog.model.dto.Post;


public interface TestRepository extends JpaRepository<Comment, Integer> {
	@Query( value = "select comment_No, contents from Comment" , nativeQuery = true)
	public List<Object[]> test1();
}
