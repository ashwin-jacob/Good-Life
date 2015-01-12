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
	public void promoteInstructor(String username, String roleTypeCode)
			throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRoleTypeCode(roleTypeCode);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void disableInstructor(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void activateInstructor(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addExistingInstructorToRoster(String username, Integer rosterId)
			throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRosterId(rosterId);
       	this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public void deleteInstructorInRoster(String username, Integer rosterId)
			throws UserNotFoundException {
		Instructor user = findInstructorByUserName(username);
       	user.setRosterId(null);
        this.sessionFactory.getCurrentSession().save(user);			
	}
}
