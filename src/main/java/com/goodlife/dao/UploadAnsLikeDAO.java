package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.UploadAnsLike;

public interface UploadAnsLikeDAO {
	
	public void addUploadAnsLike(UploadAnsLike uploadAnsLike);	
	public List<UploadAnsLike> findLikesUserName(String username) throws LikeNotFoundException;
	public List<UploadAnsLike> findUploadAnsLikebyAnsId(Integer id) throws LikeNotFoundException;
	public Integer nLikeByAnsId(Integer id) throws LikeNotFoundException;
}