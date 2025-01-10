package com.example.santa.find.controller;

import com.example.santa.find.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/find")
public class FindController {

    @Autowired
    private FindService findService;

    // 아이디/비밀번호 찾기 페이지 렌더링
    @GetMapping("")
    public String showFindPage() {
        return "find/find"; // templates/find/find.html 렌더링
    }

    // 아이디 찾기 요청 처리
    @PostMapping("/id")
    public String findId(@RequestParam String email, Model model) {
        String userId = findService.findIdByEmail(email);

        if (userId == null) {
            model.addAttribute("message", "No ID found for the provided email.");
        } else {
            model.addAttribute("userId", userId);
        }

        return "find/find_result"; // templates/find/find_result.html
    }

    // 비밀번호 재설정 링크 요청 처리
    @PostMapping("/password/reset")
    public String sendResetLink(@RequestParam String email, Model model) {
        try {
            findService.sendPasswordResetLink(email);
            model.addAttribute("message", "A password reset link has been sent to your email.");
            model.addAttribute("success", true); // 성공 여부
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", "Invalid email address. Please try again.");
            model.addAttribute("success", false); // 실패 여부
        }
        return "find/reset_link_result"; // templates/find/reset_link_result.html
    }

    // 비밀번호 재설정 페이지 렌더링
    @GetMapping("/password/reset")
    public String showResetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token); // 토큰을 모델에 추가
        return "find/reset_password"; // templates/find/reset_password.html
    }

    // 비밀번호 재설정 처리
    @PostMapping("/password")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword,
            Model model) {
        try {
            findService.resetPassword(token, newPassword);
            model.addAttribute("message", "Your password has been successfully reset.");
            model.addAttribute("success", true); // 성공 여부
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("success", false); // 실패 여부
        }
        return "find/reset_password_result"; // templates/find/reset_password_result.html
    }
}
