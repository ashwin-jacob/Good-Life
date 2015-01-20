package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UploadPostFlagDAO;
import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.model.UploadPostFlag;

public class UploadPostFlagDAOImpl implements UploadPostFlagDAO {
	
	private static final String FIND_WHO_FLAGGED = 
			"select * from UPLOAD_POST_FLAG where flgd_by = :username";
	
	private static final String FIND_BY_POST = 
			"select * from UPLOAD_POST_FLAG where pst_id = :postId";
	
	private static final String FIND_N_FLAGS_BY_POST = 
			"select count(pst_id) from UPLOAD_POST_FLAG where pst_id = :postId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<UploadPostFlag> findFlaggedUserName(String username)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_FLAGGED);
		query.setParameter("username", username);
		List<UploadPostFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public List<UploadPostFlag> findUploadPostFlagbyPostId(Integer id)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_POST);
		query.setParameter("postId", id);
		List<UploadPostFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public void addUploadPostFlag(UploadPostFlag uploadPostFlag) {
		this.sessionFactory.getCurrentSession().save(uploadPostFlag);
	}

	@Override
	public Integer nFlagByPostId(Integer id) throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_FLAGS_BY_POST);
		query.setParameter("postId", id);
		List<Integer> flagList = query.list();
		return flagList.get(0);
	}

}
