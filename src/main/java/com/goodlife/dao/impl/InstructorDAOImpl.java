package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.InstructorDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;

@Repository
public class InstructorDAOImpl implements InstructorDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException {
		Instructor user = (Instructor) this.sessionFactory.getCurrentSession().get(Instructor.class, username);
		if (null == user) {
        	throw new UserNotFoundException("User: " + username + ".  Not found in the database!");
        }
		return user;
	}
	
	@Override
	public void promoteInstructor(String username, char roleTypeCode)
			throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRoleTypeCode(roleTypeCode);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void disableInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRegistered(false);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void enableInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRegistered(true);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void addInstructor(Instructor user) {
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void deleteInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
		this.sessionFactory.getCurrentSession().delete(user);
	}
	
	@Override
	public void suspendInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
		//Integer usrStsId = user.getUserStatusId();
		// TODO
		// UserStatus status = (UserStatus) this.sessionFactory.getCurrentSession().get(UserStatus.class, usrStsId);
		// status.setStatusTypeCode("S");
		this.sessionFactory.getCurrentSession().save(user);
		// this.sessionFactory.getCurrentSession().save(status);
	}
	
	@Override
	public void activateInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
		//Integer usrStsId = user.getUserStatusId();
		// TODO
		// UserStatus status = (UserStatus) this.sessionFactory.getCurrentSession().get(UserStatus.class, usrStsId);
		// status.setStatusTypeCode("A");
		this.sessionFactory.getCurrentSession().save(user);
		// this.sessionFactory.getCurrentSession().save(status);
	}
}
