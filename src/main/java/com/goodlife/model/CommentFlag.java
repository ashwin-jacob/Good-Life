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



@Entity (name = "CommentFlag")
@Table(name="COMMENT_FLAG", catalog = "goodlife")
public class CommentFlag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flag_id", unique = true, nullable = false)
	private Integer flagId;

	@Column(name = "flgd_by", length = 50)
	private String flaggedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cmmt_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Integer commentId;

	public Integer getFlagId() {
		return flagId;
	}

	public void setFlagId(Integer flagId) {
		this.flagId = flagId;
	}

	public String getFlaggedBy() {
		return flaggedBy;
	}

	public void setFlaggedBy(String flaggedBy) {
		this.flaggedBy = flaggedBy;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
}
