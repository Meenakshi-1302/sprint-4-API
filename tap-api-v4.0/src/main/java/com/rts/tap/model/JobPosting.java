package com.rts.tap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobPostingId;

	private String jobDescription;

	private String jobTitle;

	@OneToOne
	private BasicJobTable basicJobTable;

	@OneToOne
	private MRF mrf;

	@Column(name = "JobPoster", nullable = true, length = 100000000)
	private byte[] poster;

	private List<String> jobType;

	private List<String> Shift;

	private String url;

	public Long getJobPostingId() {
		return jobPostingId;
	}

	public void setJobPostingId(Long jobPostingId) {
		this.jobPostingId = jobPostingId;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BasicJobTable getBasicJobTable() {
		return basicJobTable;
	}

	public void setBasicJobTable(BasicJobTable basicJobTable) {
		this.basicJobTable = basicJobTable;
	}

	public MRF getMrf() {
		return mrf;
	}

	public void setMrf(MRF mrf) {
		this.mrf = mrf;
	}

	public byte[] getPoster() {
		return poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}

	public List<String> getJobType() {
		return jobType;
	}

	public void setJobType(List<String> jobType) {
		this.jobType = jobType;
	}

	public List<String> getShift() {
		return Shift;
	}

	public void setShift(List<String> shift) {
		Shift = shift;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public JobPosting(Long jobPostingId, String jobDescription, String jobTitle, BasicJobTable basicJobTable, MRF mrf,
			byte[] poster, List<String> jobType, List<String> shift, String url) {
		super();
		this.jobPostingId = jobPostingId;
		this.jobDescription = jobDescription;
		this.jobTitle = jobTitle;
		this.basicJobTable = basicJobTable;
		this.mrf = mrf;
		this.poster = poster;
		this.jobType = jobType;
		Shift = shift;
		this.url = url;
	}

	public JobPosting() {
		super();
		// TODO Auto-generated constructor stub
	}

}
