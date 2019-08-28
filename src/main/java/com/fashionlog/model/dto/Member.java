package com.fashionlog.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "posts")
@Entity
public class Member {
	@Id
	private int memberNo;
	
	@Column(columnDefinition = "char")
	private String id;
	 
	@Column(columnDefinition = "char")
	private String password;
	
	@Column(columnDefinition = "char")
	private String nickname;
	
	@Column(columnDefinition = "char")
	private String phonenumber;
	
	@Column(columnDefinition = "char")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "PROFILE_IMAGE_NO")
	private File profileImageNo;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO1")
	private Style styleNo1;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO2")
	private Style styleNo2;
	
	@ManyToOne
	@JoinColumn(name = "STYLE_NO3")
	private Style styleNo3;
	
	@Transient
	private Long likesCount;
	
	@OneToMany(mappedBy = "memberNo")
	private List<Post> posts = new ArrayList<Post>();
	
	@Transient
	public void setLikesCount() {
		Long count = (long) 0;
		for (Post post:posts) {
			count += post.getLikesCount();
		}
		this.setLikesCount(count);
	}
	
}

