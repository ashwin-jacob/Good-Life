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
			"select user.usr_nm, user.frst_nm, user.lst_nm, user.email, user.role_typ_cd "
			+ "from USERS user where user.roster_id = :rosterId";
	
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

	@Override
	public List<Student> findStudentByRosterId(Integer rosterId) throws UserNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_ROSTER).setParameter("rosterId", rosterId);
		List<Student> userList = query.list();
		return userList;
	}
}
