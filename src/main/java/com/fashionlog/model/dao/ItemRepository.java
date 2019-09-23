package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.dto.Item;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.service.ItemService;


public interface ItemRepository extends JpaRepository<Item, Integer>, ItemService{
	public List<Item> findByPostNoOrderByTagNoAsc(Post post);

	public Item deleteByPostNo(Post postNo);
	
	public Long countByBrandNo(Brand brand);
	
	
}
