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
    public String updateEmployeeRoles(@RequestParam(name = "employeeCodes", required = false) List<String> employeeCodes,
                                      @RequestParam String newRole) {
        if (employeeCodes == null || employeeCodes.isEmpty()) {
            throw new IllegalArgumentException("권한을 변경할 직원이 선택되지 않았습니다.");
        }
        employeeService.updateEmployeeRoles(employeeCodes, newRole);
        return "redirect:/employee";
    }

    // 직원 삭제 (다중)
    @PostMapping("/delete")
    public String deleteEmployees(@RequestParam(name = "employeeCodes", required = false) List<String> employeeCodes) {
        if (employeeCodes == null || employeeCodes.isEmpty()) {
            throw new IllegalArgumentException("삭제할 직원이 선택되지 않았습니다.");
        }
        employeeService.deleteEmployees(employeeCodes);
        return "redirect:/employee";
    }
}

