package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;

public interface InstructorDAO {
	
	public Instructor findInstructorByUserName(String username) throws UserNotFoundException;
	public Instructor findInstructorByRosterId(Integer rosterId) throws UserNotFoundException;
	public List<Student> findStudentsByRosterId(Integer rosterId);
	
	// public Integer disableInstructor(String username) throws UserNotFoundException;
	// public Integer enableInstructor(String username) throws UserNotFoundException;
	// Integer addInstructor(Instructor user);
	// public void deleteInstructor(String username) throws UserNotFoundException;
}