package com.goodlife.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserStatus;

public interface UserStatusDAO{
	
	public List<UserStatus> findByUserId(Integer userId) throws UserNotFoundException;
	public List<UserStatus> findByStatusCode(char statusTypeCode)throws UserNotFoundException;
	public List<UserStatus> findCurrentSuspendedUsers() throws UserNotFoundException;
	public Integer findNumberofSuspensionsByUserId(Integer userId) throws UserNotFoundException;
	public Integer addUserStatus(UserStatus userStatus) throws UserNotFoundException;
	public Boolean changeEndDate(Integer userStatusId, Date newEndDate) throws UserNotFoundException;
	public Boolean deleteUserStatus(Integer userStatusId) throws UserNotFoundException;
	public UserStatus findByUserStatusId(Integer userStatusId) throws ObjectNotFoundException;
	public UserStatus findCurrentStatusByUser(Integer userId);
}
