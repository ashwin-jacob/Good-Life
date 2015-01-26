package com.goodlife.controller;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.SubChapter;

@Controller
@RequestMapping(value = "/subchapterlookup")
public class SubChapterController {
	
	@Inject
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	static final Logger logger = LogManager.getLogger(ChapterController.class.getName());
	
	@Autowired
	SubChapterDAO subChapterDAO;
	
	@ResponseBody
	@RequestMapping(value = "/addsubchapter", method = RequestMethod.GET)
	public AjaxResponse<Integer> addSubChapter(@RequestParam(value="chapId") Integer chapId,
											 @RequestParam(value="subChapTitle") String subChapTitle,
											 @RequestParam(value="subChapDescr") String subChapDescr,
											 @RequestParam(value="orderId") Integer orderId) throws SubChapterNotFoundException {
		
		SubChapter subChapter = new SubChapter();
		subChapter.setSubChapTitle(subChapTitle);
		subChapter.setSubChapDescr(subChapDescr);
		subChapter.setOrderId(orderId);
		//chapter.setPublished(true);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(subChapterDAO.addSubChapter(subChapter));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "deletesubchapter", method = RequestMethod.GET)
	public AjaxResponse<Integer> deleteSubChapter(@RequestParam(value="subchapId") Integer subChapId) throws SubChapterNotFoundException {
		
		subChapterDAO.deleteSubChapter(subChapId);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		
		return response;
	}
	
	

}
