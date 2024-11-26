package com.rts.tap.service.impl.admin;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.AdminDao;
import com.rts.tap.model.Admin;
import com.rts.tap.serviceimplementation.AdminServiceImpl;

class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminDao adminDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAdmin() {
        // Arrange
        Admin admin = new Admin();
        admin.setAdminId(1L); // Assuming Admin has an ID field
        admin.setUsername("John Doe"); // Set other fields as necessary

        // Act
        adminService.addAdmin(admin);

        // Assert
        verify(adminDao, times(1)).save(admin); // Verify that save was called once with the admin object
    }
}