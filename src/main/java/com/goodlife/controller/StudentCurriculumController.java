package com.goodlife.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;
import com.goodlife.model.SubChapter;

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
	private UploadedAnswerDAO uploadedAnswerDAO;
	
	@RequestMapping(value = "/getallowedchapters", method = RequestMethod.GET)
	public List<Chapter> getAllowedChapters(@RequestParam(value = "userId") Integer userId){
		List<Chapter> chapList = studentDAO.getAllowedChapters(userId);
		if(chapList == null)
			return new ArrayList<Chapter>();
		else
			return chapList;
	}
	
	@RequestMapping(value = "/getsubchapsbychapter", method = RequestMethod.GET)
	public List<SubChapter> getSubChapsByChapter(@RequestParam(value = "chapId") Integer chapId){
		try {
			List<SubChapter> subChapList = subChapDAO.getSubChapListByChapter(chapId);
			return subChapList;
		} catch (SubChapterNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<SubChapter>();
		}
	}
	
	@RequestMapping(value = "/getmultichoicebysubchap", method = RequestMethod.GET)
	public List<MultiChoiceQ> getMultiChoiceBySubChap(@RequestParam(value = "subChapId") Integer subChapId){
		List<MultiChoiceQ> multiChoiceList = multiChoiceQDAO.getAllMultiChoice(subChapId);
		if(multiChoiceList == null)
			return new ArrayList<MultiChoiceQ>();
		else
			return multiChoiceList;
	}
	
	@RequestMapping(value = "/getmultichoiceoptions", method = RequestMethod.GET)
	public List<MultiChoiceOption> getMultiChoiceOptions(@RequestParam(value = "multiQuesId") Integer multiQuesId){
		try {
			List<MultiChoiceOption> optionList = multiChoiceOptionDAO.getMultiChoiceOptions(multiQuesId);
			return optionList;
		} catch (MultipleChoiceOptionNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<MultiChoiceOption>();
		}
	}
	
	@RequestMapping(value = "/getmultichoiceuseranswer", method = RequestMethod.GET)
	public Integer getMultiChoiceUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "multiQuesId") Integer multiQuesId){
		return multiChoiceUserAnsDAO.getUserAnswer(userId, multiQuesId);
	}
	
	@RequestMapping(value = "/issubchapcomplete", method = RequestMethod.GET)
	public Boolean isSubChapComplete(@RequestParam(value = "userId") Integer userId,
									 @RequestParam(value = "subChapId") Integer subChapId){
		
		Boolean isComplete = multiChoiceUserAnsDAO.isMultiChoiceSubChapComplete(userId, subChapId) ||
							 shortAnswerUserAnsDAO.isShortAnswerSubChapComplete(userId, subChapId) ||
							 uploadedAnswerDAO.isUploadedQuestionComplete(userId, subChapId);
		
		return isComplete;
	}
	
	@RequestMapping(value = "/updatecurrentchapter", method = RequestMethod.GET)
	public Boolean updateCurrentChapter(@RequestParam(value = "userId") Integer userId,
										@RequestParam(value = "chapId") Integer chapId){
		if(studentDAO.updateCurrentChapter(userId, chapId) == null)
			return Boolean.FALSE;
		else
			return studentDAO.updateCurrentChapter(userId, chapId);
	}
	
}