package com.goodlife.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.Chapter;


@Controller
@RequestMapping(value = "/chapterlookup")
@Transactional
public class ChapterController {
	
	@Autowired
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	static final Logger logger = LogManager.getLogger(ChapterController.class.getName());
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@ResponseBody
	@RequestMapping(value = "/addchapter", method = RequestMethod.GET)
	public AjaxResponse<Integer> addChapter(@RequestParam(value="chapTitle") String chapTitle,
											 @RequestParam(value="chapDescr") String chapDescr,
											 @RequestParam(value="orderId") Integer orderId) throws ChapterNotFoundException {
		
		Chapter chapter = new Chapter();
		chapter.setChapTitle(chapTitle);
		chapter.setChapDescr(chapDescr);
		chapter.setOrderId(orderId);
		chapter.setPublished(false);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.addChapter(chapter));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/publishchapter", method = RequestMethod.GET)
	public AjaxResponse<Boolean> publishChapter(@RequestParam(value="chapId") Integer chapId,
												@RequestParam(value="published") Boolean published) throws ChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.updatePublished(chapId,published));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletechapter", method = RequestMethod.GET)
	public AjaxResponse<Integer> deleteChapter(@RequestParam(value="chapId") Integer chapId) throws ChapterNotFoundException {
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.deleteChapter(chapId));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listpublishedchapters", method = RequestMethod.GET)
	public AjaxResponse<List<Chapter>> listPublishedChapters() throws ChapterNotFoundException {
		
		List<Chapter> allPublishedChapterList = chapterDAO.listAllPublishedChapters();
		
		AjaxResponse<List<Chapter>> response = new AjaxResponse<List<Chapter>>();
		response = ajaxResponseBuilder.createSuccessResponse(allPublishedChapterList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listsavedchapterdrafts", method = RequestMethod.GET)
	public AjaxResponse<List<Chapter>> listAllChapterDrafts() throws ChapterNotFoundException {
		
		List<Chapter> allSavedChapterDraftsList = chapterDAO.listAllChapterDrafts();
		
		AjaxResponse<List<Chapter>> response = new AjaxResponse<List<Chapter>>();
		response = ajaxResponseBuilder.createSuccessResponse(allSavedChapterDraftsList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatechapterorder", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateChapterOrder(List<Integer> newChapterOrderList) throws ChapterNotFoundException {
				
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.updateOrder(newChapterOrderList));
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechaptertitle", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateChapterTitle(Integer chapId, String chapTitle) throws ChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.updateTitle(chapId, chapTitle));
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechapterdescr", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateChapterDescr(Integer chapId, String chapDescr) throws ChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.updateDescr(chapId, chapDescr));
		return response;
		
	}
	
	
}
