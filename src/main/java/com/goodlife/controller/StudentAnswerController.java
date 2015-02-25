package com.goodlife.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.goodlife.model.Chapter;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;
import com.goodlife.model.MultiChoiceUserAns;
import com.goodlife.model.ShortAnswerUserAnswer;
import com.goodlife.model.SubChapter;
import com.goodlife.model.UploadFileQ;
import com.goodlife.model.UploadedAnswer;

@Controller
@RequestMapping(value = "/student")
@Transactional
public class StudentAnswerController {
	
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
	
	@RequestMapping(value = "/updatecurrentchapter", method = RequestMethod.GET)
	public String updateStudentChapter(@RequestParam(value = "userId") Integer userId,
										@RequestParam(value = "chapId") Integer chapId){
		Boolean isCurrChapAdded = studentDAO.updateCurrentChapter(userId, chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isCurrChapAdded);
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
	
	
	@RequestMapping(value = "/addmultichoiceuseranswer", method = RequestMethod.GET)
	public String addMultiChoiceUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "multiQuesId") Integer multiQuesId,
											@RequestParam(value = "userAnswer") Integer userAnswer){
		
		MultiChoiceUserAns multiChoiceUserAns = new MultiChoiceUserAns(userId, multiQuesId, userAnswer);
		
		
		Boolean isMultiChoiceAnsAdded =  multiChoiceUserAnsDAO.addMultiChoiceAnswer(multiChoiceUserAns);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isMultiChoiceAnsAdded);
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
	
	@RequestMapping(value = "/addshortansweruseranswer", method = RequestMethod.GET)
	public String addShortAnswerUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "saQId") Integer saQId,
											@RequestParam(value = "userAnswer") String userAnswer){
		
		ShortAnswerUserAnswer shortAnswerUserAnswer = new ShortAnswerUserAnswer();
		shortAnswerUserAnswer.setUserId(userId);
		shortAnswerUserAnswer.setSaQId(saQId);
		shortAnswerUserAnswer.setUserAnswer(userAnswer);
		
		Boolean isShortAnsAdded = shortAnswerUserAnsDAO.addUserAnswer(shortAnswerUserAnswer);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isShortAnsAdded);
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
	
	@RequestMapping(value = "/adduploadeduseranswer", method = RequestMethod.GET)
	public String addUploadedUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "uploadQuesId") Integer uploadQuesId,
											@RequestParam(value = "mediaTypeId") Integer mediaTypeId,
											@RequestParam(value = "filePath") String filePath){
		
		UploadedAnswer uploadedAnswer = new UploadedAnswer();
		uploadedAnswer.setUserId(userId);
		uploadedAnswer.setUploadQuesId(uploadQuesId);
		uploadedAnswer.setMediaTypeId(mediaTypeId);
		uploadedAnswer.setFilePath(filePath);
		
		Integer isUploadedAnswerAdded = uploadedAnswerDAO.addUploadedAnswer(uploadedAnswer);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isUploadedAnswerAdded);
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
	
	@RequestMapping(value = "/updateshortansweruseranswer", method = RequestMethod.GET)
	public String updateShortAnswerUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "saQId") Integer saQId,
											@RequestParam(value = "userAnswer") String userAnswer){
		
		ShortAnswerUserAnswer shortAnswerUserAnswer = shortAnswerUserAnsDAO.getUserAnswer(userId, saQId);
		shortAnswerUserAnswer.setUserAnswer(userAnswer);
		
		Boolean isShortAnsUpdated = shortAnswerUserAnsDAO.addUserAnswer(shortAnswerUserAnswer);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isShortAnsUpdated);
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
	
	@RequestMapping(value = "/updatemultichoiceuseranswer", method = RequestMethod.GET)
	public String updateMultiChoiceUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "multiQuesId") Integer multiQuesId,
											@RequestParam(value = "userAnswer") Integer userAnswer){
		
		MultiChoiceUserAns multiChoiceUserAns = multiChoiceUserAnsDAO.getUserAnswerObj(userId, multiQuesId);
		multiChoiceUserAns.setUserAnswer(userAnswer);
		
		Boolean isMultiChoiceAnsUpdated = multiChoiceUserAnsDAO.addMultiChoiceAnswer(multiChoiceUserAns);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isMultiChoiceAnsUpdated);
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
	
	@RequestMapping(value = "/updateuploadeduseranswer", method = RequestMethod.GET)
	public String updateUploadedUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "uploadQuesId") Integer uploadQuesId,
											@RequestParam(value = "mediaTypeId") Integer mediaTypeId,
											@RequestParam(value = "filePath") String filePath){
		
		UploadedAnswer uploadedAnswer = uploadedAnswerDAO.getUserAnswer(userId, uploadQuesId);
		uploadedAnswer.setMediaTypeId(mediaTypeId);
		uploadedAnswer.setFilePath(filePath);
		
		
		Integer uploadedAnswerId = uploadedAnswerDAO.addUploadedAnswer(uploadedAnswer);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadedAnswerId);
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
	
	
}
