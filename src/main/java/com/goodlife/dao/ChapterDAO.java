package com.goodlife.dao;

import java.util.Date;
import java.util.List;

import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.UserStatus;

public interface ChapterDAO {

	public Integer addChapter(Chapter chapter);
	public Integer deleteChapter(Integer chapterId) throws ChapterNotFoundException;
	public Chapter findByChapterId(Integer chapterId) throws ChapterNotFoundException;
	public Boolean updateOrder(List<Integer> chapterList) throws ChapterNotFoundException;
	public Boolean updateTitle(Integer chapterId, String newChapterTitle) throws ChapterNotFoundException;
	public Boolean updateDescr(Integer chapterId, String newChapterDescr) throws ChapterNotFoundException;
	public List<Chapter> listAllChapters() throws ChapterNotFoundException;
	public List<Chapter> listAllPublishedChapters() throws ChapterNotFoundException;
	public List<Chapter> listAllChapterDrafts() throws ChapterNotFoundException;
	public Boolean updatePublished(Integer chapterId, Boolean published) throws ChapterNotFoundException;
	
}
