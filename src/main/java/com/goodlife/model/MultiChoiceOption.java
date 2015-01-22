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
@Table (name = "MULTI_CHOICE_OPTION", catalog = "goodlife")
public class MultiChoiceOption implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "option_id", nullable = false, unique = true)
	private Integer optionId;
	
	@Id
	@JoinColumn(name = "mc_q_id", nullable  = false)
	private Integer multiQuesId;
	
	@Column(name = "choice_txt")
	private String choiceText;

	public MultiChoiceOption() {
		super();
	}

	public MultiChoiceOption(Integer optionId, Integer multiQuesId,
			String choiceText) {
		super();
		this.optionId = optionId;
		this.multiQuesId = multiQuesId;
		this.choiceText = choiceText;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Integer getMultiQuesId() {
		return multiQuesId;
	}

	public void setMultiQuesId(Integer multiQuesId) {
		this.multiQuesId = multiQuesId;
	}

	public String getChoiceText() {
		return choiceText;
	}

	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}
	
	
}
