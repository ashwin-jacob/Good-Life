package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.*;

public interface SubChapterDAO {
	
	public Integer addSubChapter(Integer chapId, String subChapDescr, String subChapTitle, Integer orderId) throws UserNotFoundException;
	public void deleteSubChapter(Integer subChapId) throws UserNotFoundException;
	public void updateOrder(List<Integer> subChapterIdList) throws UserNotFoundException;
	public void updateTitle(Integer subChapId, String subChapTitle) throws UserNotFoundException;
	public void updateDescription(Integer subChapId, String subChapDescr) throws UserNotFoundException;
	public List<SubChapter> getSubChapListByChapter(Integer chapId) throws UserNotFoundException;
}
