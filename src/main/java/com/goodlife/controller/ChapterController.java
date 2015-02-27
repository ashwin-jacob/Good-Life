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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.ChapterPage;
import com.goodlife.model.ObjectPair;
import com.goodlife.model.SubChapter;


@Controller
@RequestMapping(value = "/chapterlookup")
@Transactional
public class ChapterController {
	
	static final Logger logger = LogManager.getLogger(ChapterController.class.getName());
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@Autowired
	private ChapterPageDAO chapterPageDAO;
	
	@Autowired
	private SubChapterDAO subChapterDAO;
	
	@ResponseBody
	@RequestMapping(value = "/addchapter", method = RequestMethod.POST)
	public String addChapter(@RequestParam(value="chapTitle") String chapTitle,
											 @RequestParam(value="chapDescr") String chapDescr,
											 @RequestParam(value="orderId") String orderId){
		logger.debug("inside add chapter");

		Chapter chapter = new Chapter();
		chapter.setChapTitle(chapTitle);
		chapter.setChapDescr(chapDescr);
		chapter.setOrderId(Integer.valueOf(orderId));
		chapter.setPublished(false);
		
		Integer response = 0;
		response = chapterDAO.addChapter(chapter);
		
		logger.debug("response - add chapter: "+ response);

		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		logger.error("hitting the chapter homepage");
		return "curriculum/chapterHome";
	}
	
	@ResponseBody
	@RequestMapping(value = "/publishchapter", method = RequestMethod.GET)
	public String publishChapter(@RequestParam(value="chapId") Integer chapId,
												@RequestParam(value="published") Boolean published){
		
		Boolean response = chapterDAO.updatePublished(chapId,published);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletechapter", method = RequestMethod.GET)
	public String deleteChapter(@RequestParam(value="chapId") Integer chapId){
		
		Boolean response = chapterDAO.deleteChapter(chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listpublishedchapters", method = RequestMethod.GET)
	public String listPublishedChapters() {
		
		List<Chapter> allPublishedChapterList = chapterDAO.listAllPublishedChapters();
				
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(allPublishedChapterList);
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
	 * The JSON response is an array of ObjectPair objects(which contain a chapter object and its corresponding subchapter list)
	 * The response first prints off all the chapter objects and then the corresponding sub chapter list
	 * Example string:
	 * {"objR":{"chapId":1,"chapDescr":"CHAPTER 1 DESCRIPTION","chapTitle":"CHAPTER 1 TITLE","orderId":1,"published":true},
	 * "objL":[{"subChapId":1,"chapId":1,"subChapDescr":"Sub Chapter 1 Description","subChapTitle":"Sub Chapter 1 Title","orderId":1,"published":true}
	 */
	@ResponseBody
	@RequestMapping(value ="listcurriculum", method = RequestMethod.GET)
	public String listCurriculum(){
		
		List<ObjectPair> curriculumTreeList = new ArrayList<ObjectPair>();
		List<Chapter> chapterList = chapterDAO.listAllChapters();
		List<SubChapter> subChapList;
		
		for(int i = 0; i < chapterList.size(); i++){
			subChapList = subChapterDAO.getSubChapListByChapter(chapterList.get(i).getChapId());
			if(subChapList == null)
				subChapList = new ArrayList<SubChapter>();
			curriculumTreeList.add(new ObjectPair(chapterList.get(i),subChapList));
		}
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(curriculumTreeList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listsavedchapterdrafts", method = RequestMethod.GET)
	public String listAllChapterDrafts() {
		
		List<Chapter> allSavedChapterDraftsList = chapterDAO.listAllChapterDrafts();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(allSavedChapterDraftsList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatechapterorder", method = RequestMethod.GET)
	public String updateChapterOrder(@RequestParam(value="newChapterOrderList")List<Integer> newChapterOrderList){
				
		Boolean response = chapterDAO.updateOrder(newChapterOrderList);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechaptertitle", method = RequestMethod.GET)
	public String updateChapterTitle(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapTitle")String chapTitle){
		
		Boolean response = chapterDAO.updateTitle(chapId, chapTitle);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechapterdescr", method = RequestMethod.GET)
	public String updateChapterDescr(@RequestParam(value="chapId")Integer chapId,
													@RequestParam(value="chapDescr")String chapDescr){
		
		Boolean response = chapterDAO.updateDescr(chapId, chapDescr);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "addchapterpage", method = RequestMethod.GET)
	public String addChapterPage(@RequestParam(value="chapId") Integer chapId,
			 									@RequestParam(value="pageNum") Integer pageNum,
			 									@RequestParam(value="pageUrl") String pageUrl){
		
		ChapterPage chapterPage = new ChapterPage();
		chapterPage.setChapId(chapId);
		chapterPage.setPageNum(pageNum);
		chapterPage.setPageUrl(pageUrl);
		
		Integer response = chapterPageDAO.addChapterPage(chapterPage);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletechapterpage", method = RequestMethod.GET)
	public String deleteChapterPage(@RequestParam(value="pageId") Integer pageId){
		
		Boolean response = chapterPageDAO.deleteChapterPage(pageId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listchapterpagesbychapid", method = RequestMethod.GET)
	public String listChapterPagesByChapId(@RequestParam(value="chapId") Integer chapId){
		
		List<ChapterPage> chapterPagesList = new ArrayList<ChapterPage>();
		try {
			chapterPagesList = chapterPageDAO.findAllChapterPagesByChapterId(chapId);
		} catch (ChapterPageNotFoundException e1) {
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(chapterPagesList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatechapterpageorder", method = RequestMethod.GET)
	public String updateChapterPageOrder(@RequestParam(value="newChapterPageOrderList")List<Integer> newChapterPageOrderList){
				
		Boolean response = chapterPageDAO.updateChapterPageOrder(newChapterPageOrderList);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "updatechapterpageurl", method = RequestMethod.GET)
	public String updateChapterPageUrl(@RequestParam(value="pageId")Integer pageId,
														@RequestParam(value="pageUrl")String pageUrl){
		
		Boolean response = chapterPageDAO.updatePageUrl(pageId, pageUrl);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "deletechapterpagesbychapid", method = RequestMethod.GET)
	public String deleteAllChapterPagesByChapId(@RequestParam(value="chapId") Integer chapId){
		
		Boolean response = chapterPageDAO.deleteAllPagesByChapterId(chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
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
