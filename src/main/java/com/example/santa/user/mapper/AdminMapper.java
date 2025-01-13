package com.example.santa.user.mapper;

import com.example.santa.user.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<AdminVO> getAllAdmins();

    int updateEmployeeRole(@Param("employeeCode") String employeeCode, @Param("role") String role);

    int updateAdminRole(@Param("employeeCode") String employeeCode, @Param("role") String role);

    int deleteAdmin(@Param("adminId") int adminId);
}
