package com.rts.tap.service;

import java.util.List;
import com.rts.tap.model.Role;

public interface RoleService {
	
	void addRole(Role role);
	List<Role> getAllRole();
	Role getRoleById(Long id);
	void updateRole(Role role);
     
}
