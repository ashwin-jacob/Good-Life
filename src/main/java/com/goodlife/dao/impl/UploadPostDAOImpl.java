package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UploadPostDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadPost;

public class UploadPostDAOImpl implements UploadPostDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addUploadPost(String subjectText, String descriptionText,
			String filePath, Integer mediaTypeId, Integer userId)
			throws UserNotFoundException {

		UploadPost uploadPost = new UploadPost();
		uploadPost.setSubjectText(subjectText);
		uploadPost.setDescriptionText(descriptionText);
		uploadPost.setFilePath(filePath);
		uploadPost.setMediaTypeId(mediaTypeId);
		uploadPost.setUserId(userId);
		
		this.sessionFactory.getCurrentSession().save(uploadPost);
		
		return uploadPost.getPostId();
	}

	@Override
	public void deleteUploadPost(Integer postId) throws UserNotFoundException {

		UploadPost uploadPost = new UploadPost();
		try{
			uploadPost = (UploadPost)this.sessionFactory.getCurrentSession().load(UploadPost.class, postId);
		}catch(ObjectNotFoundException e){
			uploadPost = (UploadPost)this.sessionFactory.getCurrentSession().get(UploadPost.class, postId);
		}
	}

	@Override
	public List<UploadPost> getUploadPostByUser(Integer userId)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UploadPost> getRecentPosts() throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
