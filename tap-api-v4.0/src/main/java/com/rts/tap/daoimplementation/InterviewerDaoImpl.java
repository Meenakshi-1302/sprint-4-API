package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.InterviewerDao;
import com.rts.tap.model.Interviewer;

import jakarta.persistence.EntityManager;

@Repository
public class InterviewerDaoImpl implements InterviewerDao {
	EntityManager eManager;

	public InterviewerDaoImpl(EntityManager eManager) {
		super();
		this.eManager = eManager;
	}

	@Override
	public void addInterviewer(Interviewer interviewer) {
		eManager.persist(interviewer);
	}

	@Override
	public void deleteInterviewerById(Long interviewerId) {
		Interviewer interviewer = eManager.find(Interviewer.class, interviewerId);
		eManager.remove(interviewer);
	}
	
	@Override
	public void updateInterviewer(Interviewer interviewer) {
		eManager.merge(interviewer);
	}
}
