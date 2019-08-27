package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fashionlog.model.dto.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	@Query(value = "select brand_no, name from brand", nativeQuery = true)
	public List<Object[]> findBrandQuery();
	
//	public List<Brand> findAllOrderByItemCountAsc();
	
}
