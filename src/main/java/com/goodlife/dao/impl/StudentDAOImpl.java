package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.StudentDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

@Repository
public class StudentDAOImpl implements StudentDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Student findStudentByUserName(String username) throws UserNotFoundException {
		Student user = (Student) this.sessionFactory.getCurrentSession().get(Student.class, username);
		if (null == user) {
        	throw new UserNotFoundException("User: " + username + ".  Not found in the database!");
        }
		return user;
	}
	
	@Override
	public void promoteStudent(String username, char roleTypeCode)
			throws UserNotFoundException {
		Student user = findStudentByUserName(username);
        user.setRoleTypeCode(roleTypeCode);
       	this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void suspendStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
		//Integer usrStsId = user.getUserStatusId();
		//TODO
		this.sessionFactory.getCurrentSession().save(user);
		// this.sessionFactory.getCurrentSession().save(status);
	}
	
	@Override
	public void activateStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
		//Integer usrStsId = user.getUserStatusId();
		// TODO
		this.sessionFactory.getCurrentSession().save(user);
		// this.sessionFactory.getCurrentSession().save(status);
	}
	
	@Override
	public void disableStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
		user.setRegistered(false);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void addExistingStudentToRoster(String username, Integer rosterId)
			throws UserNotFoundException {
		Student user = findStudentByUserName(username);
        user.setRosterId(rosterId);
        this.sessionFactory.getCurrentSession().save(user);	
	}

	@Override
	public void enableStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
		user.setRegistered(true);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void deleteStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
        this.sessionFactory.getCurrentSession().delete(user);		
	}

	@Override
	public void addStudent(Student user) {
        this.sessionFactory.getCurrentSession().save(user);		
	}
}
