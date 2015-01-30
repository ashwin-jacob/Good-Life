package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;

public interface InstructorDAO {
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException;
	public List<Instructor> findInstructorByRosterId(Integer rosterId) throws UserNotFoundException;
	
	// public Integer disableInstructor(String username) throws UserNotFoundException;
	// public Integer enableInstructor(String username) throws UserNotFoundException;
	// Integer addInstructor(Instructor user);
	// public void deleteInstructor(String username) throws UserNotFoundException;
}