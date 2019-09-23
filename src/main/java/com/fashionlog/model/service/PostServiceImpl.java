package com.fashionlog.model.service;

import java.util.ArrayList;
import java.util.Collections;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.fashionlog.model.dao.PostRepository;
import com.fashionlog.model.dto.Follow;
import com.fashionlog.model.dto.Member;
import com.fashionlog.model.dto.Post;
import com.fashionlog.model.dto.Style;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;

	// 프로필 페이지에서 보여지는 피드를 가져옴
		@Override
		public List<Post> getProfileFeed(Member member, Pageable paging) {
			return postRepository.findByMemberNoOrderByUploadTimeDesc(member, paging);
		}
		
	//팔로우하는 사람들의 글을 피드로 가져옴
	@Override
	public Set<Post> getFeedByFollowee(Member user, Pageable paging) {
		Set<Post> followeesPosts = new HashSet<>();
		for (Follow followee : user.getFollowees()) {
			List<Post> postList = postRepository.findByMemberNoOrderByUploadTimeDesc(followee.getFolloweeMemNo(),
					paging);
			followeesPosts.addAll(postList);
		}
		return followeesPosts;
	}

	//나의 글을 피드로 가져옴
	@Override
	public Set<Post> getFeedByMe(Member user, Pageable paging) {
		List<Post> myPostList = postRepository.findByMemberNoOrderByUploadTimeDesc(user, paging);
		return new HashSet<Post>(myPostList);
	}

	//나의 선호 스타일인 글을 피드로 가져옴
	@Override
	public Set<Post> getFeedByStyle(Member user, Pageable paging) {
		List<Post> stylePostList = getPostByStyle(user.getStyleNo1(), paging);
		stylePostList.addAll(getPostByStyle(user.getStyleNo2(), paging));
		stylePostList.addAll(getPostByStyle(user.getStyleNo3(), paging));
		return new HashSet<Post>(stylePostList);
	}

	@Override
	public Set<Post> getAllFeed(Pageable paging) {
		List<Post> allPost = postRepository.findAll();
		return new HashSet<Post>(allPost);
	}

	//getFeedByStyle 내에서 스타일 null을 체크하고 반복작업 수행
	private List<Post> getPostByStyle(Style style, Pageable paging) {
		List<Post> postList = new ArrayList<>();
		if (style != null) {
			postList.addAll(postRepository.findByStyleNo1OrderByUploadTimeDesc(style, paging));
			postList.addAll(postRepository.findByStyleNo2OrderByUploadTimeDesc(style, paging));
			postList.addAll(postRepository.findByStyleNo3OrderByUploadTimeDesc(style, paging));
		}
		return postList;
	}

	public List<Post> getPostToFeed(Member user, Pageable paging){
		// 가져오는 값들을 중복없이 저장하기 위해 set 생성
				Set<Post> feedSet = new HashSet<>();
				// 스타일 글 페이징해서 담기
				feedSet.addAll(getFeedByStyle(user, paging));
				// 팔로이 글 페이징해서 담기
				feedSet.addAll(getFeedByFollowee(user, paging));
				// 내글 페이징해서 담기
				feedSet.addAll(getFeedByMe(user, paging));
				List<Post> feed = new ArrayList<Post>(feedSet);
				Collections.sort(feed);
				return feed;
	}

}
