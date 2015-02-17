package com.goodlife.controller;

import java.util.List;

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
	public Integer addSubChapter(@RequestParam(value="chapId") Integer chapId,
											 @RequestParam(value="subChapTitle") String subChapTitle,
											 @RequestParam(value="subChapDescr") String subChapDescr,
											 @RequestParam(value="orderId") Integer orderId) throws SubChapterNotFoundException {
		
		SubChapter subChapter = new SubChapter();
		subChapter.setChapId(chapId);
		subChapter.setSubChapTitle(subChapTitle);
		subChapter.setSubChapDescr(subChapDescr);
		subChapter.setOrderId(orderId);
		
		return subChapterDAO.addSubChapter(subChapter);
	}
	
	@ResponseBody
	@RequestMapping(value = "deletesubchapter", method = RequestMethod.GET)
	public Boolean deleteSubChapter(@RequestParam(value="subchapId") Integer subChapId) throws SubChapterNotFoundException {
		
		Boolean response = subChapterDAO.deleteSubChapter(subChapId);
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "listallsubchaptersbychapter", method = RequestMethod.GET)
	public List<SubChapter> listAllSubChaptersByChapter(@RequestParam(value="chapId") Integer chapId) throws SubChapterNotFoundException {
		
		List<SubChapter> response = subChapterDAO.getSubChapListByChapter(chapId);
		
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatesubchapterorder", method = RequestMethod.GET)
	public Boolean updateSubChapterOrder(@RequestParam(value="newSubChapterOrderList") List<Integer> newSubChapterOrderList) throws SubChapterNotFoundException {
		
		Boolean response = subChapterDAO.updateOrder(newSubChapterOrderList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatesubchaptertitle", method = RequestMethod.GET)
	public Boolean updateSubChapterTitle(@RequestParam(value="subChapId") Integer subChapId,
													   @RequestParam(value="subChapTitle") String subChapTitle) throws SubChapterNotFoundException {
		
		Boolean response = subChapterDAO.updateTitle(subChapId, subChapTitle);
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updatesubchapterdescr", method = RequestMethod.GET)
	public Boolean updateSubChapterDescr(@RequestParam(value="subChapId") Integer subChapId,
													   @RequestParam(value="subChapDescr") String subChapDescr) throws SubChapterNotFoundException {
		
		Boolean response = subChapterDAO.updateDescription(subChapId, subChapDescr);
		return response;
		
	}

}
