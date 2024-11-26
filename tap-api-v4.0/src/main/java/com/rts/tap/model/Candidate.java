//package com.rts.tap.model;
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.Table;
//
//@Entity
//@Table
//public class Candidate {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long candidateId;
//
//	@Column(nullable = true)
//	private String firstName;
//
//	@Column(nullable = true)
//	private String lastName;
//
//	@Column(nullable = true)
//	private String mobileNumber;
//
//	@Column(nullable = true)
//	private String email;
//
//	@Column(nullable = true)
//	private int experience;
//
//	@Column(nullable = true)
//	private String resume;
//
//	@Column(nullable = true)
//	private String source;
//
//	@Column(nullable = true)
//	private long sourceId;
//
//	@Column(nullable = true)
//	private String skill;
//
//	@Column(nullable = true)
//	private String location;
//
//	@Column(nullable = true)
//	private String panNumber;
//
//	@Column(nullable = true)
//	private String status;
//
//	@Column(nullable = true)
//	private String password;
//
//	@Column(nullable = true)
//	private Boolean isPasswordChanged;
//
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private CandiateDocument documents;
//
//	@Lob
//	@Column(length = 1000000000, nullable = true)
//	private byte[] candidateResume;
//
//	@Column(nullable = true)
//	private LocalDateTime assignedAt;
//
//	@PrePersist
//	protected void onCreate() {
//		assignedAt = LocalDateTime.now();
//	}
//
//	public Candidate() {
//		super();
//	}
//
//	public static CandidateBuilder builder() {
//		return new CandidateBuilder();
//	}
//
//	public static class CandidateBuilder {
//		private Long candidateId;
//		private String firstName;
//		private String lastName;
//		private String mobileNumber;
//		private String email;
//		private int experience;
//		private String resume;
//		private String source;
//		private long sourceId;
//		private String skill;
//		private String location;
//		private String panNumber;
//		private String status;
//		private String password;
//		private Boolean isPasswordChanged;
//		private CandiateDocument documents;
//		private byte[] candidateResume;
//		private LocalDateTime assignedAt;
//
//		public CandidateBuilder candidateId(Long candidateId) {
//			this.candidateId = candidateId;
//			return this;
//		}
//
//		public CandidateBuilder firstName(String firstName) {
//			this.firstName = firstName;
//			return this;
//		}
//
//		public CandidateBuilder lastName(String lastName) {
//			this.lastName = lastName;
//			return this;
//		}
//
//		public CandidateBuilder mobileNumber(String mobileNumber) {
//			this.mobileNumber = mobileNumber;
//			return this;
//		}
//
//		public CandidateBuilder email(String email) {
//			this.email = email;
//			return this;
//		}
//
//		public CandidateBuilder experience(int experience) {
//			this.experience = experience;
//			return this;
//		}
//
//		public CandidateBuilder resume(String resume) {
//			this.resume = resume;
//			return this;
//		}
//
//		public CandidateBuilder source(String source) {
//			this.source = source;
//			return this;
//		}
//
//		public CandidateBuilder sourceId(long sourceId) {
//			this.sourceId = sourceId;
//			return this;
//		}
//
//		public CandidateBuilder skill(String skill) {
//			this.skill = skill;
//			return this;
//		}
//
//		public CandidateBuilder location(String location) {
//			this.location = location;
//			return this;
//		}
//
//		public CandidateBuilder panNumber(String panNumber) {
//			this.panNumber = panNumber;
//			return this;
//		}
//
//		public CandidateBuilder status(String status) {
//			this.status = status;
//			return this;
//		}
//
//		public CandidateBuilder password(String password) {
//			this.password = password;
//			return this;
//		}
//
//		public CandidateBuilder isPasswordChanged(Boolean isPasswordChanged) {
//			this.isPasswordChanged = isPasswordChanged;
//			return this;
//		}
//
//		public CandidateBuilder documents(CandiateDocument documents) {
//			this.documents = documents;
//			return this;
//		}
//
//		public CandidateBuilder candidateResume(byte[] candidateResume) {
//			this.candidateResume = candidateResume;
//			return this;
//		}
//
//		public Candidate build() {
//			Candidate candidate = new Candidate();
//			candidate.setCandidateId(this.candidateId);
//			candidate.setFirstName(this.firstName);
//			candidate.setLastName(this.lastName);
//			candidate.setMobileNumber(this.mobileNumber);
//			candidate.setEmail(this.email);
//			candidate.setExperience(this.experience);
//			candidate.setResume(this.resume);
//			candidate.setSource(this.source);
//			candidate.setSourceId(this.sourceId);
//			candidate.setSkill(this.skill);
//			candidate.setLocation(this.location);
//			candidate.setPanNumber(this.panNumber);
//			candidate.setStatus(this.status);
//			candidate.setPassword(this.password);
//			candidate.setIsPasswordChanged(this.isPasswordChanged);
//			candidate.setDocuments(this.documents);
//			candidate.setCandidateResume(this.candidateResume);
//			return candidate;
//		}
//	}
//
//	public Long getCandidateId() {
//		return candidateId;
//	}
//
//	public void setCandidateId(Long candidateId) {
//		this.candidateId = candidateId;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getMobileNumber() {
//		return mobileNumber;
//	}
//
//	public void setMobileNumber(String mobileNumber) {
//		this.mobileNumber = mobileNumber;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public int getExperience() {
//		return experience;
//	}
//
//	public void setExperience(int experience) {
//		this.experience = experience;
//	}
//
//	public String getResume() {
//		return resume;
//	}
//
//	public void setResume(String resume) {
//		this.resume = resume;
//	}
//
//	public String getSource() {
//		return source;
//	}
//
//	public void setSource(String source) {
//		this.source = source;
//	}
//
//	public long getSourceId() {
//		return sourceId;
//	}
//
//	public void setSourceId(long sourceId) {
//		this.sourceId = sourceId;
//	}
//
//	public String getSkill() {
//		return skill;
//	}
//
//	public void setSkill(String skill) {
//		this.skill = skill;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	public String getPanNumber() {
//		return panNumber;
//	}
//
//	public void setPanNumber(String panNumber) {
//		this.panNumber = panNumber;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public Boolean getIsPasswordChanged() {
//		return isPasswordChanged;
//	}
//
//	public void setIsPasswordChanged(Boolean isPasswordChanged) {
//		this.isPasswordChanged = isPasswordChanged;
//	}
//
//	public CandiateDocument getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(CandiateDocument documents) {
//		this.documents = documents;
//	}
//
//	public byte[] getCandidateResume() {
//		return candidateResume;
//	}
//
//	public void setCandidateResume(byte[] candidateResume) {
//		this.candidateResume = candidateResume;
//	}
//
//	public LocalDateTime getAssignedAt() {
//		return assignedAt;
//	}
//
//	public void setAssignedAt(LocalDateTime assignedAt) {
//		this.assignedAt = assignedAt;
//	}
//
//}


