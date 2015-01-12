package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;

public interface StudentDAO {
	public Student findStudentByUserName(String username) throws UserNotFoundException;
	public void promoteStudent(String username, String roleTypeCode) throws UserNotFoundException;
	
	public void suspendStudent(String username) throws UserNotFoundException;
	public void disableStudent(String username) throws UserNotFoundException;
	public void activateStudent(String username) throws UserNotFoundException;
	
	public void addExistingStudentToRoster(String username, Integer rosterId) throws UserNotFoundException;
	public void deleteStudentInRoster(String username, Integer rosterId) throws UserNotFoundException;
}