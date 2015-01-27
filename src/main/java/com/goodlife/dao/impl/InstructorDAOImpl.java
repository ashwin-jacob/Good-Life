package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.InstructorDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;

@Repository
public class InstructorDAOImpl implements InstructorDAO  {

	private static final String QUERY_ROSTER = 
			"from USERS user where user.roster_id = :rosterId and user.role_typ_cd = " + 'M' +
			"OR user.role_typ_cd = " + 'F';
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException {
		Instructor user = (Instructor) this.sessionFactory.getCurrentSession().get(Instructor.class, username);
		if (null == user) {
        	throw new UserNotFoundException("Instructor: " + username + ".  Not found in the database!");
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

	@Override
	public List<Instructor> findInstructorByRosterId(Integer rosterId)
			throws UserNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_ROSTER).setParameter("rosterId", rosterId);
		List<Instructor> userList = query.list();
		return userList;
	}
}
