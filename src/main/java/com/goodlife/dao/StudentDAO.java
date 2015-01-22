package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

public interface StudentDAO {
	public Student findStudentByUserName(String username) throws UserNotFoundException;
	public Integer promoteStudent(String username, char roleTypeCode) throws UserNotFoundException;
	
	public Integer disableStudent(String username) throws UserNotFoundException;
	public Integer enableStudent(String username) throws UserNotFoundException;
	Integer addStudent(Student user);
	public void deleteStudent(String username) throws UserNotFoundException;
	
	public Integer addExistingStudentToRoster(String username, Integer rosterId) throws UserNotFoundException;
}