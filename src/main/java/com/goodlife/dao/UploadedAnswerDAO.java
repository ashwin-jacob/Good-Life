package com.goodlife.dao;


import org.hibernate.ObjectNotFoundException;

import com.goodlife.model.UploadedAnswer;


public interface UploadedAnswerDAO {

	public Integer addUploadedAnswer(UploadedAnswer uploadedAnswer);
	public Boolean setApproveAnswer(Integer uploadAnswerId, Boolean aprvd) throws ObjectNotFoundException;
	public Boolean setSharedAnswer(Integer uploadAnswerId, Boolean shared) throws ObjectNotFoundException;
	public UploadedAnswer getUserAnswer(Integer userId, Integer uploadQuesId) throws ObjectNotFoundException;
}
