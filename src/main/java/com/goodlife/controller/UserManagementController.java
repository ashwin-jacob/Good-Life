package com.goodlife.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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
import com.goodlife.exceptions.StateNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

/*
 * User management on (filtered) user list for Super Admin
 */
@Controller
@RequestMapping(value = "/userlookup")
public class UserManagementController {
	
	@Inject
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	static final Logger logger = LogManager.getLogger(UserManagementController.class.getName());
	
	@Inject
	private UserStatusDAO userStatusDAO;
	
	@Inject
	private UsersDAO usersDAO;
	
	/*
	 * ** field should match the db column name.
	 * example from UI : { "input": "whateveUserTypes",
	 * 						"field": "lst_nm",
	 * 						"sb": 1,
	 * 						"mb": 0,
	 * 						"fb": 0}
	 */
	@RequestMapping(value="{input}/{type}/student?{sb}moderator?{mb}facilitator?{fb}", 
					method=RequestMethod.POST, produces="filtered/json")
	public AjaxResponse<List<Users>> getList(ModelMap model,
				@PathVariable @RequestParam(value="input") String input, 
				@PathVariable @RequestParam(value="field") String field,
				@PathVariable @RequestParam(value="sb") Integer sb,
				@PathVariable @RequestParam(value="mb") Integer mb,
				@PathVariable @RequestParam(value="fb") Integer fb) {

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
		
		AjaxResponse<List<Users>> response = new AjaxResponse<List<Users>>();
		response = ajaxResponseBuilder.createSuccessResponse(filteredList);
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/suspend", method = RequestMethod.GET)
	public AjaxResponse<Boolean> suspendUser(@RequestParam(value="userId") Integer userId,
											 @RequestParam(value="endDate") Date endDate) throws UserNotFoundException {
		
		UserStatus userStatus = new UserStatus();
		userStatus.setUserId(userId);
		userStatus.setStartDate(new Date());
		userStatus.setStatusTypeCode('S');
		userStatus.setEndDate(endDate);
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(userStatusDAO.suspendUser(userStatus));
		
		//model.addAttribute("userStatusId", userStatusId);
		//model.addAttribute("userStatus", "suspended");
		
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public AjaxResponse<Boolean> activateUser(@RequestParam(value="userStatusId") Integer userStatusId) throws UserNotFoundException {
		
		UserStatus userStatus = userStatusDAO.findByUserStatusId(userStatusId);
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(userStatusDAO.changeUserStatus(userStatusId, 'A'));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public AjaxResponse<Boolean> deleteUser(@RequestParam(value="userStatusId") Integer userStatusId) throws UserNotFoundException {
		
		UserStatus userStatus = userStatusDAO.findByUserStatusId(userStatusId);
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(userStatusDAO.changeUserStatus(userStatusId, 'D'));

		return response;
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
