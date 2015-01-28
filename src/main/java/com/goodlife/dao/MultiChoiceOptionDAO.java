package com.goodlife.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.model.MultiChoiceOption;

public interface MultiChoiceOptionDAO {
	
	public Integer addMultiChoiceOption(MultiChoiceOption multiChoiceOption);
	public void updateChoiceText(Integer optionId, String text) throws ObjectNotFoundException;
	public List<MultiChoiceOption> getMultiChoiceOptions(Integer multiQuesId);
	public void deleteMultiChoiceOption(Integer optionId) throws ObjectNotFoundException;
	public MultiChoiceOption findMultiChoiceOptionById(Integer optionId) throws ObjectNotFoundException;
}
