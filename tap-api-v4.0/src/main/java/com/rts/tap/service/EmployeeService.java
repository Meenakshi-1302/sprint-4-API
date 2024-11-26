package com.rts.tap.service;


import java.util.List;
import java.util.Map;

import com.rts.tap.dto.BulkEmployeeCreationDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.Employee.EmploymentStatus;
import com.rts.tap.model.LoginCredentials;

public interface EmployeeService {
	
	String addEmployee(Employee employeee);
	
	Map<String, Object> addBulkEmployee(List<BulkEmployeeCreationDto> bulkEmployee); // for bulk Employee Creation
	
	List<Employee> getAllEmployee();
	List<String> getAllEmployeeEmail();
	Employee getEmployeeById(Long employeeId);
	void addLoginCredentials(LoginCredentials loginCredentials);
	
	String updateEmployee(Long employeeId, Employee employee);	
	
	//Code By Velan
	List<Employee> getEmployeesByRoles();
     

//	void updateEmployeeStatus(Long employeeId, EmploymentStatus employeeStatus);

//	void updateEmployeeStatus(EmployeeDto employeeDto);

	void updateEmployeeStatus(Long employeeId, EmploymentStatus employeeStatus);

	String checkEmployeeStatus(String email);

	boolean existsByEmail(String email);
	     
}
