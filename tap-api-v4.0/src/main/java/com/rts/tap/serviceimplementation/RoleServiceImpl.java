package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

   private RoleDao roleDao;

	public RoleServiceImpl(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}

	@Override
	public void addRole(Role role) {
		roleDao.save(role);	
	}

	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

	@Override
	public void updateRole(Role role) {
		roleDao.update(role);	
	}

	@Override
	public Role getRoleById(Long id) {
		return roleDao.getRoleById(id);
	}

}
