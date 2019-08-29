package com.fashionlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.StyleRepository;
import com.fashionlog.model.dto.Category;
import com.fashionlog.model.dto.Style;



@Controller
public class TestController {
	@Autowired
	private StyleRepository styleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BrandRepository brandRepository;
	
	//로그인 할때의 컨트롤러에서 다음과 같이 동작함.
	@RequestMapping("/test")
	public String startTest(Model model) {
		//DB에서 목록을 가져와서 SelectBox에 이용
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		
		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		return "view";
	}
}
