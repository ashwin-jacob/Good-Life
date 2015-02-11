package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.SubChapter;

public interface SubChapterDAO {
	
	public Integer addSubChapter(SubChapter subChapter) throws SubChapterNotFoundException;
	public Boolean deleteSubChapter(Integer subChapId) throws SubChapterNotFoundException;
	public Boolean updateOrder(List<Integer> subChapterIdList) throws SubChapterNotFoundException;
	public Boolean updateTitle(Integer subChapId, String subChapTitle) throws SubChapterNotFoundException;
	public Boolean updateDescription(Integer subChapId, String subChapDescr) throws SubChapterNotFoundException;
	public List<SubChapter> getSubChapListByChapter(Integer chapId) throws SubChapterNotFoundException;
	public SubChapter getSubChapterById(Integer subChapId) throws SubChapterNotFoundException;
}
