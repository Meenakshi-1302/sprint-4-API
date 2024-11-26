package com.rts.tap.dao;

import com.rts.tap.model.ApproverLevel;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproverLevelDao {
	
	public void saveApproverLevel(List<ApproverLevel> approverLevel);
	public void deleteApproverLevel(long approverLevelId);
	public void updateApproverLevel(ApproverLevel approverLevel);
	public List<ApproverLevel> getApproverLevelByMrfId(Long mrfId);
	public ApproverLevel findApproverLevelById(long approverLevelId);
	ApproverLevel getApproverLevelByMrfIdAndLevel(Long mrfId, int level);
	public void saveSingleApproverLevel(ApproverLevel approverLevel);
	
}
