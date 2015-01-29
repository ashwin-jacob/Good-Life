package com.goodlife.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;

@Controller
@RequestMapping(value = "/multichoice")
public class MultiChoiceController {
	
	@Inject
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	static final Logger logger = LogManager.getLogger(MultiChoiceController.class.getName());
	
	@Autowired
	private MultiChoiceQDAO mcQdao;
	
	@Autowired
	private MultiChoiceOptionDAO mcOptdao;
	
	@ResponseBody
	@RequestMapping(value = "/addmultichoicequestion", method = RequestMethod.GET)
	public AjaxResponse<Integer> addMultiChoiceQuestion(@RequestParam(value="mcQId") Integer mcQId,
											 @RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="corrAns") Integer corrAns,
											 @RequestParam(value="orderId") Integer orderId) {
		
		MultiChoiceQ mcQ = new MultiChoiceQ();
		mcQ.setSubChapId(subChapId);
		mcQ.setHelpText(helpTxt);
		mcQ.setCorrectAnswer(corrAns);
		mcQ.setOrderId(orderId);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(mcQdao.addMultiChoice(mcQ));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addmultichoiceoption", method = RequestMethod.GET)
	public AjaxResponse<Integer> addMultiChoiceOption(@RequestParam(value="mcQId") Integer mcQId,
											 @RequestParam(value="choiceText") String choiceText) {
		
		MultiChoiceOption mcOpt = new MultiChoiceOption();
		mcOpt.setMultiQuesId(mcQId);
		mcOpt.setChoiceText(choiceText);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(mcOptdao.addMultiChoiceOption(mcOpt));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletemultichoiceoption", method = RequestMethod.GET)
	public AjaxResponse<Integer> deleteMultiChoiceOption(@RequestParam(value="Id") Integer mcOptId) {
		
		try {
			mcOptdao.deleteMultiChoiceOption(mcOptId);
		} catch (ObjectNotFoundException e) {
			logger.debug("DELETE: Multi Choice Option id: " + mcOptId + " not found.");
		}
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletemultichoiceques", method = RequestMethod.GET)
	public AjaxResponse<Integer> deleteMultiChoiceQuestion(@RequestParam(value="Id") Integer mcQId) {
		
		try {
			mcQdao.deleteMultiChoice(mcQId);
		} catch (ObjectNotFoundException e) {
			logger.debug("DELETE: Multi Choice id: " + mcQId + " not found.");
		}
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listalloptionsbyquestion", method = RequestMethod.GET)
	public AjaxResponse<List<MultiChoiceOption>> listAllOptionsByQuestion(@RequestParam(value="quesId") Integer quesId) throws ObjectNotFoundException {
		
		List<MultiChoiceOption> optionList = mcOptdao.getMultiChoiceOptions(quesId);	
		
		AjaxResponse<List<MultiChoiceOption>> response = new AjaxResponse<List<MultiChoiceOption>>();
		response = ajaxResponseBuilder.createSuccessResponse(optionList);
		
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/updateoptiontext", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateOptionText(@RequestParam(value="optionId") Integer optionId, 
			@RequestParam(value="optionText") String optionText) throws ObjectNotFoundException {
		
		mcOptdao.updateChoiceText(optionId, optionText);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listallquestionbysubchapter", method = RequestMethod.GET)
	public AjaxResponse<List<MultiChoiceQ>> listAllQuestionBySubchapter(
			@RequestParam(value="subChapId") Integer subChapId) throws ObjectNotFoundException {
		
		List<MultiChoiceQ> mcQList = mcQdao.getAllMultiChoice(subChapId);	
		
		AjaxResponse<List<MultiChoiceQ>> response = new AjaxResponse<List<MultiChoiceQ>>();
		response = ajaxResponseBuilder.createSuccessResponse(mcQList);
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatequestionorder", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateQuestionOrder(
			@RequestParam(value="multiChoiceIdList") List<Integer> multiChoiceIdList) throws ObjectNotFoundException {
		
		mcQdao.updateOrder(multiChoiceIdList);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatequestiontext", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateQuestionText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") String quesText) throws ObjectNotFoundException {
		
		mcQdao.updateQuestionText(multiChoiceId, quesText);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateHelpText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="helpText") String helpText) throws ObjectNotFoundException {
		
		mcQdao.updateQuestionText(multiChoiceId, helpText);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatecorrectanswer", method = RequestMethod.GET)
	public AjaxResponse<Integer> updateCorrectAnswer(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") Integer correctAnswer) throws ObjectNotFoundException {
		
		mcQdao.updateCorrectAnswer(multiChoiceId, correctAnswer);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(0);
		return response;
	}	
}
