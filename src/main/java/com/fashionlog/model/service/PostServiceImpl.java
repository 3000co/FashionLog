package com.fashionlog.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fashionlog.model.dao.FileRepository;
import com.fashionlog.model.dao.LikesRepository;
import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.File;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private FileRepository fileRepository;
	@Value("${file.uploadPath}")
	private String uploadPath;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private LikesRepository likesRepository;

	/**
	 * uploadPath 변수가 상대경로일 필요성 uploadPath 가 db저장 될 때, 이미지를 뿌려줄 때도 쓸 수 있는 형태인지 파일명이나
	 * 경로가 너무 길어서 db에 안들어갈 때 handling
	 */
	@Override
	public File insertFile(MultipartFile mulFile, Model model, HttpServletRequest request) throws Exception {
		Date now = new Date();
		String savedName = uploadFile(mulFile.getOriginalFilename(), mulFile.getBytes(), now, mulFile);

		File file = new File();
		file.setType(request.getParameter("type"));
		file.setName(savedName);
		file.setPath(uploadPath + "/" + savedName);
		file.setSize((int) mulFile.getSize());
		fileRepository.save(file);

		File fileName = fileRepository.findByName(savedName);

		return fileName;
	}

	private String uploadFile(String originalName, byte[] fileData, Date now, MultipartFile mulFile) throws Exception {
		SimpleDateFormat uid = new SimpleDateFormat("yyyyMMddhhmmss");
		String savedName = uid.format(now) + "_";
//		String savedName = uid.format(now)+"_"+originalName;		

		java.io.File target = new java.io.File(uploadPath, savedName);
		System.out.println(target.getAbsolutePath());
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}

	@Override
	public void countLikes() {
		List<Post> posts = postRepository.findAll();
		for (Post post : posts) {
			post.setLikesCount(likesRepository.countByPostNo(post));
			postRepository.save(post);
			System.out.println("post likes count : " + post);
		}
	}
	@Override
	public Map<Integer, Post> getFeedByFollowee(Member user, Pageable paging) {
		Map<Integer, Post> followeesPosts = new HashMap<>();
		for (Follow followee : user.getFollowees()) {
			List<Post> postList = postRepository.findByMemberNoOrderByUploadTimeDesc(followee.getFolloweeMemNo(), paging);
			for (Post post : postList) {
				followeesPosts.put(post.getPostNo(), post);
			}
		}
		return followeesPosts;
	}
}
