package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UploadAnsLikeDAO;
import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.CommentLike;
import com.goodlife.model.UploadAnsLike;

public class UploadAnsLikeDAOImpl implements UploadAnsLikeDAO {
	
	private static final String FIND_WHO_LIKES = 
			"select * from UPLOAD_ANS_LIKE where flgd_by = :username";
	
	private static final String FIND_BY_ANS = 
			"select * from UPLOAD_ANS_LIKE where ans_id = :ansId";
	
	private static final String FIND_N_LIKES_BY_ANS = 
			"select count(ans_id) from UPLOAD_ANS_LIKE where ans_id = :ansId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<UploadAnsLike> findLikesUserName(String username)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_LIKES);
		query.setParameter("username", username);
		List<UploadAnsLike> likeList = query.list();
		return likeList;
	}

	@Override
	public List<UploadAnsLike> findUploadAnsLikebyAnsId(Integer id)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_ANS);
		query.setParameter("ansId", id);
		List<UploadAnsLike> likeList = query.list();
		return likeList;
	}

	@Override
	public void addUploadAnsLike(UploadAnsLike uploadAnsLike) {
		this.sessionFactory.getCurrentSession().save(uploadAnsLike);
	}

	@Override
	public Integer nLikeByAnsId(Integer id) throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_LIKES_BY_ANS);
		query.setParameter("ansId", id);
		List<Integer> likeList = query.list();
		return likeList.get(0);
	}

}
