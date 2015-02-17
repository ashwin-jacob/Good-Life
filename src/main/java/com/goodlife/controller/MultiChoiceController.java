package com.goodlife.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;

@Controller
@RequestMapping(value = "/multichoice")
public class MultiChoiceController {
	
	static final Logger logger = LogManager.getLogger(MultiChoiceController.class.getName());
	
	@Autowired
	private MultiChoiceQDAO mcQdao;
	
	@Autowired
	private MultiChoiceOptionDAO mcOptdao;
	
	@ResponseBody
	@RequestMapping(value = "/addmultichoicequestion", method = RequestMethod.GET)
	public Integer addMultiChoiceQuestion(@RequestParam(value="questionText") String questionText,
											 @RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="corrAns") Integer corrAns,
											 @RequestParam(value="orderId") Integer orderId) {
		
		MultiChoiceQ mcQ = new MultiChoiceQ();
		mcQ.setQuesText(questionText);
		mcQ.setSubChapId(subChapId);
		mcQ.setHelpText(helpTxt);
		mcQ.setCorrectAnswer(corrAns);
		mcQ.setOrderId(orderId);
		
		return mcQdao.addMultiChoice(mcQ);
	}
	
	@ResponseBody
	@RequestMapping(value = "/addmultichoiceoption", method = RequestMethod.GET)
	public Integer addMultiChoiceOption(@RequestParam(value="mcQId") Integer mcQId,
											 @RequestParam(value="choiceText") String choiceText) {
		
		MultiChoiceOption mcOpt = new MultiChoiceOption();
		mcOpt.setMultiQuesId(mcQId);
		mcOpt.setChoiceText(choiceText);

		return mcOptdao.addMultiChoiceOption(mcOpt);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletemultichoiceoption", method = RequestMethod.GET)
	public Boolean deleteMultiChoiceOption(@RequestParam(value="Id") Integer mcOptId) throws MultipleChoiceOptionNotFoundException {
				
		Boolean response = mcOptdao.deleteMultiChoiceOption(mcOptId);
		if(response == null)
			return Boolean.FALSE;
		else
			return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletemultichoiceques", method = RequestMethod.GET)
	public Boolean deleteMultiChoiceQuestion(@RequestParam(value="Id") Integer mcQId) throws MultipleChoiceNotFoundException {
		
		Boolean response = mcQdao.deleteMultiChoice(mcQId);
		if(response == null)
			return Boolean.FALSE;
		else
			return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listalloptionsbyquestion", method = RequestMethod.GET)
	public List<MultiChoiceOption> listAllOptionsByQuestion(@RequestParam(value="quesId") Integer quesId) throws MultipleChoiceOptionNotFoundException {
		
		List<MultiChoiceOption> optionList = mcOptdao.getMultiChoiceOptions(quesId);	
		
		if(optionList == null)
			return new ArrayList<MultiChoiceOption>();
		else
			return optionList;
	}

	@ResponseBody
	@RequestMapping(value = "/updateoptiontext", method = RequestMethod.GET)
	public Boolean updateOptionText(@RequestParam(value="optionId") Integer optionId, 
			@RequestParam(value="optionText") String optionText) throws MultipleChoiceOptionNotFoundException {
		
		Boolean response = mcOptdao.updateChoiceText(optionId, optionText);
		if(response == null)
			return Boolean.FALSE;
		else
			return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listallquestionbysubchapter", method = RequestMethod.GET)
	public List<MultiChoiceQ> listAllQuestionBySubchapter(
			@RequestParam(value="subChapId") Integer subChapId) throws ObjectNotFoundException {
		
		List<MultiChoiceQ> mcQList = mcQdao.getAllMultiChoice(subChapId);	
		
		if(mcQList == null)
			return new ArrayList<MultiChoiceQ>();
		else
			return mcQList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatequestionorder", method = RequestMethod.GET)
	public Boolean updateQuestionOrder(
			@RequestParam(value="multiChoiceIdList") List<Integer> multiChoiceIdList) throws MultipleChoiceNotFoundException {
				
		Boolean response = mcQdao.updateOrder(multiChoiceIdList);
		if(response == null)
			return Boolean.FALSE;
		else
			return response;	}
	
	@ResponseBody
	@RequestMapping(value = "/updatequestiontext", method = RequestMethod.GET)
	public Boolean updateQuestionText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") String quesText) throws MultipleChoiceNotFoundException {
				
		Boolean response = mcQdao.updateQuestionText(multiChoiceId, quesText);
		if(response == null)
			return Boolean.FALSE;
		else
			return response;
		}
	
	@ResponseBody
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public Boolean updateHelpText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="helpText") String helpText) throws MultipleChoiceNotFoundException {
				
		Boolean response = mcQdao.updateQuestionText(multiChoiceId, helpText);
		if(response == null)
			return Boolean.FALSE;
		else
			return response;	}
	
	@ResponseBody
	@RequestMapping(value = "/updatecorrectanswer", method = RequestMethod.GET)
	public Boolean updateCorrectAnswer(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") Integer correctAnswer) throws MultipleChoiceNotFoundException {
				
		Boolean response = mcQdao.updateCorrectAnswer(multiChoiceId, correctAnswer);
		if(response == null)
			return Boolean.FALSE;
		else
			return response;
		}	
}
