package com.fashionlog.model.dto;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@DynamicInsert
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reportNo;
	
	@ManyToOne
	@JoinColumn(name = "TARGET_POST_NO")
	private Post targetPostNo;
	
	@ManyToOne
	@JoinColumn(name = "TARGET_COMMENT_NO")
	private Comment targetCommentNo;
	
	private int type;
	
	@Column(columnDefinition = "char")
	private String reason;
	
	@ManyToOne
	@JoinColumn(name = "REPORT_MEM_NO")
	private Member reportMemNo;
	
	@ManyToOne
	@JoinColumn(name = "TARGET_MEM_NO")
	private Member targetMemNo;
	
	@CreationTimestamp
	private Timestamp reportTime;
	
	private Timestamp checkTime;
	
	@ColumnDefault("미확인")
	private String checkHistory;
	
}
