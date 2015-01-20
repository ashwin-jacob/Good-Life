package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.CommentLike;

public interface CommentLikeDAO {
	
	public void addCommentLike(CommentLike commentLike);	
	public List<CommentLike> findLikesUserName(String username) throws LikeNotFoundException;
	public List<CommentLike> findCommentLikebyCmtId(Integer id) throws LikeNotFoundException;
	public Integer nLikeByCmtId(Integer id) throws LikeNotFoundException;
}