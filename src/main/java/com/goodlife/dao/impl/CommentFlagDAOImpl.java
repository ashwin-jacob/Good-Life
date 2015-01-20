package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.CommentFlagDAO;
import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.CommentFlag;

public class CommentFlagDAOImpl implements CommentFlagDAO {
	
	private static final String FIND_WHO_FLAGGED = 
			"select * from COMMENT_FLAG where flgd_by = :username";
	
	private static final String FIND_BY_COMMENT = 
			"select * from COMMENT_FLAG where cmmt_id = :commentId";
	
	private static final String FIND_N_FLAGS_BY_CMT = 
			"select count(cmmt_id) from COMMENT_FLAG where cmmt_id = :commentId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<CommentFlag> findFlaggedUserName(String username)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_FLAGGED);
		query.setParameter("username", username);
		List<CommentFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public List<CommentFlag> findCommentFlagbyCmtId(Integer id)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_COMMENT);
		query.setParameter("commentId", id);
		List<CommentFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public void addCommentFlag(CommentFlag commentFlag) {
		this.sessionFactory.getCurrentSession().save(commentFlag);
	}

	@Override
	public Integer nFlagByCmtId(Integer id) throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_FLAGS_BY_CMT);
		query.setParameter("commentId", id);
		List<Integer> flagList = query.list();
		return flagList.get(0);
	}

}
