package com.fashionlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.StyleRepository;

@Controller
public class TestController {

	@Autowired
	StyleRepository styleRepository;

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@RequestMapping("/aa")
	public String aa() {
		System.out.println("sssssss");
		return "test";
	}

}

