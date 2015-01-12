package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;

public interface InstructorDAO {
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException;
	
	public void promoteInstructor(String username, String roleTypeCode) throws UserNotFoundException;
	
	public void disableInstructor(String username) throws UserNotFoundException;
	public void activateInstructor(String username) throws UserNotFoundException;
	
	public void addExistingInstructorToRoster(String username, Integer rosterId) throws UserNotFoundException;
	public void deleteInstructorInRoster(String username, Integer rosterId) throws UserNotFoundException;
}