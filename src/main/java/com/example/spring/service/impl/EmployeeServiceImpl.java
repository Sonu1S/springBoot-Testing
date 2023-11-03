package com.example.spring.service.impl;

import com.example.spring.exception.ResourceNotFoundException;
import com.example.spring.model.Employee;
import com.example.spring.repository.EmployeeRepository;
import com.example.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee savedEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already Exist With the given email: "
                    + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}
