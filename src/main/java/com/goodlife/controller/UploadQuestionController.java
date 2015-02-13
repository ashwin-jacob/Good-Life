package com.goodlife.controller;

import java.util.List;

import javax.inject.Inject;

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
import com.goodlife.model.AjaxResponse;
import com.goodlife.model.UploadFileQ;

@Controller
@Transactional
@RequestMapping(value = "/uploadQuestion")
public class UploadQuestionController {
	
	static final Logger logger = LogManager.getLogger(UploadQuestionController.class.getName());
	
	@Inject
	private AjaxResponseBuilder ajaxResponseBuilder;
	
	@Autowired
	private UploadFileQDAO uploadDAO;
	
	@ResponseBody
	@RequestMapping(value = "/adduploadfilequestion", method = RequestMethod.GET)
	public AjaxResponse<Integer> addUploadFileQuestion(@RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="descr") String descr){
											 //@RequestParam(value="orderId") Integer orderId) {
		
		UploadFileQ uploadFileQ = new UploadFileQ();
		uploadFileQ.setDescription(descr);
		uploadFileQ.setSubChapId(subChapId);
		uploadFileQ.setHelpText(helpTxt);
		//uploadFileQ.setOrderId(orderId);
		
		AjaxResponse<Integer> response = new AjaxResponse<Integer>();
		response = ajaxResponseBuilder.createSuccessResponse(uploadDAO.addUploadFileQuestion(uploadFileQ));
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteuploadfilequestion", method = RequestMethod.GET)
	public AjaxResponse<Boolean> deleteUploadFileQuestion(@RequestParam(value="uploadQuesId") Integer uploadQuesId) 
			throws ObjectNotFoundException {
				
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(uploadDAO.deleteUploadFileQuestion(uploadQuesId));
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatedescription", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateDescription(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="descr") String descr) 
			throws ObjectNotFoundException {
				
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(uploadDAO.updateDescription(uploadQuesId, descr));
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateHelpText(@RequestParam(value="uploadQuesId") Integer uploadQuesId, 
			@RequestParam(value="helpTxt") String helpText) throws ObjectNotFoundException {
				
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(uploadDAO.updateHelpText(uploadQuesId, helpText));
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/alluploadquestionsbysubchapter") 
	public AjaxResponse<List<UploadFileQ>> allUploadQuestionsBySubchapId(@RequestParam(value="subChapId") Integer subChapId)
			throws ObjectNotFoundException {
		
		List<UploadFileQ> quesList = uploadDAO.findAllUploadFileQBySubchapId(subChapId);
		
		AjaxResponse<List<UploadFileQ>> response = new AjaxResponse<List<UploadFileQ>>();
		response = ajaxResponseBuilder.createSuccessResponse(quesList);
		return response;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/updateorder", method = RequestMethod.GET)
	public AjaxResponse<Boolean> updateOrder(@RequestParam(value="uploadQuesId") List<Integer> quesIdList) 
			throws ObjectNotFoundException {
				
		AjaxResponse<Boolean> response = new AjaxResponse<Boolean>();
		response = ajaxResponseBuilder.createSuccessResponse(uploadDAO.updateOrder(quesIdList));
		return response;
	}*/
}
