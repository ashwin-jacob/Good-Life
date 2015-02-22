package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.ChapterController;
import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.model.Chapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class ChapterControllerTest {

	private static final Integer CHAP_ID = 1;
	private static final String CHAP_TITLE = "CHAPTER 1 TITLE";
	private static final Integer ORDER = 1;
	private static final String CHAP_DESC = "CHAPTER 1 DESCRIPTION";
	private static final Boolean PUBLISHED = true;
	//private static final Integer PAGE_NUM = 1;
	private static final Integer NEW_PAGE_NUM = 3;
	private static final String NEW_PAGE_URL = "HTTP://newpage.com";
	private static final Integer PAGE_ID = 1;
	private static final Integer NEW_PAGE_ID = 4;
	
	@Autowired
	private ChapterController chapterController;
	
	@Autowired
	private ChapterPageDAO chapterPageDAO;
	
	@SuppressWarnings("unused")
	@Before
	public void setUp() throws ChapterNotFoundException {
		Chapter chapter = createChapter();
	}

	@Test
	@Transactional
	public void testListPublishedChapters() throws ChapterNotFoundException {
		String chapList = chapterController.listPublishedChapters();
		assertNotNull(chapList);
	}
	
	@Test
	@Transactional
	public void testListAllChapterDrafts() throws ChapterNotFoundException {
		List<Chapter> chapList = chapterController.listAllChapterDrafts();
		assertTrue(chapList.size() > 0);
	}
	
	@Test
	@Transactional
	public void testAddChapter() throws ChapterNotFoundException{
		String chapId = chapterController.addChapter(CHAP_TITLE, CHAP_DESC, Integer.toString(ORDER + 1));
		assertNotNull(chapId);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteChapter() throws ChapterNotFoundException{
		Integer chapId = chapterController.deleteChapter(CHAP_ID);
		Integer expectedId = 0;
		assertEquals(chapId,expectedId);
	}
	
	@Test
	@Transactional
	public void testpublishChapter() throws ChapterNotFoundException{
		Boolean isPublished = chapterController.publishChapter(CHAP_ID,true);
		assertTrue(isPublished);
	}
	
	@Test
	@Transactional
	public void testUpdateChapterTitle() throws ChapterNotFoundException{
		String updatedTitle = "New Title!";
		Boolean isUpdated = chapterController.updateChapterTitle(CHAP_ID,updatedTitle);
		assertTrue(isUpdated);
	}
	
	@Test
	@Transactional
	public void testUpdateChapterDescr() throws ChapterNotFoundException{
		String updatedDescr = "New Description!";
		Boolean isUpdated = chapterController.updateChapterTitle(CHAP_ID,updatedDescr);
		assertTrue(isUpdated);
	}
	
	@Test
	@Transactional
	public void testUpdateChapterOrder() throws ChapterNotFoundException{
		List<Integer> chapList = new ArrayList<Integer>();
		chapList.add(2);
		chapList.add(1);
		assertTrue(chapterController.updateChapterOrder(chapList));
	}
	
	@Test
	@Transactional
	public void testAddChapterPage() throws ChapterNotFoundException, ChapterPageNotFoundException {
		Integer pageId = chapterController.addChapterPage(CHAP_ID, NEW_PAGE_NUM, NEW_PAGE_URL);
		assertEquals(pageId,NEW_PAGE_ID);
		assertEquals(chapterPageDAO.findByPageId(NEW_PAGE_ID).getChapId(),CHAP_ID);
		assertEquals(chapterPageDAO.findByPageId(NEW_PAGE_ID).getPageNum(),NEW_PAGE_NUM);
		assertEquals(chapterPageDAO.findByPageId(NEW_PAGE_ID).getPageUrl(),NEW_PAGE_URL);
	}
	
	@Test
	@Transactional
	@Rollback
	public void  testDeleteChapterPage() throws ChapterPageNotFoundException{
		Integer pageCount = chapterController.listChapterPagesByChapId(CHAP_ID).size();
		Boolean success = chapterController.deleteChapterPage(PAGE_ID);
		assertTrue(success);
		assertTrue(pageCount > chapterController.listChapterPagesByChapId(CHAP_ID).size());		
	}
	
	@Test
	@Transactional
	public void testListChapterPagesByChapId() throws ChapterPageNotFoundException{
		Integer pageCount = chapterController.listChapterPagesByChapId(CHAP_ID).size();
		assertTrue(pageCount > 0);
	}
	
	@Test
	@Transactional
	public void testUpdateChapterPageOrder() throws ChapterPageNotFoundException{
		List<Integer> newOrder = new ArrayList<Integer>();
		newOrder.add(0,PAGE_ID+1);
		newOrder.add(1,PAGE_ID);
		Boolean success = chapterController.updateChapterPageOrder(newOrder);
		assertTrue(success);
		assertEquals(chapterPageDAO.findByPageId(PAGE_ID).getPageNum(),Integer.valueOf(2));
	}
	
	@Test
	@Transactional
	public void testUpdateChapterPageUrl() throws ChapterPageNotFoundException{
		Boolean success = chapterController.updateChapterPageUrl(PAGE_ID, NEW_PAGE_URL);
		assertTrue(success);
		assertEquals(chapterPageDAO.findByPageId(PAGE_ID).getPageUrl(),NEW_PAGE_URL);
	}
	
	@Test
	@Transactional
	public void testDeleteChapterPagesByChapId() throws ChapterPageNotFoundException{
		Boolean success = chapterController.deleteAllChapterPagesByChapId(CHAP_ID);
		assertTrue(success);
		Integer newSize = chapterController.listChapterPagesByChapId(CHAP_ID).size();
		assertEquals(newSize,Integer.valueOf(0));
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
