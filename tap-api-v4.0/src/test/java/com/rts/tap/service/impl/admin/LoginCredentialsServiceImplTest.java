package com.rts.tap.service.impl.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.LoginCredentialsDao;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.serviceimplementation.LoginCredentialsServiceImpl;

class LoginCredentialsServiceImplTest {

    @Mock
    private LoginCredentialsDao loginCredentialsRepository;

    @InjectMocks
    private LoginCredentialsServiceImpl loginCredentialsService;

    private LoginCredentials loginCredentials;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginCredentials = new LoginCredentials();
        loginCredentials.setUserEmail("john.doe@example.com");
        loginCredentials.setPasswordHash("hashedPassword");
    }

    @Test
    void testLoginCredentialsServiceImpl() {
        // Here you can test the constructor or any initialization logic if needed
        assertNotNull(loginCredentialsService);
    }

    @Test
    void testCreate() {
        // Mock the repository to return the saved login credentials
        when(loginCredentialsRepository.save(any(LoginCredentials.class))).thenReturn(loginCredentials);

        // Call the create method
        LoginCredentials createdLoginCredentials = loginCredentialsService.create(loginCredentials);

        // Verify that the create method was called on the repository
        verify(loginCredentialsRepository, times(1)).save(loginCredentials);

        // Assert that the created login credentials are not null
        assertNotNull(createdLoginCredentials);
        assertEquals("john.doe@example.com", createdLoginCredentials.getUserEmail());
    }

   
}