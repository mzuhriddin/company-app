package com.example.springbootmvc.repository;

import com.example.springbootmvc.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findDepartmentByCompany_Id(Integer id);
}
