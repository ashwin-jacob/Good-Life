package com.goodlife.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceQ;

public interface MultiChoiceQDAO {
	
	public Integer addMultiChoice(MultiChoiceQ multiChoiceQ);
	public void deleteMultiChoice(Integer multiChoiceId) throws MultipleChoiceNotFoundException;
	public void updateOrder(List<Integer> multiChoiceIdList) throws MultipleChoiceNotFoundException;
	public void updateQuestionText(Integer multiChoiceId, String quesText) throws MultipleChoiceNotFoundException;
	public void updateHelpText(Integer multiChoiceId, String helpText) throws MultipleChoiceNotFoundException;
	public void updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer) throws MultipleChoiceNotFoundException;
	public List<MultiChoiceQ> getAllMultiChoice(Integer multiChoiceId);
	public MultiChoiceQ getMultiChoiceQById(Integer multiChoiceId) throws MultipleChoiceNotFoundException;

}
