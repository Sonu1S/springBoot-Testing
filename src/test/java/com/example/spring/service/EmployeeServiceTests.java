package com.example.spring.service;

import com.example.spring.exception.ResourceNotFoundException;
import com.example.spring.model.Employee;
import com.example.spring.repository.EmployeeRepository;
import com.example.spring.service.impl.EmployeeServiceImpl;
//import org.assertj.core.api.Assert;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
//import java.util.Properties;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(1L)
                .firstName("Sonu")
                .lastName("Sharma")
                .email("sonu@gmail.com")
                .build();
        // employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
    }
    //JUNIT TEST FOR  get employee By id operation
    @DisplayName("JUNIT test for save employee By id method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){

        //given  precondition or setup
//      Employee employee = Employee.builder()  //we can create object inside the setup method
//              .id(1L)
//              .firstName("Sonu")
//              .lastName("Sharma")
//              .email("sonu@gmail.com")
//              .build();
       given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

       given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //when - action or the behaviour that we are going to test
        Employee saveEmployee = employeeService.savedEmployee(employee);

        System.out.println(saveEmployee);
        //then - verify the output
       Assertions.assertThat(saveEmployee).isNotNull();
    }

    //which is old style without using Mockito

//    private EmployeeRepository employeeRepository;
//    private EmployeeService employeeService;
//
//    @BeforeEach
//    public void setup(){
//
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeRepository);
//    }
//    //JUNIT TEST FOR  get employee By id operation
//    @DisplayName("JUNIT test for save employee By id method")
//    @Test
//    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
//
//        //given  precondition or setup
//        Employee employee = Employee.builder()
//                .id(1L)
//                .firstName("Sonu")
//                .lastName("Sharma")
//                .email("sonu@gmail.com")
//                .build();
//        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
//                .willReturn(Optional.empty());
//
//        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
//
//        System.out.println(employeeRepository);
//        System.out.println(employeeService);
//
//        //when - action or the behaviour that we are going to test
//        Employee savedEmployee = employeeService.savedEmployee(employee);
//
//        System.out.println(savedEmployee);
//        //then - verify the output
//        Assertions.assertThat(savedEmployee).isNotNull();
//    }

    //JUNIT TEST FOR  get employee By id operation
    @DisplayName("JUNIT test for save employee By id method which throw exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){

        //given  precondition or setup

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //when - action or the behaviour that we are going to test
       org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.savedEmployee(employee);
        });
       
        //then - verify the output
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    //JUNIT TEST FOR  get employee By id operation
       @DisplayName("JUNIT test for get All employees method")
    @Test
    public void givenEmployeeList_whenGetAllEmployee_thenReturnEmployeeList(){

        //given  precondition or setup
          Employee employee1 = Employee.builder()
                   .id(2L)
                   .firstName("Monu")
                   .lastName("Sharma")
                   .email("monu@gmail.com")
                   .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        //when - action or the behaviour that we are going to test
         List<Employee> employeeList = employeeService.getAllEmployee();

         //then - verify the output
           Assertions.assertThat(employeeList).isNotNull();
           Assertions.assertThat(employeeList.size()).isEqualTo(2);

    }
//negative senerio

    //JUNIT TEST FOR  get employee By id operation
    @DisplayName("JUNIT test for get All employees method(negative scenario)")
    @Test
    public void givenEmptyEmployeeList_whenGetAllEmployee_thenReturnEmployeeList(){

        //given  precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Monu")
                .lastName("Sharma")
                .email("monu@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployee();

        //then - verify the output
        Assertions.assertThat(employeeList).isEmpty();
        Assertions.assertThat(employeeList.size()).isEqualTo(0);

    }

    //JUNIT TEST FOR  get employeeById operation
    @DisplayName("JUNIT test for get employeeById method ")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){

        //given  precondition or setup

        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        //when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();

        //then - verify the output

        // Assertions.assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee).isNotNull();

    }

    //JUNIT TEST FOR  get employeeById operation
    @DisplayName("JUNIT test for Update employee method ")
    @Test
    public void givenEmployeeObject_whenUpdatedEmployee_thenReturnEmployee(){

        //given  precondition or setup

        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setFirstName("Ram");
        employee.setEmail("ram@gmail.com");

        //when - action or the behaviour that we are going to test
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        //then - verify the output

        // Assertions.assertThat(savedEmployee).isNotNull();
        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");

    }

    //JUNIT TEST FOR delete employeeById operation
    @DisplayName("JUNIT test for deleteEmployee method ")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){

        //given  precondition or setup
        long employeeId =1L;
        //when return type is null
       willDoNothing().given(employeeRepository).deleteById(employeeId);

        //when - action or the behaviour that we are going to test
        employeeService.deleteEmployee(employeeId);

        //then - verify the output
        verify(employeeRepository,times(1)).deleteById(employeeId);
    }
}
