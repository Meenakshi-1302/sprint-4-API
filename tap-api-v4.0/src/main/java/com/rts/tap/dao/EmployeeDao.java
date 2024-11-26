package com.rts.tap.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rts.tap.dto.BulkEmployeeCreationDto;
import com.rts.tap.model.Employee;


public interface EmployeeDao {

	void save(Employee employee);
	Map<String, Object> saveBulk(List<BulkEmployeeCreationDto> bulkEmployee); // For Adding Bulk Employee
	List<String> getAllEmployeeByEmails(); // For Bulk Employee Creation to check with existing Employee
	List<Employee> getAllEmployee(); // For Display all the employee
	void updateEmployee(Employee employee);
	
	Employee getEmployeeById(Long employeeId);
	String checkEmployeeStatus(String email);
	boolean existsByEmail(String employeeEmail);
	
	
	//Code By velan
	List<Employee> getEmployeesByRoles();
	

	Employee findEmployeeByEmail(String email);
	
	Optional<Employee> findByempId(Long employeeId);
	

}

