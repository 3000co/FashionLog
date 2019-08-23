package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fashionlog.model.dto.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
//	@Query(value = "select name, category_no from category", nativeQuery = true)
//	public List<Object[]> findCategoryQuery();
	
//	@Query(value = "select category_no from fashionlog.category where name = ?name", nativeQuery = true)
//	public Category findbyNameQuery(@Param("name") String name);
}
