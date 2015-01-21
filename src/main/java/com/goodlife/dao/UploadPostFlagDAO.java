package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.model.UploadPostFlag;

public interface UploadPostFlagDAO {
	
	public void addUploadPostFlag(UploadPostFlag uploadPostFlag);	
	public List<UploadPostFlag> findFlaggedUserName(String username) throws FlagNotFoundException;
	public List<UploadPostFlag> findUploadPostFlagbyPostId(Integer id) throws FlagNotFoundException;
	public Integer nFlagByPostId(Integer id) throws FlagNotFoundException;
}