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
	public Integer disableInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRegistered(false);
        Instructor savedInstructor = (Instructor) this.sessionFactory.getCurrentSession().save(user);
        return savedInstructor.getUserId();
	}

	@Override
	public Integer enableInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRegistered(true);
        Instructor savedInstructor = (Instructor) this.sessionFactory.getCurrentSession().save(user);
        return savedInstructor.getUserId();
	}

	@Override
	public Integer addInstructor(Instructor user) {
		Instructor savedInstructor = (Instructor) this.sessionFactory.getCurrentSession().save(user);
		return savedInstructor.getUserId();
	}

	@Override
	public void deleteInstructor(String username) throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
		this.sessionFactory.getCurrentSession().delete(user);
	}
}
