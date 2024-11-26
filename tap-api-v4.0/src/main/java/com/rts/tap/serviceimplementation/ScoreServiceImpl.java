package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.ScoreDao;
import com.rts.tap.model.Score;
import com.rts.tap.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	private ScoreDao scoreDao;

	public ScoreServiceImpl(ScoreDao scoreDao) {
		super();
		this.scoreDao = scoreDao;
	}

	@Override
	public void saveScore(Score score) {
		scoreDao.save(score);
	}

	@Override
	public void updateScore(Score score) {
		scoreDao.update(score);
	}

	@Override
	public Score getScoreById(Long id) {
		return scoreDao.findById(id);
	}

	@Override
	public List<Score> getAllScores() {
		return scoreDao.findAll();
	}

	@Override
	public void deleteScore(Long id) {
		scoreDao.delete(id);
	}

	@Override
	public List<Score> getScoreOfAssessedCandidate(Long id) {
		return scoreDao.findByMrfId(id);
	}

	@Override
	public List<Score> getScoreByMrfIdAndCandidateId(Long mrfId, Long candidateId) {
		return scoreDao.findScoreByMrfIdAndCandidateId(mrfId, candidateId);
	}
}
