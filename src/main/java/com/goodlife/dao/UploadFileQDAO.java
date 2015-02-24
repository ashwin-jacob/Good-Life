
package com.goodlife.dao;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.model.UploadFileQ;

public interface UploadFileQDAO {

	public Integer addUploadFileQuestion(UploadFileQ uploadFileQ);
	public Boolean deleteUploadFileQuestion(Integer uploadQuesId)throws ObjectNotFoundException;
	public Boolean updateDescription(Integer uploadQuesId, String description) throws ObjectNotFoundException;
	public Boolean updateHelpText(Integer uploadQuesId, String helpText) throws ObjectNotFoundException;
	public UploadFileQ getUploadFileQuestion(Integer uploadFileQId) throws ObjectNotFoundException;
	public UploadFileQ getUploadFileQBySubchapId(Integer subChapId);
	public Boolean setPublishedUploadFileQ(Integer quesId, Boolean published);
}
