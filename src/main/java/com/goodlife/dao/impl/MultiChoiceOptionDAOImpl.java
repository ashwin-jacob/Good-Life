package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.MultiChoiceOption;

public class MultiChoiceOptionDAOImpl implements MultiChoiceOptionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer addMultiChoiceOption(Integer multiQuesId, String choiceText)
			throws UserNotFoundException {
		
		MultiChoiceOption multiChoiceOption = new MultiChoiceOption();
		multiChoiceOption.setMultiQuesId(multiQuesId);
		multiChoiceOption.setChoiceText(choiceText);
		
		this.sessionFactory.getCurrentSession().save(multiChoiceOption);
		
		return multiChoiceOption.getOptionId();
	}

	@Override
	public void updateChoiceText(Integer optionId) throws UserNotFoundException {
		
		MultiChoiceOption multiChoiceOption = new MultiChoiceOption();
		multiChoiceOption = (MultiChoiceOption)this.sessionFactory.getCurrentSession().load(MultiChoiceOption.class, optionId);
		multiChoiceOption.setOptionId(optionId);
		this.sessionFactory.getCurrentSession().save(multiChoiceOption);		
	}

}
