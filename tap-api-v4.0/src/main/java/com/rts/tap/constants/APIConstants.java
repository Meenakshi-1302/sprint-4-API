
package com.rts.tap.constants;

public class APIConstants {
	public static final String BASE_URL = "/tap";
	public static final String FRONT_END_URL = "http://localhost:3000";

	// TEAM - A
	public static final String ADD_ADMIN_URL = "/createadmin";
	public static final String GET_ADMIN_URL = "/admin";

	public static final String CROSS_ORIGIN_URL = "http://localhost:3000";

	// ORGANIZATION
	public static final String ADD_ORGANIZATION_URL = "/createorganization";
	public static final String UPDATE_ORGANIZATION_URL = "/updateorganization/{id}";
	public static final String GETALL_ORGANIZATION_URL = "/getallorganization";

	// BUSINESS UNIT
	public static final String ADD_BUSINESSUNIT_URL = "/createbusinessunit";
	public static final String GETALL_BUSINESSUNIT_URL = "/getallbusinessunit";
	public static final String GET_ALL_INTERVIEW_URL = "/getallinterview";
	 

	public static final String GET_BUSINESSUNIT_BY_LOCATION_URL = "/getbusinessunitbylocation/{location}";
	public static final String UPDATE_BUSINESSUNIT_URL = "/updatebusinessunit/{id}";

	// DEPARTMENT
	public static final String ADD_DEPARTMENT_URL = "/createdepartment";
	public static final String UPDATE_DEPARTMENT_URL = "/updatedepartment/{id}";
	public static final String GETALL_DEPARTMENT_URL = "/getalldepartment";
	public static final String GET_DEPARTMENT_BY_ID_URL = "/getdepartment/{id}";

	// ROLE
	public static final String ADD_ROLE_URL = "/createrole";
	public static final String GETALL_ROLE_URL = "/getallrole";
	public static final String UPDATE_ROLE_URL = "/updaterole/{id}";
	public static final String GET_ROLE_BY_ID_URL = "/getrolebyid/{id}";

	// OTP
	public static final String CHECK_LOGIN_CREDENTIALS_URL = "/login";
	public static final String CREATE_LOGIN_URL = "/create";
	public static final String VERIFY_OTP_URL = "/verify-otp";
	public static final String RESEND_OTP_URL = "/resend-otp";
	public static final String UPDATE_PASSWORD_URL = "/updatepassword";
	
	// EMPLOYEE
	public static final String ADD_EMPLOYEE_URL = "/createemployee";
	public static final String ADD_BULK_EMPLOYEE_URL = "/createbulkemployee";
	public static final String GETALL_EMPLOYEE_URL = "/getallemployee";
	public static final String UPDATE_EMPLOYEE_URL = "/updateemployee/{employeeId}";
	public static final String GET_EMPLOYEE_BY_ID = "/getEmployeeById/{employeeId}";
	public static final String GETALL_EMPLOYEE_EMAIL_URL = "/getAllEmployeeEmail";
	public static final String CHECK_EMPLOYEE_STATUS_URL = "/checkEmployeeStatus";
	public static final String UPDATE_EMPLOYEE_STATUS_URL = "/updateEmployeeStatus/{employeeId}";
	public static final String GET_EMPLOYEE_BY_EMAIL = "/checkemailexist/{email}";

	// LOCATION
	public static final String ADD_ORG_LOCATION_URL = "/organizationlocation";
	public static final String GET_ALL_ORG_LOCATIONS_URL = "/getallorganizationlocations";
	public static final String GET_ORG_LOCATION_BY_ID_URL = "/getorganizationlocation/{id}";
	public static final String UPDATE_ORG_LOCATION_URL = "/updateorganizationlocation/{id}";

	// Recruitment Process
	public static final String ADD_RECRUITMENT_PROCESS = "/addRecruitmentProcess";
	public static final String UPDATE_RECRUITMENT_PROCESS_LEVEL = "/updateRecruitmentProcessLevel";
	public static final String DELETE_RECRUITMENT_PROCESS_LEVEL = "/deleteRecruitmentProcessLevel/{recruitmentProcessId}";
	public static final String GET_RECRUITMENT_PROCESS_LEVELS = "/getRecruitmentProcessLevels/{mrfId}";
	public static final String INSERT_RECRUITMENT_PROCESS_LEVEL = "/insertRecruitmentProcessLevel";
	public static final String GET_CANDIDATE_BY_RPID = "/getCandidateByRpId/{rpId}";
	
