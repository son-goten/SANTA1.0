package com.example.santa.login.controller;

import com.example.santa.login.service.LoginService;
import com.example.santa.login.vo.LoginVO;
import com.example.santa.login.vo.UserDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // 로그인 페이지 보여주기
    @GetMapping
    public String showLoginPage() {
        return "login/login"; // 로그인 페이지 (login.html)
    }

    // 로그인 인증 처리
    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String userId,
                               @RequestParam String password,
                               Model model,
                               HttpSession session) {
        // 사용자 인증
        LoginVO user = loginService.authenticateUser(userId, password);

        if (user != null) {
            // 로그인 성공: 기본 정보 세션에 저장
            session.setAttribute("user", user);

            // 추가 정보 가져오기
            UserDetailsVO userDetails = loginService.getUserDetails(userId);
            if (userDetails != null) {
                session.setAttribute("userDetails", userDetails); // 세션에 추가 정보 저장
            }

            return "redirect:/home"; // 성공 시 홈 페이지로 리다이렉트
        } else {
            // 로그인 실패: 에러 메시지 전달
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("userId", userId); // 입력한 userId를 다시 반환
            return "login/login"; // 로그인 페이지로 다시 이동
        }
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }
}
