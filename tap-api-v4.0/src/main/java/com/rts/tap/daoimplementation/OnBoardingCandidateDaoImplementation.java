package com.rts.tap.daoimplementation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.OnBoardingCandidateDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.model.Offer;
import com.rts.tap.model.VendorPerformance;
import com.rts.tap.model.VendorPerformanceHistory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OnBoardingCandidateDaoImplementation implements OnBoardingCandidateDao {

	EntityManager entityManager;

	public OnBoardingCandidateDaoImplementation(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public String changeCandidateStatus(long candidateId) {
		String getOfferHql = "SELECT offer FROM Offer offer WHERE offer.candidate.candidateId = :id";
		Query query = entityManager.createQuery(getOfferHql).setParameter("id", candidateId);
		Offer offer = (Offer) query.getSingleResult();
		Candidate candidate = offer.getCandidate();
		if (candidate.getStatus().equals("JOINED")) {
			return "Candidate Already Joined";
		}
		candidate.setStatus("JOINED");

		// update candidate status as 'JOINED'
		entityManager.merge(candidate);

		MRF mrf = offer.getMrf();
		if (candidate.getSource().equals("VENDOR")) {

			// get MrfVendor to get vendor
			String getMRFVendor = "SELECT mrfvendor FROM MRFVendor mrfvendor WHERE mrfvendor.vendor.vendorId = :vendorId ";
			query = entityManager.createQuery(getMRFVendor).setParameter("vendorId", candidate.getSourceId());
			MRFVendor mrfVendor = (MRFVendor) query.getSingleResult();
			int count = mrfVendor.getAchievedCount() + 1;
			mrfVendor.setAchievedCount(count);

			// update MRF vendor achieved count
			entityManager.merge(mrfVendor);

			// get Vendor Performance History -> VPH
			String getVendorPerformanceHistory = "SELECT vph FROM VendorPerformanceHistory vph WHERE vph.vendor.vendorId = :vendorId AND vph.mrf.mrfId = :mrfId";
			query = entityManager.createQuery(getVendorPerformanceHistory)
					.setParameter("vendorId", candidate.getSourceId()).setParameter("mrfId", mrf.getMrfId());
			VendorPerformanceHistory vendorPerformanceHistory = (VendorPerformanceHistory) query.getSingleResult();
			vendorPerformanceHistory.setUpdatedDate(LocalDateTime.now());
			double offerAcceptance = calculateOfferAcceptanceRateVendor(mrfVendor);
			double time2fill = calculateTimeToFillVendor(vendorPerformanceHistory, mrf);
			vendorPerformanceHistory.setOfferAcceptanceRate(offerAcceptance);
			vendorPerformanceHistory.setTimeToFill(time2fill);
			double collectiveScore = calculateCollectiveScoreVendor(time2fill, offerAcceptance);
			vendorPerformanceHistory.setCollectiveScore(collectiveScore);
			vendorPerformanceHistory.setGrade(calculateGrade((int) collectiveScore));
			if (mrfVendor.getAssignedCount() == mrfVendor.getAchievedCount()) {
				vendorPerformanceHistory.setClosedDate(LocalDateTime.now());
			}
			entityManager.merge(vendorPerformanceHistory);
			
			// get vendor Overall performance 
			String getVendorPerformance = "SELECT vp FROM VendorPerformance vp WHERE vp.vendor.vendorId = :vendorId";
			query = entityManager.createQuery(getVendorPerformance).setParameter("vendorId", candidate.getSourceId());
			VendorPerformance vendorPerformance = (VendorPerformance)query.getSingleResult();
			double overallScore =  getAllPerformanceHistory(candidate.getSourceId());
			vendorPerformance.setOverallPerformance(overallScore);
			vendorPerformance.setGrade(calculateGrade((int)overallScore));
			entityManager.persist(vendorPerformance);			
		}
		return "Candidate Joined Sucessfully";
	}

	// method to calculate offer Acceptance rate for vendor
	double calculateOfferAcceptanceRateVendor(MRFVendor mrfVendor) {
		double result = mrfVendor.getAchievedCount() / mrfVendor.getAssignedCount();
		if (result > 1.0) {
			return 100.0;
		} else {
			result = result * 100;
			return result;
		}
	}

	// method to find time to fill for vendor
	double calculateTimeToFillVendor(VendorPerformanceHistory vendorPerformanceHistory, MRF mrf) {
		System.out.println("time to fill calculate started here");
		long vendorCompletion = 0, mrfClosure = 1;
		if (vendorPerformanceHistory.getAssignedDate() == null || vendorPerformanceHistory.getUpdatedDate() == null) {
			vendorCompletion = 0; // Or handle null values as needed
		} else {
			Duration duration = Duration.between(vendorPerformanceHistory.getUpdatedDate(),
					vendorPerformanceHistory.getAssignedDate());
			vendorCompletion = duration.toDays();
		}
		if (mrf.getMrfCriteria().getContractStartDate() == null || mrf.getMrfCriteria().getClosureDate() == null) {
			mrfClosure = 0;
		} else {
			long diffInMillies = Math.abs(mrf.getMrfCriteria().getContractStartDate().getTime()
					- mrf.getMrfCriteria().getClosureDate().getTime());
			long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			mrfClosure = diffInDays;
		}
		return (100 - ((vendorCompletion / mrfClosure) * 100));
	}

	// method to calculate collective score vendor performance history
	double calculateCollectiveScoreVendor(double time2fill, double offerAcceptance) {
		double result = (time2fill + offerAcceptance) / 2;
		return result;
	}

	// convert collective score into Grades 'S' or 'A' or 'B'
	String calculateGrade(int collectiveScore) {
		String grade;
		switch (collectiveScore / 10) {
		case 10: // for scores in range 100-90
		case 9:
			grade = "S"; // Grade S for scores 90-100
			break;
		case 8: // for scores in range 89-80
			grade = "A"; // Grade A for scores 80-89
			break;
		case 7: // for scores in range 79-70
			grade = "B"; // Grade B for scores 70-79
			break;
		case 6: // for scores in range 69-60
			grade = "C"; // Grade C for scores 60-69
			break;
		case 5: // for scores in range 59-50
			grade = "D"; // Grade D for scores 50-59
			break;
		default: // for scores below 50
			grade = "E"; // Grade E for scores 40-49 and below
			break;
		}
		return grade;
	}

	@SuppressWarnings("unchecked")
	double getAllPerformanceHistory(long id){
		String getAllVendorHistory = "SELECT SUM(vph.collectiveScore), COUNT(vph) FROM VendorPerformanceHistory vph WHERE vph.vendor.vendorId = :vendorId";
		Query query = entityManager.createQuery(getAllVendorHistory).setParameter("vendorId", id);
		// Get the result as a list (single row result, so list size should be 1)
        List<Object[]> resultSet = query.getResultList();

        // Check if the result is non-empty (just in case no matching records are found)
        if (resultSet != null && !resultSet.isEmpty()) {
            Object[] resultRow = resultSet.get(0);  // Get the first (and only) result
            Double sum = (Double) resultRow[0];  // The sum of the collectiveScore
            Long count = (Long) resultRow[1];
            double result = sum/count;
            return result;
        } else {
			return 0;
		}
	}
}
