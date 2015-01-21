package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UploadPostLikeDAO;
import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.UploadPostLike;

public class UploadPostLikeDAOImpl implements UploadPostLikeDAO {
	
	private static final String FIND_WHO_LIKES = 
			"select * from UPLOAD_POST_LIKE where flgd_by = :username";
	
	private static final String FIND_BY_POST = 
			"select * from UPLOAD_POST_LIKE where pst_id = :postId";
	
	private static final String FIND_N_LIKES_BY_POST = 
			"select count(pst_id) from UPLOAD_POST_LIKE where pst_id = :postId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<UploadPostLike> findLikesUserName(String username)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_LIKES);
		query.setParameter("username", username);
		List<UploadPostLike> likeList = query.list();
		return likeList;
	}

	@Override
	public List<UploadPostLike> findUploadPostLikebyPostId(Integer id)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_POST);
		query.setParameter("postId", id);
		List<UploadPostLike> likeList = query.list();
		return likeList;
	}

	@Override
	public void addUploadPostLike(UploadPostLike uploadPostLike) {
		this.sessionFactory.getCurrentSession().save(uploadPostLike);
	}

	@Override
	public Integer nLikeByPostId(Integer id) throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_LIKES_BY_POST);
		query.setParameter("postId", id);
		List<Integer> likeList = query.list();
		return likeList.get(0);
	}

}
