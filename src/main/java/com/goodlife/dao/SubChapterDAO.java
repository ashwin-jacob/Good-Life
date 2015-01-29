package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.*;

public interface SubChapterDAO {
	
	public Integer addSubChapter(SubChapter subChapter);
	public void deleteSubChapter(Integer subChapId) throws SubChapterNotFoundException;
	public void updateOrder(List<Integer> subChapterIdList) throws SubChapterNotFoundException;
	public void updateTitle(Integer subChapId, String subChapTitle) throws SubChapterNotFoundException;
	public void updateDescription(Integer subChapId, String subChapDescr) throws SubChapterNotFoundException;
	public List<SubChapter> getSubChapListByChapter(Integer chapId) throws SubChapterNotFoundException;
	public SubChapter getSubChapterById(Integer subChapId) throws SubChapterNotFoundException;
}
