package com.goodlife.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.SubChapter;
import com.goodlife.model.UploadFileQ;

@Controller
@RequestMapping(value = "/student")
@Transactional
public class StudentCurriculumController {
	static final Logger logger = LogManager.getLogger(StudentCurriculumController.class.getName());

	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@Autowired
	private SubChapterDAO subChapDAO;
	
	@Autowired
	private MultiChoiceQDAO multiChoiceQDAO;

	@Autowired
	private MultiChoiceOptionDAO multiChoiceOptionDAO;
	
	@Autowired
	private MultiChoiceUserAnsDAO multiChoiceUserAnsDAO;
	
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnswerUserAnsDAO;
	
	@Autowired
	private ShortAnswerQDAO shortAnswerQDAO;
	
	@Autowired
	private UploadedAnswerDAO uploadedAnswerDAO;
	
	@Autowired
	private UploadFileQDAO uploadFileQDAO;
	
	@RequestMapping(value = "/getallowedchapters", method = RequestMethod.GET)
	public String getAllowedChapters(@RequestParam(value = "userId") Integer userId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(studentDAO.getAllowedChapters(userId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/getsubchapsbychapter", method = RequestMethod.GET)
	public String getSubChapsByChapter(@RequestParam(value = "chapId") Integer chapId){
		
		List<SubChapter> subChapList = new ArrayList<SubChapter>();
		try {
			subChapList = subChapDAO.getSubChapListByChapter(chapId);
		} catch (SubChapterNotFoundException e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/getmultichoicebysubchap", method = RequestMethod.GET)
	public String getMultiChoiceBySubChap(@RequestParam(value = "subChapId") Integer subChapId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(multiChoiceQDAO.getAllMultiChoice(subChapId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/getmultichoiceoptions", method = RequestMethod.GET)
	public String getMultiChoiceOptions(@RequestParam(value = "multiQuesId") Integer multiQuesId){
		
		List<MultiChoiceOption> optionList = new ArrayList<MultiChoiceOption>();
		try {
			optionList = multiChoiceOptionDAO.getMultiChoiceOptions(multiQuesId);
		} catch (MultipleChoiceOptionNotFoundException e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(optionList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/getmultichoiceuseranswer", method = RequestMethod.GET)
	public String getMultiChoiceUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "multiQuesId") Integer multiQuesId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(multiChoiceUserAnsDAO.getUserAnswer(userId, multiQuesId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/getshortansweruseranswer", method = RequestMethod.GET)
	public String getShortAnswerUserAnswer(@RequestParam(value = "userId") Integer userId,
														  @RequestParam(value = "shortAnsId") Integer shortAnsId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnswerUserAnsDAO.getUserAnswer(userId, shortAnsId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/issubchapcomplete", method = RequestMethod.GET)
	public String isSubChapComplete(@RequestParam(value = "userId") Integer userId,
									 @RequestParam(value = "subChapId") Integer subChapId){
		
		Boolean isComplete = multiChoiceUserAnsDAO.isMultiChoiceSubChapComplete(userId, subChapId) ||
							 shortAnswerUserAnsDAO.isShortAnswerSubChapComplete(userId, subChapId) ||
							 uploadedAnswerDAO.isUploadedQuestionComplete(userId, subChapId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isComplete);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	/*
	 * Returns an array of the form object(ie upload file question)
	 * and a character representing the type of object(ie 'm')
	 * The array is always of size 2 or null.
	 * 
	 * Return types for index 1
	 * - List<MultipleChoiceQ>
	 * - List<ShortAnswerQ>
	 * - List<UploadFileQ> --> always size = 1
	 * 
	 * Return types for index 2
	 * - 'm' --> for list of multiple choice questions
	 * - 's' --> for list of short answer questions
	 * - 'u' --> for a single upload file question
	 */
	@RequestMapping(value = "/getsubchapform", method = RequestMethod.GET)
	public String getSubChapForm(@RequestParam(value = "subChapId") Integer subChapId) throws SubChapterNotFoundException{
		
		ArrayList<Object> formArray = new ArrayList<Object>();
		if(multiChoiceQDAO.getAllMultiChoice(subChapId) != null){
			formArray.add(multiChoiceQDAO.getAllMultiChoice(subChapId));
			formArray.add('m');
		}
		else if(shortAnswerQDAO.getShortAnswerBySubChapter(subChapId) != null){
			formArray.add(shortAnswerQDAO.getShortAnswerBySubChapter(subChapId));
			formArray.add('s');
		}
		else if(uploadFileQDAO.getUploadFileQuestion(subChapId) != null){
			List<UploadFileQ> uploadList = new ArrayList<UploadFileQ>();
			uploadList.add(uploadFileQDAO.getUploadFileQuestion(subChapId));
			formArray.add(uploadList);
			formArray.add('u');
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(formArray);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/updatecurrentchapter", method = RequestMethod.GET)
	public String updateCurrentChapter(@RequestParam(value = "userId") Integer userId,
										@RequestParam(value = "chapId") Integer chapId){
		
		Boolean isSuccess;
		if(studentDAO.updateCurrentChapter(userId, chapId) == null)
			isSuccess = Boolean.FALSE;
		else
			isSuccess = studentDAO.updateCurrentChapter(userId, chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isSuccess);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
}