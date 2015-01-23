package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;

public interface MultiChoiceUserAnsDAO {
	
	public void addMultiChoiceAnswer(Integer userId, Integer multiQuesId, Integer userAnswer) throws UserNotFoundException;
	public Integer getUserAnswer(Integer userId, Integer multiQuesId) throws UserNotFoundException;

}