	public static final String GET_EMPLOYEES = "/getAllEmployee";
	
	public static final String JOB_POSTING_URL="/jobposting";
	 

	// Approval Level
	public static final String ADD_APPROVERLEVEL_URL = "/addApproverLevel";
	public static final String UPDATE_APPROVERLEVEL_URL = "/updateApproverLevel";
	public static final String DELETE_APPROVERLEVEL_URL = "/deleteApproverLevel/{approverLevelId}";
	public static final String GET_APPROVERLEVEL_URL = "/getApproverLevel/{mrfId}";
	public static final String GET_WORKFLOW_URL = "/getWorkflow/{mrfId}";
	public static final String GET_EMPLOYEE_URL = "/getEmployee/{employeeId}";
	public static final String ADD_SINGLE_APPROVERLEVEL_URL = "/addSingleApproverLevel";
	
	// Interview
	public static final String GET_CANDIDATES_BY_RECRUITMENT_PROCESS = "/getCandidatesByRecruitmentProcessId/{recruitmentProcessId}";
	public static final String GET_CANDIDATE_RECRUITMENT_PROCESS_BY_CANDIDATE_ID_MRF_ID = "/getCandidateRecruitmentProcessByCandidateIdAndMrfId";

	public static final String GET_ALL_EMPLOYEE_BY_ROLE = "/getEmployeesByRoles";
	public static final String GET_CANDIDATE_BY_MRFID = "/getCandidateByMrfId/{mrfId}";
	public static final String GET_OFFER_BY_MRFID = "/getOfferByMrfId/{mrfId}";
	public static final String GET_CLIENT_BY_MRFID = "viewClientNameByMrfId/{mrfId}";
	public static final String REMAINING_DAYS_FOR_MRF = "remainingDaysForMrf/{mrfId}";
	public static final String CLIENT_LOGIN = "/client-login";

	public static final String BASE_ASSESSMENT_URL = "tap/recruiter/assessment";
	public static final String SAVE_ASSESSMENT_URL = "/save";
	public static final String GET_ALL_ASSESSMENT_URL = "/list";
	public static final String GET_BY_ID_ASSESSMENT_URL = "/get/{id}";
	public static final String UPDATE_ASSESSMENT_URL = "/update/{id}";
	public static final String DELETE_ASSESSMENT_URL = "/delete/{id}";
	public static final String GET_ASSESSMENT_BY_MRF_ID_URL = "/getassessment/{id}";

	// CANDIDATE
	public static final String BASE_CANDIDATE_URL = "/candidates";
	public static final String SAVE_CANDIDATE_URL = "/post";
	public static final String GET_ALL_CANDIDATE_URL = "/all";
	public static final String GET_BY_ID_CANDIDATE_URL = "/get/{id}";
	public static final String UPDATE_CANDIDATE_URL = "/update/{id}";
	public static final String DELETE_CANDIDATE_URL = "/delete/{id}";
	public static final String LIST_APPLIED_JOBS = "/applied-job";
	public static final String COUNT_APPLIED_JOBS = "/applied-job-count";
	public static final String ASSESSMENT_COUNT_CANDIDATE = "/assessment-count";
	public static final String INTERVIEW_COUNT_CANDIDATE = "/interview-count";
	public static final String INTERVIEW_LEVELS_CANDIDATE = "/interview-levels";
	public static final String TYPE_OF_INTERVIEW = "/interview-types";

	public static final String BASE_MRFCANDIDATE_URL = "/mrfCandidates";
	public static final String SAVE_MRFCANDIDATE_URL = "/save";
	public static final String GET_ALL_MRFCANDIDATE_URL = "/list";
	public static final String GET_BY_ID_MRFCANDIDATE_URL = "/get/{id}";
	public static final String UPDATE_MRFCANDIDATE_URL = "/update/{id}";
	public static final String DELETE_MRFCANDIDATE_URL = "/delete/{id}";
	public static final String GET_REMAINING_MRFCANDIDATE_URL = "/remainingcandidate/{id}";

