package com.fashionlog.model.dao;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.fashionlog.model.dto.Brand;
import com.fashionlog.model.dto.Item;
import com.fashionlog.model.service.ItemService;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;

public interface ItemRepository extends JpaRepository<Item, Integer>, ItemService{
	public Item findByPostNo(int postNo);
	
	public Item countByItemNo(int itemNo);//?어디에 사용하지? pk로 레코드갯수 세어봤자 1갠데,,?
	
	public Item deleteByPostNo(int postNo);
	
	public Long countByBrandNo(Brand brand);
	
	
}
