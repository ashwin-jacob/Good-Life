package com.goodlife.dao;

import org.hibernate.ObjectNotFoundException;
import com.goodlife.model.MultiChoiceUserAns;

public interface MultiChoiceUserAnsDAO {
	
	public Boolean addMultiChoiceAnswer(MultiChoiceUserAns multiChoiceAns) throws ObjectNotFoundException;
	public Integer getUserAnswer(Integer userId, Integer multiQuesId) throws ObjectNotFoundException;
	public Boolean isMultiChoiceCorrect(Integer userId, Integer multiQuesId) throws ObjectNotFoundException;
	public Boolean isMultiChoiceSubChapComplete(Integer userId, Integer subChapId) throws ObjectNotFoundException;
}
