package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

@Repository
public class StudentDAOImpl implements StudentDAO  {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private UsersDAO userDAO;

	@Override
	public Student findStudentByUserId(Integer userId)
			throws ObjectNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eqOrIsNull("userId", userId));
		Student student = (Student) criteria.uniqueResult();
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentByRosterId(Integer rosterId)
			throws ObjectNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eqOrIsNull("rosterId", rosterId));
		List<Student> studentList = criteria.list();
		return studentList;
	}

	@Override
	public Boolean addStudent(Student user) throws UserNotFoundException {
		Users student = userDAO.findByUserId(user.getUserId());
		if(student == null)
			throw new UserNotFoundException("User Id: " + user.getUserId() + " not found.");
		this.sessionFactory.getCurrentSession().save(user);
		return Boolean.TRUE;
	}

	@Override
	public Boolean deleteStudent(Integer userId)
			throws ObjectNotFoundException {
		try{
			Student student = findStudentByUserId(userId);
			this.sessionFactory.getCurrentSession().delete(student);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean addExistingStudentToRoster(Integer userId, Integer rosterId)
			throws ObjectNotFoundException {
		try{
			Student student = findStudentByUserId(userId);
			student.setRosterId(rosterId);
			this.sessionFactory.getCurrentSession().saveOrUpdate(student);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> getAllowedChapters(Integer userId)
			throws ObjectNotFoundException {
		
		Student student = findStudentByUserId(userId);
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Chapter.class);
		criteria.add(Restrictions.sqlRestriction("CHAP_ID <= " + student.getCurrentChapterId()));
		List<Chapter> chapterList = criteria.list();
		return chapterList;
	}
	
	@Override
	public Boolean updateCurrentChapter(Integer userId, Integer chapId) {
		
		try{
			Student student = findStudentByUserId(userId);
			student.setCurrentChapterId(chapId);
			this.sessionFactory.getCurrentSession().saveOrUpdate(student);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
	}
}
