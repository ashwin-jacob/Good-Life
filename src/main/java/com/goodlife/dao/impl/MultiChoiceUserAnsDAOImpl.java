package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceUserAns;

public class MultiChoiceUserAnsDAOImpl implements MultiChoiceUserAnsDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addMultiChoiceAnswer(Integer userId, Integer multiQuesId,
			Integer userAnswer) throws UserNotFoundException {
		
		MultiChoiceUserAns multiChoiceAns = new MultiChoiceUserAns();
		multiChoiceAns.setUserId(userId);
		multiChoiceAns.setMultiQuesId(multiQuesId);
		multiChoiceAns.setUserAnswer(userAnswer);
		
		this.sessionFactory.getCurrentSession().save(multiChoiceAns);
	}

}
