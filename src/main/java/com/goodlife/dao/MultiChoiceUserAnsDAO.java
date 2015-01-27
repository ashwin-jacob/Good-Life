package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceUserAns;

public interface MultiChoiceUserAnsDAO {
	
	public void addMultiChoiceAnswer(MultiChoiceUserAns multiChoiceAns) throws UserNotFoundException;
	public Integer getUserAnswer(Integer userId, Integer multiQuesId) throws UserNotFoundException;

}
