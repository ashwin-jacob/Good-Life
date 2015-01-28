package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.SubChapter;

@Repository
public class SubChapterDAOImpl implements SubChapterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addSubChapter(SubChapter subChapter){
		
		this.sessionFactory.getCurrentSession().save(subChapter);
				
		return subChapter.getSubChapId();
	}
	@Override
	public void deleteSubChapter(Integer subChapId) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = new SubChapter();
		subChapter = (SubChapter)this.sessionFactory.getCurrentSession().load(SubChapter.class, subChapId);
		this.sessionFactory.getCurrentSession().delete(subChapter);
	}
	@Override
	public void updateOrder(List<Integer> subChapterIdList) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = new SubChapter();
		for (int i = 0; i < subChapterIdList.size(); i++){
			subChapter = (SubChapter)this.sessionFactory.getCurrentSession().load(SubChapter.class, subChapterIdList.get(i));
			subChapter.setOrderId(i);
			this.sessionFactory.getCurrentSession().save(subChapter);
		}
	}
	@Override
	public void updateTitle(Integer subChapId, String subChapTitle) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = new SubChapter();
		subChapter = (SubChapter)this.sessionFactory.getCurrentSession().load(SubChapter.class, subChapId);
		subChapter.setSubChapTitle(subChapTitle);
		this.sessionFactory.getCurrentSession().save(subChapter);
	}
	@Override
	public void updateDescription(Integer subChapId, String subChapDescr) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = new SubChapter();
		subChapter = (SubChapter)this.sessionFactory.getCurrentSession().load(SubChapter.class, subChapId);
		subChapter.setSubChapDescr(subChapDescr);;
		this.sessionFactory.getCurrentSession().save(subChapter);
	}
	@Override
	public List<SubChapter> getSubChapListByChapter(Integer chapId)
			throws SubChapterNotFoundException {

		List<SubChapter> subChapList;
		try{
			subChapList = (List<SubChapter>)this.sessionFactory.getCurrentSession().load(SubChapter.class, chapId);
		}catch(ObjectNotFoundException e){
			subChapList = (List<SubChapter>)this.sessionFactory.getCurrentSession().get(SubChapter.class, chapId);
		}
		return subChapList;
	}
}
