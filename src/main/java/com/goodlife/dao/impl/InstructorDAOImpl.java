package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.InstructorDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;

@Repository
public class InstructorDAOImpl implements InstructorDAO  {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Instructor.class);
		criteria.add(Restrictions.eqOrIsNull("username", username));
		Instructor instructor = (Instructor) criteria.uniqueResult();
		if (null == instructor) {
        	throw new UserNotFoundException("Instructor: " + username + ".  Not found in the database!");
        }
		return instructor;
	}
	
	@Override
	public Instructor findInstructorByRosterId(Integer rosterId)
			throws UserNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Instructor.class);
		criteria.add(Restrictions.eqOrIsNull("rosterId", rosterId));
		Instructor userList = (Instructor) criteria.uniqueResult();
		if(userList == null)
			throw new UserNotFoundException("Instructor rosterId: " + rosterId + " not found.");
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentsByRosterId(Integer rosterId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Instructor.class);
		criteria.add(Restrictions.eqOrIsNull("rosterId", rosterId));
		List<Student> studentList = criteria.list();
		if(studentList == null)
			return new ArrayList<Student>();
		else
			return studentList;
	}
}
