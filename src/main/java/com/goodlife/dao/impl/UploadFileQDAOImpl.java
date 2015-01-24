package com.goodlife.dao.impl;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadFileQ;

public class UploadFileQDAOImpl implements UploadFileQDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addUploadFileQuestion(Integer subChapId, String helpText, String description)
			throws UserNotFoundException {

		UploadFileQ uploadFileQ = new UploadFileQ();
		uploadFileQ.setSubChapId(subChapId);
		uploadFileQ.setHelpText(helpText);
		
		this.sessionFactory.getCurrentSession().save(uploadFileQ);
		
		return uploadFileQ.getUploadQuesId();
	}
	
	@Override
	public void updateDescripton(Integer uploadQuesId, String description)
			throws UserNotFoundException {

		UploadFileQ uploadFileQ = new UploadFileQ();
		try{
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().load(UploadFileQ.class, uploadQuesId);
		}catch(ObjectNotFoundException e){
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().get(UploadFileQ.class, uploadQuesId);
		}
		uploadFileQ.setDescription(description);
	}

	@Override
	public void updateHelpText(Integer uploadQuesId, String helpText)
			throws UserNotFoundException {

		UploadFileQ uploadFileQ = new UploadFileQ();
		try{
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().load(UploadFileQ.class, uploadQuesId);
		}catch(ObjectNotFoundException e){
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().get(UploadFileQ.class, uploadQuesId);
		}
		uploadFileQ.setHelpText(helpText);
	}

	@Override
	public UploadFileQ getUploadFileQuestion(Integer subChapId)
			throws UserNotFoundException {

		UploadFileQ uploadFileQ = new UploadFileQ();
		try{
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().load(UploadFileQ.class, subChapId);
		}catch(ObjectNotFoundException e){
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().get(UploadFileQ.class, subChapId);
		}
		
		return uploadFileQ;
	}

}
