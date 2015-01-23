package com.goodlife.service;

import java.util.List;

import com.goodlife.exceptions.InvalidEmailToken;
import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

public interface UserService {

	public void activateAndUpdateUser(String email, String passwd, String token, boolean resetPassword)
			throws InvalidEmailToken, UserAlreadyExistsException, UserNotFoundException;
	
	public Users findByUserName(String username) throws UserNotFoundException;
	
	public List<Users> findByFirstName(String firstname) throws UserNotFoundException;
	
	public List<Users> findByLastName(String lastname) throws UserNotFoundException;
	
	public List<Users> findByEmail(String email) throws UserNotFoundException;
	
	public List<Users> findByRoleType(char roleTypeCode) throws UserNotFoundException;
	
	//TODO
	//public Integer nFlags(String username);
}
