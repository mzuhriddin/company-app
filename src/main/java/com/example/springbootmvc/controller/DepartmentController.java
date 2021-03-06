package com.example.springbootmvc.controller;

import com.example.springbootmvc.dto.DepartmentDTO;
import com.example.springbootmvc.entity.Employee;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.repository.EmployeeRepository;
import com.example.springbootmvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    //zaproslarni tutib ishlatish
//    @RequestMapping(method = RequstMethod.GET)
    @GetMapping
    public String getDepartmentPage(Model model) {

        model.addAttribute("list", departmentRepository.findAll());
        //listini yuborish
        return "department/department";
    }

    @GetMapping("/add")
    public String getSaveDepartment(Model model) {

        model.addAttribute("companyList", companyRepository.findAll());

        return "department/department-add";
    }

    @PostMapping("/add")
    public String saveDepartment(@ModelAttribute DepartmentDTO dto) {
        departmentService.add(dto);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        Optional<Employee> employeeByDepartment_id = employeeRepository.findEmployeeByDepartment_Id(id);
        if (employeeByDepartment_id.isEmpty()) {
            departmentRepository.deleteById(id);
        }
        return "redirect:/department";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("companyList", companyRepository.findAll());
        model.addAttribute("department", departmentRepository.getById(id));
        return "department/department-edit";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@ModelAttribute DepartmentDTO departmentDTO, @PathVariable Integer id) {
        departmentService.update(departmentDTO, id);
        return "redirect:/department";
    }
}
