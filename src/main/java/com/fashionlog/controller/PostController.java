package com.fashionlog.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dao.BrandRepository;
import com.fashionlog.model.dao.CategoryRepository;
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
	private PostService postService;
	
	
	@RequestMapping("/postWrite")
	public String startTest(Model model, HttpServletResponse response) {
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		Member member = memberRepository.findById("a");
		
		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		model.addAttribute("member", member);
		return "post/post";
	}

	/**
	 * 1. file 올리기
	 * @param mulFile (파일)
	 * @param model
	 * @param request
	 * @return FileNo
	 * @throws Exception
	 */
	@RequestMapping("/fileInsert")
	@ResponseBody
	public int fileInsert(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception {
		File file = postService.insertFile(mulFile, model, request);
		return file.getFileNo();
	}
	
	/**
	 * 2. fileNo를 받아서 post 올리기
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
	 * @param item
	 */
	@RequestMapping("/itemInsert")
	@ResponseBody
	public void itemTest(Item item) {
		Item getItem = itemRepository.save(item);
//		return i;
	}
	
	@RequestMapping("/feed")
	public String getPost(Model model, HttpSession session, @PageableDefault(sort = { "postNo" }, direction = Direction.DESC, size = 5)Pageable paging) {
		//로그인한 사람 user
		Member user = (Member) session.getAttribute("member");
		user = memberRepository.findById(user.getMemberNo()).get();
		//가져오는 값들을 저장하기 위한 map 생성, 중복되는 것은 map에 담으면서 덮어씌워짐
		Map<Integer,Post> feed = new HashMap<>();
		//팔로이 글 페이징해서 map에 담기
		feed.putAll(postService.getFeedByFollowee(user, paging));
		//내글 페이징해서 map에 담기 
		feed.putAll(postService.getFeedByMe(user, paging));
		//스타일 글 페이징해서 map에 담기
		feed.putAll(postService.getFeedByStyle(user, paging));
		model.addAttribute("feed",feed);
		return "feed";
	}
	
}
