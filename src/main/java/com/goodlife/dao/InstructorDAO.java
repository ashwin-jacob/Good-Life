package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;

public interface InstructorDAO {
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException;
	public void promoteInstructor(String username, char roleTypeCode) throws UserNotFoundException;
	
	public Integer disableInstructor(String username) throws UserNotFoundException;
	public Integer enableInstructor(String username) throws UserNotFoundException;
	Integer addInstructor(Instructor user);
	public void deleteInstructor(String username) throws UserNotFoundException;
}