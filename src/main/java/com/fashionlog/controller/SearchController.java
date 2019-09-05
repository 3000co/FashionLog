package com.fashionlog.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dao.StyleRepository;
import com.fashionlog.model.dto.Category;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.dto.Style;

@Controller
public class SearchController {

	@Autowired
	StyleRepository styleRepository;

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	PostRepository postRepository;

	@RequestMapping("/test")
	public String startTest(Model model) {
		// DB에서 목록을 가져와서 SelectBox에 이용
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		

		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		return "view";
	}

	@RequestMapping("/feed/searched")
	public String searchProcess(HttpServletRequest request, Model model) {
		List<String> searchTokenList = new ArrayList<>();
		List<String[]> searchTokenArrayList = new ArrayList<>();

		splitWordsByAmp(request.getParameter("searchWords"), searchTokenList);
		splitWordsByColon(searchTokenList, searchTokenArrayList);
		
		
//		  for (int i = 0; i <= searchTokenArrayList.size() - 1; i++) { String[] temp =
//		  searchTokenArrayList.get(i); for (String t : temp) { System.out.println(t); }
//		  }

		System.err.println(itemRepository.getSearchResult(searchTokenArrayList));
		Integer[] searchedResultNoList = itemRepository.getSearchResult(searchTokenArrayList);
		
		//검색된 item을 가진 post 가지고 오는 파트
		List<Post> searchPostList = new ArrayList<Post>();
		Post searchedPost;

		
		for(int postNo : searchedResultNoList) {
			searchedPost = postRepository.findById(postNo).get();
			searchPostList.add(searchedPost);
		}
		
		model.addAttribute("searchedPost", searchPostList);
		
	
		return "searchedFeed";
	}

	private List<String> splitWordsByAmp(String searchWords, List<String> searchTokenList) {
		String[] searchTokenArray;
		searchTokenArray = searchWords.split(" ");

		
		 for(String temp:searchTokenArray) {
			 searchTokenList.add(temp); }
		 
		return searchTokenList;
	}

	private List<String[]> splitWordsByColon(List<String> searchTokenList, List<String[]> searchTokenArrayList) {

		for (String temp : searchTokenList) {
			String[] searchToken = new String[2];
			searchToken = temp.split(":");
			// ':'없는 통합검색일경우 토큰 형태 조정
			  if(searchToken.length == 1) {
				  String[] tempToken = new String[2];
				  tempToken[0] = "통합검색";
				  tempToken[1] = searchToken[0];
				  searchToken = tempToken;
			  }
			searchTokenArrayList.add(searchToken);
		
		}
		return searchTokenArrayList;
	}

}

