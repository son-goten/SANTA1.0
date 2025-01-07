package com.example.santa.createAccount.service;

import com.example.santa.createAccount.mapper.AdministratorMapper;
import com.example.santa.createAccount.vo.AdministratorVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    private final AdministratorMapper administratorMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdministratorService(AdministratorMapper administratorMapper) {
        this.administratorMapper = administratorMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerAdministrator(AdministratorVO administrator) {
        // 비밀번호 검증
        String password = administrator.getPassword();
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("비밀번호는 최소 1개의 특수문자와 숫자를 포함해야 합니다.");
        }

        // 사원코드로 역할 가져오기
        String role = administratorMapper.findRoleByEmployeeCode(administrator.getEmployeeCode());
        if (role == null) {
            throw new IllegalArgumentException("유효하지 않은 사원코드입니다.");
        }

        // 이메일 형식 검증
        String email = administrator.getEmail();
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("올바른 이메일 형식을 입력하세요.");
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(password);
        administrator.setPassword(encryptedPassword);
        administrator.setRole(role);

        // 관리자 정보 삽입
        administratorMapper.insertAdministrator(administrator);
    }

    public boolean isUserIdDuplicate(String userId) {
        return administratorMapper.countByUserId(userId) > 0;
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && email.matches(emailPattern);
    }
}
