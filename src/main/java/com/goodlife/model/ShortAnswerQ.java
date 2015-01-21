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

@Entity
@Table(name = "SHORT_ANS_Q", catalog = "goodlife")
public class ShortAnswerQ implements Serializable{
	
	@Id
	@Column(name = "sa_q_id", nullable = false, unique = true)
	private Integer saQId;
	
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "help_txt")
	private String helpTxt;
	
	@Column(name = "order_id", nullable = false)
	private Integer orderId;

	public ShortAnswerQ() {
		super();
	}

	public ShortAnswerQ(Integer saQId, Integer subChapId, String question,
			String helpTxt, Integer orderId) {
		super();
		this.saQId = saQId;
		this.subChapId = subChapId;
		this.question = question;
		this.helpTxt = helpTxt;
		this.orderId = orderId;
	}

	public Integer getSaQId() {
		return saQId;
	}

	public void setSaQId(Integer saQId) {
		this.saQId = saQId;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getHelpTxt() {
		return helpTxt;
	}

	public void setHelpTxt(String helpTxt) {
		this.helpTxt = helpTxt;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	

}
