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
@Table(name = "CHAPTER", catalog = "goodlife")
public class Chapter implements Serializable{
	
	@Id
	@Column(name = "chap_id", nullable = false, unique = true)
	private Integer chapId;
	
	@Column(name = "chap_descr")
	private String chapDescr;
	
	@Column(name = "chap_title", nullable = false)
	private String chapTitle;
	
	@Column(name = "order_id", nullable = false)
	private Integer orderId;

	public Chapter() {
		super();
	}

	public Chapter(Integer chapId, String chapDescr, String chapTitle,
			Integer orderId) {
		super();
		this.chapId = chapId;
		this.chapDescr = chapDescr;
		this.chapTitle = chapTitle;
		this.orderId = orderId;
	}

	public Integer getChapId() {
		return chapId;
	}

	public void setChapId(Integer chapId) {
		this.chapId = chapId;
	}

	public String getChapDescr() {
		return chapDescr;
	}

	public void setChapDescr(String chapDescr) {
		this.chapDescr = chapDescr;
	}

	public String getChapTitle() {
		return chapTitle;
	}

	public void setChapTitle(String chapTitle) {
		this.chapTitle = chapTitle;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
