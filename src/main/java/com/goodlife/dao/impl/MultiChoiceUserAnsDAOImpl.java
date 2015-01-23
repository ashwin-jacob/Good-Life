package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public Integer getUserAnswer(Integer userId, Integer multiQuesId)
			throws UserNotFoundException {
		
		List<MultiChoiceUserAns> multiChoiceAns;
		Query query;
		query = this.sessionFactory.getCurrentSession().createQuery("FROM MC_USER_ANS WHERE USR_ID = :userId AND MC_Q_ID = :multiQuesId");
		multiChoiceAns = query.list();
		return multiChoiceAns.get(0).getUserAnswer();
	}

}
