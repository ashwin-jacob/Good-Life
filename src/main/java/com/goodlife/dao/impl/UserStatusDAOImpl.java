package com.goodlife.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

@Repository
public class UserStatusDAOImpl implements UserStatusDAO{
		
		@Autowired
	    private SessionFactory sessionFactory;
		
		@Autowired
		private UsersDAO usersDAO;

		@SuppressWarnings("unchecked")
		@Override
		public List<UserStatus> findByUserId(Integer userId) throws UserNotFoundException {
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UserStatus.class);
			criteria.add(Restrictions.eqOrIsNull("userId", userId));
			List<UserStatus> userStatusList = criteria.list();
			if (null == userId) {
	        	throw new UserNotFoundException("User: " + userId + ".  Not found in the database!");
	        }
			return userStatusList;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<UserStatus> findByStatusCode(char statusTypeCode)
				throws UserNotFoundException {
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UserStatus.class);
			criteria.add(Restrictions.eqOrIsNull("statusTypeCode", statusTypeCode));
			List<UserStatus> userStatusList = criteria.list();
			return userStatusList;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<UserStatus> findCurrentSuspendedUsers()
				throws UserNotFoundException {
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UserStatus.class);
			criteria.add(Restrictions.and(Restrictions.eqOrIsNull("statusTypeCode", 's'), Restrictions.sqlRestriction("end_dt >= current_date")));
			List<UserStatus> userStatusList = criteria.list();
			return userStatusList;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Integer findNumberofSuspensionsByUserId(Integer userId)
				throws UserNotFoundException {
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UserStatus.class);
			criteria.add(Restrictions.and(Restrictions.eqOrIsNull("userId", userId),Restrictions.eqOrIsNull("statusTypeCode", 's')));
			List<Users> userStatusList = criteria.list();
			return userStatusList.size();
		}

		@Override
		public Integer addUserStatus(UserStatus userStatus)
				throws UserNotFoundException {
			
			Users user = usersDAO.findByUserId(userStatus.getUserId());
			
			if(user != null)
				this.sessionFactory.getCurrentSession().save(userStatus);
			else
				throw new UserNotFoundException("User Id: " + userStatus.getUserId() + " not found");

			return userStatus.getUserStatusId();
		}

		@Override
		public Boolean changeEndDate(Integer userStatusId, Date newEndDate)
				throws UserNotFoundException {
			try{
				UserStatus userStatus = findByUserStatusId(userStatusId);
				userStatus.setEndDate(newEndDate);
				this.sessionFactory.getCurrentSession().save(userStatus);
				return Boolean.TRUE;
			}catch(ObjectNotFoundException e){
				return Boolean.FALSE;
			}			
		}


		@Override
		public Boolean deleteUserStatus(Integer userStatusId)
				throws ObjectNotFoundException {
			
			try{
				UserStatus userStatus = findByUserStatusId(userStatusId);
				this.sessionFactory.getCurrentSession().delete(userStatus);
				return Boolean.TRUE;
			}catch(ObjectNotFoundException e){
				return Boolean.FALSE;
			}
		}
		
		@Override
		public UserStatus findByUserStatusId(Integer userStatusId) throws ObjectNotFoundException{
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UserStatus.class);
			criteria.add(Restrictions.eqOrIsNull("userStatusId", userStatusId));
			UserStatus userStatus = (UserStatus) criteria.uniqueResult();
			if(userStatus == null)
				throw new ObjectNotFoundException(null,"User status Id: " + userStatusId + " not found.");
			return userStatus;
		}

		@Override
		public UserStatus findCurrentStatusByUser(Integer userId) {
			
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UserStatus.class);
			criteria.add(Restrictions.and(Restrictions.eqOrIsNull("userId", userId), Restrictions.and(Restrictions.ge("endDate", new Date()), Restrictions.le("startDate", new Date()))));
			UserStatus userStatus = (UserStatus) criteria.uniqueResult();
			if(userStatus == null)
				return null;
			else
				return userStatus;
		}
		
		

}
