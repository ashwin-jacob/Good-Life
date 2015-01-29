package com.goodlife.dao;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.ShortAnswerUserAnswer;

public interface ShortAnswerUserAnswerDAO {

	public void addUserAnswer(ShortAnswerUserAnswer shortAnswerUA) throws ObjectNotFoundException;
	public ShortAnswerUserAnswer getUserAnswer(Integer userId, Integer saQId) throws ObjectNotFoundException;
	public void approveAnswer(Integer userId) throws UserNotFoundException;
}
