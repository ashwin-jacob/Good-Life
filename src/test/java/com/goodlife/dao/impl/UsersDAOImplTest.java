package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" }) 
public class UsersDAOImplTest {

	private static final Integer USER_ID = 1;
	private static final String USER_NAME = "dhaval";
	private static final Integer INV_CD = 123456;
	private static final char ROLE = 'S';
	private static final String EMAIL = "dhaval.raj@tsgforce.com";
	private static final String FNAME = "Dhaval";
	private static final String LNAME = "Raj";
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Before
	public void setUp() {
		//usersDAO = mock(UsersDAO.class);
		Users user = createUser();
		//usersDAO.addUser(user);
	}

	@Test
	public void testDeleteUser() throws UserNotFoundException {
		usersDAO.deleteUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertNull(found);
	}

	@Test
	public void testFindByUserName() throws UserNotFoundException {
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(found.getUserId(), USER_ID);
	}

	@Test
	public void testDisableUser() throws UserNotFoundException {
		usersDAO.disableUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(found.isRegistered(), Boolean.FALSE);
	}

	@Test
	public void testEnableUser() throws UserNotFoundException {
		usersDAO.enableUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(found.isRegistered(), Boolean.TRUE);
	}

//	@Test
//	public void testFindByRoleType() throws UserNotFoundException {
//		List<Users> moderators = usersDAO.findByRoleType(ROLE);
//		assertTrue(moderators.getClass().isArray());
//		if (moderators != null) {
//			assertTrue(moderators instanceof Users);
//		}
//	}

	@Test
	public void testFindByFirstName() throws UserNotFoundException {
		List<Users> users = usersDAO.findByFirstName(FNAME);
		assertTrue(users.getClass().isArray());
		if (users != null) {
			assertTrue(users instanceof Users);
		}
	}

	@Test
	public void testFindByLastName() throws UserNotFoundException {
		List<Users> users = usersDAO.findByLastName(LNAME);
		System.out.println("SIZWE:" + users.size());
		assertTrue(users.size() == 1);
		//assertTrue(users.getClass().isArray());
//		if (users != null) {
//			assertTrue(users instanceof Users);
//		}
	}

	@Test
	public void testFindByEmail() throws UserNotFoundException {
		List<Users> users = usersDAO.findByEmail(EMAIL);
		assertTrue(users.getClass().isArray());
		if (users != null) {
			assertTrue(users instanceof Users);
		}
	}
	
	public static Users createUser() {
		Users user = new Users();
		user.setUsername(USER_NAME);
		user.setInvitationCode(INV_CD);
		user.setEmail(EMAIL);
		user.setFirstname(FNAME);
		user.setLastname(LNAME);
		return user;
	}
}
