package com.rts.tap.dao;

import com.rts.tap.model.Interviewer;

public interface InterviewerDao {
	public void addInterviewer(Interviewer interviewer);

	public void deleteInterviewerById(Long interviewerId);
	
	public void updateInterviewer(Interviewer interviewer);
}
