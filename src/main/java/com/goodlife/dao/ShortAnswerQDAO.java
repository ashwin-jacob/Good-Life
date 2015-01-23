package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.ShortAnswerQ;

public interface ShortAnswerQDAO {

	public Integer addShortAnswerQuestion(Integer subChapId, String question, String helpText, Integer orderId) throws UserNotFoundException;
	public void updateQuestionText(Integer saQId, String question) throws UserNotFoundException;
	public void updateHelpText(Integer saQId, String helpText) throws UserNotFoundException;
	public void updateOrderId(List<Integer> saQIdList) throws UserNotFoundException;
	public ShortAnswerQ getShortAnswerById(Integer saQId) throws UserNotFoundException;
	public List<ShortAnswerQ> getShortAnswerBySubChapter(Integer subChapId) throws UserNotFoundException;
	
}
