package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

public interface StudentDAO {
	public Student findStudentByUserName(String username) throws UserNotFoundException;
	public void promoteStudent(String username, char roleTypeCode) throws UserNotFoundException;
	
	public void suspendStudent(String username) throws UserNotFoundException;
	public void activateStudent(String username) throws UserNotFoundException;
	public void disableStudent(String username) throws UserNotFoundException;
	public void enableStudent(String username) throws UserNotFoundException;
	void addStudent(Student user);
	public void deleteStudent(String username) throws UserNotFoundException;
	
	public void addExistingStudentToRoster(String username, Integer rosterId) throws UserNotFoundException;
}