package com.rts.tap.service.impl.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.model.Department;
import com.rts.tap.serviceimplementation.DepartmentServiceImpl;

class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentDao departmentDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDepartment() {
        // Arrange
        Department department = new Department();
        department.setDepartmentId(1L); // Assuming Department has an ID field
        department.setDepartmentName("HR"); // Set other fields as necessary

        // Act
        departmentService.addDepartment(department);

        // Assert
        verify(departmentDao, times(1)).save(department); // Verify that save was called once with the department object
    }

    @Test
    void testGetAllDepartments() {
        // Arrange
        Department department1 = new Department();
        department1.setDepartmentId(1L);
        department1.setDepartmentName("HR");

        Department department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setDepartmentName("Finance");

        List<Department> departments = Arrays.asList(department1, department2);
        when(departmentDao.getAllDepartments()).thenReturn(departments);

        // Act
        List<Department> result = departmentService.getAllDepartments();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("HR", result.get(0).getDepartmentName());
        assertEquals("Finance", result.get(1).getDepartmentName());
        verify(departmentDao, times(1)).getAllDepartments(); // Verify that getAllDepartments was called once
    }
}