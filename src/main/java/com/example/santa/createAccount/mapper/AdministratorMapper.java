package com.example.santa.createAccount.mapper;

import com.example.santa.createAccount.vo.AdministratorVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdministratorMapper {

    // 사원코드로 역할 조회
    String findRoleByEmployeeCode(String employeeCode);

    // 관리자 정보 삽입
    void insertAdministrator(AdministratorVO administrator);

    // ID 중복 확인
    int countByUserId(String userId);
}
