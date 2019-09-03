package com.fashionlog.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dto.Category;
import com.fashionlog.model.dto.Item;
import com.fashionlog.model.dto.QBrand;
import com.fashionlog.model.dto.QCategory;
import com.fashionlog.model.dto.QItem;
import com.fashionlog.model.dto.QPost;
import com.fashionlog.model.dto.QStyle;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.AbstractSQLQuery;
import com.querydsl.jpa.JPQLQuery;

import antlr.StringUtils;

@Service
public class itemServiceImpl extends QuerydslRepositorySupport implements itemService{
	
	QItem item = QItem.item;
	QPost post = QPost.post;
	QCategory category = QCategory.category;
	QStyle style = QStyle.style;
	QBrand brand = QBrand.brand;
	
	public  itemServiceImpl(){
		super(Item.class);
	}
	

	
@Override
public List<Object> getSearchResult(List<String> tokenList) {
	List searchResult = new ArrayList<Object>();
	JPQLQuery searchQuery = from(item);
	searchQuery.select(item.color,post.postNo,post.contents); 
	
	
	setJoinConnection(tokenList, searchQuery);

	
	return searchResult;
	
	
}@Override
public List<Object> getItemPost(String itemName, String contents) {
	
	
	List itemPostList = new ArrayList<Object>();
	JPQLQuery jpqlQuery = from(item);
	jpqlQuery.leftJoin(item.postNo, post);
	jpqlQuery.select(item.color,post.postNo,post.contents); 
	jpqlQuery.where(ctItemName(itemName),
			ctContents(contents)
			);
	//eqCategoryNo(categoryNo)jpqlQuery.orderBy(item.itemNo.desc());
	 
    itemPostList = jpqlQuery.fetch();

	return itemPostList;
}

private JPQLQuery setJoinConnection(List<String> tokenList, JPQLQuery searchQuery){
	
	
	for (String token : tokenList) {
		switch(token) {
		
		case "":
		case "색상":
			searchQuery.leftJoin(item.postNo, post);
			break;
			
		case "카테고리":
			searchQuery.leftJoin(item.categoryNo, category);
			break;
			
		case "스타일":
			searchQuery.leftJoin(post.styleNo1, style );
			break;
			
		case "브랜드":
			searchQuery.leftJoin(item.categoryNo, category);
			break;
		
		}
	}
	return searchQuery;
}

private BooleanExpression ctItemName(String itemName) {
	if(org.springframework.util.StringUtils.isEmpty(itemName)) {
		return null;
	}
	return item.name.contains(itemName);
}

private BooleanExpression ctContents(String contents) {
	if(org.springframework.util.StringUtils.isEmpty(contents)) {
		return null;
	}
	return post.contents.contains(contents);
}

private BooleanExpression eqCategoryNo(String categoryNo) {
	if(org.springframework.util.StringUtils.isEmpty(categoryNo)) {
		return null;
	}
	return post.contents.eq(categoryNo);
}
private BooleanExpression eqBrandNo(String BrandNo) {
	if(org.springframework.util.StringUtils.isEmpty(BrandNo)) {
		return null;
	}
	return post.contents.eq(BrandNo);
}
private BooleanExpression eqColor(String color) {
	if(org.springframework.util.StringUtils.isEmpty(color)) {
		return null;
	}
	return post.contents.eq(color);
}
private BooleanExpression eqStyle(String StyleNo) {
	if(org.springframework.util.StringUtils.isEmpty(StyleNo)) {
		return null;
	}
	return post.contents.contains(StyleNo);
}
	
}
