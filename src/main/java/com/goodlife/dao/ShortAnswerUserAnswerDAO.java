package com.goodlife.dao;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.ShortAnswerUserAnswer;

public interface ShortAnswerUserAnswerDAO {

	public Boolean addUserAnswer(ShortAnswerUserAnswer shortAnswerUA) throws ObjectNotFoundException;
	public ShortAnswerUserAnswer getUserAnswer(Integer userId, Integer saQId) throws ObjectNotFoundException;
	public Boolean approveAnswer(Integer userId, Integer saQId);
	public Boolean isShortAnswerSubChapComplete(Integer userId, Integer subChapId);
}
