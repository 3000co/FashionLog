package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	
}
