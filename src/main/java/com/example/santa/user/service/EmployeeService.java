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

    // 직원 리스트 및 검색
    public List<EmployeeVO> searchEmployees(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return employeeMapper.getAllEmployees();
        } else {
            return employeeMapper.searchEmployees(keyword);
        }
    }

    // 직원 권한 변경 (다중)
    public boolean updateEmployeeRoles(List<String> employeeCodes, String newRole) {
        return employeeMapper.updateEmployeeRoles(employeeCodes, newRole) > 0;
    }

    // 직원 삭제 (다중)
    public boolean deleteEmployees(List<String> employeeCodes) {
        return employeeMapper.deleteEmployees(employeeCodes) > 0;
    }
}

