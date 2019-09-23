package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Style;

public interface StyleRepository extends JpaRepository<Style, Integer> {
	
	public Style findById(int styleNo);
	
}
