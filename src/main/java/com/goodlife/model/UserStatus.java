package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "UserStatus")
@Table(name = "USER_STATUS", catalog = "goodlife")
@Inheritance
public class UserStatus implements Serializable{
	
	@Id
	@Column(name = "usr_sts_id", unique = true, nullable = false)
	@GeneratedValue
	private Integer userStatusId;
	
	@ManyToOne
	@JoinColumn(name = "usr_id", unique = false, nullable = false)
	@GeneratedValue
	private Integer userId;
	
	
	@Column(name = "sts_typ_cd", unique = false, nullable = false)
	@GeneratedValue
	private char statusTypeCode;
	
	
	@Column(name = "strt_dt", unique = false, nullable = true)
	@GeneratedValue
	private Date startDate;
	
	
	@Column(name = "end_dt", unique = false, nullable = true)
	@GeneratedValue
	private Date endDate;
	
	public UserStatus(){
		
	}

	public UserStatus(Integer userStatusId, Integer userId, char statusTypeCode,
			Date startDate, Date endDate) {
		super();
		this.userStatusId = userStatusId;
		this.userId = userId;
		this.statusTypeCode = statusTypeCode;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(Integer userStatusId) {
		this.userStatusId = userStatusId;
	}

	public char getStatusTypeCode() {
		return statusTypeCode;
	}

	public void setStatusTypeCode(char statusTypeCode) {
		this.statusTypeCode = statusTypeCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
