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

    // 직원 리스트 조회
    @GetMapping
    public String listEmployees(Model model) {
        List<EmployeeVO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "user/employeeList";
    }

    // 직원 권한 변경
    @PostMapping("/updateRole")
    public String updateEmployeeRole(@RequestParam String employeeCode,
                                     @RequestParam String newRole) {
        employeeService.updateEmployeeRole(employeeCode, newRole);
        return "redirect:/employee";
    }

    // 직원 계정 삭제
    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam String employeeCode) {
        employeeService.deleteEmployee(employeeCode);
        return "redirect:/employee";
    }
}
