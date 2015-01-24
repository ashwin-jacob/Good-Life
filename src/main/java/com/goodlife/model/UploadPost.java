package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "UPLOAD_POST", catalog = "goodlife")
public class UploadPost implements Serializable {
	
	@Id
	@Column(name = "post_id", unique = true)
	private Integer postId;
	
	@Column(name = "subj_txt")
	private String subjectText;
	
	@Column(name = "descr_txt")
	private String descriptionText;
	
	@Column(name = "file_path")
	private String filePath;
	
	@JoinColumn(name = "media_typ_id")
	private Integer mediaTypeId;
	
	@JoinColumn(name = "usr_id", nullable = false)
	private Integer userId;

	public UploadPost() {
		super();
	}

	public UploadPost(Integer postId, String subjectText,
			String descriptionText, String filePath, Integer mediaTypeId,
			Integer userId) {
		super();
		this.postId = postId;
		this.subjectText = subjectText;
		this.descriptionText = descriptionText;
		this.filePath = filePath;
		this.mediaTypeId = mediaTypeId;
		this.userId = userId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getSubjectText() {
		return subjectText;
	}

	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
