package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.dto.Item;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.service.itemService;

public interface ItemRepository extends JpaRepository<Item, Integer>, itemService{
	public List<Item> findByPostNoOrderByTagNoAsc(Post post);
	
	public Item countByItemNo(int itemNo);//?어디에 사용하지? pk로 레코드갯수 세어봤자 1갠데,,?
	
	public Item deleteByPostNo(int postNo);
	
	public Long countByBrandNo(Brand brand);
	
	
}
