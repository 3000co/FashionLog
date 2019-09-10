package com.fashionlog.model.service;

import java.util.List;


public interface itemService {
	public List<Object> getItemPost(String itemName);
	public Integer[] getSearchResult(List<String[]> searchTokenArrayList);
}
