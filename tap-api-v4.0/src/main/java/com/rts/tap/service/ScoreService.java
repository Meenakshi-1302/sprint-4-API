package com.rts.tap.service;

import com.rts.tap.model.Score;

import java.util.List;

public interface ScoreService {
    void saveScore(Score score);
    void updateScore(Score score);
    Score getScoreById(Long id);
    List<Score> getAllScores();
    void deleteScore(Long id);
    List<Score> getScoreOfAssessedCandidate(Long id);
	List<Score> getScoreByMrfIdAndCandidateId(Long mrfId, Long candidateId);
}

