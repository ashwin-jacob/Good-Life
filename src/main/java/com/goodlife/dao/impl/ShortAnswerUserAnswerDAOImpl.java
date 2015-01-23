package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
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
	public ShortAnswerUserAnswer getUserAnswer(Integer userId, Integer saQId)
			throws UserNotFoundException {

		List<ShortAnswerUserAnswer> shortAnswerUA;
		Query query;
		
		query = this.sessionFactory.getCurrentSession().createQuery("FROM SHORT_ANS_USER_ANS WHERE USR_ID = :userId AND SA_Q_ID = :saQId");
		query.setParameter("userId", userId);
		query.setParameter("saQId", saQId);
		
		shortAnswerUA = query.list();
		
		return shortAnswerUA.get(0);
	}

	@Override
	public void approveAnswer(Integer userId)
			throws UserNotFoundException {
		
		ShortAnswerUserAnswer shortAnswerUA = new ShortAnswerUserAnswer();
		try{
			shortAnswerUA = (ShortAnswerUserAnswer)this.sessionFactory.getCurrentSession().load(ShortAnswerUserAnswer.class, userId);
		}catch(ObjectNotFoundException e){
			shortAnswerUA = (ShortAnswerUserAnswer)this.sessionFactory.getCurrentSession().get(ShortAnswerUserAnswer.class, userId);
		}
		shortAnswerUA.setAprvd(true);
		this.sessionFactory.getCurrentSession().save(shortAnswerUA);
	}

}
