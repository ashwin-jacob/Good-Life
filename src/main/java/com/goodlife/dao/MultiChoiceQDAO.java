package com.goodlife.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.model.MultiChoiceQ;

public interface MultiChoiceQDAO {
	
	public Integer addMultiChoice(MultiChoiceQ multiChoiceQ);
	public void deleteMultiChoice(Integer multiChoiceId) throws ObjectNotFoundException;
	public void updateOrder(List<Integer> multiChoiceIdList) throws ObjectNotFoundException;
	public void updateQuestionText(Integer multiChoiceId, String quesText) throws ObjectNotFoundException;
	public void updateHelpText(Integer multiChoiceId, String helpText) throws ObjectNotFoundException;
	public void updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer) throws ObjectNotFoundException;
	public List<MultiChoiceQ> getAllMultiChoice(Integer multiChoiceId);

}
