package com.goodlife.service;

import com.goodlife.exceptions.InvalidEmailToken;
import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;

public interface UserService {

	public void ActivateAndUpdateUser(String email, String passwd, String token, boolean resetPassword)
			throws InvalidEmailToken, UserAlreadyExistsException, UserNotFoundException;
}
