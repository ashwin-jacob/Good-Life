package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadedAnswer;


public class UploadedAnswerDAOImpl implements UploadedAnswerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addUploadedAnswer(UploadedAnswer uploadedAnswer)
			throws UserNotFoundException {
		
		this.sessionFactory.getCurrentSession().save(uploadedAnswer);
		
		return uploadedAnswer.getUploadAnswerId();
	}

	@Override
	public Boolean setApproveAnswer(Integer uploadAnswerId, Boolean aprvd)
			throws UserNotFoundException {

		UploadedAnswer uploadedAnswer = new UploadedAnswer();
		try{
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().load(UploadedAnswer.class, uploadAnswerId);
		}catch(ObjectNotFoundException e){
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().get(UploadedAnswer.class, uploadAnswerId);
		}
		uploadedAnswer.setAprvd(aprvd);
		this.sessionFactory.getCurrentSession().save(uploadedAnswer);
		return aprvd;
	}

	@Override
	public Boolean setShareAnswer(Integer uploadAnswerId, Boolean shared)
			throws UserNotFoundException {
		
		UploadedAnswer uploadedAnswer = new UploadedAnswer();
		try{
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().load(UploadedAnswer.class, uploadAnswerId);
		}catch(ObjectNotFoundException e){
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().get(UploadedAnswer.class, uploadAnswerId);
		}
		uploadedAnswer.setShared(shared);
		return null;
	}

	@Override
	public UploadedAnswer getUserAnswer(Integer userId, Integer uploadQuesId)
			throws UserNotFoundException {
		
		List<UploadedAnswer> uploadedAnswer;
		
		Query query;
		query = this.sessionFactory.getCurrentSession().createQuery("FROM UPLOADED_ANS WHERE USR_ID = :userId AND UP_Q_ID = :uploadQuesId");
		query.setParameter("userId", userId);
		query.setParameter("uploadQuesId", uploadQuesId);
		
		uploadedAnswer = query.list();
		
		return uploadedAnswer.get(0);
	}

}
