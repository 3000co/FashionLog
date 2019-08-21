package com.fashionlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dto.Category;
import com.fashionlog.model.dto.Item;

@Controller
public class PostController {
	@Autowired
	private ItemRepository itemRepository;	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@RequestMapping("/")
	public String startTest(Model model) {
		List<String> categoryName = categoryRepository.findCategoryQuery();
		model.addAttribute("categoryName", categoryName);
//		Category categoryNo = categoryRepository.findbyNameQuery("모자");
//		model.addAttribute("categoryNo", categoryNo);
		return "post/item";
	}
	
	@RequestMapping("/item")
	@ResponseBody
	public void itemTest(Item item) {
		System.out.println(item.getCategoryNo());
		//Item i = itemRepository.save(item);
		//return i;
	}
}
