package com.fashionlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.StyleRepository;
import com.fashionlog.model.dto.Category;
import com.fashionlog.model.dto.Item;
import com.fashionlog.model.dto.Style;

@Controller
public class PostController {
	@Autowired
	private ItemRepository itemRepository;	
	@Autowired
	private StyleRepository styleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BrandRepository brandRepository;
	
	@RequestMapping("/post")
	public String startTest(Model model) {
		//DB에서 목록을 가져와서 SelectBox에 이용
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		
		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		
		return "post/post";
	}
	
	@RequestMapping("/item")
	@ResponseBody
	public Item itemTest(Item item) {
//		System.out.println("뭐뭐뭐뭠지?");
		System.out.println(item);
//		System.out.println(item.getCategoryNo().getCategoryNo());
//		System.out.println("되?");
//		System.out.println(item.getBrandNo().getBrandNo());
		Item i = itemRepository.save(item);
		return i;
	}
}
