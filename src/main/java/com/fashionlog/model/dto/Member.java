package com.fashionlog.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@ToString(exclude = {"posts","followers","followees"})
@Entity
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

	@ManyToOne
	@JoinColumn(name = "PROFILE_IMAGE_NO", insertable = false, updatable = false)
	private File profileImageNo;
	
	@Column(columnDefinition = "char")
	@Enumerated(EnumType.STRING)
	private Role role;
//	private boolean enabled;

  @ManyToOne
	@JoinColumn(name = "STYLE_NO1")
	private Style styleNo1;

	@ManyToOne
	@JoinColumn(name = "STYLE_NO2")
	private Style styleNo2;

	@ManyToOne
	@JoinColumn(name = "STYLE_NO3")
	private Style styleNo3;

	@OneToMany(mappedBy = "memberNo", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<Post>();

	@Transient
	private Long likesCount;
	
	@OneToMany(mappedBy = "followeeMemNo", cascade = CascadeType.ALL)
	private List<Follow> followers;

	@OneToMany(mappedBy = "followerMemNo", cascade = CascadeType.ALL)
	private List<Follow> followees;

	@Transient
	public void setLikesCount() {
		Long count = (long) 0;
		for (Post post:posts) {
			count += post.getLikesCount();
		}
		this.setLikesCount(count);
	}
}
