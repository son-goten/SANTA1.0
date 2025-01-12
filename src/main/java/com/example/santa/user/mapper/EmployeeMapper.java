package com.example.santa.user.mapper;

import com.example.santa.user.vo.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    // 직원 리스트 조회 및 검색
    List<EmployeeVO> getAllEmployees();

    List<EmployeeVO> searchEmployees(@Param("keyword") String keyword);

    // 직원 권한 변경 (다중)
    int updateEmployeeRoles(@Param("employeeCodes") List<String> employeeCodes,
                            @Param("newRole") String newRole);

    // 직원 삭제 (다중)
    int deleteEmployees(@Param("employeeCodes") List<String> employeeCodes);
}

