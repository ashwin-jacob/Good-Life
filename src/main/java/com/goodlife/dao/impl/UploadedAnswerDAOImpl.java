package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadedAnswer;

@Repository
public class UploadedAnswerDAOImpl implements UploadedAnswerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addUploadedAnswer(UploadedAnswer uploadedAnswer) {
		
		this.sessionFactory.getCurrentSession().save(uploadedAnswer);
		
		return uploadedAnswer.getUploadAnswerId();
	}

	@Override
	public Boolean setApproveAnswer(Integer uploadAnswerId, Boolean aprvd)
			throws ObjectNotFoundException {

		UploadedAnswer uploadedAnswer;
		try {
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().load(UploadedAnswer.class, uploadAnswerId);
		} catch (ObjectNotFoundException e){
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().get(UploadedAnswer.class, uploadAnswerId);
		}
		
		if (uploadedAnswer == null) {
			throw new ObjectNotFoundException(null, "No uploaded answer found with id: " + uploadAnswerId);
		}
		
		uploadedAnswer.setAprvd(aprvd);
		this.sessionFactory.getCurrentSession().save(uploadedAnswer);
		return aprvd;
	}

	@Override
	public Boolean setSharedAnswer(Integer uploadAnswerId, Boolean shared)
			throws ObjectNotFoundException {
		
		UploadedAnswer uploadedAnswer;
		try{
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().load(UploadedAnswer.class, uploadAnswerId);
		}catch(ObjectNotFoundException e){
			uploadedAnswer = (UploadedAnswer)this.sessionFactory.getCurrentSession().get(UploadedAnswer.class, uploadAnswerId);
		}
		if (uploadedAnswer == null) {
			throw new ObjectNotFoundException(null, "No uploaded answer found with id: " + uploadAnswerId);
		}
		
		uploadedAnswer.setShared(shared);
		return null;
	}

	@Override
	public UploadedAnswer getUserAnswer(Integer userId, Integer uploadQuesId)
			throws ObjectNotFoundException {
		
		List<UploadedAnswer> uploadedAnswer;
		
		Query query;
		query = this.sessionFactory.getCurrentSession().createQuery("FROM UPLOADED_ANS WHERE USR_ID = :userId AND UP_Q_ID = :uploadQuesId");
		query.setParameter("userId", userId);
		query.setParameter("uploadQuesId", uploadQuesId);
		
		uploadedAnswer = query.list();
		
		if (uploadedAnswer == null) {
			throw new ObjectNotFoundException(null, 
					"User answer for user " + userId + " upload question " + uploadQuesId + " not found.");
		}
		return uploadedAnswer.get(0);
	}

}
