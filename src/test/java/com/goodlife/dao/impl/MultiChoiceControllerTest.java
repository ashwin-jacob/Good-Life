package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.ChapterController;
import com.goodlife.controller.MultiChoiceController;
import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class MultiChoiceControllerTest {

	private static final Integer MC_Q_ID = 1;
	private static final Integer NEW_MC_Q_ID = 3;
	private static final Integer SUB_CHAP_ID = 1;
	private static final String HELP_TEXT = "THIS IS HELP TEXT";
	private static final String QUES_TEXT = "QUESTION 2 TEXT";
	private static final Integer CORR_ANS = 1;
	private static final Integer ORDER = 2;
	private static final String CHOICE_TEXT = "OPTION 1";
	private static final Integer OPTION_ID = 1;
	private static final Integer NEW_OPTION_ID = 3;
	
	@Autowired
	private MultiChoiceController multiChoiceController;
	
	@Autowired
	private MultiChoiceQDAO multiChoiceQ;
	
	@Autowired
	private MultiChoiceOptionDAO multiChoiceOption;
	
	@Test
	@Transactional
	public void testAddMultiChoiceQuestion() throws MultipleChoiceNotFoundException{
		Integer multiId = multiChoiceController.addMultiChoiceQuestion(QUES_TEXT, SUB_CHAP_ID, HELP_TEXT, CORR_ANS, ORDER).getcontent();
		assertEquals(multiId,NEW_MC_Q_ID);
	}
	
	@Test
	@Transactional
	public void testDeleteMultiChoiceQuestion() throws MultipleChoiceNotFoundException{
		Boolean success = multiChoiceController.deleteMultiChoiceQuestion(1).getcontent();
		assertTrue(success);
	}
	
	@Test
	@Transactional
	public void testAddMultiChoiceOption() throws MultipleChoiceNotFoundException{
		Integer multiId = multiChoiceController.addMultiChoiceOption(NEW_MC_Q_ID, CHOICE_TEXT).getcontent();
		assertEquals(multiId,NEW_OPTION_ID);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteMultiChoiceOption() throws MultipleChoiceOptionNotFoundException{
		Boolean success = multiChoiceController.deleteMultiChoiceOption(OPTION_ID).getcontent();
		assertTrue(success);
	}
	
	@Test
	@Transactional
	public void testListAllOptionsByQuestion() throws MultipleChoiceOptionNotFoundException{
		List<MultiChoiceOption> multiChoiceList = multiChoiceController.listAllOptionsByQuestion(MC_Q_ID).getcontent();
		assertTrue(multiChoiceList.size() > 0);
	}
	
	@Test
	@Transactional
	public void testUpdateOptionText() throws MultipleChoiceOptionNotFoundException{
		String newText = "New Text";
		Boolean success = multiChoiceController.updateOptionText(OPTION_ID, newText).getcontent();
		assertTrue(success);
		assertEquals(multiChoiceOption.getMultiChoiceOptions(MC_Q_ID).get(0).getChoiceText(),newText);
	}
	
	@Test
	@Transactional
	public void testListAllQuestionBySubChapter() {
		List<MultiChoiceQ> multiChoiceQList = multiChoiceController.listAllQuestionBySubchapter(SUB_CHAP_ID).getcontent();
		assertTrue(multiChoiceQList.size() > 0);
	}
	
	@Test
	@Transactional
	public void testUpdateQuestionOrder() throws MultipleChoiceNotFoundException{
		List<Integer> quesOrder = new ArrayList<Integer>();
		quesOrder.add(0,MC_Q_ID+1);
		quesOrder.add(1,MC_Q_ID);
		Boolean success = multiChoiceController.updateQuestionOrder(quesOrder).getcontent();
		assertTrue(success);
		assertEquals(multiChoiceQ.getMultiChoiceQById(MC_Q_ID).getOrderId(),Integer.valueOf(2));
	}
	
	@Test
	@Transactional
	public void testUpdateQuestionText() throws MultipleChoiceNotFoundException{
		Boolean success = multiChoiceController.updateQuestionText(MC_Q_ID, QUES_TEXT).getcontent();
		assertTrue(success);
		assertEquals(multiChoiceQ.getMultiChoiceQById(MC_Q_ID).getQuesText(),QUES_TEXT);
	}
	@Test
	@Transactional
	public void testUpdateHelpText() throws MultipleChoiceNotFoundException{
		Boolean success = multiChoiceController.updateHelpText(MC_Q_ID, HELP_TEXT).getcontent();
		assertTrue(success);
		assertEquals(multiChoiceQ.getMultiChoiceQById(MC_Q_ID).getHelpText(),HELP_TEXT);
	}
	
	@Test
	@Transactional
	public void testCorrectAnswer() throws MultipleChoiceNotFoundException{
		Boolean success = multiChoiceController.updateCorrectAnswer(MC_Q_ID, CORR_ANS + 1).getcontent();
		assertTrue(success);
		assertTrue(multiChoiceQ.getMultiChoiceQById(MC_Q_ID).getCorrectAnswer() == CORR_ANS + 1);
	}
}
