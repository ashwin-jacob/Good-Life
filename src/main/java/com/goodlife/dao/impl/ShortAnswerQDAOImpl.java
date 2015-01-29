package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.exceptions.ShortAnswerNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.ShortAnswerQ;

@Repository
public class ShortAnswerQDAOImpl implements ShortAnswerQDAO{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Integer addShortAnswerQuestion(ShortAnswerQ shortAnswerQ) throws ShortAnswerNotFoundException {
		
		this.sessionFactory.getCurrentSession().save(shortAnswerQ);
		
		return shortAnswerQ.getSaQId();
	}

	@Override
	public void updateQuestionText(Integer saQId, String question)
			throws ShortAnswerNotFoundException {
		
		ShortAnswerQ shortAns = getShortAnswerById(saQId);
		shortAns.setQuestion(question);
		this.sessionFactory.getCurrentSession().save(shortAns);
	}

	@Override
	public void updateHelpText(Integer saQId, String helpText)
			throws ShortAnswerNotFoundException {
		
		ShortAnswerQ shortAns = getShortAnswerById(saQId);
		shortAns.setHelpText(helpText);
		this.sessionFactory.getCurrentSession().save(shortAns);
	}

	@Override
	public void updateOrderId(List<Integer> saQIdList)
			throws ShortAnswerNotFoundException {
		
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
		throws ShortAnswerNotFoundException {
		
		ShortAnswerQ shortAnswerQ = new ShortAnswerQ();
		try{
			shortAnswerQ = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		}catch(ObjectNotFoundException e){
			shortAnswerQ = (ShortAnswerQ)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, saQId);
		}
		if(null == shortAnswerQ){
			throw new ShortAnswerNotFoundException("Short Answer Question Id: " + saQId + " not found in the database!");
		}
		return shortAnswerQ;
	}

	@Override
	public List<ShortAnswerQ> getShortAnswerBySubChapter(Integer subChapId)
			throws SubChapterNotFoundException {

		List<ShortAnswerQ> shortAnsList;
		
		try{
			shortAnsList = (List<ShortAnswerQ>)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, subChapId);
		}catch(ObjectNotFoundException e){
			shortAnsList = (List<ShortAnswerQ>)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, subChapId);
		}
		if(null == shortAnsList){
			throw new SubChapterNotFoundException("Sub Chapter Id: " + subChapId + " not found in the database!");
		}
		return shortAnsList;
	}

}
