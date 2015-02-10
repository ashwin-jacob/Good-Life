package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.ChapterController;
import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.Users;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class ChapterControllerTest {

	private static final Integer CHAP_ID = 1;
	private static final String CHAP_TITLE = "CHAPTER 1 TITLE";
	private static final Integer ORDER = 1;
	private static final String CHAP_DESC = "CHAPTER 1 DESCRIPTION";
	private static final Boolean PUBLISHED = true;
	private static final int ARRAY_SIZE = 1;
	
	@Autowired
	private ChapterController chapterController;
	
	@Before
	public void setUp() throws ChapterNotFoundException {
		Chapter chapter = createChapter();
	}

	@Test
	@Transactional
	public void testListPublishedChapters() throws ChapterNotFoundException {
		List<Chapter> chapList = chapterController.listPublishedChapters().getcontent();
		assertEquals(chapList.size(),ARRAY_SIZE);
	}
	
	@Test
	@Transactional
	public void testAddChapter() throws ChapterNotFoundException{
		Integer chapId = chapterController.addChapter(CHAP_TITLE, CHAP_DESC, ORDER + 1).getcontent();
		Integer expectedId = 2;
		assertEquals(chapId,expectedId);
	}
	
	@Test
	@Transactional
	public void testpublishChapter() throws ChapterNotFoundException{
		Integer chapId = chapterController.publishChapter(CHAP_ID,true).getcontent();
		Integer expectedId = 1;
		assertEquals(chapId,expectedId);
	}
	
	public static Chapter createChapter() {
		Chapter chapter = new Chapter();
		chapter.setChapTitle(CHAP_TITLE);
		chapter.setChapDescr(CHAP_DESC);
		chapter.setOrderId(ORDER);
		chapter.setPublished(PUBLISHED);
		chapter.setChapId(CHAP_ID);
		return chapter;
	}
}
