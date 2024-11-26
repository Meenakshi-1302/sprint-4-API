//package com.rts.tap.daoimplementation;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//import com.rts.tap.dao.ThirdPartyDAO;
//import com.rts.tap.model.Candidate;
//import com.rts.tap.model.Client;
//import com.rts.tap.model.Vendor;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.NoResultException;
//import jakarta.persistence.Query;
//
//@Repository
//public class ThirdPartyDaoImpl implements ThirdPartyDAO {
//
//	private EntityManager entityManager;
//
//	public ThirdPartyDaoImpl(EntityManager entityManager) {
//		super();
//		this.entityManager = entityManager;
//	}
//
//	@Override
//	public Object doLogin(String email, String password) {
//		String checkEmail = email;
//
//		String hql = "SELECT tr.role FROM ThirdPartyCredentitals v JOIN v.role tr WHERE v.email = :email";
//		String role = (String) entityManager.createQuery(hql).setParameter("email", email).getSingleResult();
//
//		System.out.println(role);
//		if (role.equalsIgnoreCase("client")) {
//
//			String emailLoginQuery = "SELECT v FROM Client v WHERE v.thirdPartyCredentitals.email=:email";
//			Query query = entityManager.createQuery(emailLoginQuery).setParameter("email", email);
//
//			try {
//				Client client = (Client) query.getSingleResult();
//				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//				System.out.println(passwordEncoder.encode(password));
//				System.out.println(client.getPassword());
//				if (passwordEncoder.matches(password, client.getPassword())) {
//					return client;
//				} else {
//					return null;
//				}
//			} catch (NoResultException e) {
//				System.out.println("No client found for email: " + email);
//				return null;
//			}
//		} else if (role.equalsIgnoreCase("vendor")) {
//			String emailLoginQuery = "SELECT v FROM Vendor v WHERE v.thirdPartyCredentitals.email=:email AND v.thirdPartyCredentitals.password = :password";
//			Query query = entityManager.createQuery(emailLoginQuery).setParameter("email", email)
//					.setParameter("password", password);
//			System.out.println(email + "hi");
//			System.out.println(password + "punda");
//			return (Vendor) query.getSingleResult();
//		} else {
//			String hq = "SELECT c FROM Candidate c WHERE c.email = :email AND c.password = :password";
//			Query query = entityManager.createQuery(hq).setParameter("email", email).setParameter("password", password);
//			return (Candidate) query.getSingleResult();
//		}
//
//	}
//
//}

package com.rts.tap.daoimplementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.CandidateDao;
import com.rts.tap.dao.ThirdPartyDAO;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Client;
import com.rts.tap.model.ThirdPartyCredentitals; // Import the credentials model
import com.rts.tap.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

@Repository
public class ThirdPartyDaoImpl<T> implements ThirdPartyDAO {

	private EntityManager entityManager;
	private CandidateDao<T> dao;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public ThirdPartyDaoImpl(EntityManager entityManager, CandidateDao<T> dao) {
		super();
		this.entityManager = entityManager;
		this.dao = dao;
	}

	@Override
	public Object doLogin(String email, String password) {
		try {

			Candidate candidate = dao.findByEmail(email);

			if (candidate.getPassword().equals(password)) {
				return candidate;
			}
		} catch (EmptyResultDataAccessException e) {
		}

		try {
			ThirdPartyCredentitals credentials = (ThirdPartyCredentitals) entityManager
					.createQuery("SELECT c FROM ThirdPartyCredentitals c WHERE c.email = :email")
					.setParameter("email", email).getSingleResult();

			String role = credentials.getRole().getRole();
			System.out.println("Role: " + role);
			System.out.println("Email: " + email);

			if (role.equalsIgnoreCase("client")) {
				if (!passwordEncoder.matches(password, credentials.getPassword())) {
					return null;
				}
				return entityManager
						.createQuery("SELECT c FROM Client c WHERE c.thirdPartyCredentitals = :cred", Client.class)
						.setParameter("cred", credentials).getSingleResult();

			} else if (role.equalsIgnoreCase("vendor")) {
				String emailPasswordQuery = "SELECT v FROM Vendor v WHERE v.thirdPartyCredentitals.email = :email AND v.thirdPartyCredentitals.password = :password";
				return entityManager.createQuery(emailPasswordQuery, Vendor.class).setParameter("email", email)
						.setParameter("password", password).getSingleResult();
			}

		} catch (NoResultException e) {
			System.out.println("No user found for email: " + email);
			return null;
		}

		return null;
	}

	@Override
	public boolean emailExists(String email) {
		String hql = "From ThirdPartyCredentitals tpc where tpc.email = :email";
		Query q = entityManager.createQuery(hql);
		q.setParameter("email", email);

		ThirdPartyCredentitals thirdPartyCredentitals = (ThirdPartyCredentitals) q.getSingleResult();
		if (thirdPartyCredentitals == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public String updateForgotPassword(String email, String newPassword) {
		String hql = "update ThirdPartyCredentitals t set t.password = :newPassword where t.email = :email";
		Query q = entityManager.createQuery(hql);
        q.setParameter("email", email);
        q.setParameter("newPassword", newPassword);
        
        int updatedCount = q.executeUpdate();
        return updatedCount > 0 ? "Password Updated Successfully" : "Password updation failed";
	}

	@Override
	public ThirdPartyCredentitals findbyEmail(String email) {
		String hql = "From ThirdPartyCredentitals tpc where tpc.email = :email";
		Query q = entityManager.createQuery(hql, ThirdPartyCredentitals.class);
		q.setParameter("email", email);
		return (ThirdPartyCredentitals) q.getSingleResult();
	}

	@Override
	public Client findClientByThirdPartyId(Long id) {
		String hql = "From Client c where c.thirdPartyCredentitals.thirdPartyCredentialsId = :id";
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		return (Client) q.getSingleResult();
	}
	
	@Override
	public Vendor findVendorByThirdPartyId(Long id) {
		String hql = "From Vendor v where v.thirdPartyCredentitals.thirdPartyCredentialsId = :id";
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id);
		return (Vendor) q.getSingleResult();
	}

	@Override
	public ThirdPartyCredentitals verifyOtp(String email, String otp) {
		String hql = "From ThirdPartyCredentitals t where t.email = :email and t.otp = :otp";
		Query q = entityManager.createQuery(hql);
		q.setParameter("email", email);
		q.setParameter("otp", otp);
		return (ThirdPartyCredentitals) q.getSingleResult();
	}

}
