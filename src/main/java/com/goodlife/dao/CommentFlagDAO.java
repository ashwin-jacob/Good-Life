package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.CommentFlag;

public interface CommentFlagDAO {
	
	public void addCommentFlag(CommentFlag commentFlag);	
	public List<CommentFlag> findFlaggedUserName(String username) throws FlagNotFoundException;
	public List<CommentFlag> findCommentFlagbyCmtId(Integer id) throws FlagNotFoundException;
	public Integer nFlagByCmtId(Integer id) throws FlagNotFoundException;
}