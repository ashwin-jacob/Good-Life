package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceQ;
import com.goodlife.model.MultiChoiceUserAns;

@Repository
public class MultiChoiceUserAnsDAOImpl implements MultiChoiceUserAnsDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private MultiChoiceQDAO multiChoiceQDAO;
	
	@Override
	public Boolean addMultiChoiceAnswer(MultiChoiceUserAns multiChoiceAns) throws ObjectNotFoundException {
		try {
			multiChoiceQDAO.getMultiChoiceQById(multiChoiceAns.getMultiQuesId());
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceAns);
			return Boolean.TRUE;
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public Integer getUserAnswer(Integer userId, Integer multiQuesId)
			throws ObjectNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceUserAns.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("multiQuesId", multiQuesId),Restrictions.eqOrIsNull("userId", userId)));
		MultiChoiceUserAns multiChoiceAns = (MultiChoiceUserAns) criteria.uniqueResult();
		if(multiChoiceAns == null)
			return null;
		else
			return multiChoiceAns.getUserAnswer();
	}

	@Override
	public Boolean isMultiChoiceCorrect(Integer userId, Integer multiQuesId)
			throws ObjectNotFoundException {
		Integer userAns = getUserAnswer(userId,multiQuesId);
		try {
			Integer corrAns = multiChoiceQDAO.getMultiChoiceQById(multiQuesId).getCorrectAnswer();
			return Boolean.valueOf(userAns == corrAns);
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
	}

	@Override
	public Boolean isMultiChoiceSubChapComplete(Integer userId,
			Integer subChapId) throws ObjectNotFoundException {
		Integer userAns;
		Boolean isComplete = Boolean.TRUE;
		List<MultiChoiceQ> multiChoiceQList = multiChoiceQDAO.getAllMultiChoice(subChapId);
		if(multiChoiceQList == null || multiChoiceQList.isEmpty())
			isComplete = Boolean.FALSE;
		else{
			for(int i = 0; i < multiChoiceQList.size(); i++){
				userAns = getUserAnswer(userId,multiChoiceQList.get(i).getMultiQuesId());
				if(userAns == null)
					isComplete =  Boolean.FALSE;
			}
		}
		
		return isComplete;
	}

	@Override
	public MultiChoiceUserAns getUserAnswerObj(Integer userId,
			Integer multiQuesId) throws ObjectNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceUserAns.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("multiQuesId", multiQuesId),Restrictions.eqOrIsNull("userId", userId)));
		MultiChoiceUserAns multiChoiceAnsObj = (MultiChoiceUserAns) criteria.uniqueResult();
		if(multiChoiceAnsObj == null)
			return null;
		else
			return multiChoiceAnsObj;
	}

}
