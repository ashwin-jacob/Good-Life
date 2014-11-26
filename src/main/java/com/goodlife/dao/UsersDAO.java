package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

public interface UsersDAO 
{
	public Users findByUserName(String username);
	void addUser(Users user);
	void deleteUser(String username) throws UserNotFoundException;
	public void disableUser(String username) throws UserNotFoundException;
	public void enableUser(String username) throws UserNotFoundException;
}