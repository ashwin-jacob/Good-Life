package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table (name = "SUBCHAPTER", catalog = "goodlife")
public class SubChapter implements Serializable{

	@Id
	@Column(name = "sub_chap_id", nullable = false, unique = true)
	private Integer subChapId;	
	
	@JoinColumn(name = "chap_id", nullable = false)
	private Integer chapId;
	
	@Column(name = "sub_chap_descr")
	private String subChapDescr;
	
	@Column(name = "sub_chap_title", nullable = false)
	private String subChapTitle;
	
	@Column(name = "order_id", nullable = false)
	private Integer orderId;
	
	public SubChapter(){}
	
	public SubChapter(Integer subChapId,
					  Integer chapId,
					  String subChapDescr,
					  String subChapTitle,
					  Integer orderId){
		this.subChapId  = subChapId;
		this.chapId = chapId;
		this.subChapDescr = subChapDescr;
		this.subChapTitle = subChapTitle;
		this.orderId = orderId;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public Integer getChapId() {
		return chapId;
	}

	public void setChapId(Integer chapId) {
		this.chapId = chapId;
	}

	public String getSubChapDescr() {
		return subChapDescr;
	}

	public void setSubChapDescr(String subChapDescr) {
		this.subChapDescr = subChapDescr;
	}

	public String getSubChapTitle() {
		return subChapTitle;
	}

	public void setSubChapTitle(String subChapTitle) {
		this.subChapTitle = subChapTitle;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
