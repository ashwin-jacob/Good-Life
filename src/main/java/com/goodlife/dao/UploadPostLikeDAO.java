package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.UploadPostLike;

public interface UploadPostLikeDAO {
	
	public void addUploadPostLike(UploadPostLike uploadPostLike);	
	public List<UploadPostLike> findLikesUserName(String username) throws LikeNotFoundException;
	public List<UploadPostLike> findUploadPostLikebyPostId(Integer id) throws LikeNotFoundException;
	public Integer nLikeByPostId(Integer id) throws LikeNotFoundException;
}