package com.goodlife.dao;

import java.util.List;



import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.model.ChapterPage;

public interface ChapterPageDAO {
	
	public Integer addChapterPage(ChapterPage chapterPage) throws ChapterNotFoundException;
	public Boolean deleteChapterPage(Integer pageId) throws ChapterPageNotFoundException;
	public ChapterPage findByPageId(Integer pageId)throws ChapterPageNotFoundException;
	public List<ChapterPage> findAllChapterPagesByChapterId(Integer chapterId) throws ChapterPageNotFoundException;
	public Boolean updateChapterPageOrder(List<Integer> chapterPageList) throws ChapterPageNotFoundException;
	public Boolean updatePageUrl(Integer pageId, String newUrl) throws ChapterPageNotFoundException;
	public Boolean deleteAllPagesByChapterId(Integer chapterId) throws ChapterPageNotFoundException;
	public Boolean setPublishChapterPage(Integer pageId, Boolean published);
}
