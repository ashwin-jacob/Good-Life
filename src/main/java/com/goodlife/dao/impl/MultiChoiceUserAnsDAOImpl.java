package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceUserAns;

@Repository
public class MultiChoiceUserAnsDAOImpl implements MultiChoiceUserAnsDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addMultiChoiceAnswer(MultiChoiceUserAns multiChoiceAns) throws ObjectNotFoundException {
		
		this.sessionFactory.getCurrentSession().save(multiChoiceAns);
	}

	@Override
	public Integer getUserAnswer(Integer userId, Integer multiQuesId)
			throws ObjectNotFoundException {
		
		List<MultiChoiceUserAns> multiChoiceAns;
		Query query;
		query = this.sessionFactory.getCurrentSession().createQuery("FROM MC_USER_ANS WHERE USR_ID = :userId AND MC_Q_ID = :multiQuesId");
		query.setParameter("userId", userId);
		query.setParameter("multiQuesId", multiQuesId);
		multiChoiceAns = query.list();
		if(multiChoiceAns.size() > 1){
			throw new ObjectNotFoundException(null, "User Id: " + userId + " and Multiple Choice Question Id: " + multiQuesId + " return too many results!");
		}
		return multiChoiceAns.get(0).getUserAnswer();
	}

}
