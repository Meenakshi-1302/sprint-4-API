package com.rts.tap.dto;

import java.util.List;
import com.rts.tap.model.ApproverLevel;

public class ApproverLevelDto {
	List<ApproverLevel> approverLevel;
	Long buHeadId =7l;

	public ApproverLevelDto() {
		super();
	}

	public List<ApproverLevel> getApproverLevel() {
		return approverLevel;
	}

	public void setApproverLevel(List<ApproverLevel> approverLevel) {
		this.approverLevel = approverLevel;
	}

	public Long getBuHeadId() {
		return buHeadId;
	}

	public void setBuHeadId(Long buHeadId) {
		this.buHeadId = buHeadId;
	}
}
