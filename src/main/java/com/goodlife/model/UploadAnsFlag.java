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



@Entity (name = "UploadAnsFlag")
@Table(name="UPLOAD_ANS_FLAG", catalog = "goodlife")
public class UploadAnsFlag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flg_id", unique = true, nullable = false)
	private Integer flagId;

	@Column(name = "flgd_by", length = 50)
	private String flaggedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "up_ans_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Integer upAnsId;

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

	public Integer getUpAnsId() {
		return upAnsId;
	}

	public void setUpAnsId(Integer upAnsId) {
		this.upAnsId = upAnsId;
	}
	
}
