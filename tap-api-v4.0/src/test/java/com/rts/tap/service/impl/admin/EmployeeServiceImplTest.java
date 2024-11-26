//package com.rts.tap.service.impl.admin;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.rts.tap.dao.EmployeeDao;
//import com.rts.tap.model.Employee;
//import com.rts.tap.serviceimplementation.EmployeeServiceImpl1;
//
//class EmployeeServiceImplTest {
//
//    @InjectMocks
//    private EmployeeServiceImpl1 employeeService;
//
//    @Mock
//    private EmployeeDao employeeDao;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddEmployee() {
//        // Arrange
//        Employee employee = new Employee();
//        employee.setEmployeeId(1L); // Assuming Employee has an ID field
//        employee.setEmployeeEmail("john.doe@example.com");
//        employee.setEmployeeStatus(Employee.EmploymentStatus.ACTIVE);
//        employee.setCreatedDate(LocalDateTime.now());
//        employee.setUpdatedDate(LocalDateTime.now());
//
//        // Act
//        employeeService.addEmployee(employee);
//
//        // Assert
//        verify(employeeDao, times(1)).save(employee); // Verify that save was called once with the employee object
//    }
//
//    @Test
//    void testGetAllEmployee() {
//        // Arrange
//        Employee employee1 = new Employee();
//        employee1.setEmployeeId(1L);
//        employee1.setEmployeeEmail("john.doe@example.com");
//        employee1.setEmployeeStatus(Employee.EmploymentStatus.ACTIVE);
//
//        Employee employee2 = new Employee();
//        employee2.setEmployeeId(2L);
//        employee2.setEmployeeEmail("jane.doe@example.com");
//        employee2.setEmployeeStatus(Employee.EmploymentStatus.INACTIVE);
//
//        List<Employee> employees = Arrays.asList(employee1, employee2);
//        when(employeeDao.getAllEmployee()).thenReturn(employees);
//
//        // Act
//        List<Employee> result = employeeService.getAllEmployee();
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("john.doe@example.com", result.get(0).getEmployeeEmail());
//        assertEquals("jane.doe@example.com", result.get(1).getEmployeeEmail());
//        verify(employeeDao, times(1)).getAllEmployee(); // Verify that getAllEmployee was called once
//    }
//
//    @Test
//    void testUpdateEmployee() {
//        // Arrange
//        Employee employee = new Employee();
//        employee.setEmployeeId(1L); // Assuming Employee has an ID field
//        employee.setEmployeeEmail("john.doe@example.com");
//        employee.setEmployeeStatus(Employee.EmploymentStatus.ACTIVE);
//
//        // Act
//        employeeService.updateEmployee(employee);
//
//        // Assert
//        verify(employeeDao, times(1)).updateEmployee(employee); // Verify that updateEmployee was called once with the employee object
//    }
//}