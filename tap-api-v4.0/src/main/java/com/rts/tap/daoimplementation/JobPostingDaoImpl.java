package com.rts.tap.daoimplementation;
 
import java.util.List;
 
import org.springframework.stereotype.Repository;
 
import com.rts.tap.dao.JobPostingDao;
import com.rts.tap.model.JobPosting;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
 
@Repository
public class JobPostingDaoImpl implements JobPostingDao {
 
	@PersistenceContext
    private EntityManager entityManager;
 
    @Override
    public List<JobPosting> getAll() {
        TypedQuery<JobPosting> query = entityManager.createQuery("SELECT jp FROM JobPosting jp", JobPosting.class);
        return query.getResultList();
    }
}
