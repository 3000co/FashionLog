package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	public Item findByPostNo(int postNo);
	
	public Item countByItemNo(int itemNo);//?어디에 사용하지? pk로 레코드갯수 세어봤자 1갠데,,?
	
	public Item deleteByPostNo(int postNo);
	
	public Long countByBrandNo(Brand brand);
	/*
	 * 검색용
	 * @Query(value = ) 
	 * Item findByAllQueryNative();
	 */
}
