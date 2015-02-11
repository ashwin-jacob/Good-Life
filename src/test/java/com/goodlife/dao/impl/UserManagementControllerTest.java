package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.ChapterController;
import com.goodlife.controller.UserManagementController;
import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.Users;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class UserManagementControllerTest {

	private static final Integer USER_ID = 1;
	private static final String USER_NAME = "dhaval";
	private static final Integer INV_CD = 123456;
	private static final char ROLE = 'S';
	private static final String EMAIL = "dhaval.raj@tsgforce.com";
	private static final String FNAME = "Dhaval";
	private static final String LNAME = "Raj";
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private UserStatusDAO userStatus;
	
	@Autowired
	private UserManagementController userManagement;
	
	@Before
	public void setUp() throws UserNotFoundException {
		Users user = createUser();
	}

	@Test
	@Transactional
	public void testGetList() {
		List<Users> userList = userManagement.getList(new ModelMap(), "Raj", "lst_nm", 1, 0, 0).getcontent();
		assertEquals(userList.get(0).getFirstname(),"Dhaval");
	}
	
	@Test
	@Transactional
	public void testActivateUser() throws UserNotFoundException{
		assertTrue(userManagement.activateUser(USER_ID).getcontent());
	}
	
	@Test
	@Transactional
	public void testSuspendUser() throws UserNotFoundException{
		assertTrue(userManagement.suspendUser(USER_ID, new Date()).getcontent());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteUser() throws UserNotFoundException{
		assertTrue(userManagement.deleteUser(USER_ID).getcontent());
	}
	
	public static Users createUser() {
		Users user = new Users();
		user.setUsername(USER_NAME);
		user.setInvitationCode(INV_CD);
		user.setEmail(EMAIL);
		user.setFirstname(FNAME);
		user.setLastname(LNAME);
		user.setRoleTypeCode(ROLE);
		return user;
	}
}
