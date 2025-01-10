package com.example.santa.register.vo;

import lombok.Data;

@Data
public class RegisterVO {
    private String userId;
    private String password;
    private String employeeCode;
    private String role; // role은 자동 설정
    private String name;
    private String email;
}
