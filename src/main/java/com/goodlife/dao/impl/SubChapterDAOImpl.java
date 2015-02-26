package com.goodlife.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.SubChapter;

@Repository
public class SubChapterDAOImpl implements SubChapterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addSubChapter(SubChapter subChapter) throws SubChapterNotFoundException{
		
		Serializable success = this.sessionFactory.getCurrentSession().save(subChapter);
		
		if(success != null)
			return subChapter.getSubChapId();
		else
			throw new SubChapterNotFoundException("Sub Chapter was not added.");
	}
	@Override
	public Boolean deleteSubChapter(Integer subChapId) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = getSubChapterById(subChapId);
		this.sessionFactory.getCurrentSession().delete(subChapter);
		if(this.sessionFactory.getCurrentSession().get(SubChapter.class, subChapId) != null){
			return Boolean.FALSE;
		}
		else
			return Boolean.TRUE;
	}
	@Override
	public Boolean updateOrder(List<Integer> subChapterIdList) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = new SubChapter();
		for (int i = 0; i < subChapterIdList.size(); i++){
			subChapter = getSubChapterById(subChapterIdList.get(i));
			if(subChapter == null)
				throw new SubChapterNotFoundException("Sub Chapter Id: " + subChapterIdList.get(i) + " was not found.");
			subChapter.setOrderId(i+1);
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
		}
		return Boolean.TRUE;
	}
	@Override
	public Boolean updateTitle(Integer subChapId, String subChapTitle) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = getSubChapterById(subChapId);
		if(subChapter != null){
			subChapter.setSubChapTitle(subChapTitle);
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
		}
		else
			throw new SubChapterNotFoundException("Sub Chapter Id: " + subChapId + " was not found.");
		
		return Boolean.TRUE;
	}
	@Override
	public Boolean updateDescription(Integer subChapId, String subChapDescr) 
		throws SubChapterNotFoundException{
		
		SubChapter subChapter = getSubChapterById(subChapId);
		if(subChapter != null){
			subChapter.setSubChapDescr(subChapDescr);
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
		}
		else
			throw new SubChapterNotFoundException("Sub Chapter Id: " + subChapId + " was not found.");
		
		return Boolean.TRUE;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SubChapter> getSubChapListByChapter(Integer chapId)
			throws SubChapterNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubChapter.class);
		criteria.add(Restrictions.eqOrIsNull("chapId", chapId));
		List<SubChapter> subChapList = criteria.list();
		if(null == subChapList){
			throw new SubChapterNotFoundException("subChapId: " + chapId + " not found in the database!");
		}
		return subChapList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubChapter> getPublishedSubChapListByChap(Integer chapId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubChapter.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("chapId", chapId),Restrictions.eq("published", true)));
		List<SubChapter> subChapList = criteria.list();
		return subChapList;
	}
	
	@Override
	public SubChapter getSubChapterById(Integer subChapId)
			throws SubChapterNotFoundException {

		SubChapter subChapter;
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(SubChapter.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		subChapter = (SubChapter) criteria.uniqueResult();
		if(null == subChapter){
			throw new SubChapterNotFoundException("subChapId: " + subChapId + " not found in the database!");
		}
		return subChapter;
	}
	@Override
	public Boolean setPublishSubChapter(Integer subChapId, Boolean published) {

		Boolean isSuccess;
		try {
			SubChapter subChapter = getSubChapterById(subChapId);
			subChapter.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
			isSuccess = Boolean.TRUE;
		} catch (SubChapterNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}
}
