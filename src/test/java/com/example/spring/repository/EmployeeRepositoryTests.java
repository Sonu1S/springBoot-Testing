package com.example.spring.repository;

import com.example.spring.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //it is use to test repository layer annoation
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void Setup(){
        employee = Employee.builder()
                .firstName("sonu")
                .lastName("sharma")
                .email("sonu@gmail.com")
                .build();
    }
    //junit test for save employee operation;

    @DisplayName("JUnit test for save employee operation") //we can change the Junit test case name
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("sonu")
//                .lastName("sharma")
//                .email("sonu@gmail.com")
//                .build();
        //when - action or the behaviour that  we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
//        Assertions.assertThat(savedEmployee).isNotNull();
//        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }
    //JUNIT TEST FOR  get all employee operation
    @DisplayName("JUNIT TEST FOR  get all employee operation")
    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeeList(){

        //given  precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("sonu")
//                .lastName("sharma")
//                .email("sonu@gmail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("Sonu")
                .lastName("kumar")
                .email("s@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
         assertThat(employeeList).isNotNull();
         assertThat(employeeList.size()).isEqualTo(2);
    }

    //JUNIT TEST FOR  get employee By id operation
    @DisplayName("JUNIT test for get employee By id operation")
    @Test
    public void givenTheEmployeeObject_whenFindById_thenReturnEmployeeObject(){

        //given  precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("sonu")
//                .lastName("sharma")
//                .email("sonu@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();

    }

    //JUNIT TEST FOR  get employee By email operation
    @DisplayName("JUNIT test for get employee By email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmil_thenReturnEmployeeObject(){

        //given  precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("sonu")
//                .lastName("sharma")
//                .email("sonu@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        //then - verify the output
       assertThat(employeeDB).isNotNull();
    }

    //JUNIT TEST FOR  update employee By id operation
       @DisplayName("JUNIT test for update employee By id operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnEmployee(){

        //given  precondition or setup
//           Employee employee = Employee.builder()
//                   .firstName("sonu")
//                   .lastName("sharma")
//                   .email("sonu@gmail.com")
//                   .build();
           employeeRepository.save(employee);
        //when - action or the behaviour that we are going to test
           Employee saveEmployee = employeeRepository.findById(employee.getId()).get();
           saveEmployee.setEmail("ravi@gmail.com");
           saveEmployee.setFirstName("Ravi");
           Employee updatedEmployee = employeeRepository.save(saveEmployee);

           //then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("ravi@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ravi");
    }

    //JUNIT TEST FOR  Delete employee By id operation
    @DisplayName("JUNIT test for Delete employee By id operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenreturnEmployee(){

        //given  precondition or setup
//           Employee employee = Employee.builder()
//                   .firstName("sonu")
//                   .lastName("sharma")
//                   .email("sonu@gmail.com")
//                   .build();
           employeeRepository.save(employee);

        //when - action or the behaviour that we are going to test
           employeeRepository.deleteById(employee.getId());
           Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

           //then - verify the output
      assertThat(employeeOptional).isEmpty();

    }

    //Junit test for custom query using JPQL with index
    @DisplayName("JUNIT test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){

        //given  precondition or setup
//           Employee employee = Employee.builder()
//                   .firstName("sonu")
//                   .lastName("sharma")
//                   .email("sonu@gmail.com")
//                   .build();
           employeeRepository.save(employee);
           String firstName = "sonu";
           String lastName = "sharma";

        //when - action or the behaviour that we are going to test
           Employee savedEmploee = employeeRepository.findByJPQL(firstName, lastName);

           //then - verify the output
           assertThat(savedEmploee).isNotNull();
    }

    //Junit test for custom query using JPQL with Named params
    @DisplayName("JUNIT test for custom query using JPQL with Names params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){

        //given  precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("sonu")
//                .lastName("sharma")
//                .email("sonu@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "sonu";
        String lastName = "sharma";

        //when - action or the behaviour that we are going to test
        Employee savedEmploee = employeeRepository.findByJPQL(firstName, lastName);

        //then - verify the output
        assertThat(savedEmploee).isNotNull();
    }

    //JUNIT TEST FOR  Custom Query using Native SQL with index
    @DisplayName("JUNIT test for  Custom Query using Native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){

        //given  precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("sonu")
//                .lastName("sharma")
//                .email("sonu@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "sonu";
        String lastName = "sharma";

        //when - action or the behaviour that we are going to test
        Employee savedEmploee = employeeRepository.findByNativeSQL(firstName,lastName);

        //then - verify the output
        assertThat(savedEmploee).isNotNull();
    }

    //JUNIT TEST FOR  Custom Query using Native SQL with Named Params
    @DisplayName("JUNIT test for  Custom Query using Native SQL with namedParams")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){

        //given  precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("sonu")
//                .lastName("sharma")
//                .email("sonu@gmail.com")
//                .build();
        employeeRepository.save(employee);
//        String firstName = "sonu";
//        String lastName = "sharma";

        //when - action or the behaviour that we are going to test
        Employee savedEmploee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(),
                employee.getLastName());

        //then - verify the output
        assertThat(savedEmploee).isNotNull();
    }

    //JUNIT TEST FOR  get employee By id operation
    //   @DisplayName("JUNIT test for get employee By id operation")
//    @Test
//    public void given_when_then(){
//
//        //given  precondition or setup
//
//        //when - action or the behaviour that we are going to test
//
//        //then - verify the output
//
//    }
}
