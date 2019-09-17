package com.fashionlog.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
import com.fashionlog.model.dao.CommentRepository;
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
import com.fashionlog.model.service.FileService;
import com.fashionlog.model.service.LikesService;
import com.fashionlog.model.service.PostService;
import com.fashionlog.security.SecurityUser;

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
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Autowired
	private LikesService likesService;

	@RequestMapping("/postWrite")
	public String startTest(Model model, HttpServletResponse response, @AuthenticationPrincipal SecurityUser securityUser) {
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		Member user = securityUser.getMember();		
		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		model.addAttribute("member", user);
		return "post/post";
	}

	/**
	 * 1. file 올리기
	 * 
	 * @param mulFile (파일)
	 * @param model
	 * @param request
	 * @return FileNo
	 * @throws Exception
	 */
	@RequestMapping("/fileInsert")
	@ResponseBody
	public int fileInsert(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception {
		File file = fileService.insertFile(mulFile, model, request);
		return file.getFileNo();
	}

	/**
	 * 2. fileNo를 받아서 post 올리기
	 * 
	 * @param post
	 * @return postNo
	 */
	@RequestMapping("/postInsert")
	@ResponseBody
	public int postInsert(Post post) {
		Post getPost = postRepository.save(post);
		return getPost.getPostNo();
	}

	/**
	 * 3. postNo를 받아서 item만들고(view에서 작업함) 올리기
	 * 
	 * @param item
	 */
	@RequestMapping("/itemInsert")
	@ResponseBody
	public void itemInsert(Item item) {
		itemRepository.save(item);
	}

	@RequestMapping("/afterPostWrite")
	public String afterPostWrite() {

		return "feed";
	}

	@RequestMapping("/post/{postNo}")
	public String getPost(@PathVariable int postNo, Model model) {
		Post post = postRepository.findById(postNo).get();
		model.addAttribute("post", post);
		model.addAttribute("itemList", itemRepository.findByPostNoOrderByTagNoAsc(post));
		model.addAttribute("commentList", commentRepository.findByPostNo(post));
		return "view";
	}

	@RequestMapping("/allFeed")
	public String getPost(Model model,
			@PageableDefault(sort = { "postNo" }, direction = Direction.DESC, size = 30) Pageable paging) {
		List<Post> allFeed = new ArrayList<>(postService.getAllFeed(paging));
		Collections.sort(allFeed);
		model.addAttribute("feed", allFeed);
		return "feed";
	}

	@RequestMapping("/feed")
	public String getFeed(Model model, @AuthenticationPrincipal SecurityUser securityUser,
			@PageableDefault(sort = { "postNo" }, direction = Direction.DESC, size = 5) Pageable paging) {
		// 로그인한 사람 user
		Member user = securityUser.getMember();		
		if (user == null)
			return "redirect:/login";
		user = memberRepository.findById(user.getMemberNo()).get();
		List<Post> feed = postService.getPostToFeed(user,paging);
		for(Post post:feed) {
			likesService.countLikes(post);
		}
		model.addAttribute("feed", feed);
		return "feed";
	}
	
	@RequestMapping(value = "/getMoreFeed", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMoreFeed(Pageable paging, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember();
		Map<String, Object> newFeed = new HashMap<>();
		List<Post> feedList = postService.getPostToFeed(user,paging);
		for(Post post :feedList) {
			Map<String, Object> feedVo = new HashMap<>();
			feedVo.put("postNo", post.getPostNo());
			feedVo.put("postImageNo", post.getPostImageNo().getPath());
			feedVo.put("uploadTime", post.getUploadTime());
			feedVo.put("uploader", post.getMemberNo().getNickname());
			likesService.countLikes(post);
			feedVo.put("likesCount", post.getLikesCount());
			newFeed.put(post.getPostNo()+"", feedVo);
		}
		return newFeed;
	}

}