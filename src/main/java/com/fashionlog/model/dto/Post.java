package com.fashionlog.model.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.fashionlog.model.dao.LikesRepository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"itemList", "commentList"})
@Entity
public class Post implements Comparable<Post> {
	@Transient
	@Autowired
	LikesRepository likesRepository;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member memberNo;
	
	@CreationTimestamp
	private Timestamp uploadTime;
	
	@OneToOne
	@JoinColumn(name = "POST_IMAGE_NO")
	private File postImageNo;
	
	private String contents;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO1")
	private Style styleNo1;

	@ManyToOne
	@JoinColumn(name = "STYLE_NO2")
	private Style styleNo2;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO3")
	private Style styleNo3;
	
	@OneToMany(mappedBy = "postNo")
	private List<Comment> commentList = new ArrayList<Comment>();
	
	@OneToMany(mappedBy = "postNo")
	private List<Item> itemList = new ArrayList<Item>();
	
	@Transient
	private Long likesCount;
	
//	public Long getLikesCount() {
////		if(likesCount==null) {
//		try {
//			likesCount = likesRepository.countByPostNo(this);			
//		} catch (Exception e) {
//			System.err.print(e.getLocalizedMessage());
//		}
////		}
//		return this.likesCount;
//	}

	@Override
	public int compareTo(Post o) {
		if(this.getPostNo()>o.getPostNo()) {
			return -1;
		}else if(this.getPostNo()<o.getPostNo()) {
			return 1;
		}
		return 0;
//		}else if(this.getUploadTime().getTime()>o.getUploadTime().getTime()) {
//			return -1;
//		}
	}
}
