package com.example.santa.createAccount.controller;

import com.example.santa.createAccount.service.AdministratorService;
import com.example.santa.createAccount.vo.AdministratorVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdministratorController {
    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/admin/signup")
    public String showSignupForm() {
        return "createAccount/signup"; // templates/createAccount/signup.html로 연결
    }

    @PostMapping("/admin/signup")
    public String registerAdministrator(AdministratorVO administrator, Model model) {
        administratorService.registerAdministrator(administrator);
        model.addAttribute("message", "관리자 계정이 성공적으로 생성되었습니다.");
        return "createAccount/success"; // templates/createAccount/success.html로 연결
    }
}

