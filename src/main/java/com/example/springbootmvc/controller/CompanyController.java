package com.example.springbootmvc.controller;

import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.entity.Department;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping
    public String getCompanyPage(Model model) {
        model.addAttribute("list", companyRepository.findAll());
        return "company/company";
    }

    @GetMapping("/add")
    public String getSaveCompany() {
        return "company/company-add";
    }

    @PostMapping("/add")
    public String saveCompany(@ModelAttribute Company company) {
        companyService.add(company);
        return "redirect:/company";
    }

    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        Optional<Department> departmentByCompany_id = departmentRepository.findDepartmentByCompany_Id(id);
        if (departmentByCompany_id.isEmpty()) {
            companyRepository.deleteById(id);
        }
        return "redirect:/company";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        Company company = new Company();
        company.setId(id);
        company.setName(companyRepository.findById(id).get().getName());
        model.addAttribute("company", company);
        return "company/company-edit";
    }

    @PostMapping("/edit/{id}")
    public String editCompany(@ModelAttribute Company company) {
        companyService.add(company);
        return "redirect:/company";
    }
}
