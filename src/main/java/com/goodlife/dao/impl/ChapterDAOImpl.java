package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

@Repository
public class ChapterDAOImpl implements ChapterDAO{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Integer addChapter(Chapter chapter) {
		
		this.sessionFactory.getCurrentSession().save(chapter);
		return chapter.getChapId();
	}

	@Override
	public Integer deleteChapter(Integer chapterId)
			throws ChapterNotFoundException {
		
		Chapter chapter = findByChapterId(chapterId);
        this.sessionFactory.getCurrentSession().delete(chapter);
        if(findByChapterId(chapterId) == null)
        	return 0;
        else
        	return chapterId;
	}

	@Override
	public Chapter findByChapterId(Integer chapterId)
			throws ChapterNotFoundException {
		
		Chapter chapter = (Chapter) this.sessionFactory.getCurrentSession().get(Chapter.class, chapterId);
		if (null == chapterId) {
        	throw new ChapterNotFoundException("Chapter: " + chapterId + ".  Not found in the database!");
        }
		return chapter;
	}

	@Override
	public Boolean updateOrder(List<Integer> chapterList) throws ChapterNotFoundException {
		Chapter chapter = new Chapter();
		try{
			for(int i=0; i<chapterList.size(); i++){
				chapter = (Chapter)this.sessionFactory.getCurrentSession().get(Chapter.class,chapterList.get(i));
				chapter.setOrderId(i);
				this.sessionFactory.getCurrentSession().save(chapter);
			}
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
		
	}

	@Override
	public Boolean updateTitle(Integer chapterId, String newChapterTitle)
			throws ChapterNotFoundException {
		
		try{
			Chapter chapter = findByChapterId(chapterId);
			chapter.setChapTitle(newChapterTitle);
			this.sessionFactory.getCurrentSession().save(chapter);
			
			return Boolean.TRUE;
			
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean updateDescr(Integer chapterId, String newChapterDescr)
			throws ChapterNotFoundException {
		
		try{
			Chapter chapter = findByChapterId(chapterId);
			chapter.setChapDescr(newChapterDescr);
			this.sessionFactory.getCurrentSession().save(chapter);
			
			return Boolean.TRUE;
			
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
		
	}
	
	@Override
	public Boolean updatePublished(Integer chapterId, Boolean published) throws ChapterNotFoundException{
		
		try{
			Chapter chapter = findByChapterId(chapterId);
			chapter.setPublished(published);
			this.sessionFactory.getCurrentSession().save(chapter);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@Override
	public List<Chapter> listAllChapters() throws ChapterNotFoundException {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("from CHAPTER");
		List<Chapter> allChapterList = query.list();
		return allChapterList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> listAllPublishedChapters()
			throws ChapterNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Chapter.class);
		criteria.add(Restrictions.eqOrIsNull("published", true));
		List<Chapter> publishedChapterList = criteria.list();
		return publishedChapterList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> listAllChapterDrafts() throws ChapterNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Chapter.class);
		criteria.add(Restrictions.eqOrIsNull("published", false));
		List<Chapter> chapterDraftList = criteria.list();
		return chapterDraftList;
	}
	
	
	
	

}
