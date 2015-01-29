package com.goodlife.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.model.MultiChoiceOption;

public interface MultiChoiceOptionDAO {
	
	public Integer addMultiChoiceOption(MultiChoiceOption multiChoiceOption);
	public void updateChoiceText(Integer optionId, String text) throws MultipleChoiceOptionNotFoundException;
	public List<MultiChoiceOption> getMultiChoiceOptions(Integer multiQuesId) throws MultipleChoiceOptionNotFoundException;
	public void deleteMultiChoiceOption(Integer optionId) throws MultipleChoiceOptionNotFoundException;
	public MultiChoiceOption findMultiChoiceOptionById(Integer optionId) throws MultipleChoiceOptionNotFoundException;
}