	public static final String GET_SELECTED_MRFCANDIDATE_URL = "/selectedcandidate/{mrfId}";

	public static final String BASE_SCORE_URL = "/scores";
	public static final String SAVE_SCORE_URL = "/post";
	public static final String GET_ALL_SCORE_URL = "/all";
	public static final String GET_BY_ID_SCORE_URL = "/get/{id}";
	public static final String UPDATE_SCORE_URL = "/update/{id}";
	public static final String DELETE_SCORE_URL = "/delete/{id}";
	public static final String GET_ASSESSED_CANDIDATE_URL = "/getcandidates/{id}";
	public static final String GET_SCORE_BY_MRFID_AND_CANDIDATEID = "/getScoreByMrfIdAndCandidateId";

	public static final String BASE_SCHEDULE_EMAIL_URL = "/schedule";
	public static final String SAVE_SCHEDULE_EMAIL_URL = "/post";

	public static final String ADD_MRF = "mrf/addMrf";
	public static final String UPDATE_MRF = "mrf/updateMrf/{mrfId}";
	public static final String DELETE_MRF = "mrf/deleteMrf/{mrfId}";
	public static final String GET_MRF = "mrf/getMrf/{mrfId}";
	public static final String GET_ALL_MRF = "mrf/getAllMrf";
	public static final String GET_ALL_SUBREQUIREMENTID_MAPPED_WITH_MRF = "mrf/getAllMrfSubRequirementId";
	public static final String GET_RESOURCE_FILLED_COUNT = "mrf/requirement-filled-count/{requirementId}";

	public static final String CLIENTS_PATH = BASE_URL + "/clients";

	public static final String GET_CLIENTBYID = "/{id}";

	public static final String UPDATE_CLIENT_APPROVED = "/UpdateCLientApprove/{id}";
	
	public static final String ADD_JOB_DESCRIPTION = "/jobDescription/addJobDescription";
	public static final String EDIT_JOB_DESCRIPTION = "/jobDescription/editJobDescription/{id}";
	public static final String GET_ALL_JOB_DESCRIPTIONS = "/jobDescription/viewAllJobDescriptions";
	public static final String GET_JOB_DESCRIPTION_BY_ID = "/jobDescription/getJobDescriptionById/{id}";
	public static final String GET_JOB_DESCRIPTION_BY_JOBTITLE = "/jobDescription/getJobDescriptionByJobTitle/{jobTitle}";
	public static final String GET_ALL_JOB_TITLES = "/jobDescription/viewAllJobTitles";
	public static final String ADD_JOB_DESCRIPTION_MRFJD = "/jobDescription/addJobDescriptionAssignToMrf";
	
	public static final String ASSIGN_MRF_TO_RECRUITING_MANAGER = "mrf/assignToRecruitingManager";
	public static final String GET_ALL_RECRUITING_MANAGER = "mrf/listOfRecruitingManager";
	public static final String GET_ALL_ASSIGNED_MRF_TO_RECRUITING_MANAGER = "mrf/assigned";
	public static final String GET_OFFER_ASSIGNED_BY_RECRUITING_MANAGER = "offerApproval/employee/{employeeId}";
	
	public static final String FRONTEND_URL = "http://localhost:3000";

	// client
	public static final String GET_TOTAL_MRF_ASSIGNED_TO_RECRUITER = "/totalmrfassigned/{mrfRecruitersId}";

	// client
	public static final String GET_CLIENT_URL = "/client";

	public static final String CLIENT_ID_BY_EMAIL = "/client-email";

	public static final String GET_CLIENT_BY_ID = "/client-profile-by-id/{clientId}";
	public static final String GET_CLIENT_BY_EMAIL = "/client-profile-by-email/{clientEmail}";
	public static final String UPDATE_CLIENT_PROFILE_BY_ID = "/client-profile-update-by-id/{clientId}";
	public static final String UPDATE_CLIENT_PROFILE_BY_EMAIL = "/client-profile-update-by-email/{clientEmail}";
	public static final String UPDATE_CLIENT_ORGANIZATION_LOGO_BY_ID = "/client-logo-update-by-id/{clientId}/logo";

