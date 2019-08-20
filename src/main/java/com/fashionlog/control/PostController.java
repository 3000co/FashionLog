package com.fashionlog.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dto.Item;

@Controller
public class PostController {
	@Autowired
	ItemRepository itemrepository;
	
	@RequestMapping("/")
	public String startTest() {
		return "post/item";
	}
	
	@RequestMapping("item")
	public ResponseEntity<Item> itemTest(Item item) {
		return new ResponseEntity<Item>(itemrepository.save(item),HttpStatus.OK);
	}
}
