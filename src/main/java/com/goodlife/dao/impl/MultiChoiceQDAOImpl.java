package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceQ;

@Repository
public class MultiChoiceQDAOImpl implements MultiChoiceQDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addMultiChoice(MultiChoiceQ multiChoiceQ) {
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceQ);
		return multiChoiceQ.getMultiQuesId();
	}

	@Override
	public Boolean deleteMultiChoice(Integer multiChoiceId) throws MultipleChoiceNotFoundException {

		MultiChoiceQ multiChoice = getMultiChoiceQById(multiChoiceId);
		if(multiChoice != null)
			this.sessionFactory.getCurrentSession().delete(multiChoice);
		else
			throw new MultipleChoiceNotFoundException("MultiChoice Question Id: " + multiChoiceId + " not found.");
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateOrder(List<Integer> multiChoiceIdList)
			throws MultipleChoiceNotFoundException {

		MultiChoiceQ multiChoice = new MultiChoiceQ();
		for (int i = 0; i < multiChoiceIdList.size(); i++){
			multiChoice = getMultiChoiceQById(multiChoiceIdList.get(i));
			if(multiChoice == null)
				throw new MultipleChoiceNotFoundException("MultiChoice Question Id: " + multiChoiceIdList.get(i) + " not found.");
			multiChoice.setOrderId(i+1);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateQuestionText(Integer multiChoiceId, String quesText)
			throws MultipleChoiceNotFoundException {
		
		MultiChoiceQ multiChoice = getMultiChoiceQById(multiChoiceId);
		if(multiChoice == null)
			throw new MultipleChoiceNotFoundException("MultiChoice Question Id: " + multiChoiceId + " not found.");
		multiChoice.setQuesText(quesText);
		this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateHelpText(Integer multiChoiceId, String helpText)
			throws MultipleChoiceNotFoundException {
		
		MultiChoiceQ multiChoice = getMultiChoiceQById(multiChoiceId);
		if(multiChoice == null)
			throw new MultipleChoiceNotFoundException("MultiChoice Question Id: " + multiChoiceId + " not found.");
		multiChoice.setHelpText(helpText);
		this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer)
			throws MultipleChoiceNotFoundException {
		
		MultiChoiceQ multiChoice = getMultiChoiceQById(multiChoiceId);
		if(multiChoice == null)
			throw new MultipleChoiceNotFoundException("MultiChoice Question Id: " + multiChoiceId + " not found.");
		multiChoice.setCorrectAnswer(correctAnswer);
		this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		return Boolean.TRUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceQ> getAllMultiChoice(Integer subChapId) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceQ.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		List<MultiChoiceQ> multiChoiceList = criteria.list();
		return multiChoiceList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceQ> getAllPublishedMultiChoice(Integer subChapId) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceQ.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("subChapId", subChapId),Restrictions.eq("published", true)));
		List<MultiChoiceQ> multiChoiceList = criteria.list();
		return multiChoiceList;
	}
	
	@Override
	public MultiChoiceQ getMultiChoiceQById(Integer multiChoiceId) throws MultipleChoiceNotFoundException{
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceQ.class);
		criteria.add(Restrictions.eqOrIsNull("multiQuesId", multiChoiceId));
		MultiChoiceQ multiChoice = (MultiChoiceQ) criteria.uniqueResult();
		if(null == multiChoice){
			throw new MultipleChoiceNotFoundException("Multiple Choice Id: " + multiChoiceId + " not found in database!");
		}
		
		return multiChoice;
	}

	@Override
	public Boolean setPublishMultiChoiceQ(Integer multiChoiceId,
			Boolean published) {

		Boolean isSuccess;
		try {
			MultiChoiceQ multiChoiceQ = getMultiChoiceQById(multiChoiceId);
			multiChoiceQ.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceQ);
			isSuccess = Boolean.TRUE;
		} catch (MultipleChoiceNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}
	

}