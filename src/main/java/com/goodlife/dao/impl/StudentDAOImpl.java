package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.StudentDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO  {

	private static final String QUERY_ROSTER = 
			"from USERS user where user.roster_id = :rosterId and user.role_typ_cd = " + 'S';
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Student findStudentByUserName(String username) throws UserNotFoundException {
		Student user = (Student) this.sessionFactory.getCurrentSession().get(Student.class, username);
		if (null == user) {
        	throw new UserNotFoundException("Student: " + username + ".  Not found in the database!");
        }
		return user;
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
	public List<Student> findStudentByRosterId(Integer rosterId) throws UserNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_ROSTER).setParameter("rosterId", rosterId);
		List<Student> userList = query.list();
		return userList;
	}
}
