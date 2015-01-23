package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.Table;

@Entity
@Table (name = "MULTI_CHOICE_Q", catalog = "goodlife")
public class MultiChoiceQ implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mc_q_id", nullable = false, unique = true)
	private Integer multiQuesId;
	
	@Column(name = "q_txt")
	private String quesText;
	
	@Column(name = "help_txt")
	private String helpText;
	
	@Column(name = "corr_ans")
	private Integer correctAnswer;
	
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Column(name = "order_id")
	private Integer orderId;

	public MultiChoiceQ() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultiChoiceQ(Integer multiQuesId, String quesText, String helpText,
			Integer correctAnswer, Integer subChapId, Integer orderId) {
		super();
		this.multiQuesId = multiQuesId;
		this.quesText = quesText;
		this.helpText = helpText;
		this.correctAnswer = correctAnswer;
		this.subChapId = subChapId;
		this.orderId = orderId;
	}

	public Integer getMultiQuesId() {
		return multiQuesId;
	}

	public void setMultiQuesId(Integer multiQuesId) {
		this.multiQuesId = multiQuesId;
	}

	public String getQuesText() {
		return quesText;
	}

	public void setQuesText(String quesText) {
		this.quesText = quesText;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
