package com.fashionlog.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.FileRepository;
import com.fashionlog.model.dao.ItemRepository;
import com.fashionlog.model.dao.MemberRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dao.StyleRepository;
import com.fashionlog.model.dto.Category;
import com.fashionlog.model.dto.File;
import com.fashionlog.model.dto.Item;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.dto.Style;
import com.google.gson.Gson;

@Controller
public class PostController {
	@Autowired
	private ItemRepository itemRepository;	
	@Autowired
	private PostRepository postRepository;	
	@Autowired
	private StyleRepository styleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private MemberRepository memberRepository;

	@RequestMapping("/post")
	public String startTest(Model model, HttpServletResponse response) {
		//DB에서 목록을 가져와서 SelectBox에 이용
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		Member member = memberRepository.findById("a");
		
		System.out.println("르아ㅡ라ㅡㅇ라늘");
//		System.out.println(new Gson().toJson(member));
//		model.addAttribute("member", new Gson().toJson(member));
//		JSONObject jsonobject=new JSONObject();
//		jsonobject.put("error","Invalid username");
//		try {
//			response.getWriter().write(jsonobject.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		model.addAttribute("member", member);
		
		return "post/post";
	}

	@RequestMapping("/item")
	@ResponseBody
	public void itemTest(Item item, Post post) {
		Post p = postRepository.save(post);
		System.out.println(p);
//		System.out.println(item);
//		Item i = itemRepository.save(item);
//		return i;
	}

}
