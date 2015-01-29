package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.ShortAnswerUserAnswer;

@Repository
public class ShortAnswerUserAnswerDAOImpl implements ShortAnswerUserAnswerDAO{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addUserAnswer(ShortAnswerUserAnswer shortAnswerUA)
			throws ObjectNotFoundException {
		
		this.sessionFactory.getCurrentSession().save(shortAnswerUA);
	}

	@Override
	public ShortAnswerUserAnswer getUserAnswer(Integer userId, Integer saQId)
			throws ObjectNotFoundException {

		List<ShortAnswerUserAnswer> shortAnswerUA;
		Query query;
		
		query = this.sessionFactory.getCurrentSession().createQuery("FROM SHORT_ANS_USER_ANS WHERE USR_ID = :userId AND SA_Q_ID = :saQId");
		query.setParameter("userId", userId);
		query.setParameter("saQId", saQId);
		
		shortAnswerUA = query.list();
		
		if(null == shortAnswerUA){
			throw new ObjectNotFoundException(null, "The combination of user Id: " + userId + " and saQId: " + saQId + "were not found in the database!");
		}
		if(shortAnswerUA.size() > 1){
			throw new ObjectNotFoundException(null, "The combination of user Id: " + userId + " and saQId: " + saQId + "returned too many results in the database!");
		}
		
		return shortAnswerUA.get(0);
	}

	@Override
	public void approveAnswer(Integer userId)
			throws UserNotFoundException {
		
		ShortAnswerUserAnswer shortAnswerUA;
		try{
			shortAnswerUA = (ShortAnswerUserAnswer)this.sessionFactory.getCurrentSession().load(ShortAnswerUserAnswer.class, userId);
		}catch(ObjectNotFoundException e){
			shortAnswerUA = (ShortAnswerUserAnswer)this.sessionFactory.getCurrentSession().get(ShortAnswerUserAnswer.class, userId);
		}
		if(null == shortAnswerUA){
			throw new UserNotFoundException("User Id: " + userId + " not found in the database!");
		}
		shortAnswerUA.setAprvd(true);
		this.sessionFactory.getCurrentSession().save(shortAnswerUA);
	}

}
