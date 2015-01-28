package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.model.MultiChoiceQ;

public class MultiChoiceQDAOImpl implements MultiChoiceQDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addMultiChoice(MultiChoiceQ multiChoiceQ) {
		
		this.sessionFactory.getCurrentSession().save(multiChoiceQ);
		
		return multiChoiceQ.getMultiQuesId();
	}

	@Override
	public void deleteMultiChoice(Integer multiChoiceId) throws ObjectNotFoundException {

		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		this.sessionFactory.getCurrentSession().delete(multiChoice);
	}

	@Override
	public void updateOrder(List<Integer> multiChoiceIdList)
			throws ObjectNotFoundException {

		MultiChoiceQ multiChoice = new MultiChoiceQ();
		for (int i = 0; i < multiChoiceIdList.size(); i++){
			multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceIdList.get(i));
			multiChoice.setOrderId(i);
			this.sessionFactory.getCurrentSession().save(multiChoice);
		}
	}

	@Override
	public void updateQuestionText(Integer multiChoiceId, String quesText)
			throws ObjectNotFoundException {
		
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		multiChoice.setQuesText(quesText);
		this.sessionFactory.getCurrentSession().save(multiChoice);	
	}

	@Override
	public void updateHelpText(Integer multiChoiceId, String helpText)
			throws ObjectNotFoundException {
		
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		multiChoice.setHelpText(helpText);
		this.sessionFactory.getCurrentSession().save(multiChoice);
	}

	@Override
	public void updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer)
			throws ObjectNotFoundException {
		
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		try{
			multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().load(MultiChoiceQ.class, multiChoiceId);
		}catch(ObjectNotFoundException e){
			multiChoice = (MultiChoiceQ)this.sessionFactory.getCurrentSession().get(MultiChoiceQ.class, multiChoiceId);
		}
		multiChoice.setCorrectAnswer(correctAnswer);
		this.sessionFactory.getCurrentSession().save(multiChoice);
	}

	@Override
	public List<MultiChoiceQ> getAllMultiChoice(Integer subChapId) {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("FROM MULTI_CHOICE_Q WHERE SUB_CHAP_ID = :subChapId");
		query.setParameter("subChapId", subChapId);
		List<MultiChoiceQ> multiChoiceList = query.list();
		return multiChoiceList;
	}

}