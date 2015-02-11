package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

public interface UsersDAO 
{
	public Users findByUserName(String username) throws UserNotFoundException;
	public List<Users> findByFirstName(String firstname) throws UserNotFoundException;
	public List<Users> findByLastName(String lastname) throws UserNotFoundException;
	public List<Users> findByRoleTypes(List<Character> roles) throws UserNotFoundException;
	public Users findByEmail(String email) throws UserNotFoundException;
	public List<Users> findByCity(String city) throws UserNotFoundException;
	public List<Users> findByState(String state) throws UserNotFoundException;
	public List<Users> advancedQuery(String input, String field, List<Character> roles) 
			throws UserNotFoundException;
	
	Integer addUser(Users user);
	Integer deleteUser(String username) throws UserNotFoundException;
	public void disableUser(String username) throws UserNotFoundException;
	public void enableUser(String username) throws UserNotFoundException;
	public Integer promoteUser(String username, char roleTypeCode) throws UserNotFoundException;
	public Users findByUserId(Integer us) throws UserNotFoundException;
}