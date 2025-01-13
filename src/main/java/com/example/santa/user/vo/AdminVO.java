package com.example.santa.user.vo;

import lombok.Data;

@Data
public class AdminVO {
    private int adminId;        // 관리자 ID
    private String employeeCode; // 사원번호
    private String role;         // 권한
    private String name;         // 이름
    private String email;        // 이메일
}

