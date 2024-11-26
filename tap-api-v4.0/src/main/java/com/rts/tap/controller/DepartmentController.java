package com.rts.tap.controller;

import java.util.List;
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
import com.rts.tap.model.Department;
import com.rts.tap.service.DepartmentService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)

public class DepartmentController {

	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@PostMapping(APIConstants.ADD_DEPARTMENT_URL)
	public String addDepartment(@RequestBody Department department) {
		try {
			System.out.println(department.getDepartmentName());
			departmentService.addDepartment(department);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return MessageConstants.FAILURE_MESSAGE;
		}
	}

	@GetMapping(APIConstants.GETALL_DEPARTMENT_URL)
	public List<Department> viewAllDepartments() {
		return departmentService.getAllDepartments();
	}

	@PutMapping("/updatedepartment/{id}")
	public String updateDepartment(@PathVariable Long id, @RequestBody Department department) {
	    try {
	        System.out.println("Received department: " + department);
	        department.setDepartmentId(id);
	        departmentService.updateDepartment(department);
	        return "Department updated successfully!";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error updating department!";
	    }
	}

	@GetMapping(APIConstants.GET_DEPARTMENT_BY_ID_URL)
	public Department getDepartmentById(@PathVariable Long id) {
	    try {
	        Department department = departmentService.getDepartmentById(id);
	        if (department != null) {
	            return department;
	        } else {
	            throw new RuntimeException("Department not found with ID: " + id);
	        }
	    } catch (Exception e) {
	        // Handle the exception (you can also return an error message or status)
	        throw new RuntimeException("Failed to fetch department: " + e.getMessage());
	    }
	}


}
