package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goodlife.dao.UsersDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class UserDaoImplTest {
	
	@Autowired
	UsersDAO userDAO; 

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByUserName() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisableUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnableUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByRoleType() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByFirstName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByLastName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByEmail() {
		fail("Not yet implemented");
	}

}
