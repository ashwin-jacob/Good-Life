package com.goodlife.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.ChapterPageNotFoundException;
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
	@RequestMapping(value = "/addchapter", method = RequestMethod.POST)
	public String addChapter(@RequestParam(value="chapTitle") String chapTitle,
											 @RequestParam(value="chapDescr") String chapDescr,
											 @RequestParam(value="orderId") String orderId) throws ChapterNotFoundException {
		logger.debug("inside add chapter");

		Chapter chapter = new Chapter();
		chapter.setChapTitle(chapTitle);
		chapter.setChapDescr(chapDescr);
		chapter.setOrderId(Integer.valueOf(orderId));
		chapter.setPublished(false);
		
		Integer response = 0;
		response = chapterDAO.addChapter(chapter);
		
		logger.debug("response - add chapter: "+ response);

		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		logger.error("hitting the chapter homepage");
		return "curriculum/chapterHome";
	}
	
	@ResponseBody
	@RequestMapping(value = "/publishchapter", method = RequestMethod.GET)
	public Boolean publishChapter(@RequestParam(value="chapId") Integer chapId,
												@RequestParam(value="published") Boolean published) throws ChapterNotFoundException {
		
		Boolean response = chapterDAO.updatePublished(chapId,published);
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletechapter", method = RequestMethod.GET)
	public Integer deleteChapter(@RequestParam(value="chapId") Integer chapId) throws ChapterNotFoundException {
		
		Integer response = chapterDAO.deleteChapter(chapId);
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listpublishedchapters", method = RequestMethod.GET)
	public List<Chapter> listPublishedChapters() throws ChapterNotFoundException {
		
		List<Chapter> allPublishedChapterList = chapterDAO.listAllPublishedChapters();
		
		return allPublishedChapterList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listsavedchapterdrafts", method = RequestMethod.GET)
	public List<Chapter> listAllChapterDrafts() throws ChapterNotFoundException {
		
		List<Chapter> allSavedChapterDraftsList = chapterDAO.listAllChapterDrafts();
		
		return allSavedChapterDraftsList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatechapterorder", method = RequestMethod.GET)
	public Boolean updateChapterOrder(@RequestParam(value="newChapterOrderList")List<Integer> newChapterOrderList) throws ChapterNotFoundException {
				
		Boolean response = chapterDAO.updateOrder(newChapterOrderList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechaptertitle", method = RequestMethod.GET)
	public Boolean updateChapterTitle(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapTitle")String chapTitle) throws ChapterNotFoundException {
		
		Boolean response = chapterDAO.updateTitle(chapId, chapTitle);
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechapterdescr", method = RequestMethod.GET)
	public Boolean updateChapterDescr(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapDescr")String chapDescr) throws ChapterNotFoundException {
		
		Boolean response = chapterDAO.updateDescr(chapId, chapDescr);
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "addchapterpage", method = RequestMethod.GET)
	public Integer addChapterPage(@RequestParam(value="chapId") Integer chapId,
			 									@RequestParam(value="pageNum") Integer pageNum,
			 									@RequestParam(value="pageUrl") String pageUrl) throws ChapterNotFoundException{
		
		ChapterPage chapterPage = new ChapterPage();
		chapterPage.setChapId(chapId);
		chapterPage.setPageNum(pageNum);
		chapterPage.setPageUrl(pageUrl);
		
		Integer response = chapterPageDAO.addChapterPage(chapterPage);
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletechapterpage", method = RequestMethod.GET)
	public Boolean deleteChapterPage(@RequestParam(value="pageId") Integer pageId) throws ChapterPageNotFoundException {
		
		Boolean response = chapterPageDAO.deleteChapterPage(pageId);
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listchapterpagesbychapid", method = RequestMethod.GET)
	public List<ChapterPage> listChapterPagesByChapId(@RequestParam(value="chapId") Integer chapId) throws ChapterPageNotFoundException {
		
		List<ChapterPage> chapterPagesList = chapterPageDAO.findAllChapterPagesByChapterId(chapId);
		
		return chapterPagesList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatechapterpageorder", method = RequestMethod.GET)
	public Boolean updateChapterPageOrder(@RequestParam(value="newChapterPageOrderList")List<Integer> newChapterPageOrderList) throws ChapterPageNotFoundException {
				
		Boolean response = chapterPageDAO.updateChapterPageOrder(newChapterPageOrderList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechapterpageurl", method = RequestMethod.GET)
	public Boolean updateChapterPageUrl(@RequestParam(value="pageId")Integer pageId,
														@RequestParam(value="pageUrl")String pageUrl) throws ChapterPageNotFoundException {
		
		Boolean response = chapterPageDAO.updatePageUrl(pageId, pageUrl);
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "deletechapterpagesbychapid", method = RequestMethod.GET)
	public Boolean deleteAllChapterPagesByChapId(@RequestParam(value="chapId") Integer chapId) throws ChapterPageNotFoundException {
		
		Boolean response = chapterPageDAO.deleteAllPagesByChapterId(chapId);
		return response;
		
	}
	
}
