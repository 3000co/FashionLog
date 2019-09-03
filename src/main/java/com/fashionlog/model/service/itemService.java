package com.fashionlog.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fashionlog.model.dto.Item;


public interface itemService {
	public List<Object> getItemPost(String itemName, String contents);
	public List<Object> getSearchResult(List<String> tokenList);
}
