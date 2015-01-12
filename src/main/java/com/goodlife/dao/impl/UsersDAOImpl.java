package com.goodlife.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

@Repository
public class UsersDAOImpl implements UsersDAO  {
	// TODO: userStatus and Flag needs to be resolved.
	private static final String QUERY_FIND_ROLE = 
			"select user.username, user.firstname, user.lastname, user.email, user.roleTypeCode, user.userStatus "
			+ "from Users user where user.roleTypeCode = :roleTypeCode";
	
	private static final String QUERY_FIRSTNAME = 
			"select user.username, user.firstname, user.lastname, user.email, user.roleTypeCode, user.userStatus "
			+ "from Users user where user.firstname = :firstname";
	
	private static final String QUERY_LASTNAME = 
			"select user.username, user.firstname, user.lastname, user.email, user.roleTypeCode, user.userStatus "
			+ "from Users user where user.lastname = :lastname";
	
	private static final String QUERY_EMAIL = 
			"select user.username, user.firstname, user.lastname, user.email, user.roleTypeCode, user.userStatus "
			+ "from Users user where user.email = :email";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addUser(Users user) {
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void deleteUser(String username) throws UserNotFoundException {
		Users user = findByUserName(username);
        this.sessionFactory.getCurrentSession().delete(user);
	}
	
	@Override
	public Users findByUserName(String username) throws UserNotFoundException {
		Users user = (Users) this.sessionFactory.getCurrentSession().get(Users.class, username);
		if (null == user) {
        	throw new UserNotFoundException("User: " + username + ".  Not found in the database!");
        }
		return user;
	}

	@Override
	public void disableUser(String username) throws UserNotFoundException {
		Users user = findByUserName(username);
        user.setRegistered(false);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void enableUser(String username) throws UserNotFoundException{
		Users user = findByUserName(username);
        user.setRegistered(true);
        this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public List<Users> findByRoleType(String roleTypeCode)
			throws UserNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_FIND_ROLE);
		query.setParameter("roleTypeCode", roleTypeCode);
		List<Users> userList = query.list();
		return userList;
	}

	@Override
	public List<Users> findByFirstName(String firstname)
			throws UserNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_FIRSTNAME).setParameter("firstname", firstname);
		List<Users> userList = query.list();
		return userList;
	}

	@Override
	public List<Users> findByLastName(String lastname)
			throws UserNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_LASTNAME).setParameter("lastname", lastname);
		List<Users> userList = query.list();
		return userList;
	}

	@Override
	public List<Users> findByEmail(String email) throws UserNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_EMAIL).setParameter("email", email);
		List<Users> userList = query.list();
		return userList;
	}
}
