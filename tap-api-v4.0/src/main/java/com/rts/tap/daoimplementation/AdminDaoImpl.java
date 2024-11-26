package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;
import com.rts.tap.dao.AdminDao;
import com.rts.tap.model.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {

	
	private EntityManager entityManager;

	public AdminDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void save(Admin admin) {
		entityManager.persist(admin);
	}

	public Admin findEmail(String email) {
        String hql = "FROM Admin WHERE adminEmail = :email";
        TypedQuery<Admin> query = entityManager.createQuery(hql, Admin.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
	}

}
	


