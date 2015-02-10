package com.goodlife.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

@Repository
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer addUser(Users user) {
		Users savedUser = (Users) this.sessionFactory.getCurrentSession().save(
				user);
		return savedUser.getUserId();
	}

	@Override
	public Integer deleteUser(String username) throws UserNotFoundException {
		Users user = findByUserName(username);
		this.sessionFactory.getCurrentSession().delete(user);
		return user.getUserId();
	}

	@Override
	public Users findByUserName(String username) throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.eqOrIsNull("username", username));
		Users user = (Users) criteria.uniqueResult();
		if (null == user) {
			throw new UserNotFoundException("User: " + username
					+ ".  Not found in the database!");
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByLastName(String lastname)
			throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.ilike("lastname", lastname));
		List<Users> userList = criteria.list();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByFirstName(String firstname)
			throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.ilike("firstname", firstname));
		List<Users> userList = criteria.list();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Users findByEmail(String email) throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.ilike("email", email));
		return (Users) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByCity(String city) throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.ilike("city", city));
		List<Users> userList = criteria.list();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByState(String state) throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.ilike("state", state));
		List<Users> userList = criteria.list();
		return userList;
	}

	@Override
	public void disableUser(String username) throws UserNotFoundException {
		Users user = findByUserName(username);
		user.setRegistered(false);
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void enableUser(String username) throws UserNotFoundException {
		Users user = findByUserName(username);
		user.setRegistered(true);
		this.sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByRoleTypes(List<Character> roles)
			throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.in("roleTypeCode",roles));
		List<Users> userList = criteria.list();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> advancedQuery(String input, String field,
			List<Character> roles) throws UserNotFoundException {
		String sql = "from USERS user where user." + field + " = " + input;

		if (roles != null) {
			for (char role : roles) {
				sql += " and where user.role_typ_cd = " + role;
			}
		}

		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		List<Users> userList = query.list();
		return userList;
	}

	@Override
	public Integer promoteUser(String username, char roleTypeCode)
			throws UserNotFoundException {
		Users user = findByUserName(username);
		user.setRoleTypeCode(roleTypeCode);
		Users saved = (Users) this.sessionFactory.getCurrentSession()
				.save(user);
		return saved.getUserId();
	}
}
