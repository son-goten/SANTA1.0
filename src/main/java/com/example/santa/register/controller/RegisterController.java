package com.example.santa.register.controller;

import com.example.santa.register.service.RegisterService;
import com.example.santa.register.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    // 회원가입 페이지 렌더링
    @GetMapping
    public String showRegisterPage() {
        return "login/register"; // templates/login/register.html
    }

    // 회원가입 처리
    @PostMapping("/submit")
    public String registerUser(@ModelAttribute RegisterVO registerVO, Model model) {
        boolean isSuccess = registerService.registerUser(registerVO);
        if (isSuccess) {
            return "login/register-success";
            //return "redirect:/login"; // 회원가입 성공 후 로그인 페이지로 이동
        } else {
            model.addAttribute("error", "Registration failed. Please check your inputs.");
            return "login/register"; // 실패 시 다시 회원가입 페이지로 이동
        }
    }

    // 아이디 중복 체크
    @GetMapping("/check-user-id")
    @ResponseBody
    public Map<String, Boolean> checkUserId(@RequestParam("userId") String userId) {
        boolean isAvailable = registerService.isUserIdAvailable(userId);

        // JSON 형태로 응답
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", isAvailable);

        return response;
    }

    // 사원 코드 유효성 검증
    @GetMapping("/check-employee-code")
    @ResponseBody
    public Map<String, Boolean> checkEmployeeCode(@RequestParam("employeeCode") String employeeCode) {
        boolean isValid = registerService.isEmployeeCodeValid(employeeCode);
        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);
        return response;
    }
}

