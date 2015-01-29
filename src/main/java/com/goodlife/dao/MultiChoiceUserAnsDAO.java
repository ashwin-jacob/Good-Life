package com.goodlife.dao;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceUserAns;

public interface MultiChoiceUserAnsDAO {
	
	public void addMultiChoiceAnswer(MultiChoiceUserAns multiChoiceAns) throws ObjectNotFoundException;
	public Integer getUserAnswer(Integer userId, Integer multiQuesId) throws ObjectNotFoundException;

}
