package com.example.service;

import com.example.Entity.Employee;
import com.example.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setPosition("Developer");
        employee.setSalary(50000);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> list = List.of(employee);
        when(employeeRepository.findAll()).thenReturn(list);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateEmployee() {
        Employee input = new Employee();
        input.setName("Jane");
        input.setPosition("Tester");
        input.setSalary(40000);

        Employee saved = new Employee();
        saved.setId(2L);
        saved.setName("Jane");
        saved.setPosition("Tester");
        saved.setSalary(40000);

        when(employeeRepository.save(any(Employee.class))).thenReturn(saved);

        Employee result = employeeService.createEmployee(input);

        assertNotNull(result.getId());
        assertEquals("Jane", result.getName());
        verify(employeeRepository, times(1)).save(input);
    }

    @Test
    void testUpdateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee updates = new Employee();
        updates.setName("Updated Name");
        updates.setPosition("Lead Developer");
        updates.setSalary(70000);

        // Return the updated employee from the mock save call
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee updated = employeeService.updateEmployee(1L, updates);

        assertEquals("Updated Name", updated.getName());
        assertEquals("Lead Developer", updated.getPosition());
        assertEquals(70000, updated.getSalary());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
