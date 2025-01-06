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
        this.passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화를 위해 사용
    }

    public void registerAdministrator(AdministratorVO administrator) {
        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(administrator.getPassword());
        administrator.setPassword(encryptedPassword);

        administratorMapper.insertAdministrator(administrator);
    }
}

