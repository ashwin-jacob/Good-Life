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
@Table (name = "MULTI_CHOICE_Q", catalog = "goodlife")
public class MultiChoiceQ implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mc_q_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer multiQuesId;
	
	@Column(name = "q_txt")
	private String quesText;
	
	@Column(name = "help_txt")
	private String helpText;
	
	@Column(name = "corr_ans")
	private Integer correctAnswer;
	
	@JoinColumn(name = "mc_list_id", nullable = false)
	private Integer multiChoiceListId;

	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean published;

	public MultiChoiceQ() {
		super();
	}

	public MultiChoiceQ(Integer multiQuesId, String quesText, String helpText,
			Integer correctAnswer, Integer multiChoiceListId, Integer orderId,
			Boolean published) {
		super();
		this.multiQuesId = multiQuesId;
		this.quesText = quesText;
		this.helpText = helpText;
		this.correctAnswer = correctAnswer;
		this.multiChoiceListId = multiChoiceListId;
		this.orderId = orderId;
		this.published = published;
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

	public Integer getMultiChoiceListId() {
		return multiChoiceListId;
	}

	public void setMultiChoiceListId(Integer multiChoiceListId) {
		this.multiChoiceListId = multiChoiceListId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
		
}
