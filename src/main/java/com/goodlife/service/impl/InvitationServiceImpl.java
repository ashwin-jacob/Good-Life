package com.goodlife.service.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodlife.dao.UserRoleDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserRole;
import com.goodlife.model.Users;
import com.goodlife.service.InvitationService;
import com.goodlife.service.util.ApplicationMailer;

@Service("invitationService")
@Transactional
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	UsersDAO usersDao;

	@Autowired
	UserRoleDAO userRoleDao;

	@Autowired
	ApplicationMailer mailer;

	private String tempPassword = "Temp$Password";
	private int RandomMin = 100001;
	private int RandomMax = 999999;

	public void InviteUserByUsername(String username, String loggedInUser)
			throws UserAlreadyExistsException {
		Integer randomNumber;
		Users user = usersDao.findByUserName(username);
		if (user != null) {
			throw new UserAlreadyExistsException("User " + username
					+ " already exists!  Ask user to signup if not already done.  If invitation code has been misplaced, a new one can be requested.");
		}

		randomNumber = generateRandomNumber(RandomMin, RandomMax);

		Users newUser = new Users();
		newUser.setUsername(username);
		newUser.setEnabled(false);
		newUser.setPassword(tempPassword);
		newUser.setInvited_by(loggedInUser);
		newUser.setInvited_date(new Date());
		newUser.setInvitation_code(randomNumber);

		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_STUDENT");
		userRole.setUser(newUser);

		usersDao.addUser(newUser);
		userRoleDao.addUserRole(userRole);

		String subject = "creating account";
		String body = "random number is " + randomNumber;
		mailer.sendMail(username, subject, body);

	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * Integer.MAX_VALUE - 1
	 *
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random.nextInt(int)
	 */
	private Integer generateRandomNumber(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	@Override
	public void DeleteUser(String username) throws UserNotFoundException{
			usersDao.deleteUser(username);
	}

	@Override
	public void DisableUser(String username)  throws UserNotFoundException {
			usersDao.disableUser(username);
	}

	@Override
	public void EnableUser(String username) throws UserNotFoundException {
			usersDao.enableUser(username);
	}

	@Override
	public void resendInvitation(String username, boolean resetPassword) throws UserAlreadyExistsException, UserNotFoundException {
		Users user = usersDao.findByUserName(username);
		
		if (user==null) {
			throw new UserNotFoundException("Username entered does not exist in our database.");
		}
		
		if (!resetPassword && user.isEnabled()) {
			throw new UserAlreadyExistsException("The user you are trying to signup with already exists and is active.  If you forgot your password, we recommend using the reset password link.");
		}
		
		Integer randomNumber = generateRandomNumber(RandomMin, RandomMax);
		user.setInvitation_code(randomNumber);
		usersDao.addUser(user);
		String subject = "New Invitation Code";
		String body = "Invitation Code is " + randomNumber;
		mailer.sendMail(username, subject, body);
	}

}
