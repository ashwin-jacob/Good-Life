package com.goodlife.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

/*
 * User management on (filtered) user list for Super Admin
 */
@Controller
@RequestMapping(value = "/userlookup")
public class UserManagementController {
	
	static final Logger logger = LogManager.getLogger(UserManagementController.class.getName());
	
	@Autowired
	UserStatusDAO userStatusDAO;
	
	@Autowired
	UsersDAO usersDAO;
	
	@RequestMapping(value="{input}/{type}/{role}", method=RequestMethod.POST, produces="filtered/json")
	public void getList(ModelMap model,
				@PathVariable @RequestParam(value="input", required=false) String input, 
				@PathVariable @RequestParam(value="type", required=false) String type,
				@PathVariable @RequestParam(value="role", required=false) String role) {
		/* PATHS - should we separate these in the UI design?
		 * 1. role 
		 * 2. input + type
		 * 3. input + type + role
		 */ 
		if (role != null) {
			try {
				List<Users> roleUsers = usersDAO.findByRoleType(role.charAt(0));
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				logger.debug("No " + role + "s");
				e.printStackTrace();
			}
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/suspend", method = RequestMethod.GET)
	public Integer suspendUser(@RequestParam(value="userId") Integer userId, 
				@RequestParam(value="endDate") Date endDate,
				ModelMap model) throws UserNotFoundException {
		
		UserStatus userStatus = new UserStatus();
		userStatus.setUserId(userId);
		userStatus.setStartDate(new Date());
		userStatus.setStatusTypeCode('S');
		userStatus.setEndDate(endDate);
		
		//Integer userStatusId = userStatusDAO.suspendUser(userStatus);
		
		//model.addAttribute("userStatusId", userStatusId);
		model.addAttribute("userStatus", "suspended");
		
		// return userStatusId;
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public String activateUser(@RequestParam(value="userStatusId") Integer userStatusId, 
			ModelMap model) throws UserNotFoundException {
		
		UserStatus userStatus = userStatusDAO.findByUserStatusId(userStatusId);
		//userStatusDAO.activate or changeStatus() - updates the entry
		
		//userStatusDAO.activateUser(userId, startDate, endDate);
		return "#";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteUser(@RequestParam(value="userStatusId") Integer userStatusId, 
			ModelMap model) throws UserNotFoundException {
		
		UserStatus userStatus = userStatusDAO.findByUserStatusId(userStatusId);
		//userStatusDAO.activate or changeStatus() - updates the entry
		
		//userStatusDAO.activateUser(userId, startDate, endDate);
		return "#";
	}
}
