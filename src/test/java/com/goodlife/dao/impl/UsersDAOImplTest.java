package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import com.goodlife.model.Student;
import com.goodlife.model.Users;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "test-context.xml" }) 
public class UsersDAOImplTest {

	private static final Integer USER_ID = 1234;
	private static final String USER_NAME = "goodlife123";
	private static final Integer INV_CD = 010101;
	private static final char ROLE = 'M';
	private static final String EMAIL = "goodlife123@test.com";
	private static final String FNAME = "good";
	private static final String LNAME = "life";
	
	@Autowired
	private static UsersDAO usersDAO;
	
	@Before
	public void setUp() {
		usersDAO = mock(UsersDAO.class);
		Users user = createUser();
		Integer userNum;
		userNum = usersDAO.addUser(user);
		System.out.println(userNum);
		
	}
	
	/* @Test
	public void testAddUser() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testDeleteUser() throws UserNotFoundException {
		usersDAO.deleteUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertNull(found);
	}

	@Test
	public void testFindByUserName() throws UserNotFoundException {
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(USER_ID, found.getUserId());
	}

	@Test
	public void testDisableUser() throws UserNotFoundException {
		usersDAO.disableUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(Boolean.FALSE, found.isRegistered());
	}

	@Test
	public void testEnableUser() throws UserNotFoundException {
		usersDAO.enableUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(Boolean.TRUE, found.isRegistered());
	}

	@Test
	public void testFindByRoleType() throws UserNotFoundException {
		List<Character> roles = new ArrayList<Character>();
		roles.add(ROLE);
		List<Users> moderators = usersDAO.findByRoleTypes(roles);
		assertTrue(moderators.getClass().isArray());
		if (moderators != null) {
			assertTrue(moderators instanceof Users);
		}
	}

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
		assertTrue(users.getClass().isArray());
		if (users != null) {
			assertTrue(users instanceof Users);
		}
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
		user.setUserId(USER_ID);
		user.setUsername(USER_NAME);
		user.setInvitationCode(INV_CD);
		user.setEmail(EMAIL);
		user.setFirstname(FNAME);
		user.setLastname(LNAME);
		return user;
	}
}
