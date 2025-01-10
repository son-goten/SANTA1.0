package com.example.santa.login.controller;

import com.example.santa.login.service.LoginService;
import com.example.santa.login.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String showLoginPage() {
        return "login/login"; // login.html 렌더링
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String userId,
                               @RequestParam String password,
                               Model model) {
        LoginVO user = loginService.authenticateUser(userId, password);
        if (user != null) {
            model.addAttribute("userDetails", user);
            return "redirect:/home"; // 로그인 성공 시 리다이렉트
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login/login"; // 실패 시 다시 로그인 페이지
        }
    }
}
