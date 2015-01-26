package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

public class ChapterDAOImpl implements ChapterDAO{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Integer addChapter(Chapter chapter) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(chapter);
		return chapter.getChapId();
	}

	@Override
	public void deleteChapter(Integer chapterId)
			throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		Chapter chapter = findByChapterId(chapterId);
        this.sessionFactory.getCurrentSession().delete(chapter);
	}

	@Override
	public Chapter findByChapterId(Integer chapterId)
			throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		Chapter chapter = (Chapter) this.sessionFactory.getCurrentSession().get(Chapter.class, chapterId);
		if (null == chapterId) {
        	throw new ChapterNotFoundException("Chapter: " + chapterId + ".  Not found in the database!");
        }
		return chapter;
	}

	@Override
	public void updateOrder(List<Chapter> chapterList) throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<chapterList.size(); i++){
			chapterList.get(i).setOrderId(i);
			this.sessionFactory.getCurrentSession().save(chapterList.get(i));
		}
		
	}

	@Override
	public void updateTitle(Integer chapterId, String newChapterTitle)
			throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		Chapter chapter = findByChapterId(chapterId);
		chapter.setChapTitle(newChapterTitle);
		this.sessionFactory.getCurrentSession().save(chapter);
		
	}

	@Override
	public void updateDescr(Integer chapterId, String newChapterDescr)
			throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		Chapter chapter = findByChapterId(chapterId);
		chapter.setChapDescr(newChapterDescr);
		this.sessionFactory.getCurrentSession().save(chapter);
		
	}

	@Override
	public List<Chapter> listAllChapters() throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery("from CHAPTER");
		List<Chapter> allChapterList = query.list();
		return allChapterList;
	}

	@Override
	public List<Chapter> listAllPublishedChapters()
			throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery("from CHAPTER where published = :published");
		query.setParameter("published", true);
		List<Chapter> publishedChapterList = query.list();
		return publishedChapterList;
	}

	@Override
	public List<Chapter> listAllChapterDrafts() throws ChapterNotFoundException {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery("from CHAPTER where published = :published");
		query.setParameter("published", false);
		List<Chapter> chapterDraftList = query.list();
		return chapterDraftList;
	}
	
	
	
	

}
