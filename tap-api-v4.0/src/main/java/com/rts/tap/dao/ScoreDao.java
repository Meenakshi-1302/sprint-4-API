package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.Score;

public interface ScoreDao {

	void save(Score score);

	void update(Score score);

	Score findById(Long id);

	List<Score> findAll();

	void delete(Long id);
	
	List<Score> findByMrfId(Long id);

	List<Score> findScoreByMrfIdAndCandidateId(Long mrfId, Long candidateId);

}
