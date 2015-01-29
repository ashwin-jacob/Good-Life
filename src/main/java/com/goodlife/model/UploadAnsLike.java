package com.goodlife.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity (name = "UploadAnslike")
@Table(name="UPLOAD_ANS_LIKE", catalog = "goodlife")
public class UploadAnsLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lk_id", unique = true, nullable = false)
	private Integer likeId;

	@Column(name = "lkd_by")
	private Integer likedBy;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "up_ans_id", nullable = false)
	//@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Integer upAnsId;

	public Integer getlikeId() {
		return likeId;
	}

	public void setlikeId(Integer likeId) {
		this.likeId = likeId;
	}

	public Integer getlikedBy() {
		return likedBy;
	}

	public void setlikegedBy(Integer likedBy) {
		this.likedBy = likedBy;
	}

	public Integer getUpAnsId() {
		return upAnsId;
	}

	public void setUpAnsId(Integer upAnsId) {
		this.upAnsId = upAnsId;
	}
	
}
