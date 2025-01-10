package com.example.santa.user.mapper;

import com.example.santa.user.vo.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    // 직원 리스트 조회
    List<EmployeeVO> getAllEmployees();

    // 직원 권한 변경
    int updateEmployeeRole(String employeeCode, String newRole);

    // 직원 계정 삭제
    int deleteEmployee(String employeeCode);
}