	public static final String CLIENT_RESETPASSWORD_URL = "/client-reset-password";
	public static final String CLIENT_EMAIL_CHECK_URL = "/email-exists/{clientEmail}";

	public static final String CLIENT_FORGOT_PASSWORD_SEND_OTP = "/forgot-password-send-otp/{clientEmail}";
	public static final String CLIENT_FORGOT_PASSWORD_VERIFY_OTP = "/verify-otp";
	public static final String CLIENT_FORGOT_PASSWORD_UPDATE = "/update-forgot-password";

	public static final String CLIENT_ADD = "/addclient";
	public static final String CLIENT_RESET_PASSWORD_UPDATE = "/resetpwd";

	public static final String CLIENT_SEND_OTP = "/forgot-password-send-otp/{clientEmail}";

	public static final String REQUIREMENT_REQUESTMAPPING_API = BASE_URL + "/api";
	public static final String REQUIREMENT_ADD_API = "/requirement";
	public static final String REQUIREMENT_DELETE_API = "/delete/{requirementId}";
	public static final String REQUIREMENT_UPDATE_API = "/update";
	public static final String REQUIREMENT_GETALL_REQUIREMENT_API = "/allRequirements";
	public static final String REQUIREMENT_REQUIREMENTBY_CLIENT_API = "/requirementByClientId/{clientId}";
	public static final String REQUIREMENT_COUNT_CLIENT_API = "/requirementCount/{clientId}";
	public static final String REQUIREMENT_LIST_BY_CLIENT_API = "/requirement-by-client/{clientId}";
	public static final String REQUIREMENT_GET_REQUIREMENTBYId_API = "/requirement/{requirementId}";

	public static final String CLIENT_REQUESTMAPPING_API = BASE_URL + "/api";
	public static final String GET_CLIENT_CANDIDATE_HIRED = "/hiredCount/{clientId}";
	public static final String GET_CLIENT_CANDIDATE_SHORTLISTED = "/shortListedCount/{clientId}";
	public static final String GET_CLIENT_HIRED = "/hired/{requirementId}";
	public static final String GET_CLIENT_SHORTLISTED = "/shortlisted/{requirementId}";
	public static final String GET_CANDIDATE_HIRED = "/hiredCandidate/{clientId}";

	public static final String DUMMY = "/dummy/{clientId}";
	public static final String REQUESTMAPPING_SUB_REQUIREMENTS = BASE_URL + "/api";
	public static final String ADD_SUB_REQUIREMENTS = "/add-sub-requirements";
	public static final String VIEW_SUB_REQUIREMENTS = "/list-sub-requirements";

	// Recruiting Manager
	public static final String RECRUITING_MANAGER_URL = "/api/recruitingManager";
	public static final String RECRUITING_MANAGER_ASSIGN_MRF_RECRUITER = "/assignMrfs/recruiter";
	public static final String RECRUITING_MANAGER_GET_ALL_MRF = "/allMrfs/{id}";
	public static final String RECRUITING_MANAGER_GET_ALL_MRFVENDOR = "/fetch/allMrfVendors";
	public static final String RECRUITING_MANAGER_ASSIGN_MRF_VENDOR = "/assignMrfs/vendor";

	public static final String RECRUITING_MANAGER_REASSIGN_MRF_RECRUITER = "/reAssignMrfs/recruiter";
	public static final String RECRUITING_MANAGER_GET_ALL_ASSIGNED_RECRUITERS = "/fetch/allrecruiters";
	public static final String RECRUITING_MANAGER_UPDATE_MRF_RECRUITER = "/update/{id}";
	public static final String RECRUITING_MANAGER_GET_ALL_RECRUITERS = "/{id}";
	public static final String RECRUITING_MANAGER_GET_ALL_RECRUITERS_BY_MRF = "/fetchByMrfRmId/{id}";
	public static final String RECRUITING_MANAGER_UPDATE_STAGE = "/updateStage/{mrfId}";
	public static final String RECRUITING_MANAGER_FETCH_VENDORS_BY_MRF = "/fetchVendorsByMrfId/{id}";

