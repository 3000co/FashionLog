package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	public Item findByPost(int postNo);
	
	public Item countByItemNo(int itemNo);//?어디에 사용하지? pk로 레코드갯수 세어봤자 1갠데,,?
	
	public Item deleteByPost(int postNo);
	
	
	/*
	 * 검색용
	 * @Query(value = ) 
	 * Item findByAllQueryNative();
	 */
}
