package com.example.santa.createAccount.controller;

import com.example.santa.createAccount.service.AdministratorService;
import com.example.santa.createAccount.vo.AdministratorVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdministratorController {

    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "createAccount/signup";
    }

    @PostMapping("/signup")
    public String registerAdministrator(AdministratorVO administrator, Model model) {
        try {
            administratorService.registerAdministrator(administrator);
            model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다!");
            return "createAccount/success";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());

            // 입력값 유지
            model.addAttribute("userId", administrator.getUserId());
            model.addAttribute("employeeCode", administrator.getEmployeeCode());
            model.addAttribute("name", administrator.getName());
            model.addAttribute("email", administrator.getEmail());

            // 유효하지 않은 필드 표시
            if (e.getMessage().contains("사원코드")) {
                model.addAttribute("errorField", "employeeCode");
            } else if (e.getMessage().contains("비밀번호")) {
                model.addAttribute("errorField", "password");
            } else if (e.getMessage().contains("이메일")) {
                model.addAttribute("errorField", "email");
            } else if (e.getMessage().contains("사용자 ID")) {
                model.addAttribute("errorField", "userId");
            }

            return "createAccount/signup";
        }
    }

    // ID 중복 확인 API
    @GetMapping("/api/check-user-id")
    @ResponseBody
    public boolean checkUserIdDuplicate(@RequestParam("userId") String userId) {
        return administratorService.isUserIdDuplicate(userId);
    }
}
