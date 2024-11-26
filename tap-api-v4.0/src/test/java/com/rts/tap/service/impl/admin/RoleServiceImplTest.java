package com.rts.tap.service.impl.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Role;
import com.rts.tap.serviceimplementation.RoleServiceImpl;

class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role();
        role.setRoleId(1L);
        role.setRoleName("Admin");
        role.setRoleDescription("Administrator role");
    }

    @Test
    void testAddRole() {
        // Call the addRole method
        roleService.addRole(role);

        // Verify that the save method was called on the roleDao
        verify(roleDao, times(1)).save(role);
    }

    @Test
    void testGetAllRole() {

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        when(roleDao.getAllRole()).thenReturn(roles);

        List<Role> result = roleService.getAllRole();
        verify(roleDao, times(1)).getAllRole();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Admin", result.get(0).getRoleName());
    }

    @Test
    void testUpdateRole() {
   
        roleService.updateRole(role);

        verify(roleDao, times(1)).update(role);
    }
}