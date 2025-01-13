package com.example.santa.user.vo;

import lombok.Data;

@Data
public class AdminVO {
    private int adminId;          // Administrators 테이블의 관리자 ID
    private String employeeCode;  // EmployeeRoles와 Administrators 공통 필드
    private String role;          // EmployeeRoles 테이블의 권한
    private String name;          // Administrators 테이블의 이름
    private String email;         // Administrators 테이블의 이메일
}

