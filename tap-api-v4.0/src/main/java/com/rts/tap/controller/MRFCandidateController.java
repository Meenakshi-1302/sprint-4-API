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
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.service.MRFCandidateService;

@CrossOrigin(origins=APIConstants.FRONT_END_URL)
@RestController
@RequestMapping(path=APIConstants.BASE_MRFCANDIDATE_URL)
public class MRFCandidateController {

    private MRFCandidateService mrfCandidateService;

    public MRFCandidateController(MRFCandidateService mrfCandidateService) {
		super();
		this.mrfCandidateService = mrfCandidateService;
	}

	@GetMapping(path=APIConstants.GET_ALL_MRFCANDIDATE_URL)
    public List<MRFCandidate> getAllCandidates() {
        return mrfCandidateService.getAllCandidates();
    }

    @GetMapping(path=APIConstants.GET_BY_ID_MRFCANDIDATE_URL)
    public MRFCandidate getCandidateById(@PathVariable Long id) {
        return mrfCandidateService.getCandidateById(id);
    }

    @PostMapping(path=APIConstants.SAVE_MRFCANDIDATE_URL)
    public MRFCandidate saveCandidate(@RequestBody MRFCandidate mrfCandidate) {
        return mrfCandidateService.saveCandidate(mrfCandidate);
    }

    @PutMapping(path=APIConstants.UPDATE_MRFCANDIDATE_URL)
    public MRFCandidate updateCandidate(@PathVariable Long id, @RequestBody MRFCandidate mrfCandidate) {
        return mrfCandidateService.updateCandidate(id, mrfCandidate);
    }

    @DeleteMapping(path=APIConstants.DELETE_MRFCANDIDATE_URL)
    public void deleteCandidate(@PathVariable Long id) {
        mrfCandidateService.deleteCandidate(id);
    }
    
    @GetMapping(path=APIConstants.GET_REMAINING_MRFCANDIDATE_URL)
    public List<Candidate> getCandidateByScoreId(@PathVariable Long id) {
        return mrfCandidateService.getCandidateByScoreId(id);
    }
    
    @GetMapping(path=APIConstants.GET_SELECTED_MRFCANDIDATE_URL)
    public List<Candidate> getCandidateByMrfId(@PathVariable Long mrfId) {
        return mrfCandidateService.getSelectedCandidateByMrfId(mrfId);
    }
}

