package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.model.MultiChoiceOption;

public class MultiChoiceOptionDAOImpl implements MultiChoiceOptionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer addMultiChoiceOption(MultiChoiceOption multiChoiceOption) {
		
		this.sessionFactory.getCurrentSession().save(multiChoiceOption);
		
		return multiChoiceOption.getOptionId();
	}

	@Override
	public void updateChoiceText(Integer optionId, String text) throws ObjectNotFoundException {
		
		MultiChoiceOption multiChoiceOption = new MultiChoiceOption();
		try{
			multiChoiceOption = (MultiChoiceOption)this.sessionFactory.getCurrentSession().load(MultiChoiceOption.class, optionId);
		}catch(ObjectNotFoundException e){
			multiChoiceOption = (MultiChoiceOption)this.sessionFactory.getCurrentSession().get(MultiChoiceOption.class, optionId);
		}
		multiChoiceOption.setChoiceText(text);
		this.sessionFactory.getCurrentSession().save(multiChoiceOption);		
	}

	@Override
	public List<MultiChoiceOption> getMultiChoiceOptions(Integer multiQuesId) {

		Query query = this.sessionFactory.getCurrentSession().createQuery("FROM MULTI_CHOICE_OPTION WHERE MC_Q_ID = :multiQuesId");
		query.setParameter("multiQuesId", multiQuesId);
		List<MultiChoiceOption> multiChoiceList = query.list();
		
		return multiChoiceList;
	}

	@Override
	public void deleteMultiChoiceOption(Integer optionId) throws ObjectNotFoundException {
		MultiChoiceOption mcOpt = findMultiChoiceOptionById(optionId);
		this.sessionFactory.getCurrentSession().delete(mcOpt);
	}

	@Override
	public MultiChoiceOption findMultiChoiceOptionById(Integer optionId)
			throws ObjectNotFoundException {
		MultiChoiceOption mcOpt = (MultiChoiceOption) this.sessionFactory.getCurrentSession().get(MultiChoiceOption.class, optionId);
		if (null == mcOpt) {
        	throw new ObjectNotFoundException(null, "MultiChoice Option: " + optionId + ".  Not found in the database!");
        }
		return mcOpt;
	}

}
