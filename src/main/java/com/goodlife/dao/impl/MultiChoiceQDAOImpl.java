package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceQ;

public class MultiChoiceQDAOImpl implements MultiChoiceQDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addMultiChoice(String quesText, String helpText,
			Integer correctAnswer, Integer subChapId, Integer orderId)
			throws UserNotFoundException {
		
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice.setQuesText(quesText);
		multiChoice.setHelpText(helpText);
		multiChoice.setCorrectAnswer(correctAnswer);
		multiChoice.setSubChapId(subChapId);
		multiChoice.setOrderId(orderId);
		
		this.sessionFactory.getCurrentSession().save(multiChoice);
		
		return multiChoice.getMultiQuesId();
	}

	@Override
	public void deleteMultiChoice(Integer multiChoiceId)
			throws UserNotFoundException {

		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		this.sessionFactory.getCurrentSession().delete(multiChoice);
	}

	@Override
	public void updateOrder(List<Integer> multiChoiceIdList)
			throws UserNotFoundException {

		MultiChoiceQ multiChoice = new MultiChoiceQ();
		for (int i = 0; i < multiChoiceIdList.size(); i++){
			multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceIdList.get(i));
			multiChoice.setOrderId(i);
			this.sessionFactory.getCurrentSession().save(multiChoice);
		}
	}

	@Override
	public void updateQuestionText(Integer multiChoiceId, String quesText)
			throws UserNotFoundException {
		
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		multiChoice.setQuesText(quesText);
		this.sessionFactory.getCurrentSession().save(multiChoice);	
	}

	@Override
	public void updateHelpText(Integer multiChoiceId, String helpText)
			throws UserNotFoundException {
		
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		multiChoice.setHelpText(helpText);
		this.sessionFactory.getCurrentSession().save(multiChoice);
	}

	@Override
	public void updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer)
			throws UserNotFoundException {
		
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		multiChoice.setCorrectAnswer(correctAnswer);
		this.sessionFactory.getCurrentSession().save(multiChoice);
	}

}