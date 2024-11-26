package com.rts.tap.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recruiter_performance")
public class RecruiterPerformance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recruiterPerformanceId;

	@OneToOne
	@JoinColumn(name = "recruiter_id")
	private Employee recruiter;

	@Column(name = "time_to_fill")
	private String timeToFill;

	@ManyToOne
	@JoinColumn(name = "assigned_mrf")
	private MRF mrf;

	@Column(name = "assigned_date")
	private LocalDateTime assignedDate;

	@Column(name = "closed_date")
	private LocalDateTime closedDate;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Column(name = "cost_per_hire")
	private double costPerHire;

	@Column(name = "offer_acceptance")
	private double offerAcceptance;

	@Column(name = "offer_negotiation")
	private double offerNegotiation;

	@Column(name = "collective_score_grade")
	private String collectiveScoreGrade;

}
