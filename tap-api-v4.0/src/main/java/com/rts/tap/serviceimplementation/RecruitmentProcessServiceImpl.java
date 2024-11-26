package com.rts.tap.serviceimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.AssessmentDao;
import com.rts.tap.dao.ClientDao;
import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.InterviewerDao;
import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.RecruitmentProcessDao;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.dto.RecruitmentProcessDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Client;
import com.rts.tap.model.Employee;
import com.rts.tap.model.Interviewer;
import com.rts.tap.model.MRF;
import com.rts.tap.model.RecruitmentProcess;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.service.RecruitmentProcessService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecruitmentProcessServiceImpl implements RecruitmentProcessService {
	RecruitmentProcessDao recruitmentProcessDao;
	MRFDao mrfDao;
	EmployeeDao employeeDao;
	WorkFlowDao workFlowDao;
	InterviewerDao interviewerDao;
	AssessmentDao assessmentDao;
	ClientDao clientDao;

	public RecruitmentProcessServiceImpl(RecruitmentProcessDao recruitmentProcessDao, MRFDao mrfDao,
			EmployeeDao employeeDao, WorkFlowDao workFlowDao, InterviewerDao interviewerDao,
			AssessmentDao assessmentDao, ClientDao clientDao) {
		super();
		this.recruitmentProcessDao = recruitmentProcessDao;
		this.mrfDao = mrfDao;
		this.employeeDao = employeeDao;
		this.workFlowDao = workFlowDao;
		this.interviewerDao = interviewerDao;
		this.assessmentDao = assessmentDao;
		this.clientDao = clientDao;
	}

	@Override
	public void setRecruitmentProcess(RecruitmentProcessDto recruitmentProcessDto) {
		MRF mrf = mrfDao.findById(recruitmentProcessDto.getRecruitmentProcesses().get(0).getMrf().getMrfId());

		for (RecruitmentProcess recruitmentProcess : recruitmentProcessDto.getRecruitmentProcesses()) {
			recruitmentProcess.setMrf(mrf);

			if (recruitmentProcess.getRecruitmentProcessType().equalsIgnoreCase("interview")) {
				List<Interviewer> interviewers = new ArrayList<>();
				for (Interviewer interviewer : recruitmentProcess.getInterviewer()) {
					if (interviewer.getEmployee().getEmployeeId() != null) {
						Employee employee = employeeDao.getEmployeeById(interviewer.getEmployee().getEmployeeId());
						interviewer.setEmployee(employee);
						interviewer.setClient(null);
					}

					else if (interviewer.getClient().getClientId() != null) {
						Client client = clientDao.getClientById(interviewer.getClient().getClientId());
						interviewer.setClient(client);
						interviewer.setEmployee(null);
					}

					interviewerDao.addInterviewer(interviewer);

					interviewer.setInterviewerId(interviewer.getInterviewerId());
					interviewers.add(interviewer);
				}
				recruitmentProcess.setInterviewer(interviewers);
			}

			else if (recruitmentProcess.getRecruitmentProcessType().equalsIgnoreCase("assessment")) {
				recruitmentProcess.setInterviewer(null);
			}

			recruitmentProcessDao.setRecruitmentLevel(recruitmentProcess);
		}

		WorkFlow workFlow = new WorkFlow();
		workFlow.setCount(recruitmentProcessDto.getRecruitmentProcesses().size());
		workFlow.setWorkFlowType(MessageConstants.SET_WORKFLOW_TYPE_RECRUITMENT_PROCESS);
		workFlow.setStatus(MessageConstants.SET_WORKFLOW_STATUS_AS_PENDING);
		workFlow.setMrf(mrf);
		Employee recruitmentManager = employeeDao.getEmployeeById(recruitmentProcessDto.getRecruiterManagerId());
		workFlow.setEmployee(recruitmentManager);

		workFlowDao.addWorkFlow(workFlow);
	}

	@Override
	public void updateRecruitmentProcessLevel(RecruitmentProcess recruitmentProcess) {
		if (recruitmentProcess.getRecruitmentProcessType().equalsIgnoreCase("interview")) {
			List<Interviewer> interviewers = new ArrayList<>();
			for (Interviewer interviewer : recruitmentProcess.getInterviewer()) {
				if (interviewer.getEmployee().getEmployeeId() != null) {
					Employee employee = employeeDao.getEmployeeById(interviewer.getEmployee().getEmployeeId());
					interviewer.setEmployee(employee);
					interviewer.setClient(null);
				}

				else if (interviewer.getClient().getClientId() != null) {
					Client client = clientDao.getClientById(interviewer.getClient().getClientId());
					interviewer.setClient(client);
					interviewer.setEmployee(null);
				}

				if (interviewer.getInterviewerId() == null) {
					interviewerDao.addInterviewer(interviewer);
				}

				interviewer.setInterviewerId(interviewer.getInterviewerId());

				interviewers.add(interviewer);
			}
			recruitmentProcess.setInterviewer(interviewers);
		}

		recruitmentProcessDao.updateRecruitmentProcess(recruitmentProcess);

		WorkFlow workFlow = workFlowDao.findWorkFlowForRecruitmentProcessByMrf(recruitmentProcess.getMrf());
		workFlowDao.updateWorkFlow(workFlow);
	}

	@Override
	public void deleteRecruitmentProcessLevel(Long recruitmentProcessId) {
		RecruitmentProcess recruitmentProcess = recruitmentProcessDao.findById(recruitmentProcessId);

		List<RecruitmentProcess> recruitmentProcesses = recruitmentProcessDao
				.findRecruitmentProcessByMrfId(recruitmentProcess.getMrf().getMrfId());
		for (RecruitmentProcess rp : recruitmentProcesses) {
			if (rp.getLevel() > recruitmentProcess.getLevel()) {
				rp.setLevel(rp.getLevel() - 1);
				recruitmentProcessDao.updateRecruitmentProcess(rp);
			}
		}

		if (recruitmentProcess.getRecruitmentProcessType().equalsIgnoreCase("interview")) {
			for (Interviewer interviewer : recruitmentProcess.getInterviewer()) {
				interviewerDao.deleteInterviewerById(interviewer.getInterviewerId());
			}
		}

		recruitmentProcessDao.deleteRecruitmentProcessById(recruitmentProcessId);

		WorkFlow workFlow = workFlowDao.findWorkFlowForRecruitmentProcessByMrf(recruitmentProcess.getMrf());
		workFlow.setCount(workFlow.getCount() - 1);
		workFlowDao.updateWorkFlow(workFlow);
	}

	@Override
	public List<RecruitmentProcess> getRecruitmentProcessLevels(Long mrfId) {
		return recruitmentProcessDao.findRecruitmentProcessByMrfId(mrfId);
	}

	@Override
	public void insertRecruitmentProcessLevel(RecruitmentProcess recruitmentProcess) {
		MRF mrf = mrfDao.findById(recruitmentProcess.getMrf().getMrfId());
		recruitmentProcess.setMrf(mrf);

		if (recruitmentProcess.getRecruitmentProcessType().equalsIgnoreCase("interview")) {
			List<Interviewer> interviewers = new ArrayList<>();
			for (Interviewer interviewer : recruitmentProcess.getInterviewer()) {
				Employee employee = employeeDao.getEmployeeById(interviewer.getEmployee().getEmployeeId());
				interviewer.setEmployee(employee);

				interviewerDao.addInterviewer(interviewer);

				interviewer.setInterviewerId(interviewer.getInterviewerId());

				interviewers.add(interviewer);
			}

			recruitmentProcess.setInterviewer(interviewers);
		}

		else if (recruitmentProcess.getRecruitmentProcessType().equalsIgnoreCase("assessment")) {
			recruitmentProcess.setInterviewer(null);
		}

		List<RecruitmentProcess> recruitmentProcesses = recruitmentProcessDao
				.findRecruitmentProcessByMrfId(recruitmentProcess.getMrf().getMrfId());
		if (recruitmentProcess.getLevel() <= recruitmentProcesses.size()) {
			for (RecruitmentProcess rp : recruitmentProcesses) {
				if (rp.getLevel() >= recruitmentProcess.getLevel()) {
					rp.setLevel(rp.getLevel() + 1);
					recruitmentProcessDao.updateRecruitmentProcess(rp);
				}
			}
		}
		recruitmentProcessDao.setRecruitmentLevel(recruitmentProcess);

		WorkFlow workFlow = workFlowDao.findWorkFlowForRecruitmentProcessByMrf(mrf);
		workFlow.setCount(workFlow.getCount() + 1);
		workFlowDao.updateWorkFlow(workFlow);
	}
	
	@Override
	public List<Candidate> getCandidateByRpId(Long rpId) {
		return recruitmentProcessDao.findCandidateByRpId(rpId);
	}
}
