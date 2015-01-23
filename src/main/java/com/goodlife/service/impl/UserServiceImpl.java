package com.goodlife.service.impl;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
	PasswordEncoder passwdEncoder;



	public void activateAndUpdateUser(String email, String passwd, String token, boolean resetPassword) 
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

	
	public Users findByUserName(String username) throws UserNotFoundException {
		return usersDao.findByUserName(username);
	}

	public List<Users> findByFirstName(String firstname) throws UserNotFoundException {
		return usersDao.findByFirstName(firstname);
	}

	public List<Users> findByEmail(String email) throws UserNotFoundException {
		return usersDao.findByEmail(email);
	}

	public List<Users> findByRoleType(char roleTypeCode) throws UserNotFoundException {
		return usersDao.findByRoleType(roleTypeCode);
	}

	public List<Users> findByLastName(String lastname) throws UserNotFoundException {
		return usersDao.findByLastName(lastname);
	}
	
	public List<Users> findByCity(String city) throws UserNotFoundException {
		return usersDao.findByCity(city);
	}
	
	public List<Users> findByState(String state) throws UserNotFoundException {
		return usersDao.findByState(state);
	}

}
