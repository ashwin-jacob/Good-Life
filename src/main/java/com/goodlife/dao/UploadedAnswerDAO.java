package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadedAnswer;

public interface UploadedAnswerDAO {

	public Integer addUploadedAnswer(Integer mediaTypeId, String filePath, Integer userId,
									 Boolean shared, Integer uploadQuesId) throws UserNotFoundException;
	public Boolean setApproveAnswer(Integer uploadAnswerId, Boolean aprvd) throws UserNotFoundException;
	public Boolean setShareAnswer(Integer uploadAnswerId, Boolean shared) throws UserNotFoundException;
	public UploadedAnswer getUserAnswer(Integer userId, Integer uploadQuesId) throws UserNotFoundException;
}
