package com.goodlife.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.exceptions.ShortAnswerNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.ShortAnswerQ;

@Controller
@RequestMapping(value = "/shortAnswerQuestion")
public class ShortAnswerQuestionController {
	
	static final Logger logger = LogManager.getLogger(ShortAnswerQuestionController.class.getName());
	
	@Inject
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	@Autowired
	private ShortAnswerQDAO shortAnsDAO;
	
	@ResponseBody
	@RequestMapping(value = "/addshortanswerquestion", method = RequestMethod.GET)
	public AjaxResponse<Integer> addUploadFileQuestion(@RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="descr") String descr,
											 @RequestParam(value="orderId") Integer orderId) {
		
		ShortAnswerQ shortAnswerQ = new ShortAnswerQ();
		shortAnswerQ.setSubChapId(subChapId);
		shortAnswerQ.setHelpText(helpTxt);
		shortAnswerQ.setOrderId(orderId);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(shortAnsDAO.addShortAnswerQuestion(shortAnswerQ));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatequestiontext", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateQuestionText(@RequestParam(value="shortAnsQId") Integer shortAnsQId, 
			@RequestParam(value="quesText") String quesText) throws ShortAnswerNotFoundException {
		
		shortAnsDAO.updateQuestionText(shortAnsQId, quesText);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateHelpText(@RequestParam(value="shortAnsQId") Integer shortAnsQId, 
			@RequestParam(value="helpTxt") String helpText) throws ShortAnswerNotFoundException {
		
		shortAnsDAO.updateHelpText(shortAnsQId, helpText);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/allquestionsbysubchapter") 
	public AjaxResponse<List<ShortAnswerQ>> allShortAnswerQuestionsBySubchapId(
			@RequestParam(value="subChapId") Integer subChapId) throws SubChapterNotFoundException {
		
		List<ShortAnswerQ> quesList = shortAnsDAO.getShortAnswerBySubChapter(subChapId);
		
		AjaxResponse<List<ShortAnswerQ>> response = new AjaxResponse<List<ShortAnswerQ>>();
		response = ajaxResponseBuilder.createSuccessResponse(quesList);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateorder", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateOrder(@RequestParam(value="shortAnsQIdList") 
			List<Integer> shortAnsQIdList) throws ShortAnswerNotFoundException {
		
		shortAnsDAO.updateOrderId(shortAnsQIdList);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}
}
