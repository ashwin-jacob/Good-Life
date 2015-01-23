package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;

public interface MultiChoiceOptionDAO {
	
	public Integer addMultiChoiceOption(Integer multiQuesId, String choiceText) throws UserNotFoundException;
	public void updateChoiceText(Integer optionId) throws UserNotFoundException;

}
