package com.example.santa.login.service;

import com.example.santa.login.mapper.LoginMapper;
import com.example.santa.login.vo.LoginVO;
import com.example.santa.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public LoginVO authenticateUser(String userId, String password) {
        LoginVO user = loginMapper.getUserByUserId(userId);
        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            return user; // 인증 성공
        }
        return null; // 인증 실패
    }
}
