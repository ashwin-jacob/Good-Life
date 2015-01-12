package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

public interface UsersDAO 
{
	public Users findByUserName(String username) throws UserNotFoundException;
	public List<Users> findByFirstName(String firstname) throws UserNotFoundException;
	public List<Users> findByLastName(String lastname) throws UserNotFoundException;
	public List<Users> findByRoleType(String roleTypeCode) throws UserNotFoundException;
	public List<Users> findByEmail(String email) throws UserNotFoundException;
	// public List<Users> findSuspended() throws UserNotFoundException;
	
	void addUser(Users user);
	void deleteUser(String username) throws UserNotFoundException;
	public void disableUser(String username) throws UserNotFoundException;
	public void enableUser(String username) throws UserNotFoundException;
}