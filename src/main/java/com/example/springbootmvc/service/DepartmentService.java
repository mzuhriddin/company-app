package com.example.springbootmvc.service;

import com.example.springbootmvc.dto.ApiResponse;
import com.example.springbootmvc.dto.DepartmentDTO;
import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.entity.Department;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;


    public ApiResponse add(DepartmentDTO departmentDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        if (optionalCompany.isPresent()) return new ApiResponse("Akaajon bunaqa id yoq", false);
        Company company = optionalCompany.get();

        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setCompany(company);

        Department save = departmentRepository.save(department);
        return new ApiResponse("Saved", true, save);
    }

    public ApiResponse update(DepartmentDTO departmentDTO, Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());

        if (optionalCompany.isPresent()) {
            return new ApiResponse("Akaajon bunaqa id yoq", false);
        }

        Company company = optionalCompany.get();

        Department department=new Department();
        department.setId(id);
        department.setName(departmentDTO.getName());
        department.setCompany(company);

        Department update = departmentRepository.save(department);
        return new ApiResponse("saved",true,update);
    }
}
