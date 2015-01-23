package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.ShortAnswerUserAnswer;

public interface ShortAnswerUserAnswerDAO {

	public void addUserAnswer(Integer subChapId, Integer saQId, String userAnswer, Integer userId, Boolean aprvd) throws UserNotFoundException;
	public ShortAnswerUserAnswer getUserAnswer(Integer userId, Integer saQId) throws UserNotFoundException;
	public void approveAnswer(Integer userId) throws UserNotFoundException;
}
