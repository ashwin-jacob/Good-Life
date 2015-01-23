package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.ShortAnswerUserAnswer;

public class ShortAnswerUserAnswerDAOImpl implements ShortAnswerUserAnswerDAO{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addUserAnswer(Integer subChapId, Integer saQId,
			String userAnswer, Integer userId, Boolean aprvd)
			throws UserNotFoundException {

		ShortAnswerUserAnswer shortAnswerUA = new ShortAnswerUserAnswer();
		shortAnswerUA.setSubChapId(subChapId);
		shortAnswerUA.setSaQId(saQId);
		shortAnswerUA.setUserAnswer(userAnswer);
		shortAnswerUA.setUserId(userId);
		shortAnswerUA.setAprvd(aprvd);
		
		this.sessionFactory.getCurrentSession().save(shortAnswerUA);
	}

	@Override
	public ShortAnswerUserAnswer getUserAnswer(Integer userId)
			throws UserNotFoundException {

		ShortAnswerUserAnswer shortAnswerUA = new ShortAnswerUserAnswer();
		shortAnswerUA = (ShortAnswerUserAnswer)this.sessionFactory.getCurrentSession().load(ShortAnswerUserAnswer.class, userId);
		return shortAnswerUA;
	}

	@Override
	public void approveAnswer(Integer userId)
			throws UserNotFoundException {
		
		ShortAnswerUserAnswer shortAnswerUA = new ShortAnswerUserAnswer();
		shortAnswerUA = (ShortAnswerUserAnswer)this.sessionFactory.getCurrentSession().load(ShortAnswerUserAnswer.class, userId);
		shortAnswerUA.setAprvd(true);
	}

}
