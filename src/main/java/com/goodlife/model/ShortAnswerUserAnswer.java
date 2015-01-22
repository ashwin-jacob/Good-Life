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
@Table(name = "SHORT_ANS_USER_ANS")
public class ShortAnswerUserAnswer implements Serializable{
	
	@Id
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Id
	@JoinColumn(name = "sa_q_id")
	private Integer saQId;
	
	@Column(name = "usr_ans", nullable = false)
	private String usrAns;
	
	@JoinColumn(name = "usr_id", nullable = false)
	private Integer usrId;
	
	@Column(name = "aprvd", columnDefinition = "TINYINT", length = 1)
	private boolean aprvd;

	public ShortAnswerUserAnswer() {
		super();
	}

	public ShortAnswerUserAnswer(Integer subChapId, Integer saQId,
			String usrAns, Integer usrId, boolean aprvd) {
		super();
		this.subChapId = subChapId;
		this.saQId = saQId;
		this.usrAns = usrAns;
		this.usrId = usrId;
		this.aprvd = aprvd;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public Integer getSaQId() {
		return saQId;
	}

	public void setSaQId(Integer saQId) {
		this.saQId = saQId;
	}

	public String getUsrAns() {
		return usrAns;
	}

	public void setUsrAns(String usrAns) {
		this.usrAns = usrAns;
	}

	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public boolean isAprvd() {
		return aprvd;
	}

	public void setAprvd(boolean aprvd) {
		this.aprvd = aprvd;
	}
}
