package com.rts.tap.service.impl.recruiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.ScoreDao;
import com.rts.tap.model.Score;
import com.rts.tap.serviceimplementation.ScoreServiceImpl;

public class ScoreServiceImplTest {

    @Mock
    private ScoreDao scoreDao;

    @InjectMocks
    private ScoreServiceImpl scoreService;

    private Score score;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        score = new Score();
        score.setScoreId(1L);
        score.setScore(85.0);
        score.setRemarks("Good performance");
        score.setStatus("Passed");
        score.setPasskey("some-passkey");
    }

    @Test
    public void testSaveScore() {
        doNothing().when(scoreDao).save(any(Score.class));

        scoreService.saveScore(score);

        verify(scoreDao, times(1)).save(score);
    }

    @Test
    public void testUpdateScore() {
        doNothing().when(scoreDao).update(any(Score.class));

        scoreService.updateScore(score);

        verify(scoreDao, times(1)).update(score);
    }

    @Test
    public void testGetScoreById() {
        when(scoreDao.findById(anyLong())).thenReturn(score);

        Score retrievedScore = scoreService.getScoreById(1L);

        assertNotNull(retrievedScore);
        assertEquals(score.getScoreId(), retrievedScore.getScoreId());
        verify(scoreDao, times(1)).findById(1L);
    }

    @Test
    public void testGetAllScores() {
        List<Score> scores = new ArrayList<>();
        scores.add(score);
        when(scoreDao.findAll()).thenReturn(scores);

        List<Score> retrievedScores = scoreService.getAllScores();

        assertNotNull(retrievedScores);
        assertEquals(1, retrievedScores.size());
        verify(scoreDao, times(1)).findAll();
    }

    @Test
    public void testDeleteScore() {
        doNothing().when(scoreDao).delete(anyLong());

        scoreService.deleteScore(1L);

        verify(scoreDao, times(1)).delete(1L);
    }

    @Test
    public void testGetScoreOfAssessedCandidate() {
        List<Score> scores = new ArrayList<>();
        scores.add(score);
        when(scoreDao.findByMrfId(anyLong())).thenReturn(scores);

        List<Score> retrievedScores = scoreService.getScoreOfAssessedCandidate(1L);

        assertNotNull(retrievedScores);
        assertEquals(1, retrievedScores.size());
        verify(scoreDao, times(1)).findByMrfId(1L);
    }
}