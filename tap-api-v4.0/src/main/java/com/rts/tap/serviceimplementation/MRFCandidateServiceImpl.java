package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rts.tap.dao.MRFCandidateDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.service.MRFCandidateService;

@Service
@Transactional
public class MRFCandidateServiceImpl implements MRFCandidateService {

    private MRFCandidateDao mrfCandidateRepository;

    public MRFCandidateServiceImpl(MRFCandidateDao mrfCandidateRepository) {
		super();
		this.mrfCandidateRepository = mrfCandidateRepository;
	}

	@Override
    public List<MRFCandidate> getAllCandidates() {
        return mrfCandidateRepository.getAllCandidates();
    }

    @Override
    public MRFCandidate getCandidateById(Long id) {
        return mrfCandidateRepository.getCandidateById(id);
    }

    @Override
    public MRFCandidate saveCandidate(MRFCandidate mrfCandidate) {
        return mrfCandidateRepository.saveCandidate(mrfCandidate);
    }

    @Override
    public MRFCandidate updateCandidate(Long id, MRFCandidate mrfCandidate) {
        MRFCandidate existingCandidate = mrfCandidateRepository.getCandidateById(id);
        if (existingCandidate != null) {
            existingCandidate.setCandidate(mrfCandidate.getCandidate());
            existingCandidate.setMrfRecruiter(mrfCandidate.getMrfRecruiter());
            existingCandidate.setStatus(mrfCandidate.getStatus());
            return mrfCandidateRepository.saveCandidate(existingCandidate);
        }
        return null;
    }

    @Override
    public void deleteCandidate(Long id) {
        mrfCandidateRepository.deleteCandidate(id);
    }
    
    @Override
    public List<Candidate> getCandidateByScoreId(Long id) {
        return mrfCandidateRepository.getRemainingCandidates(id);
    }

    @Override
    public List<Candidate> getSelectedCandidateByMrfId(Long id) {
        return mrfCandidateRepository.getSelectedCandidates(id);
    }
}
