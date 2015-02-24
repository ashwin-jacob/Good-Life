package com.goodlife.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.model.UploadFileQ;

@Repository
public class UploadFileQDAOImpl implements UploadFileQDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addUploadFileQuestion(UploadFileQ uploadFileQ) {

		this.sessionFactory.getCurrentSession().save(uploadFileQ);
		
		return uploadFileQ.getUploadQuesId();
	}
	
	@Override
	public Boolean updateDescription(Integer uploadQuesId, String description) throws ObjectNotFoundException {

		UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
		if(uploadFileQ == null)
			throw new ObjectNotFoundException(null,"Upload Question Id: " + uploadQuesId + " not found.");
		uploadFileQ.setDescription(description);
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateHelpText(Integer uploadQuesId, String helpText) throws ObjectNotFoundException {

		UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
		if(uploadFileQ == null)
			throw new ObjectNotFoundException(null,"Upload Question Id: " + uploadQuesId + " not found.");
		uploadFileQ.setHelpText(helpText);
		return Boolean.TRUE;
	}

	@Override
	public UploadFileQ getUploadFileQuestion(Integer uploadFileQId) throws ObjectNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadFileQ.class);
		criteria.add(Restrictions.eqOrIsNull("uploadQuesId", uploadFileQId));
		UploadFileQ uploadFileQ = (UploadFileQ) criteria.uniqueResult();
		if (uploadFileQ == null) {
			throw new ObjectNotFoundException(null, "Upload question with id " + uploadFileQId + " not found.");
		}
		return uploadFileQ;
	}

	@Override
	public UploadFileQ getUploadFileQBySubchapId(Integer subChapId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadFileQ.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		UploadFileQ quesList = (UploadFileQ) criteria.uniqueResult();
		return quesList;
	}

	@Override
	public Boolean deleteUploadFileQuestion(Integer uploadQuesId) throws ObjectNotFoundException{
		UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
		if(uploadFileQ == null)
			return Boolean.FALSE;
		else{
			this.sessionFactory.getCurrentSession().delete(uploadFileQ);
			return Boolean.TRUE;
		}
	}

	@Override
	public Boolean setPublishedUploadFileQ(Integer uploadFileQId, Boolean published){
		
		UploadFileQ uploadFileQ = getUploadFileQuestion(uploadFileQId);
		if(uploadFileQ == null)
			return Boolean.FALSE;
		else{
			uploadFileQ.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(uploadFileQ);
			return Boolean.TRUE;
		}
	}

}