package com.rts.tap.model;
 
import java.time.LocalDateTime;
import java.util.List;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
 
@Entity
@Table
public class Candidate {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long candidateId;
 
	@Column(nullable = true)
	private String firstName;
 
	@Column(nullable = true)
	private String lastName;
 
	@Column(nullable = true)
	private String mobileNumber;
 
	@Column(nullable = true)
	private String email;
 
	@Column(nullable = true)
	private int experience;
 
	@Column(nullable = true)
	private String resume;
 
	@Column(nullable = true)
	private String source;
 
	@Column(nullable = true)
	private long sourceId;
 
	@Column(nullable = true)
	private String skill;
 
	@Column(nullable = true)
	private String location;
 
	@Column(nullable = true)
	private String panNumber;
 
	@Column(nullable = true)
	private String status;
 
	@Column(nullable = true)
	private String password;
 
	@Column(nullable = true)
	private Boolean isPasswordChanged;
 
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private CandiateDocument documents;
 
	@Lob
	@Column(length = 1000000000, nullable = true)
	private byte[] candidateResume;
 
	@Column(nullable = true)
	private LocalDateTime assignedAt;
 
	@Lob
	@Column(length = 1000000000, nullable = true)
	private byte[] candidateProfileImage;
 
	@PrePersist
	protected void onCreate() {
		assignedAt = LocalDateTime.now();
	}
 
	public Candidate() {
		super();
	}
 
	public Long getCandidateId() {
		return candidateId;
	}
 
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
 
	public String getFirstName() {
		return firstName;
	}
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
 
	public String getLastName() {
		return lastName;
	}
 
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
	public String getMobileNumber() {
		return mobileNumber;
	}
 
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public int getExperience() {
		return experience;
	}
 
	public void setExperience(int experience) {
		this.experience = experience;
	}
 
	public String getResume() {
		return resume;
	}
 
	public void setResume(String resume) {
		this.resume = resume;
	}
 
	public String getSource() {
		return source;
	}
 
	public void setSource(String source) {
		this.source = source;
	}
 
	public long getSourceId() {
		return sourceId;
	}
 
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
 
	public String getSkill() {
		return skill;
	}
 
	public void setSkill(String skill) {
		this.skill = skill;
	}
 
	public String getLocation() {
		return location;
	}
 
	public void setLocation(String location) {
		this.location = location;
	}
 
	public String getPanNumber() {
		return panNumber;
	}
 
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public Boolean getIsPasswordChanged() {
		return isPasswordChanged;
	}
 
	public void setIsPasswordChanged(Boolean isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}
 
	public CandiateDocument getDocuments() {
		return documents;
	}
 
	public void setDocuments(CandiateDocument documents) {
		this.documents = documents;
	}
 
	public byte[] getCandidateResume() {
		return candidateResume;
	}
 
	public void setCandidateResume(byte[] candidateResume) {
		this.candidateResume = candidateResume;
	}
 
	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}
 
	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}
 
	public byte[] getCandidateProfileImage() {
		return candidateProfileImage;
	}
 
	public void setCandidateProfileImage(byte[] candidateProfileImage) {
		this.candidateProfileImage = candidateProfileImage;
	}
 
}