package com.example.spring.service;

import com.example.spring.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee savedEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeById(Long Id);

    Employee updateEmployee(Employee updatedEmployee);

    void deleteEmployee(long id);




}
