package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.ShortAnswerQ;

public class ShortAnswerQDAOImpl implements ShortAnswerQDAO{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Integer addShortAnswerQuestion(ShortAnswerQ shortAnswerQ) throws UserNotFoundException {
		
		this.sessionFactory.getCurrentSession().save(shortAnswerQ);
		
		return shortAnswerQ.getSaQId();
	}

	@Override
	public void updateQuestionText(Integer saQId, String question)
			throws UserNotFoundException {
		
		ShortAnswerQ shortAns = new ShortAnswerQ();
		try{
			shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		}catch(ObjectNotFoundException e){
			shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, saQId);
		}
		shortAns.setQuestion(question);
		
		this.sessionFactory.getCurrentSession().save(shortAns);
	}

	@Override
	public void updateHelpText(Integer saQId, String helpText)
			throws UserNotFoundException {
		
		ShortAnswerQ shortAns = new ShortAnswerQ();
		try{
			shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		}catch(ObjectNotFoundException e){
			shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, saQId);
		}
		shortAns.setHelpText(helpText);
		
		this.sessionFactory.getCurrentSession().save(shortAns);
	}

	@Override
	public void updateOrderId(List<Integer> saQIdList)
			throws UserNotFoundException {
		
		ShortAnswerQ shortAns = new ShortAnswerQ();
		
		for(int i = 0; i <saQIdList.size(); i++){
			try{
				shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQIdList.get(i));
			}catch(ObjectNotFoundException e){
				shortAns = (ShortAnswerQ)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, saQIdList.get(i));
			}
			shortAns.setOrderId(i);
			this.sessionFactory.getCurrentSession().save(shortAns);
		}
	}

	@Override
	public ShortAnswerQ getShortAnswerById(Integer saQId) 
		throws UserNotFoundException {
		
		ShortAnswerQ shortAnswerQ = new ShortAnswerQ();
		try{
			shortAnswerQ = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		}catch(ObjectNotFoundException e){
			shortAnswerQ = (ShortAnswerQ)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, saQId);
		}
		return shortAnswerQ;
	}

	@Override
	public List<ShortAnswerQ> getShortAnswerBySubChapter(Integer subChapId)
			throws UserNotFoundException {

		List<ShortAnswerQ> shortAnsList;
		
		try{
			shortAnsList = (List<ShortAnswerQ>)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, subChapId);
		}catch(ObjectNotFoundException e){
			shortAnsList = (List<ShortAnswerQ>)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, subChapId);
		}
		
		return shortAnsList;
	}

}
