package com.fashionlog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dao.StyleRepository;
import com.fashionlog.model.dto.Brand;
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
		List<Brand> brand = brandRepository.findAll();
		System.out.println("brand:"+ brand);
		return "/";
	}
	
	@ResponseBody
	@RequestMapping("/getAttrList/style")
	private ModelAndView getStyleList(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		List<Style> styleList = styleRepository.findAll();
		modelAndView.addObject("attrList", styleList);
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/getAttrList/brand")
	private ModelAndView getBrandList(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		List<Brand> brandList = brandRepository.findAll();
		modelAndView.addObject("attrList", brandList);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/getAttrList/category")
	private ModelAndView getCategoryList(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		List<Category> categoryList = categoryRepository.findAll();
		modelAndView.addObject("attrList", categoryList);
		
		return modelAndView;
	}
	

	@RequestMapping("/searched")
	public String searchProcess(HttpServletRequest request, Model model) {
		List<String> searchTokenList = new ArrayList<>();
		List<String[]> searchTokenArrayList = new ArrayList<>();

		splitWordsByAmp(request.getParameter("searchWords"), searchTokenList);
		splitWordsByColon(searchTokenList, searchTokenArrayList);
		
		
//		  for (int i = 0; i <= searchTokenArrayList.size() - 1; i++) { String[] temp =
//		  searchTokenArrayList.get(i); for (String t : temp) { System.out.println(t); }
//		  }

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

