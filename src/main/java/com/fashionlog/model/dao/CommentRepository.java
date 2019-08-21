package com.fashionlog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fashionlog.model.dto.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer>{
	@Query(value="select comment_No, contents, nickname, member.profile_Image_No, file.path from fashionlog.comment, member, file where comment.MEMBER_NO=member.member_no and profile_image_no=file.FILE_NO"
			, nativeQuery = true)
	public List<Object[]> getCommentList();
	
	
	
	
}
