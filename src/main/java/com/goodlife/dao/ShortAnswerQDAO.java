package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.ShortAnswerNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.ShortAnswerQ;

public interface ShortAnswerQDAO {

	public Integer addShortAnswerQuestion(ShortAnswerQ shortAnswerQ) throws ShortAnswerNotFoundException;
	public void updateQuestionText(Integer saQId, String question) throws ShortAnswerNotFoundException;
	public void updateHelpText(Integer saQId, String helpText) throws ShortAnswerNotFoundException;
	public void updateOrderId(List<Integer> saQIdList) throws ShortAnswerNotFoundException;
	public ShortAnswerQ getShortAnswerById(Integer saQId) throws ShortAnswerNotFoundException;
	public List<ShortAnswerQ> getShortAnswerBySubChapter(Integer subChapId) throws SubChapterNotFoundException;
	
}
