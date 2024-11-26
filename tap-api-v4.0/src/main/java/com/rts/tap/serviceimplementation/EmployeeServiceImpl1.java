package com.rts.tap.serviceimplementation;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.LoginCredentialsDao;
import com.rts.tap.dao.RoleDao;
import com.rts.tap.dto.BulkEmployeeCreationDto;
import com.rts.tap.dto.EmployeeDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.Employee.EmploymentStatus;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.service.EmailService1;
import com.rts.tap.service.EmployeeService;

@Service
public class EmployeeServiceImpl1 implements EmployeeService {

	private EmployeeDao employeeDao;

	private LoginCredentialsDao loginCredentialsDao;

	private EmailService1 emailService;
	
	private BusinessUnitDao businessUnitDao;
	
	private RoleDao roleDao;

	public EmployeeServiceImpl1(EmployeeDao employeeDao, LoginCredentialsDao loginCredentialsDao,
			EmailService1 emailService, BusinessUnitDao businessUnitDao, RoleDao roleDao) {
		super();
		this.employeeDao = employeeDao;
		this.loginCredentialsDao = loginCredentialsDao;
		this.emailService = emailService;
		this.businessUnitDao = businessUnitDao;
		this.roleDao = roleDao;
	}

	@Override // Add Employee
	public String addEmployee(Employee employee) {
		try {
			// Step 1: Check if email already exists
			if (employeeDao.existsByEmail(employee.getEmployeeEmail())) {
				System.out.println("emailService Exist");
				return "Email Exist";
			} else {
				employeeDao.save(employee);
				return "Employee Added";
			}			
		} catch (Exception e) {
			throw new RuntimeException("Error occurred while adding employee", e);
		}
	}

	private String generateRandomPassword(int length) {
		// Define the characters to be used in the password
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String specialCharacters = "!@#$%^&*()-_=+<>?";

		// Combine all characters
		String allCharacters = upperCase + lowerCase + numbers + specialCharacters;

		// Use SecureRandom for better randomness
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(length);

		// Ensure that the password contains at least one upper case, one lower case,
		// one number, and one special character
		password.append(upperCase.charAt(random.nextInt(upperCase.length())));
		password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
		password.append(numbers.charAt(random.nextInt(numbers.length())));
		password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

		// Fill the rest of the password length with random characters
		for (int i = 4; i < length; i++) {
			password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
		}

		// Shuffle the password to avoid any predictable patterns
		return shuffleString(password.toString());
	}

	// Helper method to shuffle the characters in the password
	private String shuffleString(String input) {
		char[] characters = input.toCharArray();
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < characters.length; i++) {
			int randomIndex = random.nextInt(characters.length);
			// Swap characters
			char temp = characters[i];
			characters[i] = characters[randomIndex];
			characters[randomIndex] = temp;
		}

		return new String(characters);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeDao.getAllEmployee();
	}

	@Override
		public String updateEmployee(Long employeeId,Employee updatedEmployee) {
			Employee existingEmployee = employeeDao.getEmployeeById(employeeId);
//			System.out.println(updatedEmployee.getEmployeeName());
		    // If found, update the business unit
		    if (existingEmployee != null) {
		    	
		    	System.out.println(updatedEmployee.getEmployeeName());
		    	System.out.println(updatedEmployee.getWorkLocation());
		    	System.out.println(updatedEmployee.getEmployeeEmail());
		    	System.out.println(updatedEmployee.getManagerId());
		    	System.out.println(roleDao.getRoleById(updatedEmployee.getRole().getRoleId()));
		    	System.out.println(businessUnitDao.getBusinessUnitByLocation(updatedEmployee.getWorkLocation()).getBusinessUnitName());
		    	
		    	existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
		    	existingEmployee.setWorkLocation(updatedEmployee.getWorkLocation());
		    	existingEmployee.setEmployeeEmail(updatedEmployee.getEmployeeEmail());
		    	existingEmployee.setManagerId(updatedEmployee.getManagerId());
		    	existingEmployee.setRole(roleDao.getRoleById(updatedEmployee.getRole().getRoleId()));
		    	existingEmployee.setBusinessUnit(businessUnitDao.getBusinessUnitByLocation(updatedEmployee.getWorkLocation()));
		    	
		        employeeDao.updateEmployee(existingEmployee);
		    } else {
		        // Handle the case where the BusinessUnit was not found
		        // For example, throw an exception or return an error message
		        throw new RuntimeException("Employee not found with id " + employeeId);
		    }
		    return "completed";
		}
	 

	@Override
	public void addLoginCredentials(LoginCredentials loginCredentials) {
		loginCredentialsDao.save(loginCredentials);
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		return employeeDao.getEmployeeById(employeeId);
	}

	// code by Velan
	@Override
	public List<Employee> getEmployeesByRoles() {
		return employeeDao.getEmployeesByRoles();
	}

//	@Override
//	public void addBulkEmployee(List<BulkEmployeeCreationDto> bulkEmployee) {
//		employeeDao.saveBulk(bulkEmployee);
//	}
	
	@Override
	public Map<String, Object> addBulkEmployee(List<BulkEmployeeCreationDto> bulkEmployee) {
	    return employeeDao.saveBulk(bulkEmployee);
	}


	@Override
	public List<String> getAllEmployeeEmail() {
		return employeeDao.getAllEmployeeByEmails();
	}

	@Override
	public void updateEmployeeStatus(Long employeeId, EmploymentStatus status) {
		// Fetch employee by ID
		Employee employee = employeeDao.getEmployeeById(employeeId);

		if (employee != null) {
			// Business logic: If the status is different, update it
			if (!employee.getEmployeeStatus().equals(status)) {
				employee.setEmployeeStatus(status);
				employeeDao.updateEmployee(employee); // Use DAO's updateEmployee method to save changes
			}
		} else {
			throw new RuntimeException("Employee not found with ID: " + employeeId);
		}
	}

	@Override
	public String checkEmployeeStatus(String email) {
		return employeeDao.checkEmployeeStatus(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		return employeeDao.existsByEmail(email);
	}

}
