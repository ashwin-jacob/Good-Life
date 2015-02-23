package com.goodlife.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.model.UploadFileQ;

@Controller
@Transactional
@RequestMapping(value = "/uploadQuestion")
public class UploadQuestionController {
	
	static final Logger logger = LogManager.getLogger(UploadQuestionController.class.getName());
	
	@Autowired
	private UploadFileQDAO uploadDAO;
	
	@ResponseBody
	@RequestMapping(value = "/adduploadfilequestion", method = RequestMethod.GET)
	public String addUploadFileQuestion(@RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="descr") String descr){
											 //@RequestParam(value="orderId") Integer orderId) {
		
		UploadFileQ uploadFileQ = new UploadFileQ();
		uploadFileQ.setDescription(descr);
		uploadFileQ.setSubChapId(subChapId);
		uploadFileQ.setHelpText(helpTxt);
		//uploadFileQ.setOrderId(orderId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.addUploadFileQuestion(uploadFileQ));
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
	@RequestMapping(value = "/deleteuploadfilequestion", method = RequestMethod.GET)
	public String deleteUploadFileQuestion(@RequestParam(value="uploadQuesId") Integer uploadQuesId) 
			throws ObjectNotFoundException {
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.deleteUploadFileQuestion(uploadQuesId));
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
	@RequestMapping(value = "/updatedescription", method = RequestMethod.GET)
	public String updateDescription(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="descr") String descr) 
			throws ObjectNotFoundException {
						
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.updateDescription(uploadQuesId, descr));
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
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public String updateHelpText(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="helpTxt") String helpText) throws ObjectNotFoundException {
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.updateHelpText(uploadQuesId, helpText));
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
	@RequestMapping(value = "/getuploadquestionbysubchapter") 
	public String getUploadQuestionBySubchapId(@RequestParam(value="subChapId") Integer subChapId)
			throws ObjectNotFoundException {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadDAO.getUploadFileQuestion(subChapId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/updateorder", method = RequestMethod.GET)
	public Boolean updateOrder(@RequestParam(value="uploadQuesId") List<Integer> quesIdList) 
			throws ObjectNotFoundException {
				
		Boolean response = new Boolean();
		response = ajaxResponseBuilder.createSuccessResponse(uploadDAO.updateOrder(quesIdList));
		return response;
	}*/
}
