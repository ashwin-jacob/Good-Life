package com.goodlife.dao.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<UploadFileQ> findAllUploadFileQBySubchapId(Integer subChapId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadFileQ.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		List<UploadFileQ> quesList = criteria.list();
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

	/*@Override
	public Boolean updateOrder(List<Integer> quesIdList) throws ObjectNotFoundException {
		UploadFileQ uploadFileQ = new UploadFileQ();
		Criteria criteria;
		for (int i = 0; i < quesIdList.size(); i++){
			criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadFileQ.class);
			criteria.add(Restrictions.eqOrIsNull("uploadQuesId", quesIdList.get(i)));
			uploadFileQ = (UploadFileQ) criteria.uniqueResult();
			if(uploadFileQ == null)
				throw new ObjectNotFoundException(null,"Upload Question Id: " + quesIdList.get(i) + " not found.");
			uploadFileQ.setOrderId(i+1);
			this.sessionFactory.getCurrentSession().save(uploadFileQ);
		}
		return Boolean.TRUE;
	}*/

}
