package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.model.ChapterPage;

@Repository
public class ChapterPageDAOImpl implements ChapterPageDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addChapterPage(ChapterPage chapterPage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(chapterPage);
		return chapterPage.getPageId();
	}

	@Override
	public void deleteChapter(Integer pageId)
			throws ChapterPageNotFoundException {
		// TODO Auto-generated method stub
		ChapterPage chapterPage = findByPageId(pageId);
        this.sessionFactory.getCurrentSession().delete(chapterPage);
		
	}

	@Override
	public ChapterPage findByPageId(Integer pageId)
			throws ChapterPageNotFoundException {
		// TODO Auto-generated method stub
		ChapterPage chapterPage = (ChapterPage) this.sessionFactory.getCurrentSession().get(ChapterPage.class, pageId);
		if (null == pageId) {
        	throw new ChapterPageNotFoundException("Chapter Page: " + pageId + ".  Not found in the database!");
        }
		return chapterPage;
	}

	@Override
	public List<ChapterPage> findAllChapterPagesByChapterId(Integer chapterId)
			throws ChapterPageNotFoundException {
		// TODO Auto-generated method stub
		List<ChapterPage> chapterPageList;
		try{
			chapterPageList = (List<ChapterPage>)this.sessionFactory.getCurrentSession().load(ChapterPage.class, chapterId);
		}catch(ObjectNotFoundException e){
			chapterPageList = (List<ChapterPage>)this.sessionFactory.getCurrentSession().get(ChapterPage.class, chapterId);
		}
		if(null == chapterPageList){
			throw new ChapterPageNotFoundException("Chapter Pages with chapter ID: " + chapterId + " not found in the database!");
		}
		return chapterPageList;
	}

	@Override
	public void updateOrder(List<ChapterPage> chapterPageList)
			throws ChapterPageNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<chapterPageList.size(); i++){
			chapterPageList.get(i).setPage_num(i);
			this.sessionFactory.getCurrentSession().save(chapterPageList.get(i));
		}
		
	}

	@Override
	public void updatePageUrl(Integer pageId, String newUrl)
			throws ChapterPageNotFoundException {
		// TODO Auto-generated method stub
		ChapterPage chapterPage = findByPageId(pageId);
		chapterPage.setPageUrl(newUrl);
		this.sessionFactory.getCurrentSession().save(chapterPage);
		
	}

}