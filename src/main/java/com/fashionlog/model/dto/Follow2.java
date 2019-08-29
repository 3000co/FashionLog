package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Access(AccessType.PROPERTY)
@Table(schema = "TEST", name ="FOLLOW")
public class Follow2 implements SocialEvent{
	private static final long serialVersionUID = 1L;
	
	private Integer follow_no;
	private Set<User> follower

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int followNo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "FOLLOW"
	joinColumns = @JoinColumn(name = "FOLLOWEE_MEM_NO"),
	inverseJoinColumns = @JoinColumn(name = "FOLLOWER_MEM_NO"))
	private Member followerMemNo;
	
	@ManyToOne
	@JoinColumn(name = "followeeMemNo")
	private Member followeeMemNo;
	
	@CreationTimestamp
	Timestamp followTime;
	
//	@OneToOne(mappedBy = "notiNo", cascade = CascadeType.REMOVE)
//	Notification notification;

}
