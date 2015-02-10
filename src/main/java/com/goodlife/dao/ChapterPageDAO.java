package com.goodlife.dao;

import java.util.List;


import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.model.ChapterPage;

public interface ChapterPageDAO {
	
	public Integer addChapterPage(ChapterPage chapterPage);
	public void deleteChapter(Integer pageId) throws ChapterPageNotFoundException;
	public ChapterPage findByPageId(Integer pageId)throws ChapterPageNotFoundException;
	public List<ChapterPage> findAllChapterPagesByChapterId(Integer chapterId) throws ChapterPageNotFoundException;
	public void updateOrder(List<ChapterPage> chapterPageList) throws ChapterPageNotFoundException;
	public void updatePageUrl(Integer pageId, String newUrl) throws ChapterPageNotFoundException;
}
