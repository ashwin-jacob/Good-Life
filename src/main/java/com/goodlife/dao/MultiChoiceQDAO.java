package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceQ;

public interface MultiChoiceQDAO {
	
	public Integer addMultiChoice(MultiChoiceQ multiChoiceQ);
	public Boolean deleteMultiChoice(Integer multiChoiceId) throws MultipleChoiceNotFoundException;
	public Boolean updateOrder(List<Integer> multiChoiceIdList) throws MultipleChoiceNotFoundException;
	public Boolean updateQuestionText(Integer multiChoiceId, String quesText) throws MultipleChoiceNotFoundException;
	public Boolean updateHelpText(Integer multiChoiceId, String helpText) throws MultipleChoiceNotFoundException;
	public Boolean updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer) throws MultipleChoiceNotFoundException;
	public List<MultiChoiceQ> getAllMultiChoice(Integer subChapId);
	public MultiChoiceQ getMultiChoiceQById(Integer multiChoiceId) throws MultipleChoiceNotFoundException;
	public Boolean setPublishMultiChoiceQ(Integer multiChoiceId, Boolean published);
	public List<MultiChoiceQ> getAllPublishedMultiChoice(Integer subChapId);
}
