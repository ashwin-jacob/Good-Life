package com.goodlife.service;

import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;

public interface InvitationService {

	public void InviteUserByUsername(String username, String loggedInUser)
			throws UserAlreadyExistsException;

	public void DeleteUser(String username) throws UserNotFoundException;

	public void DisableUser(String username) throws UserNotFoundException;

	public void EnableUser(String username) throws UserNotFoundException;

	public void resendInvitation(String username, boolean resetPassword) throws UserAlreadyExistsException, UserNotFoundException;
}
