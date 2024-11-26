package com.rts.tap.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Admin;
import com.rts.tap.service.AdminService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)

public class AdminController {

	private AdminService adminLoginService;

	public AdminController(AdminService adminLoginService) {
		super();
		this.adminLoginService = adminLoginService;
	}


	@PostMapping(APIConstants.ADD_ADMIN_URL)
	public String addAdmin(@RequestBody Admin admin) {

//	    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		try {
//	        	String encryptPassword = bcrypt.encode(user.getPassword());
//	        	user.setPassword(encryptPassword);
			adminLoginService.addAdmin(admin);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return "MessageConstants.FAILURE_MESSAGE";
		}
	}
	

//	    @PostMapping("/login")
//	    public ResponseEntity<?> loginUser(@RequestBody User user) {
//	    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//	        try {
//	            User UseredUser = serviceimpl.findUserByEmail(user.getEmail());
//	            
//	            if (UseredUser != null) {
//		            if(	bcrypt.matches(user.getPassword(),UseredUser.getPassword())) {
//		            	String role = UseredUser.getRole(); 
//		            	int id = UseredUser.getUserId();
//		            	return ResponseEntity.ok(new LoginResponse(id,"LoginSuccess", role));            	
//		            }
//		            else {
//		                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials. Please try again.");
//		            }
//	            } else {
//	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials. Please try again.");
//	            }
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error. Please try again later.");
//	        }
//	    }	
    
}

