package com.example.santa.createAccount.vo;

import lombok.Data;

@Data
public class AdministratorVO {
    private Integer adminId;
    private String userId;
    private String password;
    private String employeeCode;
    private String name;
    private String email;
    private String role;
    private String createdAt;
}
