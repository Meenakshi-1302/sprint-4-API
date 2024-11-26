package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.WorkFlow;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class WorkFlowDaoImpl implements WorkFlowDao {

	EntityManager eManager;

	public WorkFlowDaoImpl(EntityManager eManager) {
		super();
		this.eManager = eManager;
	}

	@Override
	public void addWorkFlow(WorkFlow workFlow) {
		eManager.persist(workFlow);
	}

	@Override
	public WorkFlow findWorkFlowByMrf(MRF mrf) {
		String hql = "Select w from WorkFlow w where w.mrf = :mrf";
		return (WorkFlow) eManager.createQuery(hql).setParameter("mrf", mrf).getSingleResult();
	}

	@Override
	public void updateWorkFlow(WorkFlow workFlow) {
		eManager.merge(workFlow);
	}

	@Override
	public void deleteWorkFlowById(Long workflowId) {
		WorkFlow workFlow = eManager.find(WorkFlow.class, workflowId);
		eManager.remove(workFlow);
	}

	@Override
	public WorkFlow findWorkFlowForRecruitmentProcessByMrf(MRF mrf) {
		String hql = "Select w from WorkFlow w where w.mrf = :mrf and workFlowType = :type";
		return (WorkFlow) eManager.createQuery(hql).setParameter("mrf", mrf)
				.setParameter("type", MessageConstants.SET_WORKFLOW_TYPE_RECRUITMENT_PROCESS).getSingleResult();
	}

	@Override
	public WorkFlow getWorkFlowApproverLevelByMrfId(Long mrfId) {
		String hql = "Select wf from WorkFlow wf where wf.mrf.mrfId = :mrfId and workFlowType = :type";
		return (WorkFlow) eManager.createQuery(hql).setParameter("mrfId", mrfId)
				.setParameter("type", MessageConstants.SET_WORKFLOW_TYPE_APPROVER_LEVEL).getSingleResult();
	}

	@Override
	public Employee getEmployeeByEmployeeId(Long employeeId) {
		String hql = "Select emp from Employee emp where emp.employeeId = :employeeId";
		return (Employee) eManager.createQuery(hql).setParameter("employeeId", employeeId).getSingleResult();
	}

	@Override
	public List<WorkFlow> findWorkFlowForRecruitmentProcessByEmployee(Employee employee) {
		String hql = "Select e from WorkFlow e where e.employee = :employee and e.workFlowType = :type";
		return eManager.createQuery(hql, WorkFlow.class).setParameter("employee", employee)
				.setParameter("type", MessageConstants.SET_WORKFLOW_TYPE_RECRUITMENT_PROCESS).getResultList();
	}

	@Override
	public WorkFlow getWorkFlowById(Long workFlowId) {
		return eManager.find(WorkFlow.class, workFlowId);
	}
}
