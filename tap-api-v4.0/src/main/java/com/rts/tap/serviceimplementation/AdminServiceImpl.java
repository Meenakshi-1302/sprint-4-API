package com.rts.tap.serviceimplementation;


import org.springframework.stereotype.Service;

import com.rts.tap.dao.AdminDao;
import com.rts.tap.model.Admin;
import com.rts.tap.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    
    private AdminDao repo;

	public AdminServiceImpl(AdminDao repo) {
		super();
		this.repo = repo;
	}

	public void addAdmin(Admin admin) {
		repo.save(admin);
	}
	

}
