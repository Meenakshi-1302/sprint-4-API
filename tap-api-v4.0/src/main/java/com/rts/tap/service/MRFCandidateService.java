package com.rts.tap.service;

import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import java.util.List;

public interface MRFCandidateService {
    List<MRFCandidate> getAllCandidates();
    MRFCandidate getCandidateById(Long id);
    MRFCandidate saveCandidate(MRFCandidate mrfCandidate);
    MRFCandidate updateCandidate(Long id, MRFCandidate mrfCandidate);
    void deleteCandidate(Long id);
    List<Candidate> getCandidateByScoreId(Long id);
	List<Candidate> getSelectedCandidateByMrfId(Long id);
}

