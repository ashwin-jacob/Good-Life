package com.goodlife.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.goodlife.exceptions.UploadPathException;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceUserAns;
import com.goodlife.model.ShortAnswerUserAnswer;
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
	
	private static final String UPLOAD_DIR = "/resources/usr_ans";
	
	/*@RequestMapping(value = "/updatecurrentchapter", method = RequestMethod.GET)
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
	}*/
	
	
	@ResponseBody
	@RequestMapping(value = "/addorupdateshortansweruseranswer", method = RequestMethod.GET)
	public String updateShortAnswerUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "saQId") Integer saQId,
											@RequestParam(value = "userAnswer") String userAnswer){
		
		ShortAnswerUserAnswer shortAnswerUserAnswer = shortAnswerUserAnsDAO.getUserAnswer(userId, saQId);
		if(shortAnswerUserAnswer == null){
			shortAnswerUserAnswer = new ShortAnswerUserAnswer();
			shortAnswerUserAnswer.setUserId(userId);
			shortAnswerUserAnswer.setSaQId(saQId);
			shortAnswerUserAnswer.setUserAnswer(userAnswer);
		}
		else{
			shortAnswerUserAnswer.setUserAnswer(userAnswer);
		}
		
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
	
	@ResponseBody
	@RequestMapping(value = "/addorupdatemultichoiceuseranswer", method = RequestMethod.GET)
	public String updateMultiChoiceUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "multiQuesId") Integer multiQuesId,
											@RequestParam(value = "userAnswer") Integer userAnswer){
		
		MultiChoiceUserAns multiChoiceUserAns = multiChoiceUserAnsDAO.getUserAnswerObj(userId, multiQuesId);
		Boolean isMultiChoiceAnsUpdated;
		MultiChoiceOption mcOpt;
		
		try {
			mcOpt = multiChoiceOptionDAO.findMultiChoiceOptionById(userAnswer);
		} catch (MultipleChoiceOptionNotFoundException e1) {
			mcOpt = null;
			e1.printStackTrace();
		}
			
		if(mcOpt == null)
			isMultiChoiceAnsUpdated = false;
		else if(multiChoiceUserAns == null){
			multiChoiceUserAns = new MultiChoiceUserAns(userId, multiQuesId, userAnswer);
			isMultiChoiceAnsUpdated = multiChoiceUserAnsDAO.addMultiChoiceAnswer(multiChoiceUserAns);
		}
		else{
			multiChoiceUserAns.setUserAnswer(userAnswer);
			isMultiChoiceAnsUpdated = multiChoiceUserAnsDAO.addMultiChoiceAnswer(multiChoiceUserAns);
		}
		
		
		
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
	
	@ResponseBody
	@RequestMapping(value = "/addorupdateuploadeduseranswer", method = RequestMethod.GET)
	public String updateUploadedUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "uploadQuesId") Integer uploadQuesId,
											@RequestParam(value = "mediaTypeId") Integer mediaTypeId,
											@RequestParam(value = "filePath") String filePath,
											@RequestParam(value = "mpfile") MultipartFile mpfile,
											HttpSession session) throws UploadPathException {
		
		UploadedAnswer uploadedAnswer = uploadedAnswerDAO.getUserAnswer(userId, uploadQuesId);
		
		if(uploadedAnswer == null){
			
			/*uploadedAnswer = new UploadedAnswer();
			uploadedAnswer.setUserId(userId);
			uploadedAnswer.setUploadQuesId(uploadQuesId);
			uploadedAnswer.setMediaTypeId(mediaTypeId);
			uploadedAnswer.setFilePath(filePath);*/
			uploadStudentAnswer(userId, uploadQuesId, filePath, mpfile, mediaTypeId, session);
			
		}
		else{
			uploadedAnswer.setMediaTypeId(mediaTypeId);
			uploadedAnswer.setFilePath(filePath);
		}
		
		
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
	
	// helper method for updateUploadedUserAnswer upload
	private UploadedAnswer uploadStudentAnswer(Integer userId, Integer uploadQuesId, String filePath, MultipartFile mpfile, 
			Integer mediaTypeId, HttpSession session) throws UploadPathException {
		
		UploadedAnswer uploadedAnswer = null;
		
		if (mpfile != null && mpfile.getSize() > 0) {
			Boolean uploadSuccess = false;
			String fileName = null;
			
			// Create upload directory 
			String uploadDirPath = session.getServletContext().getRealPath(UPLOAD_DIR);
			if (uploadDirPath == null) {
				uploadDirPath = "/resources/usr_ans";
				//throw new UploadPathException("upload directory is null");
			}
			// different media types are stored in different directories.
			uploadDirPath += findDir(mpfile);
			
			File uploadDir = new File(uploadDirPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
				
			//String fileExt = FilenameUtils.getExtension(mpfile.getOriginalFilename());
			//if(!fileExt.isEmpty()) fileExt = "." + fileExt;
			fileName = mpfile.getOriginalFilename();
				
			String uploadFilePath = session.getServletContext().getRealPath(UPLOAD_DIR + "/" + fileName);
			if (uploadFilePath == null) {
				uploadFilePath = uploadDirPath + "test.jpg";
				//throw new UploadPathException("upload file path is null");
			}
			File uploadFile = new File(uploadFilePath);
			
			try {
				mpfile.transferTo(uploadFile);
				uploadSuccess = true;
			} catch(IOException e) {
			}
		
			if (uploadSuccess) {
				uploadedAnswer = new UploadedAnswer();
				uploadedAnswer.setUserId(userId);
				uploadedAnswer.setUploadQuesId(uploadQuesId);
				uploadedAnswer.setMediaTypeId(mediaTypeId);
				uploadedAnswer.setFilePath("" + UPLOAD_DIR + "/" + fileName);
			}
		}
		
		if (uploadedAnswer== null) {
			return null;
		}
		return uploadedAnswer;
	}
	
	// helper method to store different media types in different directories.
	private String findDir(MultipartFile file) {
		String dir = "";
		if (file.getContentType().startsWith("image")) {
			dir = "/img";
		} if (file.getContentType().startsWith("text")) {
			dir = "/text";
		} if (file.getContentType().startsWith("video")) {
			dir = "/video";
		} 
		return dir;
	}
}

