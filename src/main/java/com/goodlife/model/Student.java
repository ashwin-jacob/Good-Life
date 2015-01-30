package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity (name = "Student")
@Table(name="STUDENT", catalog = "goodlife")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@JoinColumn(name = "usr_id", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "roster_id")
	private Integer rosterId;
	
	@JoinColumn(name = "crnt_sub_chap_id")
	private Integer currentSubChapterId;
	
	@Column(name = "start_dt")
	private Date startDate;


	public Student() {
		super();
	}

	public Student(Integer rosterId, Integer currentSubChapterId,
			Date startDate, Date promotionDate) {
		super();
		this.rosterId = rosterId;
		this.currentSubChapterId = currentSubChapterId;
		this.startDate = startDate;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}