	public static final String RECRUITING_MANAGER_GET_WORKFLOW_BY_EMPLOYEEID = "/getWorkflowbyEmployeeId/{employeeId}";
	public static final String RECRUITING_MANAGER_UPDATE_WORKFLOW = "/updateWorkFlow/{workflowId}";
	public static final String GET_WORKFLOW_FOR_RECRUITMENT_PROCESS = "/getWorkflowByMrfIdForRecruitmentProcess/{mrfId}";

	public static final String UPDATE_WORKFLOW_STATUS_AS_PENDING_FOR_RECRUITMENT_PROCESS = "/updateWorkFlowAsPendingForRecruitmentProcess/{mrfId}";
	public static final String UPDATE_WORKFLOW_STATUS_AS_PENDING_FOR_APPROVAL_PROCESS = "/updateWorkFlowAsPendingForApprovalProcess/{mrfId}";
	
	public static final String GET_OFFER_APPROVAL_BY_RECRUITINGMANAGERID = "/getAllOffersByRMId/{recruitingManagerId}";
	

	// vendor
	public static final String VENDOR_URL = "/api/vendors";
	public static final String VENDOR_UPDATE = "/{id}";
	public static final String VENDOR_DELETE = "/{id}";
	public static final String VENDOR_GET_BY_ID = "/{id}";
	public static final String VENDOR_GET_ALL = "/allVendor";

	public static final String GET_CANDIDATE_SHORTLISTED = "/short-listed/{clientId}";
	public static final String GET_CANDIDATE_SHORTLISTED_REQUIREMENT = "/short-listed-requirement/{requirementId}";
	public static final String GET_CANDIDATE_HIRED_REQUIREMENT = "/hired-requirement/{requirementId}";

	public static final String LOGIN_CREDENTIAL_GET_BY_USERID = "/login-credentials/{userId}";

	// recruiter dashboard
	public static final String RECRUITER_DASHBOARD = BASE_URL + "/api";
	public static final String GET_ALL_MRFLIST_ASSIGNED_TO_RECRUITER = "/mrfassignedtorecruiter/{employeeId}";
	public static final String RESOLVED_MRF_OF_RECRUITER = "/resolvedmrf/{employeeId}";
	public static final String PENDING_MRF_OF_RECRUITER = "/pendingmrf/{employeeId}";
	public static final String GET_TOTAL_CANDIDATES_ASSIGNED_TO_RECRUITER = "/totalrecruitercandidates/{employeeId}";
	public static final String GET_HIRED_CANDIDATES_ASSIGNED_TO_RECRUITER = "/hiredrecruitercandidates/{employeeId}";
	public static final String GET_PENDING_CANDIDATES_ASSIGNED_TO_RECRUITER = "/pendingrecruitercandidates/{employeeId}";
	public static final String GET_REJECTED_CANDIDATES_ASSIGNED_TO_RECRUITER = "/rejectedrecruitercandidates/{employeeId}";
	public static final String GET_CANDIDATES_BY_EMPLOYEEID = "/recruitercandidates/{employeeId}";
	public static final String GET_OFFER_BY_EMPLOYEEID = "/recruiteroffer/{employeeId}";
	public static final String GET_RESOLVED_MRF_COUNT_BY_MONTH = "/resolvedMRFcount/{employeeId}/{year}";
	public static final String GET_RESOLVED_MRF_CANDIDATE_COUNT_BY_MONTH = "/resolvedCandidateCount/{employeeId}/{year}";
	public static final String GET_PENDING_MRF_COUNT_BY_MONTH = "/pendingMRFcount/{employeeId}/{year}";
	public static final String GENERATE_OFFERLETTER_FOR_CANDIDATE = "/generateOfferLetter";
	public static final String GET_OFFERLETTER_BY_CANDIDATE_ID = "/getOfferLetterByCandidateId/{candidateId}";
	public static final String UPDATE_OFFER_LETTER = "/updateOfferLetter/{offerId}";

	// OfferApproval
	public static final String UPDATE_OFFER_APPROVAL_STATUS = "/updateOfferApprovalStatus";
	public static final String GET_OFFER_APPROVAL_BY_CANDIDATE_ID_AND_MRF_ID = "/getOfferApprovalByCandidateIdAndMrfId";

