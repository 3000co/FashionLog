package com.fashionlog.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dto.Item;

@Controller
public class PostController {
	@Autowired
	private ItemRepository itemRepository;
	
	@RequestMapping("/")
	public String startTest(Model model) {
		return "post/item";
	}
	
	@RequestMapping("/item")
	@ResponseBody
	public Item itemTest(Item item) {
		System.out.println(item);
		Item i = itemRepository.save(item);
		return i;
	}
}
