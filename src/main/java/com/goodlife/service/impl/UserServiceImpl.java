package com.goodlife.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodlife.dao.UserRoleDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.InvalidEmailToken;
import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;
import com.goodlife.service.UserService;
import com.goodlife.service.util.PasswordEncoder;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	static final Logger logger = LogManager
			.getLogger(UserServiceImpl.class.getName());

	
	@Autowired
	UsersDAO usersDao;

	@Autowired
	UserRoleDAO userRoleDao;
	
	@Autowired
	PasswordEncoder passwdEncoder;


	public void ActivateAndUpdateUser(String email, String passwd, String token, boolean resetPassword) 
			throws InvalidEmailToken, UserAlreadyExistsException, UserNotFoundException {
		Users user = usersDao.findByUserName(email);
		if (!user.getInvitationCode().toString().equals(token)) {
			throw new InvalidEmailToken("Email token does not match!!");
		}
		
		if (!resetPassword && user.isRegistered()) {
			throw new UserAlreadyExistsException("The user you are trying to signup with already exists and is active.  If you forgot your password, we recommend using the reset password link.");
		}
		user.setRegistered(true);
		user.setPassword(passwdEncoder.encodePassword(passwd));
		usersDao.addUser(user);
	}

}
