package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceOption;

public interface MultiChoiceOptionDAO {
	
	public Integer addMultiChoiceOption(MultiChoiceOption multiChoiceOption) throws UserNotFoundException;
	public void updateChoiceText(Integer optionId) throws UserNotFoundException;
	public List<MultiChoiceOption> getMultiChoiceOptions(Integer multiQuesId);

}
