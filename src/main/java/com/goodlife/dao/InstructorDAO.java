package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Users;

public interface InstructorDAO {
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException;
	public void promoteInstructor(String username, char roleTypeCode) throws UserNotFoundException;
	
	public void disableInstructor(String username) throws UserNotFoundException;
	public void enableInstructor(String username) throws UserNotFoundException;
	void addInstructor(Instructor user);
	public void deleteInstructor(String username) throws UserNotFoundException;
	
	public void suspendInstructor(String username) throws UserNotFoundException;
	public void activateInstructor(String username) throws UserNotFoundException;
}