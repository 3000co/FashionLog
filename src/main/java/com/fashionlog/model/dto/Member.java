package com.fashionlog.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"posts","followers","followees"})
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberNo;
	
	@Column(columnDefinition = "char")
	private String id;

	private String password;

	@Column(columnDefinition = "char")
	private String nickname;

	@Column(columnDefinition = "char")
	private String phonenumber;

	@Column(columnDefinition = "char")
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFILE_IMAGE_NO")
	private File profileImageNo;
	
	@Column(columnDefinition = "char")
	@Enumerated(EnumType.STRING)
	private Role role;
//	private boolean enabled;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STYLE_NO1")
	private Style styleNo1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STYLE_NO2")
	private Style styleNo2;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STYLE_NO3")
	private Style styleNo3;

	@JsonBackReference
	@OneToMany(mappedBy = "memberNo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> posts = new ArrayList<Post>();

	@Transient
	private Long likesCount;
	
	@JsonBackReference
	@OneToMany(mappedBy = "followeeMemNo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Follow> followers;

	@JsonBackReference
	@OneToMany(mappedBy = "followerMemNo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Follow> followees;

	public void setLikesCount() {
		Long count = (long) 0;
		for (Post post:posts) {
			count += post.getLikesCount();
		}
		this.setLikesCount(count);
	}
}
