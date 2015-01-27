
package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadFileQ;

public interface UploadFileQDAO {

	public Integer addUploadFileQuestion(UploadFileQ uploadFileQ) throws UserNotFoundException;
	public void updateDescripton(Integer uploadQuesId, String description) throws UserNotFoundException;
	public void updateHelpText(Integer uploadQuesId, String helpText) throws UserNotFoundException;
	public UploadFileQ getUploadFileQuestion(Integer subChapId) throws UserNotFoundException;
	
}
