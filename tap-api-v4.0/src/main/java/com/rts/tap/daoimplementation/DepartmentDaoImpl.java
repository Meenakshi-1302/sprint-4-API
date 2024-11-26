package com.rts.tap.daoimplementation;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

	
	private EntityManager entityManager;

	public DepartmentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void save(Department department) {
		entityManager.persist(department);
	}
	
	@Override
	public List<Department> getAllDepartments() {
		String hql = "from Department";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}
	
	@Override
	public void update(Department department) {
		entityManager.merge(department);	
	}

	@Override
	public Optional<Department> findById(Long id) {
	    Department department = entityManager.find(Department.class, id);
	    return Optional.ofNullable(department); 
	}
	
}
	


