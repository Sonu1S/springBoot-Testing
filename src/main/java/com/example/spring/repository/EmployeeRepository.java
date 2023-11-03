package com.example.spring.repository;

import com.example.spring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);

    //define the custom query using JPQL with index params
    //here e is allias in sql
    @Query("Select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    //define custom qiery using JPQL with named params
    @Query("Select e from Employee e where e.firstName =:firstName and e.lastName =:lastName")
    Employee findByNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //define custom query using Native SQL with index params
    @Query(value ="Select * from employee e where e.first_Name =?1 and e.last_name=?2", nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    //define custom query using Native SQL with named params
    @Query(value ="Select * from employee e where e.first_Name =:firstName and e.last_name=:lastName", nativeQuery = true)
    Employee findByNativeSQLNamed(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
