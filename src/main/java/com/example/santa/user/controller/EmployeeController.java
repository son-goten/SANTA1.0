package com.example.santa.user.controller;

import com.example.santa.user.service.EmployeeService;
import com.example.santa.user.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 직원 리스트 및 검색
    @GetMapping
    public String listEmployees(@RequestParam(required = false) String keyword, Model model) {
        List<EmployeeVO> employees = employeeService.searchEmployees(keyword);
        model.addAttribute("employees", employees);
        return "user/employeeList";
    }

    // 직원 권한 변경 (다중)
    @PostMapping("/updateRoles")
    public String updateEmployeeRoles(@RequestParam List<String> employeeCodes,
                                      @RequestParam String newRole) {
        employeeService.updateEmployeeRoles(employeeCodes, newRole);
        return "redirect:/employee";
    }

    // 직원 삭제 (다중)
    @PostMapping("/delete")
    public String deleteEmployees(@RequestParam List<String> employeeCodes) {
        employeeService.deleteEmployees(employeeCodes);
        return "redirect:/employee";
    }
}
