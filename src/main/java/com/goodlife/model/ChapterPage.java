package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CHAPTER_PAGE", catalog = "goodlife")
public class ChapterPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "page_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
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
