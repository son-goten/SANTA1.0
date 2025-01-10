package com.example.santa.user.service;

import com.example.santa.user.mapper.EmployeeMapper;
import com.example.santa.user.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    // 직원 리스트 조회
    public List<EmployeeVO> getAllEmployees() {
        return employeeMapper.getAllEmployees();
    }

    // 직원 권한 변경
    public boolean updateEmployeeRole(String employeeCode, String newRole) {
        return employeeMapper.updateEmployeeRole(employeeCode, newRole) > 0;
    }

    // 직원 계정 삭제
    public boolean deleteEmployee(String employeeCode) {
        return employeeMapper.deleteEmployee(employeeCode) > 0;
    }
}
