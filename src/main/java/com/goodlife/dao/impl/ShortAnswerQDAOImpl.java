package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.ShortAnswerQ;

public class ShortAnswerQDAOImpl implements ShortAnswerQDAO{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Integer addShortAnswerQuestion(Integer subChapId, String question,
			String helpText, Integer orderId) throws UserNotFoundException {

		ShortAnswerQ shortAns = new ShortAnswerQ();
		shortAns.setSubChapId(subChapId);
		shortAns.setQuestion(question);
		shortAns.setHelpText(helpText);
		shortAns.setOrderId(orderId);
		
		this.sessionFactory.getCurrentSession().save(shortAns);
		
		return shortAns.getSaQId();
	}

	@Override
	public void updateQuestionText(Integer saQId, String question)
			throws UserNotFoundException {
		
		ShortAnswerQ shortAns = new ShortAnswerQ();
		shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		shortAns.setQuestion(question);
		
		this.sessionFactory.getCurrentSession().save(shortAns);
	}

	@Override
	public void updateHelpText(Integer saQId, String helpText)
			throws UserNotFoundException {
		
		ShortAnswerQ shortAns = new ShortAnswerQ();
		shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		shortAns.setHelpText(helpText);
		
		this.sessionFactory.getCurrentSession().save(shortAns);
	}

	@Override
	public void updateOrderId(List<Integer> saQIdList)
			throws UserNotFoundException {
		
		ShortAnswerQ shortAns = new ShortAnswerQ();
		
		for(int i = 0; i <saQIdList.size(); i++){
			shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQIdList.get(i));
			shortAns.setOrderId(i);
			this.sessionFactory.getCurrentSession().save(shortAns);
		}
	}

	@Override
	public ShortAnswerQ getShortAnswerById(Integer saQId) 
		throws UserNotFoundException {
		
		ShortAnswerQ shortAnswerQ = new ShortAnswerQ();
		shortAnswerQ = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		return shortAnswerQ;
	}

}
