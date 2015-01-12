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

@Entity (name = "Instructor")
@Table(name="INSTRUCTOR", catalog = "goodlife")
public class Instructor extends Users {

	@Id
	@Column(name = "roster_id")
	@GeneratedValue
	private Integer rosterId;
	
	@Column(name = "n_stdnt")
	private Integer numStudent;
	
	@Column(name = "tot_cap")
	private Integer totalCapacity;
	
	@Column(name = "start_dt")
	private Date startDate;

	
	public Instructor() {
		super();
	}

	public Instructor(Integer rosterId, Integer numStudent,
			Integer totalCapacity, Date startDate) {
		super();
		this.rosterId = rosterId;
		this.numStudent = numStudent;
		this.totalCapacity = totalCapacity;
		this.startDate = startDate;
	}

	public Integer getRosterId() {
		return rosterId;
	}

	public void setRosterId(Integer rosterId) {
		this.rosterId = rosterId;
	}

	public Integer getNumStudent() {
		return numStudent;
	}

	public void setNumStudent(Integer numStudent) {
		this.numStudent = numStudent;
	}

	public Integer getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}