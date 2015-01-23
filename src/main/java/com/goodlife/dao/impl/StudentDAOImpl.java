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
	public Integer promoteStudent(String username, char roleTypeCode)
			throws UserNotFoundException {
		Student user = findStudentByUserName(username);
        user.setRoleTypeCode(roleTypeCode);
       	Student saved = (Student) this.sessionFactory.getCurrentSession().save(user);
       	return saved.getUserId();
	}
	
	@Override
	public Integer disableStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
		user.setRegistered(false);
		Student saved = (Student) this.sessionFactory.getCurrentSession().save(user);
		return saved.getUserId();
	}

	@Override
	public Integer addExistingStudentToRoster(String username, Integer rosterId)
			throws UserNotFoundException {
		Student user = findStudentByUserName(username);
        user.setRosterId(rosterId);
        Student saved = (Student) this.sessionFactory.getCurrentSession().save(user);	
        return saved.getUserId();
	}

	@Override
	public Integer enableStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
		user.setRegistered(true);
		Student saved = (Student) this.sessionFactory.getCurrentSession().save(user);
		return saved.getUserId();
	}

	@Override
	public void deleteStudent(String username) throws UserNotFoundException {
		Student user = findStudentByUserName(username);
        this.sessionFactory.getCurrentSession().delete(user);		
	}

	@Override
	public Integer addStudent(Student user) {
		Student saved = (Student) this.sessionFactory.getCurrentSession().save(user);	
		return saved.getUserId();
	}
}
