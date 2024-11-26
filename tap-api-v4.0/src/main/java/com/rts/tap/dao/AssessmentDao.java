package com.rts.tap.dao;

import com.rts.tap.model.Assessment;

import java.util.List;

public interface AssessmentDao {

	Assessment save(Assessment assessment);
	
	Assessment update(Assessment assessment);

	Assessment findById(Long id);

	List<Assessment> findAll();

	void delete(Long id);
	
	List<Assessment> findAssessmentByMrfId(Long id);
}
