package com.fashionlog.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import com.fashionlog.model.dto.Item;
import com.fashionlog.model.dto.QBrand;
import com.fashionlog.model.dto.QCategory;
import com.fashionlog.model.dto.QItem;
import com.fashionlog.model.dto.QPost;
import com.fashionlog.model.dto.QStyle;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

@Service
public class ItemServiceImpl extends QuerydslRepositorySupport implements ItemService{
	
	QItem item = QItem.item;
	QPost post = QPost.post;
	QCategory category = QCategory.category;
	QStyle style = QStyle.style;
	QBrand brand = QBrand.brand;
	
	public  ItemServiceImpl(){
		super(Item.class);
	}
	

	
@Override
public Integer[] getSearchResult(List<String[]> searchTokenArrayList) {
	 List<Object> searchResult = new ArrayList<>();
	 Integer[] searchResultToController;
	
	JPQLQuery searchQuery = from(item);
	setJoinConnection(searchTokenArrayList, searchQuery);
	searchQuery.select(post.postNo); 
	setSearchCondition(searchTokenArrayList, searchQuery);
//	searchQuery.where(
//			ctCategoryName("하의")
//			);
//	searchQuery.where(
//			ctItemName("바지"),
//			ctItemName("회색")
//			);
//	searchQuery.where(
//			ctItemName("회색")
//			);

	System.err.println("여기가 문제");
	searchResult =  searchQuery.fetch();
	System.err.println(searchResult.toString());
	searchResultToController = searchResult.toArray(new Integer[0]);
	
	return searchResultToController;
	
	
}@Override
public List<Object> getItemPost(String itemName) {
	
	
	List itemPostList = new ArrayList<Object>();
	JPQLQuery jpqlQuery = from(item);
	jpqlQuery.leftJoin(item.postNo, post);
	jpqlQuery.leftJoin(item.categoryNo, category);
	jpqlQuery.select(item.name,post.postNo,post.contents); 
//	jpqlQuery.where(
//			eqCategoryNo(itemName)
//			);
	//eqCategoryNo(categoryNo)jpqlQuery.orderBy(item.itemNo.desc());
    itemPostList = jpqlQuery.fetch();
    
	return itemPostList;
}

//leftjoin절
private JPQLQuery setJoinConnection(List<String[]> searchTokenArrayList, JPQLQuery searchQuery){
	searchQuery.leftJoin(item.postNo, post);

	for (String[] temp : searchTokenArrayList) {
		String tokenType = temp[0];
		
		switch(tokenType) {
			
		case "카테고리":
			searchQuery.leftJoin(item.categoryNo, category);
			System.err.println("카테고리와 조인 생성");
			break;
			
		case "스타일":
			searchQuery.leftJoin(post.styleNo1, style );
			searchQuery.leftJoin(post.styleNo2, style );
			searchQuery.leftJoin(post.styleNo3, style );
			break;
			
		case "브랜드":
			searchQuery.leftJoin(item.brandNo, brand);
			break;
		
		default:
			break;
		}
	}
	return searchQuery;
}

//where절
private JPQLQuery setSearchCondition(List<String[]> searchTokenArrayList, JPQLQuery searchQuery){
	
	for (String[] temp : searchTokenArrayList) {
		String tokenType = temp[0];
		String tokenValue = temp[1];
		
		switch(tokenType) {
		
		case "통합검색":
			//post.contents 제외
			searchQuery.where(ctItemName(tokenValue).or(ctContents(tokenValue)));
			break;
			
		case "색상":
			searchQuery.where(eqColor(tokenValue));
			break;
			
		case "카테고리":
			searchQuery.where(ctCategoryName(tokenValue));
			System.err.println("해당하는 카테고리 포스트넘 가져옴");
			break;
			
		case "스타일":
			searchQuery.where(ctStyleName(tokenValue));
			break;
			
		case "브랜드":
			searchQuery.where(ctBrandName(tokenValue));
			break;
		default:
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

private BooleanExpression ctCategoryName(String categoryName) {
	if(org.springframework.util.StringUtils.isEmpty(categoryName)) {
		return null;
	}
	System.err.println("해당 카테고리 포함한 아이템 존재함");
	return category.name.contains(categoryName);
}
private BooleanExpression ctBrandName(String brandName) {
	if(org.springframework.util.StringUtils.isEmpty(brandName)) {
		return null;
	}
	return brand.name.contains(brandName);
}
private BooleanExpression eqColor(String color) {
	if(org.springframework.util.StringUtils.isEmpty(color)) {
		return null;
	}
	return post.contents.eq(color);
}
private BooleanExpression ctStyleName(String styleName) {
	if(org.springframework.util.StringUtils.isEmpty(styleName)) {
		return null;
	}
	return style.name.contains(styleName);
}
	
}
