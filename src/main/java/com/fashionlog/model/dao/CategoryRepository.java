package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fashionlog.model.dto.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
