package com.fashionlog.model.service;

import java.util.List;


public interface ItemService {
	public List<Object> getItemPost(String itemName);
	public Integer[] getSearchResult(List<String[]> searchTokenArrayList);
}
