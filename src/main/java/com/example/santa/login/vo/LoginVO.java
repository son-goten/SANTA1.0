package com.example.santa.login.vo;

import lombok.Data;

@Data
public class LoginVO {
    private String userId;   // 로그인 ID
    private String password; // 비밀번호 (암호화된 상태)
}
