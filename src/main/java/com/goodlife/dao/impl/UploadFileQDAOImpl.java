package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceQ;
import com.goodlife.model.UploadFileQ;
import com.goodlife.model.Users;

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
	public void updateDescription(Integer uploadQuesId, String description) throws ObjectNotFoundException {

		UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
		uploadFileQ.setDescription(description);
	}

	@Override
	public void updateHelpText(Integer uploadQuesId, String helpText) throws ObjectNotFoundException {

		UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
		uploadFileQ.setHelpText(helpText);
	}

	@Override
	public UploadFileQ getUploadFileQuestion(Integer uploadFileQId) throws ObjectNotFoundException {

		UploadFileQ uploadFileQ = new UploadFileQ();
		try{
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().load(UploadFileQ.class, uploadFileQId);
		}catch(ObjectNotFoundException e){
			uploadFileQ = (UploadFileQ)this.sessionFactory.getCurrentSession().get(UploadFileQ.class, uploadFileQId);
		}
		if (uploadFileQ == null) {
			throw new ObjectNotFoundException(null, "Upload question with id " + uploadFileQId + " not found.");
		}
		
		return uploadFileQ;
	}

	@Override
	public List<UploadFileQ> findAllUploadFileQBySubchapId(Integer subChapId) {
		String SQL = "from UPLOAD_FILE_Q where sub_chap_id = :subChapId";
		Query query = this.sessionFactory.getCurrentSession().createQuery(SQL).setParameter("subChapId", subChapId);
		List<UploadFileQ> quesList = query.list();
		return quesList;
	}

	@Override
	public void updateOrder(List<Integer> quesIdList) throws ObjectNotFoundException {
		UploadFileQ uploadFileQ;
		for (int i = 0; i < quesIdList.size(); i++){
			uploadFileQ = (UploadFileQ) this.sessionFactory.getCurrentSession().load(UploadFileQ.class, quesIdList.get(i));
			uploadFileQ.setOrderId(i);
			this.sessionFactory.getCurrentSession().save(uploadFileQ);
		}
	}

}
