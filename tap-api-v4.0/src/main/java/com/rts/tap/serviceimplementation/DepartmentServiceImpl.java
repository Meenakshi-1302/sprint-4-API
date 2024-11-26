package com.rts.tap.serviceimplementation;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.model.Department;
import com.rts.tap.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao repo;

	public void addDepartment(Department department) {
		repo.save(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		return repo.getAllDepartments();
	}

	@Override
    public void updateDepartment(Department department) {
        repo.update(department); 
    }

	@Override
    public Department getDepartmentById(Long id) {
        // Using Optional to handle potential null values and ensure safety
        Optional<Department> department = repo.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new RuntimeException("Department not found with ID: " + id);
        }
    }

}
