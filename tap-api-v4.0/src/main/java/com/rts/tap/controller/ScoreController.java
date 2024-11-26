package com.rts.tap.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Score;
import com.rts.tap.service.ScoreService;

@CrossOrigin(origins=APIConstants.FRONT_END_URL)
@RestController
@RequestMapping(path=APIConstants.BASE_SCORE_URL)
public class ScoreController {

    private ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
		super();
		this.scoreService = scoreService;
	}

	@PostMapping(path=APIConstants.SAVE_SCORE_URL)
    public void createScore(@RequestBody Score score) {
        scoreService.saveScore(score);
    }

    @PutMapping(path=APIConstants.UPDATE_SCORE_URL)
    public void updateScore(@PathVariable Long id, @RequestBody Score score) {
        score.setScoreId(id);
        scoreService.updateScore(score);
    }

    @GetMapping(path=APIConstants.GET_BY_ID_SCORE_URL)
    public Score getScore(@PathVariable Long id) {
        return scoreService.getScoreById(id);
    }

    @GetMapping(path=APIConstants.GET_ALL_SCORE_URL)
    public List<Score> getAllScores() {
        return scoreService.getAllScores();
    }

    @DeleteMapping(path=APIConstants.DELETE_SCORE_URL)
    public void deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
    }
    
    @GetMapping(path=APIConstants.GET_ASSESSED_CANDIDATE_URL)
    public List<Score> getScoreOfAssessedCandidate(@PathVariable Long id) {
        return scoreService.getScoreOfAssessedCandidate(id);
    }
    
    @GetMapping(path=APIConstants.GET_SCORE_BY_MRFID_AND_CANDIDATEID)
    public List<Score> getScoreByMrfIdAndCandidateId(@RequestParam Long mrfId, @RequestParam Long candidateId) {
        return scoreService.getScoreByMrfIdAndCandidateId(mrfId,candidateId);
    }
}
