package com.goodlife.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.StateNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

/*
 * User management on (filtered) user list for Super Admin
 */
@Controller
@RequestMapping(value = "/usermanagement")
@Transactional
public class UserManagementController {
	
	static final Logger logger = LogManager.getLogger(UserManagementController.class.getName());
	
	@Inject
	private UserStatusDAO userStatusDAO;
	
	@Inject
	private UsersDAO usersDAO;
	
	/*
	 * ** field should match the db column name.
	 * example from UI : { "input": "whateveUserTypes",
	 * 						"field": "lst_nm", "frst_nm", "email", "usr_nm"
	 * 						"sb": 1,
	 * 						"mb": 0,
	 * 						"fb": 0}
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public List<Users> getList(@RequestParam(value="input") String input, 
				@RequestParam(value="field") String field,
				@RequestParam(value="sb") Integer sb,
				@RequestParam(value="mb") Integer mb,
				@RequestParam(value="fb") Integer fb) {

		List<Users> filteredList = new ArrayList<Users>();
		String searchStr = cleanInput(input, field);
		
		List<Character> roles = new ArrayList<Character>();
		if (sb == 1) {
			roles.add('S');
		} if (mb == 1) {
			roles.add('M');
		} if (fb == 1) {
			roles.add('F');
		}
	
		try {
			filteredList = usersDAO.advancedQuery(searchStr, field, roles);
		} catch (UserNotFoundException e) {
			logger.debug("No users found.");
			e.printStackTrace();
		}
		
		if(filteredList == null)
			return new ArrayList<Users>();
		else
			return filteredList;
	}
	
	
	/**
	 * addUserStatus - Assigns a user status
	 * @param userId
	 * @param statusTypeCode - s(suspended), d(deleted)
	 * @return
	 * @throws UserNotFoundException
	 */
	@ResponseBody
	@RequestMapping(value = "/adduserstatus", method = RequestMethod.GET)
	public Integer addUserStatus(@RequestParam(value="userId") Integer userId,
									@RequestParam(value="statusTypeCode") Character statusTypeCode) throws UserNotFoundException {

		UserStatus userStatus = new UserStatus();
		userStatus.setUserId(userId);
		userStatus.setStartDate(new Timestamp(new Date().getTime()));
		// 604800000 is the amount of milliseconds in 7 days
		userStatus.setEndDate(new Timestamp(new Date().getTime() + 604800000));	
		userStatus.setStatusTypeCode(statusTypeCode);
		Integer result = userStatusDAO.addUserStatus(userStatus);
		
		return result;	
	}
	
	/**
	 * deleteUserStatus - Undo a status
	 * @param userStatusId 
	 * @return
	 * @throws UserNotFoundException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteuserstatus", method = RequestMethod.GET)
	public Boolean deleteUserStatus(@RequestParam(value="userStatusId") Integer userStatusId) throws UserNotFoundException {
		Boolean result = userStatusDAO.deleteUserStatus(userStatusId);
		return result;
	}
	
	/**
	 * changeEndDate - Change the end date of suspension
	 * @param userStatusId
	 * @param newDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeenddate", method = RequestMethod.GET)
	public Boolean changeEndDate(@RequestParam(value="userStatusId") Integer userStatusId, @RequestParam(value="newDate") Date newDate){
		
		try {
			Boolean result = userStatusDAO.changeEndDate(userStatusId, newDate);
			return result;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	
	private String cleanInput(String input, String type) {
		
		input = input.trim();
		if (type == "state") {
			StateConversionUtil stateUtil = new StateConversionUtil();
			try {
				return stateUtil.lookUp(input);
			} catch (StateNotFoundException e) {
				logger.debug(input + " is an invalid state name.");
				e.printStackTrace();
			}
		}
		return input;
	}
}