	public static final String UPDATE_VENDOR_ORGANIZATION_LOGO_BY_ID = "/vendor-logo-update-by-id/{vendorId}/logo";
	// team D vendor
	public static final String VENDOR_ADD_CANDIDATE = "/add-candidate-by-vendor";

	// vendor Team-D - Nagarjun N S
	public static final String VENDOR_GET_COUNT_ASSIGNED_MRF = "/assignedMrfCount/{vendorId}";
	public static final String VENDOR_GET_COUNT_COMPLETED_MRF = "/completedMrfCount/{vendorId}";
	public static final String VENDOR_GET_ALL_MRF_COUNT = "/allMrfCount/{vendorId}";
	public static final String VENDOR_GET_ALL_MRFVENDORDETAILS_BY_VENDORID = "/allMrf/{vendorId}";// rectified
	public static final String VENDOR_GET_ALL_MRF_DETAILS_BY_VENDOR_AND_MRF_ID = "/{vendorId}/mrf/{mrfId}";// rec
	public static final String VENDOR_GET_CANDIDATE_DETAILS_BY_VENDOR_AND_MRF_ID = "/candidateDetail";// rec
	public static final String VENDOR_GET_CANDIDATE_COUNT_BY_VENDOR_AND_MRF_ID = "/candidateCountByVendorandMrfId";// rec
	public static final String VENDOR_GET_CANDIDATE_COUNT_BY_VENDOR_ID = "/candidateCountByVendorId";// rec
	public static final String VENDOR_GET_HIRED_AND_JOINED_CANDIDATE_DETAILS_BY_VENDOR_ID = "/hiredJoinedcandidatebyVendorId";// rec
	public static final String VENDOR_GET_HIRED_CANDIDATE_COUNT_BY_VENDOR_ID = "/hiredcandidatecountbyVendorId";// rec
	public static final String VENDOR_GET_JOINED_CANDIDATE_COUNT_BY_VENDOR_ID = "/joinedcandidatecountbyVendorId";// rec
	public static final String VENDOR_GET_ALL_CANDIDATE_BY_MRF_AND_VENDOR_ID = "/getallcandidatebyMrfAndVendorId";// rec
	public static final String VENDOR_GET_ALL_MRF_ASSIGNED_FOR_VENDOR = "/getallmrf/{vendorId}";// rec

	public static final String UPDATE_VENDOR_PROFILE_BY_ID_BY_VENDOR = "/Vendor-profile-update-by-id";

	public static final String VENDOR_GET_ALL_MRF_DETAILS_ASSIGNED_FOR_VENDOR = "/getallmrfdetails/{vendorId}";// rec
	public static final String VENDOR_RESET_PASSWORD_BY_EMAIL = "/vendorResetPassword";

	public static final String VENDOR_GET_REMAINING_DAYS = "/getremainingdays/{vendorId}";

	public static final String VENDOR_ID_BY_EMAIL = "/email";

	public static final String VENDOR_GET_ALL_ASSIGNEDMRF_BY_VENDORID = "/allassignedMrf/{vendorId}";
	public static final String UPDATE_MRF_STATUS_BY_VENDOR_ID = "/updatemrfstatus";

	public static final String GET_ALL_CANDIDATES_LIST_BY_VENDOR_ID = "/getAllCandidatesByVendorId";
	
	public static final String UPDATE_CANDIDATE_PROFILE = "/profileupdate/{candidateId}";
	public static final String UPDATE_OFFER_STATUS = "/acceptOffer";
	
	public static final String PASSWORD_RECOVERY = "user/password/recovery";
	public static final String FORGET_PASSWORD_EMAIL_EXISTS = "/emailexists";
	public static final String FORGET_PASSWORD_SEND_OTP = "/send-otp";
	public static final String FORGET_PASSWORD_VERIFY_OTP = "/forgot-password-verify-otp";
	public static final String FORGET_PASSWORD_UPDATE = "/forgot-password-update";
	public static final String THIRD_PARTY_RESET_PASSWORD = "/thirdParty-reset-password";
}
