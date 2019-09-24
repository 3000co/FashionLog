package com.fashionlog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.fashionlog.model.dao.FollowRepository;
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
	private FollowRepository followRepository;
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Autowired
	private LikesService likesService;

	@RequestMapping("/post/{postNo}")
	public String getPost(@PathVariable int postNo, Model model) {
		Post post = postRepository.findById(postNo).get();
		model.addAttribute("post", post);
		model.addAttribute("itemList", itemRepository.findByPostNoOrderByTagNoAsc(post));
		model.addAttribute("commentList", commentRepository.findByPostNo(post));
		return "view";
	}

	@RequestMapping("/postWrite")
	public String startTest(Model model, HttpServletResponse response,
			@AuthenticationPrincipal SecurityUser securityUser) {
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		
		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);

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
		File file = fileService.insertFile(mulFile, model, request);
		return file.getFileNo();
	}

	/**
	 * 2. fileNo를 받아서 post 올리기
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
	 * @param item
	 */
	@RequestMapping("/itemInsert")
	@ResponseBody
	public void itemInsert(Item item) {
		itemRepository.save(item);
	}


	//아이템 삭제
	@RequestMapping("/postDelete")
	@ResponseBody
	public void postDelete(Post postNo) {
//		likesRepository.deleteByPostNo(postNo);
		System.err.println(postNo.getPostNo());
		postRepository.deleteById(postNo.getPostNo());
	}
	
//	@RequestMapping("/afterPostWrite")
//	public String afterPostWrite() {
//
//		return "newsFeed";
//	}

	// 마이프로필 화면
	@RequestMapping("/user/{userNickname}")
	public String profileSetting(@PathVariable String userNickname, Model model,
	@PageableDefault(sort = { "postNo" }, direction = Direction.DESC, size = 30) Pageable paging,
	@AuthenticationPrincipal SecurityUser securityUser) {
		List<Post> feed = new ArrayList<>();
		Member userInfo = memberRepository.findByNickname(userNickname);
		Member myInfo = memberRepository.findById(securityUser.getUsername());
		List<Integer> numbers = new ArrayList<>();
		if(userInfo!=myInfo){
			numbers.add(postRepository.countByMemberNo(userInfo));
			numbers.add(followRepository.countByFolloweeMemNo(userInfo));
			numbers.add(followRepository.countByFollowerMemNo(userInfo));
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("myInfo", myInfo);
			feed = postService.getProfileFeed(userInfo, paging);
		}else{
			//포스트 수, 팔로워 수, 팔로잉 수
			numbers.add(myInfo.getPosts().size());
			numbers.add(myInfo.getFollowers().size());
			numbers.add(myInfo.getFollowees().size());
			feed = postService.getProfileFeed(myInfo, paging);
			model.addAttribute("myInfo", myInfo);
		}
		model.addAttribute("numbers", numbers);
		model.addAttribute("feed", likesService.setLikeCount(feed));
		return "/member/profile";
	}
	
	@RequestMapping("/postUpdate/{postNo}")
	public String PostUpdate(@PathVariable int postNo, Model model) {
		System.err.println(postNo);
		List<Style> style = styleRepository.findAll();
		List<Category> category = categoryRepository.findAll();
		List<Object[]> brand = brandRepository.findBrandQuery();
		
		model.addAttribute("style", style);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);
		
		Post post = postRepository.findById(postNo).get();
		model.addAttribute("post", post);
		model.addAttribute("itemList", itemRepository.findByPostNoOrderByTagNoAsc(post));

		return "post/postUpdate";
	}

	@RequestMapping("/myFeed")
	public String getFeed(Model model, @AuthenticationPrincipal SecurityUser securityUser,
			@PageableDefault(sort = { "postNo" }, direction = Direction.DESC, size = 5) Pageable paging) {
		// 로그인한 사람 user
		Member user = securityUser.getMember();
		if (user == null)
			return "redirect:/login";
		user = memberRepository.findById(user.getMemberNo()).get();
		List<Post> feed = postService.getPostToFeed(user, paging);
		model.addAttribute("feed", likesService.setLikeCount(feed));
		return "newsFeed";
	}

	@RequestMapping(value = "/getMoreFeed", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMoreFeed(Pageable paging, @AuthenticationPrincipal SecurityUser securityUser) {
		Member user = securityUser.getMember();
		user = memberRepository.findById(user.getId());
		Map<String, Object> newFeed = new HashMap<>();
		List<Post> feedList = postService.getPostToFeed(user, paging);
		for (Post post : feedList) {
			Map<String, Object> feedVo = new HashMap<>();
			feedVo.put("postNo", post.getPostNo());
			feedVo.put("postImageNo", post.getPostImageNo().getPath());
			feedVo.put("uploadTime", post.getUploadTime());
			feedVo.put("uploader", post.getMemberNo().getNickname());
			feedVo.put("uploaderProfile", post.getMemberNo().getProfileImageNo().getPath());
			likesService.countLikes(post);
			feedVo.put("likesCount", post.getLikesCount());
			newFeed.put(post.getPostNo() + "", feedVo);
		}
		return newFeed;
	}

	@RequestMapping(value = "/getMoreProfileFeed", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMoreProfileFeed(Pageable paging, String id) {
		Member user = memberRepository.findById(id);
		Map<String, Object> newFeed = new HashMap<>();
		List<Post> feedList = postRepository.findByMemberNo(user);
		for (Post post : feedList) {
			Map<String, Object> feedVo = new HashMap<>();
			feedVo.put("postNo", post.getPostNo());
			feedVo.put("postImageNo", post.getPostImageNo().getPath());
			feedVo.put("uploadTime", post.getUploadTime());
			feedVo.put("uploader", post.getMemberNo().getNickname());
			feedVo.put("uploaderProfile", post.getMemberNo().getProfileImageNo().getPath());
			likesService.countLikes(post);
			feedVo.put("likesCount", post.getLikesCount());
			newFeed.put(post.getPostNo() + "", feedVo);
		}
		return newFeed;
	}

}
