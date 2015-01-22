package com.goodlife.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

@Repository
public class UserStatusDAOImpl implements UserStatusDAO{
		
		@Autowired
	    private SessionFactory sessionFactory;

		@Override
		public List<UserStatus> findByUserId(Integer userId) throws UserNotFoundException {
			// TODO Auto-generated method stub
			Query query = this.sessionFactory.getCurrentSession().createQuery("from USER_STATUS where usr_id = :userId ");
			query.setParameter("userId", userId);
			List<UserStatus> userStatusList = query.list();
			if (null == userId) {
	        	throw new UserNotFoundException("User: " + userId + ".  Not found in the database!");
	        }
			return userStatusList;
		}

		@Override
		public List<UserStatus> findByStatusCode(char statusTypeCode)
				throws UserNotFoundException {
			// TODO Auto-generated method stub
			Query query = this.sessionFactory.getCurrentSession().createQuery("from USER_STATUS where sts_typ_cd = :statusTypeCode ");
			query.setParameter("statusTypeCode", statusTypeCode);
			List<UserStatus> userStatusList = query.list();
			return userStatusList;
		}

		@Override
		public List<UserStatus> findCurrentSuspendedUsers()
				throws UserNotFoundException {
			// TODO Auto-generated method stub
			Query query = this.sessionFactory.getCurrentSession().createQuery("from USER_STATUS where sts_typ_cd = :statusTypeCode AND end_dt >= current_date");
			query.setParameter("statusTypeCode", 'S');
			List<UserStatus> userStatusList = query.list();
			return userStatusList;
		}

		@Override
		public Integer findNumberofSuspensionsByUserId(Integer userId)
				throws UserNotFoundException {
			// TODO Auto-generated method stub
			Query query = this.sessionFactory.getCurrentSession().createQuery("from USER_STATUS where usr_id = :userId AND sts_typ_cd = :statusTypeCode");
			query.setParameter("userId", userId);
			query.setParameter("statusTypeCode", 'S');
			List<Users> userStatusList = query.list();
			return userStatusList.size();
		}

		@Override
		public void suspendUser(UserStatus userStatus)
				throws UserNotFoundException {
			// TODO Auto-generated method stub
			this.sessionFactory.getCurrentSession().save(userStatus);
		}

		@Override
		public void changeEndDate(Integer userStatusId, Date newEndDate)
				throws UserNotFoundException {
			// TODO Auto-generated method stub
			UserStatus userStatus = findByUserStatusId(userStatusId);
			userStatus.setEndDate(newEndDate);
			this.sessionFactory.getCurrentSession().save(userStatus);
			
		}

		@Override
		public UserStatus findByUserStatusId(Integer userStatusId)
				throws UserNotFoundException {
			// TODO Auto-generated method stub
			UserStatus userStatus = (UserStatus) this.sessionFactory.getCurrentSession().get(UserStatus.class, userStatusId);
			if (null == userStatusId) {
	        	throw new UserNotFoundException("User: " + userStatusId + ".  Not found in the database!");
	        }
			return userStatus;
		}
		
		

}
