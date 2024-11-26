package com.rts.tap.controller;

import java.util.List;

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
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)

public class RoleController {

	private RoleService roleService;

	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@PostMapping(APIConstants.ADD_ROLE_URL)
	public String addRole(@RequestBody Role role) {

		try {
			roleService.addRole(role);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return MessageConstants.FAILURE_MESSAGE;
		}
	}

	@GetMapping(APIConstants.GETALL_ROLE_URL)
	public List<Role> viewAllRole() {
		return roleService.getAllRole();
	}
	
	@GetMapping(APIConstants.GET_ROLE_BY_ID_URL)
   	public Role getRoleById(@PathVariable("id") Long id) {
   		return roleService.getRoleById(id);
   	}

//	@PutMapping(APIConstants.UPDATE_ROLE_URL)
//	public String updateRole(@RequestBody Role role) {
//		try {
//			roleService.updateRole(role);
//			return MessageConstants.SUCCESS_MESSAGE;
//		} catch (Exception e) {
//			return MessageConstants.FAILURE_MESSAGE;
//		}
//	}
	@PutMapping(APIConstants.UPDATE_ROLE_URL)
	public ResponseEntity<String> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
	    try {
	        
	        role.setRoleId(id);

	        // Update the role using the service
	        roleService.updateRole(role);
	        
	        return ResponseEntity.status(HttpStatus.OK)
	                             .body(MessageConstants.SUCCESS_MESSAGE);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(MessageConstants.FAILURE_MESSAGE);
	    }
	}


}
