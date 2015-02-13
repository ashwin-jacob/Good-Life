
package com.goodlife.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.model.UploadFileQ;

public interface UploadFileQDAO {

	public Integer addUploadFileQuestion(UploadFileQ uploadFileQ);
	public Boolean deleteUploadFileQuestion(Integer uploadQuesId)throws ObjectNotFoundException;
	public Boolean updateDescription(Integer uploadQuesId, String description) throws ObjectNotFoundException;
	public Boolean updateHelpText(Integer uploadQuesId, String helpText) throws ObjectNotFoundException;
	public UploadFileQ getUploadFileQuestion(Integer subChapId) throws ObjectNotFoundException;
	public List<UploadFileQ> findAllUploadFileQBySubchapId(Integer subChapId);
	//public Boolean updateOrder(List<Integer> quesIdList) throws ObjectNotFoundException;
}
