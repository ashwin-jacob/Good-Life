package com.goodlife.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	public Integer addUploadFileQuestion(@RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="descr") String descr){
											 //@RequestParam(value="orderId") Integer orderId) {
		
		UploadFileQ uploadFileQ = new UploadFileQ();
		uploadFileQ.setDescription(descr);
		uploadFileQ.setSubChapId(subChapId);
		uploadFileQ.setHelpText(helpTxt);
		//uploadFileQ.setOrderId(orderId);
		
		return uploadDAO.addUploadFileQuestion(uploadFileQ);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteuploadfilequestion", method = RequestMethod.GET)
	public Boolean deleteUploadFileQuestion(@RequestParam(value="uploadQuesId") Integer uploadQuesId) 
			throws ObjectNotFoundException {
				
		Boolean response = uploadDAO.deleteUploadFileQuestion(uploadQuesId);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatedescription", method = RequestMethod.GET)
	public Boolean updateDescription(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="descr") String descr) 
			throws ObjectNotFoundException {
				
		Boolean response = uploadDAO.updateDescription(uploadQuesId, descr);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public Boolean updateHelpText(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="helpTxt") String helpText) throws ObjectNotFoundException {
				
		Boolean response = uploadDAO.updateHelpText(uploadQuesId, helpText);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getuploadquestionbysubchapter") 
	public UploadFileQ getUploadQuestionBySubchapId(@RequestParam(value="subChapId") Integer subChapId)
			throws ObjectNotFoundException {
		
		UploadFileQ response = uploadDAO.getUploadFileQuestion(subChapId);
		return response;
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
