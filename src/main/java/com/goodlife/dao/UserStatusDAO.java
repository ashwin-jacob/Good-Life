package com.goodlife.dao;

import java.util.Date;
import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

public interface UserStatusDAO{
	
	public List<UserStatus> findByUserId(Integer userId) throws UserNotFoundException;
	public UserStatus findByUserStatusId(Integer userStatusId) throws UserNotFoundException;
	public List<UserStatus> findByStatusCode(char statusTypeCode)throws UserNotFoundException;
	public List<UserStatus> findCurrentSuspendedUsers() throws UserNotFoundException;
	public Integer findNumberofSuspensionsByUserId(Integer userId) throws UserNotFoundException;
	
	public void suspendUser(UserStatus userStatus) throws UserNotFoundException;
	public void changeEndDate(Integer userStatusId, Date newEndDate) throws UserNotFoundException;
}
