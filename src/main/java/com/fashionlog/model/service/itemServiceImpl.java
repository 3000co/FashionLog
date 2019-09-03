package com.fashionlog.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dto.Item;
import com.fashionlog.model.dto.QItem;
import com.fashionlog.model.dto.QPost;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.AbstractSQLQuery;
import com.querydsl.jpa.JPQLQuery;

@Service
public class itemServiceImpl extends QuerydslRepositorySupport implements itemService{
	
	
	
	public  itemServiceImpl(){
		super(Item.class);
	}
	

	
@Override
public List<Item> doSearch() {
	// TODO Auto-generated method stub
	return null;
	
	
}@Override
public List<Object> getItemPost() {
	List itemPostList = new ArrayList<Object>();
	QItem item = QItem.item;
	QPost post = QPost.post;
	
	JPQLQuery jpqlQuery = from(item);
	jpqlQuery.leftJoin(item.postNo, post);
	jpqlQuery.select(item.color,post.postNo,post.contents);
	
	
	jpqlQuery.orderBy(item.itemNo.desc());
	 
    itemPostList = jpqlQuery.fetch();
   
   
	
	
	
	return itemPostList;
}
}
