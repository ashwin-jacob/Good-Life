package com.goodlife.model;

import java.util.Date;

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

@Entity (name = "Student")
@Table(name="STUDENT", catalog = "goodlife")
public class Student extends Users {

	@Column(name = "roster_id")
	private Integer rosterId;
	
	@JoinColumn(name = "crnt_sub_chap_id")
	private Integer currentSubChapterId;
	
	@Column(name = "start_dt")
	private Date startDate;
	
	@Column(name = "promo_dt")
	private Date promotionDate;


	public Student() {
		super();
	}

	public Student(Integer rosterId, Integer currentSubChapterId,
			Date startDate, Date promotionDate) {
		super();
		this.rosterId = rosterId;
		this.currentSubChapterId = currentSubChapterId;
		this.startDate = startDate;
		this.promotionDate = promotionDate;
	}

	public Integer getRosterId() {
		return rosterId;
	}

	public void setRosterId(Integer rosterId) {
		this.rosterId = rosterId;
	}

	public Integer getCurrentSubChapterId() {
		return currentSubChapterId;
	}

	public void setCurrentSubChapterId(Integer currentSubChapterId) {
		this.currentSubChapterId = currentSubChapterId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getPromotionDate() {
		return promotionDate;
	}

	public void setPromotionDate(Date promotionDate) {
		this.promotionDate = promotionDate;
	}
}