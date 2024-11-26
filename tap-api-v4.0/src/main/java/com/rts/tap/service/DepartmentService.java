package com.rts.tap.service;

import java.util.List;
import com.rts.tap.model.Department;

public interface DepartmentService {
	
	void addDepartment(Department department);
	List<Department> getAllDepartments();
	void updateDepartment(Department department);
	Department getDepartmentById(Long id);
     
}
