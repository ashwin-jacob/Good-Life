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
@Table(name = "CHAPTER_PAGE", catalog = "goodlife")
public class ChapterPage implements Serializable{

	@Id
	@Column(name = "page_id", nullable = false, unique = true)
	private Integer pageId;
	
	@JoinColumn(name = "chap_id", nullable = false)
	private Integer chapId;
	
	@Column(name = "page_num")
	private Integer page_num;
	
	@Column(name = "page_url")
	private Integer pageUrl;

	public ChapterPage() {
		super();
	}

	public ChapterPage(Integer pageId, Integer chapId, Integer page_num,
			Integer pageUrl) {
		super();
		this.pageId = pageId;
		this.chapId = chapId;
		this.page_num = page_num;
		this.pageUrl = pageUrl;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getChapId() {
		return chapId;
	}

	public void setChapId(Integer chapId) {
		this.chapId = chapId;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public Integer getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(Integer pageUrl) {
		this.pageUrl = pageUrl;
	}
}
