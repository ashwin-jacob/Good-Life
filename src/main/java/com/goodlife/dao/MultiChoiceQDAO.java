package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;

public interface MultiChoiceQDAO {
	
	public Integer addMultiChoice(String questionText, String helpText, Integer correctAnswer,
			                      Integer subChapId, Integer orderId) throws UserNotFoundException;
	public void deleteMultiChoice(Integer multiChoiceId) throws UserNotFoundException;
	public void updateOrder(List<Integer> multiChoiceIdList) throws UserNotFoundException;
	public void updateQuestionText(Integer multiChoiceId, String quesText) throws UserNotFoundException;
	public void updateHelpText(Integer multiChoiceId, String helpText) throws UserNotFoundException;
	public void updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer) throws UserNotFoundException;

}
