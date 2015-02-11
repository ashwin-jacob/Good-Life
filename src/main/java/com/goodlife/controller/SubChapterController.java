package com.goodlife.controller;

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

import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.SubChapter;

@Controller
@RequestMapping(value = "/subchapterlookup")
@Transactional
public class SubChapterController {
	
	@Autowired
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	static final Logger logger = LogManager.getLogger(SubChapterController.class.getName());
	
	@Autowired
	private SubChapterDAO subChapterDAO;
	
	@ResponseBody
	@RequestMapping(value = "/addsubchapter", method = RequestMethod.GET)
	public AjaxResponse<Integer> addSubChapter(@RequestParam(value="chapId") Integer chapId,
											 @RequestParam(value="subChapTitle") String subChapTitle,
											 @RequestParam(value="subChapDescr") String subChapDescr,
											 @RequestParam(value="orderId") Integer orderId) throws SubChapterNotFoundException {
		
		SubChapter subChapter = new SubChapter();
		subChapter.setChapId(chapId);
		subChapter.setSubChapTitle(subChapTitle);
		subChapter.setSubChapDescr(subChapDescr);
		subChapter.setOrderId(orderId);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(subChapterDAO.addSubChapter(subChapter));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "deletesubchapter", method = RequestMethod.GET)
	public AjaxResponse<Boolean> deleteSubChapter(@RequestParam(value="subchapId") Integer subChapId) throws SubChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(subChapterDAO.deleteSubChapter(subChapId));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "listallsubchaptersbychapter", method = RequestMethod.GET)
	public AjaxResponse<List<SubChapter>> listAllSubChaptersByChapter(@RequestParam(value="chapId") Integer chapId) throws SubChapterNotFoundException {
		
		List<SubChapter> subChapterList = subChapterDAO.getSubChapListByChapter(chapId);
		
		AjaxResponse<List<SubChapter>> response = new AjaxResponse<List<SubChapter>>();
		response = ajaxResponseBuilder.createSuccessResponse(subChapterList);
		
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatesubchapterorder", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateSubChapterOrder(@RequestParam(value="newSubChapterOrderList") List<Integer> newSubChapterOrderList) throws SubChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(subChapterDAO.updateOrder(newSubChapterOrderList));
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatesubchaptertitle", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateSubChapterTitle(@RequestParam(value="subChapId") Integer subChapId,
													   @RequestParam(value="subChapTitle") String subChapTitle) throws SubChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(subChapterDAO.updateTitle(subChapId, subChapTitle));
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updatesubchapterdescr", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateSubChapterDescr(@RequestParam(value="subChapId") Integer subChapId,
													   @RequestParam(value="subChapDescr") String subChapDescr) throws SubChapterNotFoundException {
		
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(subChapterDAO.updateDescription(subChapId, subChapDescr));
		return response;
		
	}

}
