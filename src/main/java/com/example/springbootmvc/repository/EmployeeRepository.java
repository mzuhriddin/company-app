package com.example.springbootmvc.repository;

import com.example.springbootmvc.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeByDepartment_Id(Integer integer);
}
