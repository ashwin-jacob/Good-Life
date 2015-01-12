package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

@Repository
public class UsersDAOImpl implements UsersDAO  {

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
}
