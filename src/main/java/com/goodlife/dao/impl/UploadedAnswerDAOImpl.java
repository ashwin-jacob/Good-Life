package com.goodlife.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.model.UploadFileQ;
import com.goodlife.model.UploadedAnswer;

@Repository
public class UploadedAnswerDAOImpl implements UploadedAnswerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UploadFileQDAO uploadFileQDAO;
	
	@Override
	public Integer addUploadedAnswer(UploadedAnswer uploadedAnswer) {
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(uploadedAnswer);
		
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
		this.sessionFactory.getCurrentSession().saveOrUpdate(uploadedAnswer);
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
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadedAnswer.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("userId", userId), Restrictions.eqOrIsNull("uploadQuesId", uploadQuesId)));
		
		UploadedAnswer uploadedAnswer = (UploadedAnswer) criteria.uniqueResult();
		
		if (uploadedAnswer == null) {
			throw new ObjectNotFoundException(null, 
					"User answer for user " + userId + " upload question " + uploadQuesId + " not found.");
		}
		return uploadedAnswer;
	}
	
	@Override
	public Boolean isUploadedQuestionComplete(Integer userId, Integer subChapId){
		Boolean isComplete = Boolean.TRUE;
		UploadFileQ uploadFileQ = uploadFileQDAO.getUploadFileQBySubchapId(subChapId);
		if(uploadFileQ == null)
			isComplete = Boolean.FALSE;
		else if(getUserAnswer(userId,uploadFileQDAO.getUploadFileQBySubchapId(subChapId).getUploadQuesId()) == null ||
				getUserAnswer(userId,uploadFileQDAO.getUploadFileQBySubchapId(subChapId).getUploadQuesId()).isAprvd() == false)
			isComplete = Boolean.FALSE;
		return isComplete;
	}

}
