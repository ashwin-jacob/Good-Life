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
@Table(name = "UPLOAD_FILE_Q", catalog = "goodlife")
public class UploadFileQ implements Serializable{
	
	@Id
	@Column(name = "up_q_id", nullable = false, unique = true)
	private Integer uploadQuesId;
	
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Column(name = "help_txt")
	private String helpText;

	public UploadFileQ() {
		super();
	}

	public UploadFileQ(Integer uploadQuesId, Integer subChapId, String helpText) {
		super();
		this.uploadQuesId = uploadQuesId;
		this.subChapId = subChapId;
		this.helpText = helpText;
	}

	public Integer getUploadQuesId() {
		return uploadQuesId;
	}

	public void setUploadQuesId(Integer uploadQuesId) {
		this.uploadQuesId = uploadQuesId;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}
}
