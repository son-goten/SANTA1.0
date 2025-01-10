package com.example.santa.login.vo;

import lombok.Data;

@Data
public class UserDetailsVO {
    private String userId; // 사용자 아이디
    private String name;   // 사용자 이름
    private String email;  // 이메일
    private String role;   // 사용자 역할
}
