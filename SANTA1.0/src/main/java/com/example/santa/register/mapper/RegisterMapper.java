package com.example.santa.register.mapper;

import com.example.santa.register.vo.RegisterVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RegisterMapper {

    int getUserIdCount(String userId);

    String getRoleByEmployeeCode(String employeeCode);

    void insertUser(RegisterVO registerVO);

    int getEmployeeCodeCount(String employeeCode);
}
