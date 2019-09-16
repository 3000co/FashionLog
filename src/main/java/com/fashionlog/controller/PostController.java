package com.fashionlog.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.fashionlog.model.service.PostService;

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

	@RequestMapping("/postWrite")
	public String startTest(Model model, HttpServletResponse response, HttpSession session) {
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();

//		Member member = memberRepository.findById("a").;
		

		Member user = (Member) session.getAttribute("member");
		Member member = memberRepository.findById(user.getId());

		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
//		model.addAttribute("member", member);
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
	public int postTest(Post post) {
		Post getPost = postRepository.save(post);
		// Member.posts가 자동으로 반영되지 않을 경우
//		Member writer = post.getMemberNo();
//		writer.getPosts().add(post);
//		memberRepository.save(writer);
		return getPost.getPostNo();
	}

	/**
	 * 3. postNo를 받아서 item만들고(view에서 작업함) 올리기
	 * 
	 * @param item
	 */
	@RequestMapping("/itemInsert")
	@ResponseBody
	public void itemTest(Item item) {
		itemRepository.save(item);
//		return "/feed";
	}

	@RequestMapping("/afterPostWrite")
	public String afterPostWrite() {

		return "feed";
	}


	@RequestMapping("/post")
	public String getPost(Model model, HttpSession session) {

		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();

		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		// postview 페이지가 생기면 바꿔줄것
		return "post/post";
	}

	@RequestMapping("/post/{postNo}")
	public String getPost(@PathVariable int postNo, Model model, HttpSession session) {
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
	public String getFeed(Model model, HttpSession session,
			@PageableDefault(sort = { "postNo" }, direction = Direction.DESC, size = 5) Pageable paging) {
		// 로그인한 사람 user
		Member user = (Member) session.getAttribute("member");
		if (user == null)
			return "redirect:/login";
		user = memberRepository.findById(user.getMemberNo()).get();
		// 가져오는 값들을 중복없이 저장하기 위해 set 생성
		Set<Post> feedSet = new HashSet<>();
		// 스타일 글 페이징해서 담기
		feedSet.addAll(postService.getFeedByStyle(user, paging));
		// 팔로이 글 페이징해서 담기
		feedSet.addAll(postService.getFeedByFollowee(user, paging));
		// 내글 페이징해서 담기
		feedSet.addAll(postService.getFeedByMe(user, paging));
		List<Post> feed = new ArrayList<Post>(feedSet);
		Collections.sort(feed);
		model.addAttribute("feed", feed);
		return "feed";
	}

}
