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
import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.Chapter;
import com.goodlife.model.ChapterPage;


@Controller
@RequestMapping(value = "/chapterlookup")
@Transactional
public class ChapterController {
	
	@Autowired
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	static final Logger logger = LogManager.getLogger(ChapterController.class.getName());
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@Autowired
	private ChapterPageDAO chapterPageDAO;
	
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
	public AjaxResponse<Boolean> updateChapterOrder(@RequestParam(value="newChapterOrderList")List<Integer> newChapterOrderList) throws ChapterNotFoundException {
				
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.updateOrder(newChapterOrderList));
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechaptertitle", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateChapterTitle(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapTitle")String chapTitle) throws ChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.updateTitle(chapId, chapTitle));
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechapterdescr", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateChapterDescr(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapDescr")String chapDescr) throws ChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterDAO.updateDescr(chapId, chapDescr));
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "addchapterpage", method = RequestMethod.GET)
	public AjaxResponse<Integer> addChapterPage(@RequestParam(value="chapId") Integer chapId,
			 									@RequestParam(value="page_num") Integer page_num,
			 									@RequestParam(value="pageUrl") String pageUrl) throws ChapterPageNotFoundException{
		
		ChapterPage chapterPage = new ChapterPage();
		chapterPage.setChapId(chapId);
		chapterPage.setPage_num(page_num);
		chapterPage.setPageUrl(pageUrl);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterPageDAO.addChapterPage(chapterPage));
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletechapterpage", method = RequestMethod.GET)
	public AjaxResponse<Boolean> deleteChapterPage(@RequestParam(value="pageId") Integer pageId) throws ChapterPageNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterPageDAO.deleteChapterPage(pageId));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listchapterpagesbychapid", method = RequestMethod.GET)
	public AjaxResponse<List<ChapterPage>> listChapterPagesByChapId(@RequestParam(value="chapId") Integer chapId) throws ChapterPageNotFoundException {
		
		List<ChapterPage> chapterPagesList = chapterPageDAO.findAllChapterPagesByChapterId(chapId);
		
		AjaxResponse<List<ChapterPage>> response = new AjaxResponse<List<ChapterPage>>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterPagesList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatechapterpageorder", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateChapterPageOrder(@RequestParam(value="newChapterPageOrderList")List<Integer> newChapterPageOrderList) throws ChapterPageNotFoundException {
				
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterPageDAO.updateChapterPageOrder(newChapterPageOrderList));
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechapterpageurl", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateChapterPageUrl(@RequestParam(value="pageId")Integer pageId,
														@RequestParam(value="pageUrl")String pageUrl) throws ChapterPageNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(chapterPageDAO.updatePageUrl(pageId, pageUrl));
		return response;
		
	}
	
}
