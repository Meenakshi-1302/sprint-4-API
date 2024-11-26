package com.rts.tap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.BulkEmployeeCreationDto;
import com.rts.tap.dto.EmployeeDto;
import com.rts.tap.model.Employee;
import com.rts.tap.service.EmployeeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}	

	@PostMapping(APIConstants.ADD_EMPLOYEE_URL)
    public String addEmployee(@RequestBody Employee employee) {
        try {
        	System.out.println(employee.getManagerId());
        	
            String response = employeeService.addEmployee(employee);
            if(response.equals("Email Exist")) {
            	return MessageConstants.EMAIL_EXIST;
            }else {
            	return MessageConstants.SUCCESS_MESSAGE;
            }
        } catch (Exception e) {
            return MessageConstants.FAILURE_MESSAGE;
        }
    }


	@PostMapping(APIConstants.ADD_BULK_EMPLOYEE_URL)
	public ResponseEntity<Map<String, Object>> addBulkEmployee(@RequestBody List<BulkEmployeeCreationDto> bulkEmployee) {
	    try {
	        
	        // Call the service to handle the employee creation and get the result
	        Map<String, Object> response = employeeService.addBulkEmployee(bulkEmployee);
	        // Return the detailed response to the frontend
	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        // Log the exception details for better traceability
	        e.printStackTrace();  // This prints the stack trace to console
	        System.err.println("Error while adding bulk employees: " + e.getMessage());  // Prints error message to stderr

	        // Return a failure response with HTTP 500 INTERNAL_SERVER_ERROR status and the error message
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("error", "Failed to add employees. Error: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}


	@GetMapping(APIConstants.GETALL_EMPLOYEE_URL)
	public List<Employee> viewAllEmployee() {
		return employeeService.getAllEmployee();
	}
	
	@GetMapping(APIConstants.GET_EMPLOYEE_BY_EMAIL)
	public boolean getEmployeeByEmail(@PathVariable String email) {
		return employeeService.existsByEmail(email);
	}
	
	@GetMapping(APIConstants.GETALL_EMPLOYEE_EMAIL_URL)
	public List<String> getAllEmployeeByEmail() {
		return employeeService.getAllEmployeeEmail();
	}
	
	@GetMapping(APIConstants.CHECK_EMPLOYEE_STATUS_URL)
	public String checkEmployeeStatus(@PathVariable String email) {
		return employeeService.checkEmployeeStatus(email);
	}
	
	@GetMapping(APIConstants.GET_EMPLOYEE_BY_ID)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping(APIConstants.UPDATE_EMPLOYEE_URL)
	public String updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
		try {
			System.out.println("Contol"+employeeId);
			employeeService.updateEmployee(employeeId, employee);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return MessageConstants.FAILURE_MESSAGE;
		}
	}
		
	
	// Code by Velan
	@GetMapping(APIConstants.GET_ALL_EMPLOYEE_BY_ROLE)
	public List<Employee> getEmployeesByRoles() {
		return employeeService.getEmployeesByRoles();
	}


	@PutMapping(APIConstants.UPDATE_EMPLOYEE_STATUS_URL)
	public ResponseEntity<String> updateEmployeeStatus(
	        @PathVariable("employeeId") Long employeeId, 
	        @RequestBody @Valid EmployeeDto employeeDto) {
	    
	    try {
	        employeeService.updateEmployeeStatus(employeeId, employeeDto.getEmployeeStatus());
	        return ResponseEntity.ok("Employee status updated successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(400).body("Error: " + e.getMessage());
	    }
	}
	

}

	





