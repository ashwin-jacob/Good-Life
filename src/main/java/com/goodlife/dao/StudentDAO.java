package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

public interface StudentDAO {
	public Student findStudentByUserName(String username) throws UserNotFoundException;
	public List<Student> findStudentByRosterId(Integer rosterId) throws UserNotFoundException;

	// public Integer disableStudent(String username) throws UserNotFoundException;
	// public Integer enableStudent(String username) throws UserNotFoundException;
	// public Integer addStudent(Student user);
	// public void deleteStudent(String username) throws UserNotFoundException;
	
	public Integer addExistingStudentToRoster(String username, Integer rosterId) throws UserNotFoundException;
}